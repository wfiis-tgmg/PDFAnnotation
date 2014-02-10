package pl.edu.agh.tgmg.itext.generators.dto;

public class TableHeaderColumn {
    protected int column;
    protected int row;
    protected String text;
    String id;


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
    
    
}
