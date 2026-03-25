package quanlychitieu.model;

public class Expense extends Transaction {

    public Expense(double amount, String date,
        String description, String category) {
            super(amount, date, description, category);
    }

    public Expense(int id, double amount, String date,
        String description, String category) {
            super(id, amount, date, description, category);
    }

    @Override
    public String getType() {
        return "CHI"; // đây là khoản chi tiêu
    }
}