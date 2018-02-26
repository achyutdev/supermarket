package supermarket.kata.rule;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import supermarket.kata.Rule.RuleDTO;
import supermarket.kata.constant.RuleIDConstant;

public class RuleDTOTest {

	private RuleDTO ruleDTO;

	@Before
	public void setUp() throws Exception {
		ruleDTO = new RuleDTO(RuleIDConstant.BUNDLE_PRICE, "effectiveDate", "customParam");
	}

	@After
	public void tearDown() throws Exception {
		ruleDTO = null;
	}

	@Test
	public void testruleDTOAttributeValue() {
		assertEquals(RuleIDConstant.BUNDLE_PRICE, ruleDTO.getRuleId());
		assertEquals("effectiveDate", ruleDTO.getEffectiveDate());
		assertEquals("customParam", ruleDTO.getCustomParams());
	}

	@Test
	public void testruleDTOByChangingAttributesValue() {

		ruleDTO.setCustomParams("NEW_CUSTOM_PARAM");
		ruleDTO.setRuleId(RuleIDConstant.GET_FREE_ITEM);
		ruleDTO.setEffectiveDate("NEW_EFFECTIVE_DATE");
		assertEquals(RuleIDConstant.GET_FREE_ITEM, ruleDTO.getRuleId());
		assertEquals("NEW_EFFECTIVE_DATE", ruleDTO.getEffectiveDate());
		assertEquals("NEW_CUSTOM_PARAM", ruleDTO.getCustomParams());
	}

}
