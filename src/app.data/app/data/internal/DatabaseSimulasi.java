package app.data.internal;

import app.data.entity.Buku;
import app.data.entity.Kategori;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Simulasi penyimpanan data (pengganti database sungguhan).
 *
 * PENTING: paket app.data.internal HANYA diekspor secara terbatas
 * ("exports app.data.internal to app.logic;") di module-info.java
 * milik app.data. Artinya modul app.ui TIDAK BISA mengompilasi atau
 * menjalankan kode yang mengimpor kelas ini sama sekali — pelanggaran
 * tersebut akan ditolak oleh Java Module System saat kompilasi.
 */
public class DatabaseSimulasi {

    private static final Map<String, Buku> DATA_BUKU = new LinkedHashMap<>();

    static {
        inisialisasiData();
    }

    private static void inisialisasiData() {
        simpanBuku(new Buku("Laskar Pelangi", "Andrea Hirata", Kategori.FIKSI, 85000, 12));
        simpanBuku(new Buku("Clean Code", "Robert C. Martin", Kategori.TEKNOLOGI, 250000, 5));
        simpanBuku(new Buku("Atomic Habits", "James Clear", Kategori.NON_FIKSI, 110000, 8));
        simpanBuku(new Buku("Si Kancil Cerdik", "Anonim", Kategori.ANAK, 35000, 20));
        simpanBuku(new Buku("One Piece Vol. 1", "Eiichiro Oda", Kategori.KOMIK, 25000, 3));
    }

    public static void simpanBuku(Buku buku) {
        DATA_BUKU.put(buku.getJudul(), buku);
    }

    public static Buku cariBukuByJudul(String judul) {
        return DATA_BUKU.get(judul);
    }

    public static List<Buku> getSemuaBuku() {
        return new ArrayList<>(DATA_BUKU.values());
    }

    public static boolean updateStok(String judul, int stokBaru) {
        Buku buku = DATA_BUKU.get(judul);
        if (buku == null) {
            return false;
        }
        buku.setStok(stokBaru);
        return true;
    }
}
