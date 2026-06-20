package app.ui;

import app.data.entity.Buku;          // Boleh: entity diekspor publik & terbaca transitif lewat app.logic
import app.logic.service.TokoBukuService;

import java.util.List;
import java.util.Scanner;

/*
 * NOTE: app.ui TIDAK memiliki "requires app.data" di module-info.java-nya.
 * Kelas Buku hanya bisa dipakai di sini karena app.logic mendeklarasikan
 * "requires transitive app.data" dan app.data meng-export paket entity
 * secara publik. Sementara itu app.data.internal (DatabaseSimulasi) sama
 * sekali tidak bisa diimpor dari sini — modul Java akan menolaknya saat
 * kompilasi karena paket itu hanya dibuka khusus untuk app.logic.
 */
public class Main {

    private static final TokoBukuService service = new TokoBukuService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean jalan = true;
        while (jalan) {
            tampilkanMenu();
            String pilihan = scanner.nextLine().trim();
            switch (pilihan) {
                case "1" -> tampilkanDaftarBuku();
                case "2" -> prosesBeliBuku();
                case "0" -> {
                    System.out.println("Terima kasih telah berkunjung!");
                    jalan = false;
                }
                default -> System.out.println("Pilihan tidak dikenal.\n");
            }
        }
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("=== Toko Buku Digital ===");
        System.out.println("1. Tampilkan daftar buku");
        System.out.println("2. Beli buku");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void tampilkanDaftarBuku() {
        List<Buku> daftar = service.tampilkanSemuaBuku();
        System.out.println("\n-- Daftar Buku --");
        for (Buku b : daftar) {
            System.out.println(b);
        }
        System.out.println();
    }

    private static void prosesBeliBuku() {
        System.out.print("Judul buku: ");
        String judul = scanner.nextLine().trim();

        Buku buku = service.cariBuku(judul);
        if (buku == null) {
            System.out.println("Buku tidak ditemukan.\n");
            return;
        }

        System.out.print("Jumlah beli: ");
        int jumlah;
        try {
            jumlah = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Jumlah tidak valid.\n");
            return;
        }

        if (!service.validasiStok(judul, jumlah)) {
            System.out.println("Stok tidak mencukupi. Sisa stok: " + buku.getStok() + "\n");
            return;
        }

        double diskon = service.hitungPersenDiskon(jumlah);
        double total = service.prosesPembelian(judul, jumlah);

        System.out.printf("Pembelian berhasil! Diskon: %.0f%%, Total bayar: Rp%.0f%n%n", diskon, total);
    }
}
