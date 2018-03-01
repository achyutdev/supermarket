package supermarket.kata.Rule;

import java.util.Map;

import supermarket.kata.constant.Constant;
import supermarket.kata.model.Item;

/**
 * This rule update price map of the items that are bundle together
 * 
 * @author achyutdev
 *
 */
@SuppressWarnings("unchecked")
public class BundlePriceRuleImpl implements IRule {

	private Map<String, String> customParams;
	private Map<Item, Integer> shoppingCart;
	private Map<Item, Double> priceMap;

	@Override
	public boolean execute(Map<Constant, Object> cartObjectMap) {
		boolean ruleStatus = false;

		customParams = (Map<String, String>) cartObjectMap.get(Constant.CUSTOM_PARAMETERS);
		shoppingCart = (Map<Item, Integer>) cartObjectMap.get(Constant.SHOPPING_CART);
		priceMap = (Map<Item, Double>) cartObjectMap.get(Constant.PRICE_MAP);

		String itemName = customParams.get("itemName");
		String quantity = customParams.get("quantity");
		String price = customParams.get("bundlePrice");

		int quantityInt, numOfQuntityInCart, numOfBundle, numleftFromBundle;
		double totalPrice, priceDbl;

		if (itemName != null && quantity != null && price != null) {
			Item selectedItem = getItem(itemName);
			if (selectedItem != null) {
				quantityInt = Integer.parseInt(quantity);
				priceDbl = Double.parseDouble(price);
				numOfQuntityInCart = shoppingCart.get(selectedItem);
				numOfBundle = numOfQuntityInCart / quantityInt;
				numleftFromBundle = numOfQuntityInCart % quantityInt;

				totalPrice = numOfBundle * priceDbl + numleftFromBundle * selectedItem.getPrice();
				priceMap.put(selectedItem, totalPrice);
			}
			ruleStatus = true;

		}

		return ruleStatus;
	}

	private Item getItem(String itemName) {
		Item reqItem = null;
		for (Item item : shoppingCart.keySet()) {
			if (itemName.equals(item.getName())) {
				reqItem = item;
			}
		}
		return reqItem;
	}

}
