"""
BÀI THỰC HÀNH 03 - Test Case Kiểm thử hộp đen
Kỹ thuật áp dụng:
  - Phân lớp tương đương (Equivalence Partitioning)
  - Phân tích giá trị biên (Boundary Value Analysis)
  - Dữ liệu hợp lệ và không hợp lệ

Mỗi bài toán có:
  - ISSUE 1: Test case dữ liệu HỢP LỆ
  - ISSUE 2: Test case dữ liệu KHÔNG HỢP LỆ / BIÊN / NGOẠI LỆ
"""

import unittest
from bai_toan import (
    tinh_chu_vi, tinh_dien_tich,
    giai_phuong_trinh_bac2,
    so_ngay_trong_thang,
    kiem_tra_nguyen_to,
    tinh_tong_xen_ke,
    ucln, giai_thua,
    tinh_tong_giai_thua,
)


# ============================================================
# BÀI 1: Chu vi hình chữ nhật
# Đầu vào: a, b > 0 (số thực)
# Đầu ra: 2*(a+b)
# ============================================================
class TestChuVi(unittest.TestCase):

    # ---- ISSUE 1: Dữ liệu HỢP LỆ ----
    def test_so_nguyen_duong_binh_thuong(self):
        """EP: a,b ∈ số nguyên dương bình thường"""
        self.assertEqual(tinh_chu_vi(5, 3), 16)

    def test_so_thuc_duong(self):
        """EP: a,b ∈ số thực dương"""
        self.assertAlmostEqual(tinh_chu_vi(2.5, 1.5), 8.0)

    def test_hinh_vuong(self):
        """EP: a == b (hình vuông)"""
        self.assertEqual(tinh_chu_vi(4, 4), 16)

    def test_gia_tri_lon(self):
        """BVA: giá trị lớn"""
        self.assertEqual(tinh_chu_vi(1000, 500), 3000)

    def test_gia_tri_nho_gan_0(self):
        """BVA: giá trị rất nhỏ nhưng > 0"""
        self.assertAlmostEqual(tinh_chu_vi(0.001, 0.001), 0.004)

    # ---- ISSUE 2: Dữ liệu KHÔNG HỢP LỆ / BIÊN ----
    def test_a_bang_0(self):
        """BVA: a = 0 → không hợp lệ"""
        with self.assertRaises(ValueError):
            tinh_chu_vi(0, 5)

    def test_b_am(self):
        """EP: b < 0 → không hợp lệ"""
        with self.assertRaises(ValueError):
            tinh_chu_vi(5, -3)

    def test_ca_hai_am(self):
        """EP: a,b âm → không hợp lệ"""
        with self.assertRaises(ValueError):
            tinh_chu_vi(-2, -4)

    def test_kieu_chuoi(self):
        """EP: a là chuỗi → không hợp lệ"""
        with self.assertRaises(TypeError):
            tinh_chu_vi("abc", 5)

    def test_kieu_none(self):
        """EP: None → không hợp lệ"""
        with self.assertRaises(TypeError):
            tinh_chu_vi(None, 5)


# ============================================================
# BÀI 2: Diện tích hình chữ nhật
# ============================================================
class TestDienTich(unittest.TestCase):

    # ---- ISSUE 1: Dữ liệu HỢP LỆ ----
    def test_so_nguyen_binh_thuong(self):
        self.assertEqual(tinh_dien_tich(5, 3), 15)

    def test_so_thuc(self):
        self.assertAlmostEqual(tinh_dien_tich(2.5, 4.0), 10.0)

    def test_hinh_vuong(self):
        self.assertEqual(tinh_dien_tich(4, 4), 16)

    def test_gia_tri_rat_lon(self):
        self.assertEqual(tinh_dien_tich(1000, 1000), 1_000_000)

    def test_gia_tri_nho_hon_1(self):
        """BVA: 0 < a,b < 1"""
        self.assertAlmostEqual(tinh_dien_tich(0.5, 0.5), 0.25)

    # ---- ISSUE 2: Dữ liệu KHÔNG HỢP LỆ / BIÊN ----
    def test_a_bang_0(self):
        with self.assertRaises(ValueError):
            tinh_dien_tich(0, 5)

    def test_a_am(self):
        with self.assertRaises(ValueError):
            tinh_dien_tich(-5, 3)

    def test_ca_hai_am(self):
        with self.assertRaises(ValueError):
            tinh_dien_tich(-5, -3)

    def test_chuoi(self):
        with self.assertRaises(TypeError):
            tinh_dien_tich("abc", 3)

    def test_none(self):
        with self.assertRaises(TypeError):
            tinh_dien_tich(None, 3)


# ============================================================
# BÀI 3: Giải phương trình bậc 2: ax² + bx + c = 0
# ============================================================
class TestPhuongTrinhBac2(unittest.TestCase):

    # ---- ISSUE 1: Dữ liệu HỢP LỆ ----
    def test_delta_duong_hai_nghiem(self):
        """EP: Δ > 0 → 2 nghiệm phân biệt"""
        result = giai_phuong_trinh_bac2(1, -5, 6)
        self.assertEqual(result["so_nghiem"], 2)
        self.assertAlmostEqual(result["x1"], 3.0)
        self.assertAlmostEqual(result["x2"], 2.0)

    def test_delta_bang_0_nghiem_kep(self):
        """BVA: Δ = 0 → nghiệm kép"""
        result = giai_phuong_trinh_bac2(1, -2, 1)
        self.assertEqual(result["so_nghiem"], 1)
        self.assertAlmostEqual(result["x"], 1.0)

    def test_delta_am_vo_nghiem(self):
        """EP: Δ < 0 → vô nghiệm"""
        result = giai_phuong_trinh_bac2(1, 1, 1)
        self.assertEqual(result["so_nghiem"], 0)

    def test_he_so_am(self):
        """EP: a < 0 → vẫn giải được"""
        result = giai_phuong_trinh_bac2(-1, 5, -6)
        self.assertEqual(result["so_nghiem"], 2)

    def test_he_so_thuc(self):
        """EP: hệ số là số thực"""
        result = giai_phuong_trinh_bac2(1.0, -3.0, 2.0)
        self.assertEqual(result["so_nghiem"], 2)

    def test_delta_chinh_xac_bang_0(self):
        """BVA: b² = 4ac → Δ đúng bằng 0"""
        result = giai_phuong_trinh_bac2(4, 4, 1)  # (2x+1)²=0
        self.assertEqual(result["so_nghiem"], 1)
        self.assertAlmostEqual(result["x"], -0.5)

    # ---- ISSUE 2: Dữ liệu KHÔNG HỢP LỆ / BIÊN ----
    def test_a_bang_0(self):
        """EP: a=0 → không phải bậc 2"""
        with self.assertRaises(ValueError):
            giai_phuong_trinh_bac2(0, 2, 1)

    def test_a_chuoi(self):
        with self.assertRaises(TypeError):
            giai_phuong_trinh_bac2("a", 1, 1)

    def test_none(self):
        with self.assertRaises(TypeError):
            giai_phuong_trinh_bac2(None, 1, 1)

    def test_b_c_khong_hop_le(self):
        with self.assertRaises(TypeError):
            giai_phuong_trinh_bac2(1, "b", 1)


# ============================================================
# BÀI 4: Số ngày trong tháng
# ============================================================
class TestSoNgayTrongThang(unittest.TestCase):

    # ---- ISSUE 1: Dữ liệu HỢP LỆ ----
    def test_thang_1_31_ngay(self):
        self.assertEqual(so_ngay_trong_thang(1, 2023), 31)

    def test_thang_4_30_ngay(self):
        self.assertEqual(so_ngay_trong_thang(4, 2023), 30)

    def test_thang_2_nam_thuong(self):
        """EP: tháng 2 năm thường → 28 ngày"""
        self.assertEqual(so_ngay_trong_thang(2, 2023), 28)

    def test_thang_2_nam_nhuan_chia_4(self):
        """EP: tháng 2 năm nhuận (chia hết 4, không chia hết 100) → 29"""
        self.assertEqual(so_ngay_trong_thang(2, 2024), 29)

    def test_thang_2_nam_nhuan_chia_400(self):
        """BVA: năm 2000 là năm nhuận (chia hết 400) → 29"""
        self.assertEqual(so_ngay_trong_thang(2, 2000), 29)

    def test_thang_2_khong_nhuan_chia_100(self):
        """BVA: năm 1900 chia hết 100 nhưng không chia hết 400 → không nhuận"""
        self.assertEqual(so_ngay_trong_thang(2, 1900), 28)

    def test_thang_bien_duoi_1(self):
        """BVA: tháng = 1 (biên dưới hợp lệ)"""
        self.assertEqual(so_ngay_trong_thang(1, 2023), 31)

    def test_thang_bien_tren_12(self):
        """BVA: tháng = 12 (biên trên hợp lệ)"""
        self.assertEqual(so_ngay_trong_thang(12, 2023), 31)

    # ---- ISSUE 2: Dữ liệu KHÔNG HỢP LỆ / BIÊN ----
    def test_thang_0(self):
        """BVA: tháng = 0 → không hợp lệ"""
        with self.assertRaises(ValueError):
            so_ngay_trong_thang(0, 2023)

    def test_thang_13(self):
        """BVA: tháng = 13 → không hợp lệ"""
        with self.assertRaises(ValueError):
            so_ngay_trong_thang(13, 2023)

    def test_nam_am(self):
        """EP: năm âm → không hợp lệ"""
        with self.assertRaises(ValueError):
            so_ngay_trong_thang(3, -1)

    def test_nam_bang_0(self):
        with self.assertRaises(ValueError):
            so_ngay_trong_thang(3, 0)

    def test_thang_chuoi(self):
        with self.assertRaises(TypeError):
            so_ngay_trong_thang("ba", 2023)

    def test_nam_so_thuc(self):
        with self.assertRaises(TypeError):
            so_ngay_trong_thang(3, 2023.5)


# ============================================================
# BÀI 5: Kiểm tra số nguyên tố
# ============================================================
class TestNguyenTo(unittest.TestCase):

    # ---- ISSUE 1: Dữ liệu HỢP LỆ ----
    def test_2_la_nguyen_to(self):
        """BVA: 2 là số nguyên tố nhỏ nhất"""
        self.assertTrue(kiem_tra_nguyen_to(2))

    def test_3_la_nguyen_to(self):
        self.assertTrue(kiem_tra_nguyen_to(3))

    def test_7_la_nguyen_to(self):
        self.assertTrue(kiem_tra_nguyen_to(7))

    def test_97_la_nguyen_to(self):
        """EP: số nguyên tố lớn"""
        self.assertTrue(kiem_tra_nguyen_to(97))

    def test_4_khong_nguyen_to(self):
        """EP: số chẵn > 2 → không nguyên tố"""
        self.assertFalse(kiem_tra_nguyen_to(4))

    def test_1_khong_nguyen_to(self):
        """BVA: 1 không phải số nguyên tố"""
        self.assertFalse(kiem_tra_nguyen_to(1))

    def test_100_khong_nguyen_to(self):
        self.assertFalse(kiem_tra_nguyen_to(100))

    def test_so_lon_hop_so(self):
        self.assertFalse(kiem_tra_nguyen_to(99))  # 99 = 9*11

    # ---- ISSUE 2: Dữ liệu KHÔNG HỢP LỆ / BIÊN ----
    def test_0(self):
        """BVA: 0 không là số nguyên tố"""
        self.assertFalse(kiem_tra_nguyen_to(0))

    def test_so_am(self):
        """EP: số âm → không nguyên tố"""
        self.assertFalse(kiem_tra_nguyen_to(-5))

    def test_kieu_chuoi(self):
        with self.assertRaises(TypeError):
            kiem_tra_nguyen_to("abc")

    def test_kieu_float(self):
        with self.assertRaises(TypeError):
            kiem_tra_nguyen_to(7.0)

    def test_none(self):
        with self.assertRaises(TypeError):
            kiem_tra_nguyen_to(None)


# ============================================================
# BÀI 6: Tổng S = 1 - 2 + 3 - 4 + ... + n
# ============================================================
class TestTongXenKe(unittest.TestCase):

    # ---- ISSUE 1: Dữ liệu HỢP LỆ ----
    def test_n_bang_1(self):
        """BVA: n = 1 (biên dưới hợp lệ)"""
        self.assertEqual(tinh_tong_xen_ke(1), 1)

    def test_n_bang_2(self):
        """BVA: n = 2 → 1-2 = -1"""
        self.assertEqual(tinh_tong_xen_ke(2), -1)

    def test_n_bang_5(self):
        """EP: n lẻ bình thường → 1-2+3-4+5 = 3"""
        self.assertEqual(tinh_tong_xen_ke(5), 3)

    def test_n_bang_6(self):
        """EP: n chẵn bình thường → 1-2+3-4+5-6 = -3"""
        self.assertEqual(tinh_tong_xen_ke(6), -3)

    def test_n_chan_tong_am(self):
        """EP: n chẵn → kết quả âm"""
        self.assertEqual(tinh_tong_xen_ke(4), -2)

    def test_n_le_tong_duong(self):
        """EP: n lẻ → kết quả dương"""
        result = tinh_tong_xen_ke(7)
        self.assertGreater(result, 0)

    # ---- ISSUE 2: Dữ liệu KHÔNG HỢP LỆ / BIÊN ----
    def test_n_bang_0(self):
        """BVA: n = 0 → không hợp lệ"""
        with self.assertRaises(ValueError):
            tinh_tong_xen_ke(0)

    def test_n_am(self):
        with self.assertRaises(ValueError):
            tinh_tong_xen_ke(-5)

    def test_kieu_float(self):
        with self.assertRaises(TypeError):
            tinh_tong_xen_ke(3.5)

    def test_kieu_chuoi(self):
        with self.assertRaises(TypeError):
            tinh_tong_xen_ke("abc")

    def test_none(self):
        with self.assertRaises(TypeError):
            tinh_tong_xen_ke(None)


# ============================================================
# BÀI 7: UCLN
# ============================================================
class TestUCLN(unittest.TestCase):

    # ---- ISSUE 1: Dữ liệu HỢP LỆ ----
    def test_ucln_binh_thuong(self):
        self.assertEqual(ucln(12, 8), 4)

    def test_ucln_co_so_1(self):
        """EP: một trong hai số bằng 1 → UCLN = 1"""
        self.assertEqual(ucln(7, 1), 1)

    def test_ucln_hai_so_bang_nhau(self):
        """EP: a == b → UCLN = a"""
        self.assertEqual(ucln(5, 5), 5)

    def test_ucln_nguyen_to_voi_hop_so(self):
        """EP: a nguyên tố, không chia hết b → UCLN = 1"""
        self.assertEqual(ucln(7, 10), 1)

    def test_ucln_boi_so(self):
        """EP: a là bội của b → UCLN = b"""
        self.assertEqual(ucln(20, 4), 4)

    def test_ucln_so_lon(self):
        """BVA: số lớn"""
        self.assertEqual(ucln(1000000, 500000), 500000)

    # ---- ISSUE 2: Dữ liệu KHÔNG HỢP LỆ / BIÊN ----
    def test_a_bang_0(self):
        """BVA: a = 0 → không hợp lệ"""
        with self.assertRaises(ValueError):
            ucln(0, 5)

    def test_b_am(self):
        with self.assertRaises(ValueError):
            ucln(6, -3)

    def test_ca_hai_am(self):
        with self.assertRaises(ValueError):
            ucln(-4, -6)

    def test_kieu_float(self):
        with self.assertRaises(TypeError):
            ucln(6.0, 3)

    def test_kieu_chuoi(self):
        with self.assertRaises(TypeError):
            ucln("a", 3)


# ===========================================================
# BÀI 8: Tổng giai thừa S = 1! + 2! + ... + n!
# ===========================================================
class TestTongGiaiThua(unittest.TestCase):

    # ---- ISSUE 1: Dữ liệu HỢP LỆ ----
    def test_giai_thua_0(self):
        """BVA: 0! = 1"""
        self.assertEqual(giai_thua(0), 1)

    def test_giai_thua_1(self):
        """BVA: 1! = 1"""
        self.assertEqual(giai_thua(1), 1)

    def test_giai_thua_5(self):
        """EP: 5! = 120"""
        self.assertEqual(giai_thua(5), 120)

    def test_tong_n1(self):
        """BVA: n=1 → S = 1! = 1"""
        self.assertEqual(tinh_tong_giai_thua(1), 1)

    def test_tong_n2(self):
        """EP: n=2 → S = 1+2 = 3"""
        self.assertEqual(tinh_tong_giai_thua(2), 3)

    def test_tong_n4(self):
        """EP: n=4 → S = 1+2+6+24 = 33"""
        self.assertEqual(tinh_tong_giai_thua(4), 33)

    def test_tong_n5(self):
        """EP: n=5 → S = 1+2+6+24+120 = 153"""
        self.assertEqual(tinh_tong_giai_thua(5), 153)

    # ---- ISSUE 2: Dữ liệu KHÔNG HỢP LỆ / BIÊN ----
    def test_giai_thua_am(self):
        """EP: n âm → không xác định"""
        with self.assertRaises(ValueError):
            giai_thua(-1)

    def test_tong_n_bang_0(self):
        """BVA: n=0 → không hợp lệ cho tổng"""
        with self.assertRaises(ValueError):
            tinh_tong_giai_thua(0)

    def test_tong_n_am(self):
        with self.assertRaises(ValueError):
            tinh_tong_giai_thua(-3)

    def test_kieu_float(self):
        with self.assertRaises(TypeError):
            tinh_tong_giai_thua(3.5)

    def test_kieu_chuoi(self):
        with self.assertRaises(TypeError):
            tinh_tong_giai_thua("abc")

    def test_none(self):
        with self.assertRaises(TypeError):
            tinh_tong_giai_thua(None)


# ============================================================
# CHẠY TEST
# ============================================================
if __name__ == "__main__":
    unittest.main(verbosity=2)
