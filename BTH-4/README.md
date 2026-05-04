# Bài Thực Hành 04 - Kiểm Thử Hộp Trắng (White-box Testing)

> Môn: Đánh giá và kiểm định chất lượng phần mềm

## 📋 Mô tả bài tập

Xây dựng 8 chương trình Java và kiểm thử hộp trắng bằng JUnit 5:

| STT | Chức năng | Phương thức |
|-----|-----------|-------------|
| 1 | Tính chu vi hình chữ nhật | `chuViHCN(a, b)` |
| 2 | Tính diện tích hình chữ nhật | `dienTichHCN(a, b)` |
| 3 | Giải phương trình bậc 2 | `giaiBac2(a, b, c)` |
| 4 | Tính số ngày của một tháng | `soNgayTrongThang(thang, nam)` |
| 5 | Kiểm tra số nguyên tố | `laSoNguyenTo(n)` |
| 6 | Tính tổng S = 1 - 2 + 3 - 4 + ... + n | `tinhTongXenKe(n)` |
| 7 | Tìm UCLN của a và b | `tinhUCLN(a, b)` |
| 8 | Tính tổng S = 1! + 2! + ... + n! | `tinhTongGiaiThua(n)` |

## 🏗️ Cấu trúc dự án

```
bai04/
├── src/
│   ├── main/java/vn/edu/bai04/
│   │   └── MathUtils.java          # Mã nguồn chính
│   └── test/java/vn/edu/bai04/
│       └── MathUtilsTest.java      # Test JUnit 5
├── pom.xml                         # Maven build file
└── README.md
```

## 🚀 Hướng dẫn chạy

### Yêu cầu
- Java JDK 11 trở lên
- Maven 3.6 trở lên

### Chạy toàn bộ test
```bash
mvn te
```

### Xem báo cáo coverage (JaCoCo)
```bash
mvn test
# Mở file: target/site/jacoco/index.html
```

### Chạy trong IntelliJ IDEA / Eclipse
1. Import project dạng Maven
2. Click chuột phải vào `MathUtilsTest.java` → Run

## 📊 Phân tích mã nguồn - Kiểm thử hộp trắng

### 1. Chu vi hình chữ nhật (`chuViHCN`)

| Thành phần | Chi tiết |
|-----------|----------|
| Câu lệnh | 2 (if + return) |
| Nhánh điều kiện | 2 (a<=0 OR b<=0, else) |
| Vòng lặp | Không có |
| Đường đi logic | Path 1: a<=0 → exception; Path 2: b<=0 → exception; Path 3: hợp lệ → return |

### 2. Diện tích hình chữ nhật (`dienTichHCN`)

| Thành phần | Chi tiết |
|-----------|----------|
| Câu lệnh | 2 (if + return) |
| Nhánh điều kiện | 2 (a<=0 OR b<=0, else) |
| Vòng lặp | Không có |

### 3. Giải phương trình bậc 2 (`giaiBac2`)

| Thành phần | Chi tiết |
|-----------|----------|
| Câu lệnh | 9 |
| Nhánh điều kiện | 6 (a==0, b==0, c==0, delta<0, delta==0, delta>0) |
| Vòng lặp | Không có |
| Đường đi logic | 6 đường: vô số nghiệm, bậc 0 vô nghiệm, bậc 1, vô nghiệm thực, nghiệm kép, 2 nghiệm |

### 4. Số ngày trong tháng (`soNgayTrongThang`)

| Thành phần | Chi tiết |
|-----------|----------|
| Câu lệnh | switch với 13 case + 2 if |
| Nhánh điều kiện | 15 nhánh |
| Vòng lặp | Không có |

### 5. Số nguyên tố (`laSoNguyenTo`)

| Thành phần | Chi tiết |
|-----------|----------|
| Câu lệnh | 6 |
| Nhánh điều kiện | 5 (n<2, n==2, n%2==0, vòng lặp, n%i==0) |
| Vòng lặp | 1 vòng for (i từ 3 đến sqrt(n), bước 2) |
| Đường đi logic | Path 1: n<2; Path 2: n==2; Path 3: n chẵn; Path 4: có ước; Path 5: nguyên tố |

### 6. Tổng xen kẽ (`tinhTongXenKe`)

| Thành phần | Chi tiết |
|-----------|----------|
| Câu lệnh | 4 |
| Nhánh điều kiện | 3 (n<=0, i lẻ, i chẵn) |
| Vòng lặp | 1 vòng for (i từ 1 đến n) |

### 7. UCLN (`tinhUCLN`)

| Thành phần | Chi tiết |
|-----------|----------|
| Câu lệnh | 4 |
| Nhánh điều kiện | 3 (a<=0, b<=0, b!=0) |
| Vòng lặp | 1 vòng while |
| Đường đi logic | Path 1: a<=0; Path 2: b<=0; Path 3: lặp thuật toán Euclid |

### 8. Tổng giai thừa (`tinhTongGiaiThua` + `tinhGiaiThua`)

| Thành phần | Chi tiết |
|-----------|----------|
| Câu lệnh | 6 |
| Nhánh điều kiện | 4 (n<0, n==0, n<=0 tổng, vòng lặp) |
| Vòng lặp | 2 vòng for |

## 📋 Danh sách Test Case

### ISSUE 1 - Luồng hợp lệ (Happy Path)

| # | Test Method | Input | Expected |
|---|-------------|-------|----------|
| 1 | testChuViHCN_HopLe | a=3, b=4 | 14.0 |
| 2 | testChuViHCN_SoThuc | a=2.5, b=2.5 | 10.0 |
| 3 | testChuViHCN_HinhVuong | a=5, b=5 | 20.0 |
| 4 | testDienTichHCN_HopLe | a=3, b=4 | 12.0 |
| 5 | testGiaiBac2_HaiNghiem | a=1,b=-5,c=6 | [2.0, 3.0] |
| 6 | testGiaiBac2_NghiemKep | a=1,b=-2,c=1 | [1.0] |
| 7 | testGiaiBac2_Bac1 | a=0,b=2,c=-4 | [2.0] |
| 8 | testSoNgayThang_31Ngay | thang=1..12, nam=2023 | 31 |
| 9 | testSoNgayThang_30Ngay | thang=4,6,9,11 | 30 |
| 10 | testSoNgayThang_Thang2NamNhuan | thang=2, nam=2000/2024 | 29 |
| 11 | testSoNgayThang_Thang2NamThuong | thang=2, nam=2023/1900 | 28 |
| 12 | testSoNguyenTo_LaNguyenTo | n=2,3,5,7,11,97 | true |
| 13 | testSoNguyenTo_KhongLaNguyenTo | n=4,6,9,25 | false |
| 14 | testTongXenKe_N1 | n=1 | 1 |
| 15 | testTongXenKe_N4 | n=4 | -2 |
| 16 | testTongXenKe_N5 | n=5 | 3 |
| 17 | testUCLN_HopLe | a=12, b=8 | 4 |
| 18 | testUCLN_HaiBangNhau | a=7, b=7 | 7 |
| 19 | testGiaiThua_0 | n=0 | 1 |
| 20 | testGiaiThua_5 | n=5 | 120 |
| 21 | testTongGiaiThua_N3 | n=3 | 9 |

### ISSUE 2 - Nhánh lỗi, điều kiện biên, vòng lặp

| # | Test Method | Input | Expected |
|---|-------------|-------|----------|
| 22 | testChuViHCN_NgoaiLeA | a=-1, b=4 | Exception |
| 23 | testChuViHCN_NgoaiLeB | a=3, b=0 | Exception |
| 24 | testDienTichHCN_NgoaiLeA | a=0, b=4 | Exception |
| 25 | testGiaiBac2_VoNghiem | a=1,b=0,c=1 | [] |
| 26 | testGiaiBac2_VoSoNghiem | a=0,b=0,c=0 | [NaN, NaN] |
| 27 | testGiaiBac2_VoNghiemBac0 | a=0,b=0,c=5 | [] |
| 28 | testSoNgayThang_ThangNhoHon1 | thang=0 | Exception |
| 29 | testSoNgayThang_ThangLonHon12 | thang=13 | Exception |
| 30 | testSoNgayThang_NamKhongHopLe | nam=0 | Exception |
| 31 | testNamNhuan_Chia400 | nam=2000 | true |
| 32 | testNamNhuan_Chia100Khong400 | nam=1900 | false |
| 33 | testSoNguyenTo_SoAm | n=-1 | false |
| 34 | testSoNguyenTo_0Va1 | n=0, n=1 | false |
| 35 | testSoNguyenTo_SoChan | n=4, n=100 | false |
| 36 | testTongXenKe_NgoaiLe | n=0 | Exception |
| 37 | testUCLN_NgoaiLeA | a=0 | Exception |
| 38 | testUCLN_NgoaiLeB | b=0 | Exception |
| 39 | testGiaiThua_NgoaiLe | n=-1 | Exception |
| 40 | testTongGiaiThua_NgoaiLe | n=0 | Exception |
| 41 | testVongLap_UCLN_SoLon | a=144, b=60 | 12 |
| 42 | testVongLap_NguyenTo_SoLon | n=997 | true |
| 43 | testVongLap_TongXenKe_SoLon | n=100 | -50 |

## 🎯 Mục tiêu độ bao phủ (Coverage)

- ✅ **Statement Coverage**: 100%
- ✅ **Branch Coverage**: 100%
- ✅ **Path Coverage**: Đạt mức tối đa có thể

## 🛠️ Công nghệ sử dụng

- **Java** 11+
- **JUnit 5** (Jupiter) - Framework kiểm thử
- **Maven** - Build tool
- **JaCoCo** - Đo code coverage
