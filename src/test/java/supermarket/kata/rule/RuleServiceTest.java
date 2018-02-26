package supermarket.kata.rule;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import supermarket.kata.Rule.RuleDTO;
import supermarket.kata.Rule.RuleService;
import supermarket.kata.constant.RuleIDConstant;
import supermarket.kata.data.PriceList;
import supermarket.kata.model.Item;
import supermarket.kata.service.CheckoutService;
import supermarket.kata.service.CheckoutServiceImpl;

public class RuleServiceTest {

	private RuleService ruleService;
	private CheckoutService checkoutservice;

	@Before
	public void setUp() throws Exception {
		ruleService = new RuleService();
		checkoutservice = new CheckoutServiceImpl();
	}


	@Test
	public void testFilterRuleWithDifferentEffectiveDate() {
		List<RuleDTO> ruleList = new ArrayList<>();
		ruleList.add(new RuleDTO(RuleIDConstant.BUNDLE_PRICE, "02/11/2018", "SOME_CUSTOM_VALUE1"));
		ruleList.add(new RuleDTO(RuleIDConstant.GET_FREE_ITEM, "02/29/2018", "SOME_CUSTOM_VALUE2"));
		List<RuleDTO> filteredList = ruleService.filterRuleList(ruleList);
		assertEquals(1, filteredList.size(), 0.01);
	}

	@Test
	public void testPassedDate() {
		assertTrue(ruleService.isFuture("02/29/2018"));
		assertFalse(ruleService.isFuture("02/02/2018"));
		assertFalse(ruleService.isFuture("WRONG_VALUE"));
	}
	
	@Test
	public void testCustomParamBySendingruleDTO() {
		Map<String, String> customParaMap = ruleService
				.getCustomParam(new RuleDTO(RuleIDConstant.BUNDLE_PRICE, "02/11/2018", "value1=ONE|value2=TWO"));
		assertEquals("ONE", customParaMap.get("value1"));
		assertEquals("TWO", customParaMap.get("value2"));
	}

	@Test
	public void testCustomParamBySendingruleDTOWithOutValue() {
		Map<String, String> customParaMap = ruleService
				.getCustomParam(new RuleDTO(RuleIDConstant.BUNDLE_PRICE, "02/11/2018", "value1=ONE|value2"));
		assertEquals("ONE", customParaMap.get("value1"));
		assertEquals(null, customParaMap.get("value2"));
	}

	@Test
	public void testExecuteRule() {
		
		addItemIntoShoppingCart("A","A","B","C");
		assertEquals(150, ruleService.executeRules(checkoutservice.getShoppingCart()),0.001);
		
		addItemIntoShoppingCart("A","A","B","C");
		assertEquals(265, ruleService.executeRules(checkoutservice.getShoppingCart()),0.001);
		
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
