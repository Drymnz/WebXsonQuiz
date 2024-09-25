package LexicalAndSyntacticAnalyzer.dataAnalyzer;

public class OperationSQLKV {
    private DataAnalyzer right;
    private DataAnalyzer left;
    private ListTypeOperationSQLKV type;

    public OperationSQLKV(DataAnalyzer right, DataAnalyzer left, ListTypeOperationSQLKV type) {
        this.right = right;
        this.left = left;
        this.type = type;
    }

    public DataAnalyzer getRight() {
        return right;
    }

    public void setRight(DataAnalyzer right) {
        this.right = right;
    }

    public DataAnalyzer getLeft() {
        return left;
    }

    public void setLeft(DataAnalyzer left) {
        this.left = left;
    }

    public ListTypeOperationSQLKV getType() {
        return type;
    }

    public void setType(ListTypeOperationSQLKV type) {
        this.type = type;
    }

}
