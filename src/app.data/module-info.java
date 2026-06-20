/**
 * Modul app.data
 * - Mengelola kelas entitas data (Buku, Kategori) dan simulasi database internal.
 * - Mengekspor paket entitas data secara publik.
 * - Paket internal (app.data.internal) HANYA dibuka untuk app.logic,
 *   sehingga app.ui tidak mungkin mengaksesnya secara langsung.
 */
module app.data {
    exports app.data.entity;
    exports app.data.internal to app.logic;
}
