package pl.edu.agh.tgmg.api.annotations.styles.elements;

public enum ColorName {
    
    WHITE(255, 255, 255),
    LIGHT_GRAY(192, 192, 192),
    GRAY(128, 128, 128),
    DARK_GRAY(64, 64, 64),
    BLACK(0, 0, 0),
    RED(255, 0, 0),
    PINK(255, 175, 175),
    ORANGE(255, 200, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    MAGENTA(255, 0, 255),
    CYAN(0, 255, 255),
    BLUE(0, 0, 255);
    
    private final int R,G,B;

    private ColorName(int r, int g, int b) {
        R = r;
        G = g;
        B = b;
    }

    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }

}
