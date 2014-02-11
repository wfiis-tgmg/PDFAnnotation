package pl.edu.agh.tgmg.itext.generators.dto;

public class TableHeaderColumn {
    protected int column;
    protected int row;
    protected String text;

    public TableHeaderColumn(String text) {
        this(1, 1, text);
    }

    public TableHeaderColumn(int rowspan,int colspan, String text) {
        this.column = colspan;
        this.row = rowspan;
        this.text = text;
    }

    public int getColSpan() {
        return column;
    }

    public int getRowSpan() {
        return row;
    }

    public String getText() {
        return text;
    }

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        TableHeaderColumn other = (TableHeaderColumn) obj;
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }
    
    
}
