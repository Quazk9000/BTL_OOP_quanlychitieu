// Đây là interface Manageable, định nghĩa các phương thức để quản lý giao dịch (thêm, xóa, cập nhật, tìm kiếm)
package quanlychitieu.interfaces;

import java.util.List; // import lớp Transaction để có thể sử dụng trong các phương thức của interface
import quanlychitieu.model.Transaction; // import List để trả về danh sách giao dịch khi tìm kiếm

public interface Manageable {
    void add(Transaction t); // phương thức thêm giao dịch mới, nhận vào một đối tượng Transaction (có thể là Income hoặc Expense)
    void remove(int id); // phương thức xóa giao dịch, nhận vào id của giao dịch cần xóa
    void update(int id, Transaction t); // phương thức cập nhật giao dịch, nhận vào id của giao dịch cần cập nhật và một đối tượng Transaction mới để thay thế
    List<Transaction> search(String keyword); // phương thức tìm kiếm giao dịch, nhận vào từ khóa tìm kiếm và trả về danh sách giao dịch phù hợp
}