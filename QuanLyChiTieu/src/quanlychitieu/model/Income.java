package quanlychitieu.model;

public class Income extends Transaction {

    public Income(double amount, String date, String description, String category) {
            super(amount, date, description, category);
    }  

    public Income(int id, double amount, String date,
        String description, String category) {
            super(id, amount, date, description, category);
    }

    @Override
    public String getType() {
        return "thu nhap: "; // đây là khoản thu nhập
    }
}