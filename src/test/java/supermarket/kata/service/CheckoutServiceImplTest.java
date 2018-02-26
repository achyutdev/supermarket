package supermarket.kata.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import supermarket.kata.Rule.RuleService;
import supermarket.kata.data.PriceList;
import supermarket.kata.model.Item;

public class CheckoutServiceImplTest {

	private CheckoutServiceImpl checkoutservice;
	private RuleService ruleService;

	@Before
	public void setup() {
		checkoutservice = new CheckoutServiceImpl();
	}

	@Test
	public void testShoppingCartaddItemingNewItem() {
		Item item1 = new Item("A", PriceList.A.getPrice());
		checkoutservice.addItem(item1);
		assertEquals(true, checkoutservice.getShoppingCart().containsKey(item1));
	}

	@Test
	public void testShoppingCartMoreThanOneItem() {
		Item item1 = new Item("A", PriceList.A.getPrice());
		Item item2 = new Item("B", PriceList.B.getPrice());
		checkoutservice.addItem(item1);
		checkoutservice.addItem(item2);
		assertTrue(checkoutservice.getShoppingCart().containsKey(item1));
		assertTrue(checkoutservice.getShoppingCart().containsKey(item2));
	}

	@Test
	public void testShoppingCardaddItemingSameItemMultipleTime() {
		Item item1 = new Item("A", PriceList.A.getPrice());
		checkoutservice.addItem(item1);
		checkoutservice.addItem(item1);
		assertEquals(2, checkoutservice.getShoppingCart().get(item1), 0.00001);
	}

	@Test
	public void testRemoveItemFromShoppingCartSuccess() {
		addItemIntoShoppingCart("ABBD");
		assertTrue(checkoutservice.removeItem(getItem("B")));
		assertFalse(checkoutservice.removeItem(getItem("B")));
	}

	@Test
	public void testRemoveItemFromShoppingCart() {
		addItemIntoShoppingCart("ABBD");
		checkoutservice.removeItem(getItem("A"));
		assertFalse(checkoutservice.getShoppingCart().containsKey(getItem("A")));
	}

	@Test
	public void testRemoveItemThatDoesNotExistInShoppingCart() {
		addItemIntoShoppingCart("ABA");
		assertFalse(checkoutservice.removeItem(getItem("C"))); 
	}

	@Test
	public void testRemoveQuantityfromShoppingCart() {
		addItemIntoShoppingCart("A");
		assertTrue(checkoutservice.removeQuantity(getItem("A")));
		assertFalse(checkoutservice.removeQuantity(getItem("A")));
	}

	@Test
	public void testRemovingItemFromShoppingCartCheckQuantity() {
		addItemIntoShoppingCart("AAAA");
		checkoutservice.removeQuantity(getItem("A"));
		assertEquals(3, checkoutservice.getQuantity(getItem("A")));
		checkoutservice.removeQuantity(getItem("A"));
		assertEquals(2, checkoutservice.getQuantity(getItem("A")));
	}

	@Test
	public void testRemovingItemFromShoppingCartThatDoseNotExist() {
		addItemIntoShoppingCart("AA");
		checkoutservice.removeQuantity(getItem("B"));
		assertEquals(2, checkoutservice.getQuantity(getItem("A")));
	}

	@Test
	public void testToGetQuantityOfItemThatDoesNotExist() {
		addItemIntoShoppingCart("AA");
		assertEquals(-1, checkoutservice.getQuantity(getItem("B")));
	}

	@Test
	public void testTotalItemInShoppingCart() {
		addItemIntoShoppingCart("ABBA");
		assertEquals(2, checkoutservice.getTotalItem());
		addItemIntoShoppingCart("ABBA");
		assertEquals(2, checkoutservice.getTotalItem());
	}

	@Test
	public void testTotalQuantityInShoppingCart() {
		addItemIntoShoppingCart("ABBA");
		assertEquals(4, checkoutservice.getTotalQuantity());
		addItemIntoShoppingCart("ABBA");
		assertEquals(8, checkoutservice.getTotalQuantity());
	}

	@Test
	public void testCheckoutSelectedItemOfShoppingCart() {
		assertEquals(50, checkOut("A"), 0.001);
		assertEquals(100, checkOut("AA"), 0.001);
		assertEquals(130, checkOut("AAA"), 0.001);
		assertEquals(180, checkOut("AAAA"), 0.001);
		assertEquals(30, checkOut("B"), 0.001);
		assertEquals(45, checkOut("BB"), 0.001);
		assertEquals(75, checkOut("BBB"), 0.001);
		assertEquals(90, checkOut("BBBB"), 0.001);
		assertEquals(20, checkOut("C"), 0.001);
		assertEquals(15, checkOut("D"), 0.001);
		assertEquals(115, checkOut("ABCD"), 0.001);
	}

	private double checkOut(String items) {
		addItemIntoShoppingCart(items);
		double totalPrice = checkoutservice.checkout();
		checkoutservice.setShoppingCart(new HashMap<>());
		return totalPrice;
	}

	@After
	public void tearDown() {
		checkoutservice = null;
	}

	private void addItemIntoShoppingCart(String items) {
		String[] itemArray = items.split("|");
		for (String item : itemArray) {
			checkoutservice.addItem(getItem(item));
		}
	}

	private Item getItem(String itemName) {
		Item item = null;
		switch (itemName) {
		case "A":
			item = new Item("A", PriceList.A.getPrice());
			break;
		case "B":
			item = new Item("B", PriceList.B.getPrice());
			break;
		case "C":
			item = new Item("C", PriceList.C.getPrice());
			break;
		case "D":
			item = new Item("D", PriceList.D.getPrice());
			break;
		default:
			break;
		}
		return item;
	}

}
