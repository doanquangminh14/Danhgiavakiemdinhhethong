"""
BÀI THỰC HÀNH 03 - Kiểm thử hộp đen
Môn: Đánh giá và kiểm định chất lượng phần mềm
Tác giả: [Tên sinh viên]
"""

import math


# ============================================================
# BÀI 1: Tính chu vi hình chữ nhật
# Đầu vào: chiều dài a, chiều rộng b (số thực dương)
# Đầu ra: chu vi = 2 * (a + b)
# ============================================================
def tinh_chu_vi(a, b):
    if not isinstance(a, (int, float)) or not isinstance(b, (int, float)):
        raise TypeError("Chiều dài và chiều rộng phải là số.")
    if a <= 0 or b <= 0:
        raise ValueError("Chiều dài và chiều rộng phải lớn hơn 0.")
    return 2 * (a + b)


# ============================================================
# BÀI 2: Tính diện tích hình chữ nhật
# Đầu vào: chiều dài a, chiều rộng b (số thực dương)
# Đầu ra: diện tích = a * b
# ============================================================
def tinh_dien_tich(a, b):
    if not isinstance(a, (int, float)) or not isinstance(b, (int, float)):
        raise TypeError("Chiều dài và chiều rộng phải là số.")
    if a <= 0 or b <= 0:
        raise ValueError("Chiều dài và chiều rộng phải lớn hơn 0.")
    return a * b


# ============================================================
# BÀI 3: Giải phương trình bậc 2: ax² + bx + c = 0
# Đầu vào: a, b, c (số thực), a ≠ 0
# Đầu ra: nghiệm x1, x2 hoặc thông báo vô nghiệm / nghiệm kép
# ============================================================
def giai_phuong_trinh_bac2(a, b, c):
    if not isinstance(a, (int, float)) or not isinstance(b, (int, float)) or not isinstance(c, (int, float)):
        raise TypeError("Các hệ số a, b, c phải là số.")
    if a == 0:
        raise ValueError("Hệ số a không được bằng 0 (không phải phương trình bậc 2).")

    delta = b ** 2 - 4 * a * c

    if delta < 0:
        return {"so_nghiem": 0, "delta": delta, "thong_bao": "Phương trình vô nghiệm"}
    elif delta == 0:
        x = -b / (2 * a)
        return {"so_nghiem": 1, "delta": delta, "x": x, "thong_bao": f"Nghiệm kép x = {x}"}
    else:
        x1 = (-b + math.sqrt(delta)) / (2 * a)
        x2 = (-b - math.sqrt(delta)) / (2 * a)
        return {"so_nghiem": 2, "delta": delta, "x1": x1, "x2": x2,
                "thong_bao": f"x1 = {x1:.4f}, x2 = {x2:.4f}"}


# ============================================================
# BÀI 4: Tính số ngày của một tháng
# Đầu vào: tháng (1-12), năm (số nguyên dương)
# Đầu ra: số ngày trong tháng đó
# ============================================================
def so_ngay_trong_thang(thang, nam):
    if not isinstance(thang, int) or not isinstance(nam, int):
        raise TypeError("Tháng và năm phải là số nguyên.")
    if thang < 1 or thang > 12:
        raise ValueError("Tháng phải từ 1 đến 12.")
    if nam <= 0:
        raise ValueError("Năm phải là số nguyên dương.")

    # Kiểm tra năm nhuận
    def la_nam_nhuan(y):
        return (y % 4 == 0 and y % 100 != 0) or (y % 400 == 0)

    days_in_month = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if thang == 2 and la_nam_nhuan(nam):
        return 29
    return days_in_month[thang]


# ============================================================
# BÀI 5: Kiểm tra n có phải là số nguyên tố
# Đầu vào: n (số nguyên)
# Đầu ra: True/False
# ============================================================
def kiem_tra_nguyen_to(n):
    if not isinstance(n, int):
        raise TypeError("n phải là số nguyên.")
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    for i in range(3, int(math.sqrt(n)) + 1, 2):
        if n % i == 0:
            return False
    return True


# ============================================================
# BÀI 6: Tính tổng S = 1 - 2 + 3 - 4 + ... + n
# Đầu vào: n (số nguyên dương)
# Đầu ra: tổng S
# ============================================================
def tinh_tong_xen_ke(n):
    if not isinstance(n, int):
        raise TypeError("n phải là số nguyên.")
    if n <= 0:
        raise ValueError("n phải là số nguyên dương (n >= 1).")
    total = 0
    for i in range(1, n + 1):
        if i % 2 == 0:
            total -= i
        else:
            total += i
    return total


# ============================================================
# BÀI 7: Tìm UCLN của a và b (Thuật toán Euclid)
# Đầu vào: a, b (số nguyên dương)
# Đầu ra: UCLN(a, b)
# ============================================================
def ucln(a, b):
    if not isinstance(a, int) or not isinstance(b, int):
        raise TypeError("a và b phải là số nguyên.")
    if a <= 0 or b <= 0:
        raise ValueError("a và b phải là số nguyên dương.")
    while b != 0:
        a, b = b, a % b
    return a


# ============================================================
# BÀI 8: Tính tổng S = 1! + 2! + 3! + ... + n!
# (Có sử dụng hàm tính giai thừa)
# Đầu vào: n (số nguyên dương)
# Đầu ra: tổng S
# ============================================================
def giai_thua(n):
    """Hàm tính giai thừa của n"""
    if not isinstance(n, int):
        raise TypeError("n phải là số nguyên.")
    if n < 0:
        raise ValueError("Giai thừa không xác định với số âm.")
    if n == 0 or n == 1:
        return 1
    return n * giai_thua(n - 1)


def tinh_tong_giai_thua(n):
    if not isinstance(n, int):
        raise TypeError("n phải là số nguyên.")
    if n <= 0:
        raise ValueError("n phải là số nguyên dương (n >= 1).")
    return sum(giai_thua(i) for i in range(1, n + 1))


# ============================================================
# DEMO CHẠY THỬ
# ============================================================
if __name__ == "__main__":
    print("=" * 50)
    print("BÀI 1: Chu vi hình chữ nhật")
    print(f"  tinh_chu_vi(5, 3)  = {tinh_chu_vi(5, 3)}")

    print("\nBÀI 2: Diện tích hình chữ nhật")
    print(f"  tinh_dien_tich(5, 3) = {tinh_dien_tich(5, 3)}")

    print("\nBÀI 3: Phương trình bậc 2")
    print(f"  1x²-5x+6=0  → {giai_phuong_trinh_bac2(1, -5, 6)['thong_bao']}")
    print(f"  1x²-2x+1=0  → {giai_phuong_trinh_bac2(1, -2, 1)['thong_bao']}")
    print(f"  1x²+1x+1=0  → {giai_phuong_trinh_bac2(1, 1, 1)['thong_bao']}")

    print("\nBÀI 4: Số ngày trong tháng")
    print(f"  Tháng 2/2024 (nhuận)   = {so_ngay_trong_thang(2, 2024)} ngày")
    print(f"  Tháng 2/2023 (thường)  = {so_ngay_trong_thang(2, 2023)} ngày")

    print("\nBÀI 5: Kiểm tra số nguyên tố")
    print(f"  kiem_tra_nguyen_to(7)  = {kiem_tra_nguyen_to(7)}")
    print(f"  kiem_tra_nguyen_to(10) = {kiem_tra_nguyen_to(10)}")

    print("\nBÀI 6: Tổng xen kẽ")
    print(f"  tinh_tong_xen_ke(5) = {tinh_tong_xen_ke(5)}  (1-2+3-4+5=3)")

    print("\nBÀI 7: UCLN")
    print(f"  ucln(12, 8) = {ucln(12, 8)}")

    print("\nBÀI 8: Tổng giai thừa")
    print(f"  tinh_tong_giai_thua(4) = {tinh_tong_giai_thua(4)}  (1+2+6+24=33)")
    print("=" * 50)
