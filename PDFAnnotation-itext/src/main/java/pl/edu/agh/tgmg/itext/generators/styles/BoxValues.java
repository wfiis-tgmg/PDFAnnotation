package pl.edu.agh.tgmg.itext.generators.styles;

public class BoxValues<T> {

    private  T forAll;
    private  T forBottom;
    private  T forTop;
    private  T forLeft;
    private  T forRight;

    public BoxValues(T forBottom, T forLeft, T forRight, T forTop) {
        this(forBottom, forBottom, forLeft, forRight, forTop);
    }

    public BoxValues(T forAll, T forBottom, T forLeft, T forRight, T forTop) {
        this.forAll = forAll;
        this.forBottom = forBottom;
        this.forLeft = forLeft;
        this.forRight = forRight;
        this.forTop = forTop;
    }

    public BoxValues(T forAll) {
        this(forAll,forAll,forAll,forAll,forAll);
    }

    public T getForAll() {
        return forAll;
    }

    public T getForBottom() {
        return forBottom;
    }

    public T getForTop() {
        return forTop;
    }

    public T getForLeft() {
        return forLeft;
    }

    public T getForRight() {
        return forRight;
    }
}