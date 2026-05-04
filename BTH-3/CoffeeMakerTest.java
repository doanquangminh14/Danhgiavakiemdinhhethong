package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class CoffeeMakerTest {
	
	private CoffeeMaker coffeeMaker;
	private Recipe recipe1;
	private Recipe recipe2;

	@Before
	public void setUp() throws Exception {
		coffeeMaker = new CoffeeMaker();
		
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20"); 
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
	}

	/** --- KIỂM TRA MUA CÀ PHÊ (DIỆT MUTANT M1-M10) --- */

	@Test
	public void testMakeCoffee_ExactAmount() {
		coffeeMaker.addRecipe(recipe1);
		// M1-M10 thường thay đổi dấu >= thành >. Nếu đưa đúng 50, kết quả phải trả về 0 (đã pha).
		assertEquals(0, coffeeMaker.makeCoffee(0, 50));
	}

	@Test
	public void testMakeCoffee_ChangeReturned() {
		coffeeMaker.addRecipe(recipe1);
		// Đảm bảo tiền thối được tính toán chính xác (100 - 50 = 50)
		assertEquals(50, coffeeMaker.makeCoffee(0, 100));
	}

	@Test
	public void testMakeCoffee_InsufficientFunds() {
		coffeeMaker.addRecipe(recipe1);
		// Mutant có thể làm sai logic so sánh tiền. Trả lại toàn bộ tiền nếu thiếu dù chỉ 1 đồng.
		assertEquals(49, coffeeMaker.makeCoffee(0, 49));
	}

	@Test
	public void testMakeCoffee_InventoryUpdate() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.makeCoffee(0, 50);
		// Sau khi pha, nguyên liệu phải bị trừ chính xác. Kiểm tra chuỗi Inventory.
		String inv = coffeeMaker.checkInventory();
		assertTrue("Coffee should be 12", inv.contains("Coffee: 12"));
		assertTrue("Milk should be 14", inv.contains("Milk: 14"));
		assertTrue("Sugar should be 14", inv.contains("Sugar: 14"));
		assertTrue("Chocolate should be 15", inv.contains("Chocolate: 15"));
	}

	@Test
	public void testMakeCoffee_InsufficientInventory() {
		coffeeMaker.addRecipe(recipe2); // Cần 20 Chocolate, kho chỉ có 15
		// Nếu không đủ nguyên liệu, hệ thống KHÔNG được trừ tiền.
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}

	/** --- KIỂM TRA QUẢN LÝ CÔNG THỨC (RECIPE BOOK MUTANTS) --- */

	@Test
	public void testAddRecipe_FullCapacity() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		Recipe recipe3 = new Recipe();
		recipe3.setName("Latte");
		coffeeMaker.addRecipe(recipe3);
		
		Recipe recipe4 = new Recipe();
		recipe4.setName("Cappuccino");
		// Nếu hệ thống chỉ hỗ trợ 3 công thức, việc thêm công thức thứ 4 phải trả về false.
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}

	@Test
	public void testEditRecipe_NullIndex() {
		// Kiểm tra logic edit khi không có công thức nào tồn tại ở index đó.
		assertNull(coffeeMaker.editRecipe(0, recipe1));
	}

	@Test
	public void testDeleteRecipe_AlreadyEmpty() {
		// Kiểm tra việc xóa ở một vị trí trống.
		assertNull(coffeeMaker.deleteRecipe(0));
	}

	/** --- KIỂM TRA LỚP INVENTORY (DIỆT MUTANT TRONG SETTER) --- */

	@Test(expected = InventoryException.class)
	public void testAddInventory_ZeroAndNegative() throws InventoryException {
		// Mutant có thể cho phép số âm nếu logic kiểm tra là ">" thay vì ">="
		coffeeMaker.addInventory("0", "-1", "0", "0");
	}

	@Test
	public void testAddInventory_ValidValues() throws InventoryException {
		coffeeMaker.addInventory("1", "2", "3", "4");
		String inv = coffeeMaker.checkInventory();
		assertTrue(inv.contains("Coffee: 16"));
		assertTrue(inv.contains("Milk: 17"));
		assertTrue(inv.contains("Sugar: 18"));
		assertTrue(inv.contains("Chocolate: 19"));
	}
}
