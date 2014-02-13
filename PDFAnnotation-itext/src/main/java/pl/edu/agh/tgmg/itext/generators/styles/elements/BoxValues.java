package pl.edu.agh.tgmg.itext.generators.styles.elements;

public class BoxValues<T> {

    private  T forBottom;
    private  T forTop;
    private  T forLeft;
    private  T forRight;

    public BoxValues(T forBottom, T forLeft, T forRight, T forTop) {
        this.forBottom = forBottom;
        this.forLeft = forLeft;
        this.forRight = forRight;
        this.forTop = forTop;
    }

    public BoxValues(T forAll) {
        this(forAll,forAll,forAll,forAll);
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
    
    public void setValue(BoxValues<T> other) {
        if(other.forBottom != null) {
            forBottom = other.forBottom;
        } 
        if(other.forLeft!= null) {
            forLeft = other.forLeft;
        } 
        if(other.forRight != null) {
            forRight = other.forRight;
        } 
        if(other.forTop != null) {
            forTop = other.forTop;
        } 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((forBottom == null) ? 0 : forBottom.hashCode());
        result = prime * result + ((forLeft == null) ? 0 : forLeft.hashCode());
        result = prime * result
                + ((forRight == null) ? 0 : forRight.hashCode());
        result = prime * result + ((forTop == null) ? 0 : forTop.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BoxValues other = (BoxValues) obj;
        if (forBottom == null) {
            if (other.forBottom != null)
                return false;
        } else if (!forBottom.equals(other.forBottom))
            return false;
        if (forLeft == null) {
            if (other.forLeft != null)
                return false;
        } else if (!forLeft.equals(other.forLeft))
            return false;
        if (forRight == null) {
            if (other.forRight != null)
                return false;
        } else if (!forRight.equals(other.forRight))
            return false;
        if (forTop == null) {
            if (other.forTop != null)
                return false;
        } else if (!forTop.equals(other.forTop))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BoxValues [forBottom=" + forBottom + ", forTop=" + forTop
                + ", forLeft=" + forLeft + ", forRight=" + forRight + "]";
    }
    
}