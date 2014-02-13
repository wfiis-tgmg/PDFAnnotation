package pl.edu.agh.tgmg.api.annotations.styles.elements;

public enum TextAlignment {
    /**
     * A possible value for paragraph alignment. This specifies that the text is
     * aligned to the left indent and extra whitespace should be placed on the
     * right.
     */
    ALIGN_LEFT(0),

    /**
     * A possible value for paragraph alignment. This specifies that the text is
     * aligned to the center and extra whitespace should be placed equally on
     * the left and right.
     */
    ALIGN_CENTER(1),

    /**
     * A possible value for paragraph alignment. This specifies that the text is
     * aligned to the right indent and extra whitespace should be placed on the
     * left.
     */
    ALIGN_RIGHT(2),

    /**
     * A possible value for paragraph alignment. This specifies that extra
     * whitespace should be spread out through the rows of the paragraph with
     * the text lined up with the left and right indent except on the last line
     * which should be aligned to the left.
     */
    ALIGN_JUSTIFIED(3);
    
    private final int id;

    private TextAlignment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
