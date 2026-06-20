package app.data.entity;

/**
 * Kelas entitas yang merepresentasikan satu buku di toko.
 * Paket app.data.entity diekspor publik oleh modul app.data,
 * sehingga kelas ini boleh diakses modul lain (lewat app.logic).
 */
public class Buku {

    private final String judul;
    private final String penulis;
    private final Kategori kategori;
    private final double harga;
    private int stok;

    public Buku(String judul, String penulis, Kategori kategori, double harga, int stok) {
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return String.format("%-28s | %-18s | %-10s | Rp%-10.0f | stok: %d",
                judul, penulis, kategori, harga, stok);
    }
}
