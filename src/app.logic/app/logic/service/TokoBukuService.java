package app.logic.service;

import app.data.entity.Buku;
import app.data.internal.DatabaseSimulasi;

import java.util.List;

/**
 * Memproses logika bisnis: hitung total, diskon, dan validasi stok.
 * Modul ini adalah satu-satunya yang boleh menyentuh app.data.internal
 * (lihat module-info.java app.data: "exports app.data.internal to app.logic").
 * app.ui hanya boleh memanggil kelas publik di sini, tidak pernah
 * mengakses DatabaseSimulasi secara langsung.
 */
public class TokoBukuService {

    /** Ambil seluruh daftar buku yang tersedia di toko. */
    public List<Buku> tampilkanSemuaBuku() {
        return DatabaseSimulasi.getSemuaBuku();
    }

    /** Cari satu buku berdasarkan judul. */
    public Buku cariBuku(String judul) {
        return DatabaseSimulasi.cariBukuByJudul(judul);
    }

    /** Validasi apakah stok buku mencukupi jumlah yang diminta. */
    public boolean validasiStok(String judul, int jumlahDiminta) {
        Buku buku = DatabaseSimulasi.cariBukuByJudul(judul);
        return buku != null && jumlahDiminta > 0 && buku.getStok() >= jumlahDiminta;
    }

    /** Hitung subtotal harga sebelum diskon. */
    public double hitungSubtotal(String judul, int jumlah) {
        Buku buku = DatabaseSimulasi.cariBukuByJudul(judul);
        if (buku == null) {
            throw new IllegalArgumentException("Buku tidak ditemukan: " + judul);
        }
        return buku.getHarga() * jumlah;
    }

    /**
     * Hitung total harga setelah diskon.
     * Aturan diskon sederhana: pembelian >= 5 eksemplar mendapat 10%,
     * >= 10 eksemplar mendapat 15%.
     */
    public double hitungTotalSetelahDiskon(String judul, int jumlah) {
        double subtotal = hitungSubtotal(judul, jumlah);
        double persenDiskon = hitungPersenDiskon(jumlah);
        return subtotal - (subtotal * persenDiskon / 100.0);
    }

    public double hitungPersenDiskon(int jumlah) {
        if (jumlah >= 10) return 15.0;
        if (jumlah >= 5) return 10.0;
        return 0.0;
    }

    /**
     * Proses pembelian: validasi stok, kurangi stok, kembalikan total bayar.
     * Melempar exception jika stok tidak cukup.
     */
    public double prosesPembelian(String judul, int jumlah) {
        if (!validasiStok(judul, jumlah)) {
            throw new IllegalStateException("Stok tidak mencukupi untuk: " + judul);
        }
        double total = hitungTotalSetelahDiskon(judul, jumlah);
        Buku buku = DatabaseSimulasi.cariBukuByJudul(judul);
        DatabaseSimulasi.updateStok(judul, buku.getStok() - jumlah);
        return total;
    }
}
