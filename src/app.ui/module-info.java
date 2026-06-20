/**
 * Modul app.ui
 * - Menampilkan antarmuka CLI dan menerima input langsung dari pengguna.
 * - Hanya membutuhkan app.logic (BUKAN app.data) — sesuai larangan keras
 *   bahwa app.ui tidak boleh mengakses langsung paket internal app.data.
 *   Kelas entitas publik (Buku, Kategori) tetap bisa dipakai karena
 *   terbaca transitif lewat "requires transitive app.data" di app.logic.
 * - Berfungsi sebagai main entry point sistem.
 */
module app.ui {
    requires app.logic;
}
