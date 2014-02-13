package pl.edu.agh.tgmg.api.annotations.styles.elements;

public enum FontStyle {
    /** this is a possible style. */
    NORMAL(0),

    /** this is a possible style. */
    BOLD(1),

    /** this is a possible style. */
    ITALIC(2),

    /** this is a possible style. */
    UNDERLINE(4),

    /** this is a possible style. */
    STRIKETHRU(8),

    /** this is a possible style. */
    BOLDITALIC(1 | 2);
    
    private final int id;
    
    private FontStyle(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    
}
