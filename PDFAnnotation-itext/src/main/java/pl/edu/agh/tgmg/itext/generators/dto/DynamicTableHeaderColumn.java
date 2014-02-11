package pl.edu.agh.tgmg.itext.generators.dto;


public class DynamicTableHeaderColumn extends TableHeaderColumn {

    String fieldName;

    public DynamicTableHeaderColumn(int rowspan, int colspan, String text, String fieldName) {
        super(rowspan, colspan, text);
        this.fieldName = fieldName;
    }

    public DynamicTableHeaderColumn(String text, String fieldName) {
        super(text);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((fieldName == null) ? 0 : fieldName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DynamicTableHeaderColumn other = (DynamicTableHeaderColumn) obj;
        if (fieldName == null) {
            if (other.fieldName != null)
                return false;
        } else if (!fieldName.equals(other.fieldName))
            return false;
        return true;
    }
    
    
}
