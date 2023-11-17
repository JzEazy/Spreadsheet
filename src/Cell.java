public class Cell {
    private String mContent;

    public Cell(Integer row, Integer column) {
        this.mContent = null;
    }
    public Cell(Integer row, Integer column, String content) {
        this.mContent = content;
    }

    public void setContent(String content) {
        this.mContent = content;
    }
    public String getContent() {
        return mContent;
    }

    public boolean hasValue() {
        if(mContent == null) {
            return false;
        }
        return true;
    }
    /*
    public String getColumnAsString() {
        StringBuilder columnName = new StringBuilder();
        Integer columnNumber = mColumn;
        while (columnNumber > 0) {
            Integer remainder = (columnNumber - 1) % 26;
            columnName.insert(0, (char) ('A' + remainder));
            columnNumber = (columnNumber - remainder) / 26;
        }
        return columnName.toString();
    }
    */
}