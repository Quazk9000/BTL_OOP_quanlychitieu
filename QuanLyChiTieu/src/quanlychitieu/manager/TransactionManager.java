package quanlychitieu.manager;

import java.util.ArrayList; // import interface Manageable để TransactionManager có thể triển khai các phương thức quản lý giao dịch
import java.util.List; // import lớp Transaction để có thể sử dụng trong các phương thức của TransactionManager
import java.util.stream.Collectors; // import ArrayList để lưu trữ danh sách giao dịch trong TransactionManager
import quanlychitieu.interfaces.Manageable; // import List để trả về danh sách giao dịch khi tìm kiếm hoặc lọc
import quanlychitieu.model.Transaction; // import Collectors để sử dụng trong các phương thức lọc và tìm kiếm giao dịch bằng cách sử dụng Stream API

public class TransactionManager implements Manageable { /*sử dụng implements để TransactionManager triển khai các phương thức quản lý giao dịch
                                                            được định nghĩa trong interface Manageable*/

    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void add(Transaction t) { // phương thức thêm giao dịch mới, nhận vào một đối tượng Transaction (có thể là Income hoặc Expense)
        transactions.add(t);
        System.out.println("da them: " + t);
    }

    @Override
    public void remove(int id) {    // phương thức xóa giao dịch, nhận vào id của giao dịch cần xóa
        Transaction found = findById(id);
        if (found != null) {
            transactions.remove(found);
            System.out.println("xoa giao dich co ID la: " + id);
        } else {
            System.out.println("khong tim thay giao dich nao co ID la: " + id);
        }
    }

    @Override
    public void update(int id, Transaction newData) { /*phương thức cập nhật giao dịch, 
                                                    nhận vào id của giao dịch cần cập nhật và một đối tượng Transaction mới để thay thế*/
        Transaction found = findById(id);
        if (found != null) { // nếu tìm thấy giao dịch có id tương ứng, cập nhật các trường amount, description, category của giao dịch đó bằng dữ liệu mới từ newData
            found.setAmount(newData.getAmount());
            found.setDescription(newData.getDescription());
            found.setCategory(newData.getCategory());
            System.out.println("da cap nhat giao dich co ID la: " + id);
        } else {
            System.out.println("khong tim thay giao dich nao co ID la: " + id);
        }
    }

    @Override
    public List<Transaction> search(String keyword) { // phương thức tìm kiếm giao dịch, nhận vào từ khóa tìm kiếm và trả về danh sách giao dịch phù hợp
        String kw = keyword.toLowerCase(); // chuyển từ khóa tìm kiếm thành chữ thường để so sánh không phân biệt hoa thường
        return transactions.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(kw) // tìm kiếm trong trường description của giao dịch, nếu có chứa từ khóa thì giữ lại
                        || t.getCategory().toLowerCase().contains(kw)) // tìm kiếm trong trường category của giao dịch, nếu có chứa từ khóa thì giữ lại
                .collect(Collectors.toList()); // thu thập các giao dịch phù hợp vào một danh sách mới và trả về
    }

    public List<Transaction> filterByType(String type) { // phương thức lọc giao dịch theo loại (Income hoặc Expense), nhận vào một chuỗi và trả về danh sách giao dịch có loại tương ứng
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type)) // lọc các giao dịch có loại trùng với chuỗi đầu vào, không phân biệt hoa thường
                .collect(Collectors.toList());
    }

    public List<Transaction> filterByCategory(String category) { // phương thức lọc giao dịch theo danh mục, nhận vào một chuỗi và trả về danh sách giao dịch có danh mục tương ứng
        return transactions.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category)) // lọc các giao dịch có danh mục trùng với chuỗi đầu vào, không phân biệt hoa thường
                .collect(Collectors.toList());
    }

    public List<Transaction> filterByDate(String datePrefix) { // phương thức lọc giao dịch theo ngày, nhận vào một chuỗi và trả về danh sách giao dịch có ngày bắt đầu bằng chuỗi đó
        return transactions.stream()
                .filter(t -> t.getDate().startsWith(datePrefix)) // lọc các giao dịch có ngày bắt đầu bằng chuỗi đầu vào
                .collect(Collectors.toList());
    }

    public double getTotalIncome() { // phương thức tính tổng thu nhập, trả về tổng số tiền của tất cả các giao dịch có loại là "thu nhập"
        return transactions.stream()
                .filter(t -> t.getType().equals("thu nhap")) // lọc các giao dịch có loại là "thu nhap"
                .mapToDouble(Transaction::getAmount) // lấy trường amount của các giao dịch đã lọc và chuyển thành một DoubleStream để tính tổng
                .sum(); // trả về tổng số tiền của tất cả các giao dịch có loại là "thu nhap"
    }

    public double getTotalExpense() { // phương thức tính tổng chi tiêu, trả về tổng số tiền của tất cả các giao dịch có loại là "chi tiêu"
        return transactions.stream()
                .filter(t -> t.getType().equals("chi tieu")) // lọc các giao dịch có loại là "chi tieu"
                .mapToDouble(Transaction::getAmount) // lấy trường amount của các giao dịch đã lọc và chuyển thành một DoubleStream để tính tổng
                .sum(); // trả về tổng số tiền của tất cả các giao dịch có loại là "chi tieu"
    }

    public double getBalance() { // phương thức tính số dư còn lại sau khi trừ tổng chi tiêu khỏi tổng thu nhập.
        return getTotalIncome() - getTotalExpense();
    }

    private Transaction findById(int id) { // phương thức tìm kiếm giao dịch theo id, nhận vào id và trả về giao dịch có id đó nếu tìm thấy, ngược lại trả về null
        return transactions.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Transaction> getAll() { return transactions; } /* phương thức trả về tất cả các giao dịch hiện có trong TransactionManager
                                                                trả về một danh sách các đối tượng Transaction */

    public void setAll(List<Transaction> list) { transactions = list; } 
    /*phương thức nhận vào một danh sách các đối tượng Transaction và gán nó cho thuộc tính transactions của TransactionManager,
    dùng để cập nhật toàn bộ danh sách giao dịch trong TransactionManager, thường được sử dụng khi load dữ liệu từ file vào TransactionManager*/
}