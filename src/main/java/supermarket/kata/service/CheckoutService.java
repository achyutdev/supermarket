package supermarket.kata.service;

import java.util.Map;

import supermarket.kata.model.Item;

public interface CheckoutService {

	/**
	 * @param item
	 *            This method add item into shopping cart If item already exist
	 *            in cart, it increase quantity of item
	 */
	void addItem(Item item);

	/**
	 * 
	 * @param item
	 *            This method remove item from shopping cart
	 * 
	 */
	boolean removeItem(Item item);

	/**
	 * 
	 * @param item
	 * @return
	 * 
	 * 		This method remove quantity of item from shopping cart if only
	 *         one quantity of given item left, it remove whole item from
	 *         shopping cart
	 */
	boolean removeQuantity(Item item);

	/**
	 * 
	 * @return total number of unique item
	 */
	int getTotalItem();

	/**
	 * Return item quantity if item exist otherwise return -1
	 * 
	 * @param item
	 * @return
	 */
	int getQuantity(Item item);

	/**
	 * @return total number of item added in cart
	 */
	int getTotalQuantity();

	/**
	 * shopping cart getter
	 * 
	 * @return shopping cart
	 * 
	 */
	Map<Item, Integer> getShoppingCart();

	/**
	 * shopping cart setter
	 * 
	 * @param cart
	 */
	void setShoppingCart(Map<Item, Integer> cart);

	/**
	 * This method run rule and return final price
	 * 
	 * @return
	 */
	double checkout();

}