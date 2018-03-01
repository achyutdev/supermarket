package supermarket.kata.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import supermarket.kata.Rule.RuleService;
import supermarket.kata.model.Item;

/**
 * 
 * @author achyutdev
 * 
 *
 */
public class CheckoutServiceImpl implements CheckoutService {

	private Map<Item, Integer> shoppingCart = new HashMap<>();
	private RuleService ruleService;

	public CheckoutServiceImpl() {
		ruleService = new RuleService();
	}

	/**
	 * @param item
	 *            This method add item into shopping cart If item already exist
	 *            in cart, it increase quantity of item
	 */
	public void addItem(Item item) {
		if (shoppingCart.containsKey(item)) {
			shoppingCart.put(item, shoppingCart.get(item) + 1);
		} else {
			shoppingCart.put(item, 1);
		}
	}

	/**
	 * 
	 * @param item
	 *            This method remove item from shopping cart
	 * 
	 */
	public boolean removeItem(Item item) {
		if (item != null && shoppingCart.containsKey(item)) {
			shoppingCart.remove(item);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param item
	 * @return
	 * 
	 * 		This method remove quantity of item from shopping cart if only
	 *         one quantity of given item left, it remove whole item from
	 *         shopping cart
	 */
	public boolean removeQuantity(Item item) {
		if (item != null && shoppingCart.containsKey(item)) {
			int quantity = shoppingCart.get(item);
			if (quantity == 1) {
				shoppingCart.remove(item);
			} else {
				shoppingCart.put(item, quantity - 1);
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return total number of unique item
	 */
	public int getTotalItem() {
		return shoppingCart.size();
	}

	/**
	 * Return item quantity if item exist otherwise return -1
	 * 
	 * @param item
	 * @return
	 */
	public int getQuantity(Item item) {
		if (shoppingCart.containsKey(item)) {
			return shoppingCart.get(item);
		}
		return -1;
	}

	/**
	 * @return total number of item added in cart
	 */
	public int getTotalQuantity() {
		int total = 0;
		Set<Item> items = shoppingCart.keySet();
		for (Item item : items) {
			total += shoppingCart.get(item);
		}
		return total;

	}

	/**
	 * shopping cart getter
	 * 
	 * @return shopping cart
	 * 
	 */
	public Map<Item, Integer> getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * shopping cart setter
	 * 
	 * @param cart
	 */
	public void setShoppingCart(Map<Item, Integer> cart) {
		this.shoppingCart = cart;
	}

	/**
	 * This method run rule and return final price
	 * 
	 * @return
	 */
	public double checkout() {
		return ruleService.executeRules(shoppingCart);
	}

}
