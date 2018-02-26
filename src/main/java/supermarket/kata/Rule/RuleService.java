package supermarket.kata.Rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import supermarket.kata.constant.Constant;
import supermarket.kata.data.SpecialOfferDirectory;
import supermarket.kata.model.Item;

/**
 * This rule service get rule from rule factory and execute it Also filter
 * special offer deal based on effective date
 * 
 * @author devkoa1
 *
 */
public class RuleService {
	private Map<String, String> customParam;
	private List<RuleDTO> ruleList;
	private Map<Constant, Object> cartObjectMap;
	private Map<Item, Double> calculatedPriceMap;

	/**
	 * This method pull all special offers, sand rule list for filtering and
	 * execute all filtered rule
	 * 
	 * @param shoppingCart
	 */
	public double executeRules(Map<Item, Integer> shoppingCart) {

		ruleList = SpecialOfferDirectory.offers;
		cartObjectMap = new HashMap<>();
		calculatedPriceMap = new HashMap<>();

		List<RuleDTO> filteredRuleList = filterRuleList(ruleList);

		for (Item item : shoppingCart.keySet()) {
			calculatedPriceMap.put(item, shoppingCart.get(item) * item.getPrice());
		}

		for (RuleDTO ruleDTO : filteredRuleList) {
			customParam = getCustomParam(ruleDTO);
			cartObjectMap.put(Constant.CUSTOM_PARAMETERS, customParam);
			cartObjectMap.put(Constant.SHOPPING_CART, shoppingCart);
			cartObjectMap.put(Constant.PRICE_MAP, calculatedPriceMap);

			IRule rule = RuleFactory.getRule(ruleDTO.getRuleId());
			try {
				rule.execute(cartObjectMap);
			} catch (Exception e) {
				System.out.println("Rule Execute Error :\n RULE ID" + ruleDTO.getRuleId() + "\n" + e.getMessage());
			}
		}

		return findTotalPrice(calculatedPriceMap);

	}

	/**
	 * Calculate total price using priceMap
	 * @param priceMap
	 * @return
	 */
	private double findTotalPrice(Map<Item, Double> priceMap) {
		double sum = 0;
		for (Double price : priceMap.values()) {
			sum += price;
		}
		return sum;
	}

	/**
	 * This method return custom parameter of rule data in key value map
	 * 
	 * @param ruleDTO
	 * @return
	 */
	public Map<String, String> getCustomParam(RuleDTO ruleDTO) {
		Map<String, String> customParaMap = new HashMap<>();
		String customParamStr = ruleDTO.getCustomParams().replaceAll("\\s+", "");
		String[] customAttrs = customParamStr.split("\\|");
		for (String attr : customAttrs) {
			String[] keyValue = attr.split("=");
			String key = null, value = null;
			if (keyValue.length >= 1 && keyValue[0] != null) {
				key = keyValue[0];
			}
			if (keyValue.length >= 2 && keyValue[1] != null) {
				value = keyValue[1];
			}
			customParaMap.put(key, value);
		}
		return customParaMap;
	}

	/**
	 * This method filter rule with effective date However we can add more logic
	 * here with rule data parameter e.g. 1> sorting with sequence number 2>
	 * location base logic
	 * 
	 * @param ruleList
	 * @return
	 */
	public List<RuleDTO> filterRuleList(List<RuleDTO> ruleList) {

		return filterWithEffectiveDate(ruleList);

	}

	/**
	 * Effective date based filtering
	 * 
	 * @param ruleList
	 * @return
	 */
	private List<RuleDTO> filterWithEffectiveDate(List<RuleDTO> ruleList) {
		List<RuleDTO> tmpList = new ArrayList<>();
		for (RuleDTO ruleDTO : ruleList) {
			if (isFuture(ruleDTO.getEffectiveDate())) {
				tmpList.add(ruleDTO);
			}
		}
		return tmpList;
	}

	/**
	 * This method compare effective date value of rule data with current date
	 * 
	 * @param effectiveD
	 * @return
	 */
	public boolean isFuture(String effectiveD) {
		Date effectiveDate = null;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			effectiveDate = dateFormatter.parse(effectiveD);
			Date currentDate = new Date();
			if (currentDate.before(effectiveDate)) {
				return true;
			}
		} catch (ParseException e) {
			System.out.println("Error " + e.getMessage());
		}
		return false;
	}
}
