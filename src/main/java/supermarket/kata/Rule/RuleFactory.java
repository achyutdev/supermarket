package supermarket.kata.Rule;

import supermarket.kata.constant.RuleIDConstant;

public class RuleFactory {
	public static IRule getRule(RuleIDConstant RuleId) {

		IRule rule = null;

		switch (RuleId) {
		case BUNDLE_PRICE:
			rule = new BundlePriceRuleImpl();
			break;
		case GET_FREE_ITEM:
			rule = new GetFreeItemRuleImpl();
			break;
		// We can add more rule
		// ...
		// ...
			
		default:
			break;
		}
		return rule;
	}
}
