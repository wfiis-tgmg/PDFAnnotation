package pl.edu.agh.tgmg.api.annotations.styles.elements;

public enum VerticalAlignment {
    
    /**
     * A possible value for vertical alignment.
     */
    ALIGN_TOP(4),

    /**
     * A possible value for vertical alignment.
     */
    ALIGN_MIDDLE(5),

    /**
     * A possible value for vertical alignment.
     */
    ALIGN_BOTTOM(6);
    
    private final int id;

    private VerticalAlignment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
