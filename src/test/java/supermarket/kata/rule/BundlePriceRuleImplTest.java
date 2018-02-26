package supermarket.kata.rule;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import supermarket.kata.Rule.BundlePriceRuleImpl;
import supermarket.kata.Rule.IRule;
import supermarket.kata.constant.Constant;
import supermarket.kata.data.PriceList;
import supermarket.kata.model.Item;
import supermarket.kata.service.CheckoutService;
import supermarket.kata.service.CheckoutServiceImpl;

public class BundlePriceRuleImplTest {

	private Map<Constant, Object> cartObjectMap;
	private Map<String, String> customParams;
	private Map<Item, Double> priceMap;
	private CheckoutService checkoutservice;
	private IRule bundlePriceRule;

	@Before
	public void setUp() throws Exception {
		cartObjectMap = new HashMap<>();
		customParams = new HashMap<>();
		bundlePriceRule = new BundlePriceRuleImpl();
		checkoutservice = new CheckoutServiceImpl();
	}

	@Test
	public void testExecuteRuleWithOutSendingAnythingData() {
		cartObjectMap.put(Constant.SHOPPING_CART, checkoutservice.getShoppingCart());
		cartObjectMap.put(Constant.CUSTOM_PARAMETERS, customParams);
		cartObjectMap.put(Constant.PRICE_MAP, getPriceMap(checkoutservice.getShoppingCart()));
		assertFalse(bundlePriceRule.execute(cartObjectMap));
	}

	@Test
	public void testExecuteWithSameAItem() {
		addItemIntoShoppingCart("A", "A", "A", "A");
		populateCustomParamData("A", "3", "130");
		cartObjectMap.put(Constant.SHOPPING_CART, checkoutservice.getShoppingCart());
		cartObjectMap.put(Constant.CUSTOM_PARAMETERS, customParams);
		cartObjectMap.put(Constant.PRICE_MAP, getPriceMap(checkoutservice.getShoppingCart()));
		assertTrue(bundlePriceRule.execute(cartObjectMap));
		assertEquals(180, priceMap.get(getItem("A")), 0.01);
	}

	@Test
	public void testExecuteRuleWithDifferentBuldlePrice() {
		addItemIntoShoppingCart("A", "A", "A", "A");
		populateCustomParamData("B", "3", "130");
		cartObjectMap.put(Constant.SHOPPING_CART, checkoutservice.getShoppingCart());
		cartObjectMap.put(Constant.CUSTOM_PARAMETERS, customParams);
		cartObjectMap.put(Constant.PRICE_MAP, getPriceMap(checkoutservice.getShoppingCart()));
		assertTrue(bundlePriceRule.execute(cartObjectMap));
		assertEquals(200, priceMap.get(getItem("A")), 0.01);
	}

	private Map<Item, Double> getPriceMap(Map<Item, Integer> shoppingCart) {
		priceMap = new HashMap<>();
		for (Item item : shoppingCart.keySet()) {
			priceMap.put(item, shoppingCart.get(item) * item.getPrice());
		}
		return priceMap;
	}

	private void populateCustomParamData(String itmName, String qnty, String bndlprz) {
		customParams.put("itemName", itmName);
		customParams.put("quantity", qnty);
		customParams.put("bundlePrice", bndlprz);

	}

	@After
	public void tearDown() throws Exception {
		bundlePriceRule = null;
		customParams = null;
		cartObjectMap = null;
	}

	private void addItemIntoShoppingCart(String item1, String item2, String item3, String item4) {
		checkoutservice.addItem(getItem(item1));
		checkoutservice.addItem(getItem(item2));
		checkoutservice.addItem(getItem(item3));
		checkoutservice.addItem(getItem(item4));
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
