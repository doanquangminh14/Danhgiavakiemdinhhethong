package vn.edu.bai04;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BÀI THỰC HÀNH 04 - Kiểm thử hộp trắng (White-box Testing)
 *
 * ISSUE 1: Test JUnit cho các luồng xử lý hợp lệ (Happy Path)
 * ISSUE 2: Test JUnit cho các nhánh lỗi, điều kiện biên, vòng lặp và ngoại lệ
 *
 * Mục tiêu: 100% statement coverage và 100% branch coverage
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class MathUtilsTest {

    private MathUtils utils;

    @BeforeEach
    void setUp() {
        utils = new MathUtils();
    }

    // ===========================================================
    //  ISSUE 1: Luồng xử lý HỢP LỆ (Happy Path)
    // ===========================================================

    // --- 1. Chu vi hình chữ nhật ---
    @Test
    @DisplayName("ISSUE1 - ChuViHCN: Hợp lệ - chiều dài và chiều rộng dương")
    void testChuViHCN_HopLe() {
        assertEquals(14.0, utils.chuViHCN(3, 4));
    }

    @Test
    @DisplayName("ISSUE1 - ChuViHCN: Hợp lệ - số thực dương")
    void testChuViHCN_SoThuc() {
        assertEquals(10.0, utils.chuViHCN(2.5, 2.5));
    }

    @Test
    @DisplayName("ISSUE1 - ChuViHCN: Hợp lệ - hình vuông (a == b)")
    void testChuViHCN_HinhVuong() {
        assertEquals(20.0, utils.chuViHCN(5, 5));
    }

    // --- 2. Diện tích hình chữ nhật ---
    @Test
    @DisplayName("ISSUE1 - DienTichHCN: Hợp lệ - chiều dài và chiều rộng dương")
    void testDienTichHCN_HopLe() {
        assertEquals(12.0, utils.dienTichHCN(3, 4));
    }

    @Test
    @DisplayName("ISSUE1 - DienTichHCN: Hợp lệ - số thực dương")
    void testDienTichHCN_SoThuc() {
        assertEquals(6.25, utils.dienTichHCN(2.5, 2.5));
    }

    // --- 3. Giải phương trình bậc 2 ---
    @Test
    @DisplayName("ISSUE1 - GiaiBac2: Hai nghiệm phân biệt (delta > 0)")
    void testGiaiBac2_HaiNghiem() {
        double[] result = utils.giaiBac2(1, -5, 6);
        assertEquals(2, result.length);
        assertEquals(2.0, result[0], 1e-9);
        assertEquals(3.0, result[1], 1e-9);
    }

    @Test
    @DisplayName("ISSUE1 - GiaiBac2: Nghiệm kép (delta == 0)")
    void testGiaiBac2_NghiemKep() {
        double[] result = utils.giaiBac2(1, -2, 1);
        assertEquals(1, result.length);
        assertEquals(1.0, result[0], 1e-9);
    }

    @Test
    @DisplayName("ISSUE1 - GiaiBac2: Phương trình bậc 1 (a == 0, b != 0)")
    void testGiaiBac2_Bac1() {
        double[] result = utils.giaiBac2(0, 2, -4);
        assertEquals(1, result.length);
        assertEquals(2.0, result[0], 1e-9);
    }

    // --- 4. Số ngày trong tháng ---
    @Test
    @DisplayName("ISSUE1 - SoNgayThang: Tháng có 31 ngày")
    void testSoNgayThang_31Ngay() {
        assertEquals(31, utils.soNgayTrongThang(1, 2023));
        assertEquals(31, utils.soNgayTrongThang(3, 2023));
        assertEquals(31, utils.soNgayTrongThang(5, 2023));
        assertEquals(31, utils.soNgayTrongThang(7, 2023));
        assertEquals(31, utils.soNgayTrongThang(8, 2023));
        assertEquals(31, utils.soNgayTrongThang(10, 2023));
        assertEquals(31, utils.soNgayTrongThang(12, 2023));
    }

    @Test
    @DisplayName("ISSUE1 - SoNgayThang: Tháng có 30 ngày")
    void testSoNgayThang_30Ngay() {
        assertEquals(30, utils.soNgayTrongThang(4, 2023));
        assertEquals(30, utils.soNgayTrongThang(6, 2023));
        assertEquals(30, utils.soNgayTrongThang(9, 2023));
        assertEquals(30, utils.soNgayTrongThang(11, 2023));
    }

    @Test
    @DisplayName("ISSUE1 - SoNgayThang: Tháng 2 năm nhuận (29 ngày)")
    void testSoNgayThang_Thang2NamNhuan() {
        assertEquals(29, utils.soNgayTrongThang(2, 2000)); // Chia hết 400
        assertEquals(29, utils.soNgayTrongThang(2, 2024)); // Chia hết 4, không chia hết 100
    }

    @Test
    @DisplayName("ISSUE1 - SoNgayThang: Tháng 2 năm thường (28 ngày)")
    void testSoNgayThang_Thang2NamThuong() {
        assertEquals(28, utils.soNgayTrongThang(2, 2023));
        assertEquals(28, utils.soNgayTrongThang(2, 1900)); // Chia hết 100, không chia hết 400
    }

    // --- 5. Số nguyên tố ---
    @Test
    @DisplayName("ISSUE1 - SoNguyenTo: Số nguyên tố nhỏ")
    void testSoNguyenTo_LaNguyenTo() {
        assertTrue(utils.laSoNguyenTo(2));
        assertTrue(utils.laSoNguyenTo(3));
        assertTrue(utils.laSoNguyenTo(5));
        assertTrue(utils.laSoNguyenTo(7));
        assertTrue(utils.laSoNguyenTo(11));
        assertTrue(utils.laSoNguyenTo(13));
        assertTrue(utils.laSoNguyenTo(97));
    }

    @Test
    @DisplayName("ISSUE1 - SoNguyenTo: Số không phải nguyên tố")
    void testSoNguyenTo_KhongLaNguyenTo() {
        assertFalse(utils.laSoNguyenTo(4));
        assertFalse(utils.laSoNguyenTo(6));
        assertFalse(utils.laSoNguyenTo(9));
        assertFalse(utils.laSoNguyenTo(25));
    }

    // --- 6. Tổng xen kẽ ---
    @Test
    @DisplayName("ISSUE1 - TongXenKe: n = 1 (tổng = 1)")
    void testTongXenKe_N1() {
        assertEquals(1, utils.tinhTongXenKe(1));
    }

    @Test
    @DisplayName("ISSUE1 - TongXenKe: n = 4 (tổng = 1-2+3-4 = -2)")
    void testTongXenKe_N4() {
        assertEquals(-2, utils.tinhTongXenKe(4));
    }

    @Test
    @DisplayName("ISSUE1 - TongXenKe: n = 5 (tổng = 1-2+3-4+5 = 3)")
    void testTongXenKe_N5() {
        assertEquals(3, utils.tinhTongXenKe(5));
    }

    // --- 7. UCLN ---
    @Test
    @DisplayName("ISSUE1 - UCLN: UCLN(12, 8) = 4")
    void testUCLN_HopLe() {
        assertEquals(4, utils.tinhUCLN(12, 8));
    }

    @Test
    @DisplayName("ISSUE1 - UCLN: UCLN(a, a) = a (hai số bằng nhau)")
    void testUCLN_HaiBangNhau() {
        assertEquals(7, utils.tinhUCLN(7, 7));
    }

    @Test
    @DisplayName("ISSUE1 - UCLN: UCLN nguyên tố cùng nhau = 1")
    void testUCLN_NguyenToCungNhau() {
        assertEquals(1, utils.tinhUCLN(7, 13));
    }

    // --- 8. Giai thừa và tổng giai thừa ---
    @Test
    @DisplayName("ISSUE1 - GiaiThua: 0! = 1")
    void testGiaiThua_0() {
        assertEquals(1, utils.tinhGiaiThua(0));
    }

    @Test
    @DisplayName("ISSUE1 - GiaiThua: 5! = 120")
    void testGiaiThua_5() {
        assertEquals(120, utils.tinhGiaiThua(5));
    }

    @Test
    @DisplayName("ISSUE1 - TongGiaiThua: n=3 => 1!+2!+3! = 9")
    void testTongGiaiThua_N3() {
        assertEquals(9, utils.tinhTongGiaiThua(3));
    }

    @Test
    @DisplayName("ISSUE1 - TongGiaiThua: n=1 => 1! = 1")
    void testTongGiaiThua_N1() {
        assertEquals(1, utils.tinhTongGiaiThua(1));
    }


    // ===========================================================
    //  ISSUE 2: Nhánh lỗi, điều kiện biên, vòng lặp, ngoại lệ
    // ===========================================================

    // --- 1. Chu vi hình chữ nhật - Lỗi & Biên ---
    @Test
    @DisplayName("ISSUE2 - ChuViHCN: Ngoại lệ khi a <= 0")
    void testChuViHCN_NgoaiLeA() {
        assertThrows(IllegalArgumentException.class, () -> utils.chuViHCN(-1, 4));
        assertThrows(IllegalArgumentException.class, () -> utils.chuViHCN(0, 4));
    }

    @Test
    @DisplayName("ISSUE2 - ChuViHCN: Ngoại lệ khi b <= 0")
    void testChuViHCN_NgoaiLeB() {
        assertThrows(IllegalArgumentException.class, () -> utils.chuViHCN(3, -1));
        assertThrows(IllegalArgumentException.class, () -> utils.chuViHCN(3, 0));
    }

    // --- 2. Diện tích hình chữ nhật - Lỗi & Biên ---
    @Test
    @DisplayName("ISSUE2 - DienTichHCN: Ngoại lệ khi a <= 0")
    void testDienTichHCN_NgoaiLeA() {
        assertThrows(IllegalArgumentException.class, () -> utils.dienTichHCN(0, 4));
        assertThrows(IllegalArgumentException.class, () -> utils.dienTichHCN(-5, 4));
    }

    @Test
    @DisplayName("ISSUE2 - DienTichHCN: Ngoại lệ khi b <= 0")
    void testDienTichHCN_NgoaiLeB() {
        assertThrows(IllegalArgumentException.class, () -> utils.dienTichHCN(3, 0));
        assertThrows(IllegalArgumentException.class, () -> utils.dienTichHCN(3, -2));
    }

    // --- 3. Giải bậc 2 - Lỗi & Biên ---
    @Test
    @DisplayName("ISSUE2 - GiaiBac2: Vô nghiệm thực (delta < 0)")
    void testGiaiBac2_VoNghiem() {
        double[] result = utils.giaiBac2(1, 0, 1); // x^2 + 1 = 0
        assertEquals(0, result.length);
    }

    @Test
    @DisplayName("ISSUE2 - GiaiBac2: a=0, b=0, c=0 (vô số nghiệm)")
    void testGiaiBac2_VoSoNghiem() {
        double[] result = utils.giaiBac2(0, 0, 0);
        assertEquals(2, result.length);
        assertTrue(Double.isNaN(result[0]));
    }

    @Test
    @DisplayName("ISSUE2 - GiaiBac2: a=0, b=0, c!=0 (vô nghiệm)")
    void testGiaiBac2_VoNghiemBac0() {
        double[] result = utils.giaiBac2(0, 0, 5);
        assertEquals(0, result.length);
    }

    // --- 4. Số ngày tháng - Lỗi & Biên ---
    @Test
    @DisplayName("ISSUE2 - SoNgayThang: Ngoại lệ khi tháng < 1")
    void testSoNgayThang_ThangNhoHon1() {
        assertThrows(IllegalArgumentException.class, () -> utils.soNgayTrongThang(0, 2023));
        assertThrows(IllegalArgumentException.class, () -> utils.soNgayTrongThang(-1, 2023));
    }

    @Test
    @DisplayName("ISSUE2 - SoNgayThang: Ngoại lệ khi tháng > 12")
    void testSoNgayThang_ThangLonHon12() {
        assertThrows(IllegalArgumentException.class, () -> utils.soNgayTrongThang(13, 2023));
        assertThrows(IllegalArgumentException.class, () -> utils.soNgayTrongThang(100, 2023));
    }

    @Test
    @DisplayName("ISSUE2 - SoNgayThang: Ngoại lệ khi năm <= 0")
    void testSoNgayThang_NamKhongHopLe() {
        assertThrows(IllegalArgumentException.class, () -> utils.soNgayTrongThang(1, 0));
        assertThrows(IllegalArgumentException.class, () -> utils.soNgayTrongThang(1, -1));
    }

    @Test
    @DisplayName("ISSUE2 - NamNhuan: Năm chia hết 400 là nhuận")
    void testNamNhuan_Chia400() {
        assertTrue(utils.isNamNhuan(2000));
        assertTrue(utils.isNamNhuan(1600));
    }

    @Test
    @DisplayName("ISSUE2 - NamNhuan: Năm chia hết 100 nhưng không chia hết 400 không phải nhuận")
    void testNamNhuan_Chia100Khong400() {
        assertFalse(utils.isNamNhuan(1900));
        assertFalse(utils.isNamNhuan(1800));
    }

    // --- 5. Số nguyên tố - Lỗi & Biên ---
    @Test
    @DisplayName("ISSUE2 - SoNguyenTo: Số âm không phải nguyên tố")
    void testSoNguyenTo_SoAm() {
        assertFalse(utils.laSoNguyenTo(-1));
        assertFalse(utils.laSoNguyenTo(-7));
    }

    @Test
    @DisplayName("ISSUE2 - SoNguyenTo: 0 và 1 không phải nguyên tố")
    void testSoNguyenTo_0Va1() {
        assertFalse(utils.laSoNguyenTo(0));
        assertFalse(utils.laSoNguyenTo(1));
    }

    @Test
    @DisplayName("ISSUE2 - SoNguyenTo: Số chẵn > 2 không phải nguyên tố")
    void testSoNguyenTo_SoChan() {
        assertFalse(utils.laSoNguyenTo(4));
        assertFalse(utils.laSoNguyenTo(100));
    }

    // --- 6. Tổng xen kẽ - Lỗi & Biên ---
    @Test
    @DisplayName("ISSUE2 - TongXenKe: Ngoại lệ khi n <= 0")
    void testTongXenKe_NgoaiLe() {
        assertThrows(IllegalArgumentException.class, () -> utils.tinhTongXenKe(0));
        assertThrows(IllegalArgumentException.class, () -> utils.tinhTongXenKe(-5));
    }

    @Test
    @DisplayName("ISSUE2 - TongXenKe: Biên dưới n=1")
    void testTongXenKe_BienDuoi() {
        assertEquals(1, utils.tinhTongXenKe(1));
    }

    @Test
    @DisplayName("ISSUE2 - TongXenKe: n=2 (1-2 = -1)")
    void testTongXenKe_N2() {
        assertEquals(-1, utils.tinhTongXenKe(2));
    }

    // --- 7. UCLN - Lỗi & Biên ---
    @Test
    @DisplayName("ISSUE2 - UCLN: Ngoại lệ khi a <= 0")
    void testUCLN_NgoaiLeA() {
        assertThrows(IllegalArgumentException.class, () -> utils.tinhUCLN(0, 5));
        assertThrows(IllegalArgumentException.class, () -> utils.tinhUCLN(-3, 5));
    }

    @Test
    @DisplayName("ISSUE2 - UCLN: Ngoại lệ khi b <= 0")
    void testUCLN_NgoaiLeB() {
        assertThrows(IllegalArgumentException.class, () -> utils.tinhUCLN(5, 0));
        assertThrows(IllegalArgumentException.class, () -> utils.tinhUCLN(5, -2));
    }

    @Test
    @DisplayName("ISSUE2 - UCLN: UCLN(a, 1) = 1")
    void testUCLN_BienB1() {
        assertEquals(1, utils.tinhUCLN(10, 1));
        assertEquals(1, utils.tinhUCLN(1, 10));
    }

    @Test
    @DisplayName("ISSUE2 - UCLN: UCLN khi a là bội của b")
    void testUCLN_ABoidB() {
        assertEquals(5, utils.tinhUCLN(10, 5));
        assertEquals(3, utils.tinhUCLN(3, 9));
    }

    // --- 8. Giai thừa - Lỗi & Biên ---
    @Test
    @DisplayName("ISSUE2 - GiaiThua: Ngoại lệ khi n âm")
    void testGiaiThua_NgoaiLe() {
        assertThrows(IllegalArgumentException.class, () -> utils.tinhGiaiThua(-1));
    }

    @Test
    @DisplayName("ISSUE2 - TongGiaiThua: Ngoại lệ khi n <= 0")
    void testTongGiaiThua_NgoaiLe() {
        assertThrows(IllegalArgumentException.class, () -> utils.tinhTongGiaiThua(0));
        assertThrows(IllegalArgumentException.class, () -> utils.tinhTongGiaiThua(-3));
    }

    @Test
    @DisplayName("ISSUE2 - TongGiaiThua: n=4 => 1!+2!+3!+4! = 33")
    void testTongGiaiThua_N4() {
        assertEquals(33, utils.tinhTongGiaiThua(4));
    }

    @Test
    @DisplayName("ISSUE2 - TongGiaiThua: n=5 => 1!+2!+3!+4!+5! = 153")
    void testTongGiaiThua_N5() {
        assertEquals(153, utils.tinhTongGiaiThua(5));
    }

    // --- Vòng lặp - kiểm tra biên vòng lặp ---
    @Test
    @DisplayName("ISSUE2 - VongLap: UCLN với số lớn (nhiều lần lặp)")
    void testUCLN_SoLon() {
        assertEquals(6, utils.tinhUCLN(48, 18));
        assertEquals(12, utils.tinhUCLN(144, 60));
    }

    @Test
    @DisplayName("ISSUE2 - VongLap: Nguyên tố - số lớn (nhiều lần lặp trong vòng for)")
    void testSoNguyenTo_SoLon() {
        assertTrue(utils.laSoNguyenTo(997));
        assertFalse(utils.laSoNguyenTo(999)); // 999 = 3 * 333
    }

    @Test
    @DisplayName("ISSUE2 - VongLap: TongXenKe với n lớn")
    void testTongXenKe_SoLon() {
        // n=100: 50 cặp (1-2)+(3-4)+...+(99-100) = 50*(-1) = -50
        assertEquals(-50, utils.tinhTongXenKe(100));
        // n=101: -50 + 101 = 51
        assertEquals(51, utils.tinhTongXenKe(101));
    }
}
