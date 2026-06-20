/**
 * Modul app.logic
 * - Memproses logika bisnis: diskon, hitung total, validasi stok.
 * - Membutuhkan app.data (qualified-access ke app.data.internal diberikan
 *   khusus untuk modul ini lewat module-info.java app.data).
 * - "requires transitive" membuat app.data.entity (yang diekspor publik
 *   oleh app.data) ikut terbaca oleh siapa pun yang requires app.logic,
 *   termasuk app.ui — tanpa app.ui perlu requires app.data sendiri.
 * - Mengekspor paket logika bisnis.
 */
module app.logic {
    requires transitive app.data;
    exports app.logic.service;
}
