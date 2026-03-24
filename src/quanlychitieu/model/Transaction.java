package quanlychitieu.model;

public abstract class Transaction {
    private static int counter = 0;
    private int id;
    private double amount;
    private String date;
    private String description;
    private String category;

    // getter
    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    // setter (chỉ cho phép sửa 3 trường: amount, description, category)
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Phương thức trừu tượng để trả về loại giao dịch (Income hoặc Expense)
    public abstract String getType();

    // contructor
    public Transaction(double amount, String date, String description, String category) {
        this.id = ++counter; // tự tăng ID
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
    }

    // Constructor dùng khi load từ file (có sẵn id)
    public Transaction(int id, double amount, String date, String description, String category) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        // Cập nhật counter nếu id lớn hơn counter hiện tại
        if (id > counter) {
            counter = id;
        }
    }

    // toString để in ra màn hình
    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %.0f VND | %s | %s",
                id, getType(), date, amount, category, description);
    }
}