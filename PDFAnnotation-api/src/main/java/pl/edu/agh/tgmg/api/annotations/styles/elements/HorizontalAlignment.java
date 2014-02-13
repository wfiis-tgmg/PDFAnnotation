package pl.edu.agh.tgmg.api.annotations.styles.elements;

public enum HorizontalAlignment {

    UNDEFINED(-1),
    LEFT(0),
    CENTER(1),
    RIGHT(2);
    
    private final int id;

    private HorizontalAlignment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
