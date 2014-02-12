package pl.edu.agh.tgmg.api.annotations.styles;

public enum PageSize {
    /** This is the letter format */
    LETTER(612,792),

    /** This is the note format */
    NOTE(540,720),

    /** This is the legal format */
    LEGAL(612,1008),

    /** This is the tabloid format */
    TABLOID(792,1224),

    /** This is the executive format */
    EXECUTIVE(522,756),

    /** This is the postcard format */
    POSTCARD(283,416),

    /** This is the a0 format */
    A0(2384,3370),

    /** This is the a1 format */
    A1(1684,2384),

    /** This is the a2 format */
    A2(1191,1684),

    /** This is the a3 format */
    A3(842,1191),

    /** This is the a4 format */
    A4(595,842),

    /** This is the a5 format */
    A5(420,595),

    /** This is the a6 format */
    A6(297,420),

    /** This is the a7 format */
    A7(210,297),

    /** This is the a8 format */
    A8(148,210),

    /** This is the a9 format */
    A9(105,148),

    /** This is the a10 format */
    A10(73,105),

    /** This is the b0 format */
    B0(2834,4008),

    /** This is the b1 format */
    B1(2004,2834),

    /** This is the b2 format */
    B2(1417,2004),

    /** This is the b3 format */
    B3(1000,1417),

    /** This is the b4 format */
    B4(708,1000),

    /** This is the b5 format */
    B5(498,708),

    /** This is the b6 format */
    B6(354,498),

    /** This is the b7 format */
    B7(249,354),

    /** This is the b8 format */
    B8(175,249),

    /** This is the b9 format */
    B9(124,175),

    /** This is the b10 format */
    B10(87,124),

    /** This is the archE format */
    ARCH_E(2592,3456),

    /** This is the archD format */
    ARCH_D(1728,2592),

    /** This is the archC format */
    ARCH_C(1296,1728),

    /** This is the archB format */
    ARCH_B(864,1296),

    /** This is the archA format */
    ARCH_A(648,864),

    /** This is the American Foolscap format */
    FLSA(612,936),

    /** This is the European Foolscap format */
    FLSE(648,936),

    /** This is the halfletter format */
    HALFLETTER(396,612),

    /** This is the 11x17 format */
    _11X17(792,1224),

    /** This is the ISO 7810 ID-1 format (85.60 x 53.98 mm or 3.370 x 2.125 inch) */
    ID_1(242.65f,153),

    /** This is the ISO 7810 ID-2 format (A7 rotated) */
    ID_2(297,210),

    /** This is the ISO 7810 ID-3 format (B7 rotated) */
    ID_3(354,249),

    /** This is the ledger format */
    LEDGER(1224,792),

    /** This is the Crown Quarto format */
    CROWN_QUARTO(535,697),

    /** This is the Large Crown Quarto format */
    LARGE_CROWN_QUARTO(569,731),

    /** This is the Demy Quarto format. */
    DEMY_QUARTO(620,782),

    /** This is the Royal Quarto format. */
    ROYAL_QUARTO(671,884),

    /** This is the Crown Octavo format */
    CROWN_OCTAVO(348,527),

    /** This is the Large Crown Octavo format */
    LARGE_CROWN_OCTAVO(365,561),

    /** This is the Demy Octavo format */
    DEMY_OCTAVO(391,612),

    /** This is the Royal Octavo format. */
    ROYAL_OCTAVO(442,663),

    /** This is the small paperback format. */
    SMALL_PAPERBACK(314,504),

    /** This is the Pengiun small paperback format. */
    PENGUIN_SMALL_PAPERBACK(314,513),

    /** This is the Penguin large paperback format. */
    PENGUIN_LARGE_PAPERBACK(365,561);
    
    private final float urx, ury;
    
    private PageSize(float urx, float ury) {
        this.urx = urx;
        this.ury = ury;
    }

    public float getUrx() {
        return urx;
    }

    public float getUry() {
        return ury;
    }
}
