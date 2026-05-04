# Bài Thực Hành 03 - Kiểm Thử Hộp Đen

## Cấu trúc project
```
black_box_testing/
├── bai_toan.py          # Mã nguồn 8 bài toán
├── test_black_box.py    # Toàn bộ test case (92 test)
├── test_results.txt     # Kết quả chạy kiểm thử
└── README.md            # Tài liệu này
```

## Kết quả chạy kiểm thử
- **Tổng số test:** 92
- **Passed:** 92 / 92
- **Failed:** 0

---

## Mô tả áp dụng kiểm thử hộp đen cho từng bài

### Bài 1 & 2: Chu vi / Diện tích hình chữ nhật
| Kỹ thuật | Lớp tương đương / Giá trị biên |
|---|---|
| EP hợp lệ | a,b > 0 (nguyên), a,b > 0 (thực), a == b (hình vuông) |
| BVA | a,b rất lớn; 0 < a,b < 1 (gần biên dưới) |
| EP không hợp lệ | a = 0, b < 0, cả hai âm, kiểu chuỗi, None |

---

### Bài 3: Phương trình bậc 2
| Kỹ thuật | Lớp tương đương / Giá trị biên |
|---|---|
| EP hợp lệ | Δ > 0 (2 nghiệm), Δ < 0 (vô nghiệm), a < 0 |
| BVA | Δ = 0 (nghiệm kép — giá trị biên của Δ) |
| EP không hợp lệ | a = 0, hệ số là chuỗi, None |

---

### Bài 4: Số ngày trong tháng
| Kỹ thuật | Lớp tương đương / Giá trị biên |
|---|---|
| EP hợp lệ | tháng 31 ngày (1,3,5…), tháng 30 ngày (4,6…), tháng 2 năm thường, năm nhuận |
| BVA | tháng 1 (biên dưới), tháng 12 (biên trên); năm 1900 (không nhuận), năm 2000 (nhuận) |
| EP không hợp lệ | tháng 0, tháng 13, năm âm, năm = 0, kiểu float/chuỗi |

---

### Bài 5: Kiểm tra số nguyên tố
| Kỹ thuật | Lớp tương đương / Giá trị biên |
|---|---|
| EP hợp lệ | số nguyên tố nhỏ (2,3,7), số nguyên tố lớn (97), hợp số (4,100,99) |
| BVA | n = 0, n = 1, n = 2 (các biên quan trọng) |
| EP không hợp lệ | số âm → False, kiểu float, chuỗi, None → TypeError |

---

### Bài 6: Tổng xen kẽ S = 1-2+3-4+…+n
| Kỹ thuật | Lớp tương đương / Giá trị biên |
|---|---|
| EP hợp lệ | n lẻ (kết quả dương), n chẵn (kết quả âm) |
| BVA | n = 1 (biên dưới), n = 2 |
| EP không hợp lệ | n = 0, n âm, float, chuỗi, None |

---

### Bài 7: UCLN
| Kỹ thuật | Lớp tương đương / Giá trị biên |
|---|---|
| EP hợp lệ | UCLN bình thường, a==b, a là bội b, nguyên tố với hợp số |
| BVA | một trong hai = 1; số rất lớn |
| EP không hợp lệ | a = 0, b < 0, cả hai âm, float, chuỗi |

---

### Bài 8: Tổng giai thừa S = 1!+2!+…+n!
| Kỹ thuật | Lớp tương đương / Giá trị biên |
|---|---|
| EP hợp lệ | n = 1,2,4,5 — kiểm tra đúng giá trị tổng |
| BVA | n = 1 (biên dưới); 0! = 1, 1! = 1 |
| EP không hợp lệ | n âm, n = 0, float, chuỗi, None |

---

## Quy trình GitHub (theo yêu cầu đề)
1. Tạo repo mới trên GitHub
2. Commit mã nguồn (`bai_toan.py`, `test_black_box.py`)
3. **Issue 1:** Thiết kế và viết ca kiểm thử hộp đen cho dữ liệu **hợp lệ**
   → Commit: `[Issue #1] Add valid data test cases`
4. **Issue 2:** Thiết kế và viết ca kiểm thử hộp đen cho dữ liệu **không hợp lệ, biên, ngoại lệ**
   → Commit: `[Issue #2] Add invalid/boundary/edge case test cases`
