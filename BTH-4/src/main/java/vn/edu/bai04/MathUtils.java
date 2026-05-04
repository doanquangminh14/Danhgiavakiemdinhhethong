package vn.edu.bai04;

/**
 * BÀI THỰC HÀNH 04 - Kiểm thử hộp trắng (White-box Testing)
 * Môn: Đánh giá và kiểm định chất lượng phần mềm
 *
 * Các chức năng:
 * 1. Tính chu vi hình chữ nhật
 * 2. Tính diện tích hình chữ nhật
 * 3. Giải phương trình bậc 2
 * 4. Tính số ngày của một tháng
 * 5. Kiểm tra số nguyên tố
 * 6. Tính tổng S = 1 - 2 + 3 - 4 + ... + n
 * 7. Tìm UCLN của a và b
 * 8. Tính tổng S = 1! + 2! + 3! + ... + n!
 */
public class MathUtils {

    // =====================================================
    // 1. Tính chu vi hình chữ nhật
    // Câu lệnh: 1 (return)
    // Nhánh điều kiện: 2 (a<=0, b<=0)
    // =====================================================
    public double chuViHCN(double a, double b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Chiều dài và chiều rộng phải dương!");
        }
        return 2 * (a + b);
    }

    // =====================================================
    // 2. Tính diện tích hình chữ nhật
    // Câu lệnh: 1 (return)
    // Nhánh điều kiện: 2 (a<=0, b<=0)
    // =====================================================
    public double dienTichHCN(double a, double b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Chiều dài và chiều rộng phải dương!");
        }
        return a * b;
    }

    // =====================================================
    // 3. Giải phương trình bậc 2: ax^2 + bx + c = 0
    // Câu lệnh: nhiều nhánh (a==0, delta<0, delta==0, delta>0)
    // Nhánh điều kiện: 4 nhánh chính
    // =====================================================
    public double[] giaiBac2(double a, double b, double c) {
        if (a == 0) {
            // Phương trình bậc 1
            if (b == 0) {
                if (c == 0) {
                    return new double[]{Double.NaN, Double.NaN}; // Vô số nghiệm (dùng NaN để biểu thị)
                } else {
                    return new double[0]; // Vô nghiệm
                }
            }
            double x = -c / b;
            return new double[]{x};
        }

        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            return new double[0]; // Vô nghiệm thực
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return new double[]{x}; // Nghiệm kép
        } else {
            double x1 = (-b - Math.sqrt(delta)) / (2 * a);
            double x2 = (-b + Math.sqrt(delta)) / (2 * a);
            return new double[]{x1, x2}; // Hai nghiệm phân biệt
        }
    }

    // =====================================================
    // 4. Tính số ngày của một tháng
    // Câu lệnh: switch-case + if
    // Nhánh điều kiện: 13 nhánh (tháng không hợp lệ, 12 tháng)
    // =====================================================
    public int soNgayTrongThang(int thang, int nam) {
        if (thang < 1 || thang > 12) {
            throw new IllegalArgumentException("Tháng không hợp lệ (1-12)!");
        }
        if (nam <= 0) {
            throw new IllegalArgumentException("Năm phải dương!");
        }

        switch (thang) {
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if (isNamNhuan(nam)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1; // Không bao giờ đến đây
        }
    }

    /**
     * Kiểm tra năm nhuận
     */
    public boolean isNamNhuan(int nam) {
        return (nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0);
    }

    // =====================================================
    // 5. Kiểm tra n có phải là số nguyên tố không
    // Câu lệnh: vòng lặp + điều kiện
    // Đường đi logic: n<2, n==2, n chẵn, vòng lặp kiểm tra ước
    // =====================================================
    public boolean laSoNguyenTo(int n) {
        if (n < 2) {
            return false; // 0, 1 và số âm không phải nguyên tố
        }
        if (n == 2) {
            return true; // 2 là số nguyên tố nhỏ nhất
        }
        if (n % 2 == 0) {
            return false; // Số chẵn > 2 không phải nguyên tố
        }
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // =====================================================
    // 6. Tính tổng S = 1 - 2 + 3 - 4 + ... + n
    // Câu lệnh: vòng lặp for
    // Nhánh: n<=0, n>=1
    // Đường đi logic: dấu xen kẽ theo chẵn/lẻ
    // =====================================================
    public long tinhTongXenKe(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n phải là số nguyên dương!");
        }
        long tong = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                tong += i; // Số lẻ: cộng
            } else {
                tong -= i; // Số chẵn: trừ
            }
        }
        return tong;
    }

    // =====================================================
    // 7. Tìm UCLN của a và b (thuật toán Euclid)
    // Câu lệnh: vòng lặp while
    // Nhánh: a<=0, b<=0
    // Đường đi logic: lặp đến khi b == 0
    // =====================================================
    public int tinhUCLN(int a, int b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("a và b phải là số nguyên dương!");
        }
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // =====================================================
    // 8. Tính tổng S = 1! + 2! + 3! + ... + n!
    // Sử dụng hàm tính giai thừa riêng
    // Câu lệnh: 2 vòng lặp (giai thừa + tổng)
    // Nhánh: n<=0
    // =====================================================
    public long tinhGiaiThua(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n không được âm!");
        }
        if (n == 0) {
            return 1; // 0! = 1
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public long tinhTongGiaiThua(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n phải là số nguyên dương!");
        }
        long tong = 0;
        for (int i = 1; i <= n; i++) {
            tong += tinhGiaiThua(i);
        }
        return tong;
    }
}
