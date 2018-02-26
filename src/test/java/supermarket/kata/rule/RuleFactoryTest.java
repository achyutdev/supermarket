package supermarket.kata.rule;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import supermarket.kata.Rule.BundlePriceRuleImpl;
import supermarket.kata.Rule.GetFreeItemRuleImpl;
import supermarket.kata.Rule.RuleFactory;
import supermarket.kata.constant.RuleIDConstant;

public class RuleFactoryTest {


	@Test
	public void testFindRuleUsingRuleID() {
		
		assertEquals(new BundlePriceRuleImpl().getClass().getName(),
				RuleFactory.getRule(RuleIDConstant.BUNDLE_PRICE).getClass().getName());
		
		assertEquals(new GetFreeItemRuleImpl().getClass().getName(),
				RuleFactory.getRule(RuleIDConstant.GET_FREE_ITEM).getClass().getName());
	}

}
