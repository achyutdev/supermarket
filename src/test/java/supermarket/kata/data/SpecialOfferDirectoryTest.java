package supermarket.kata.data;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import supermarket.kata.Rule.RuleDTO;
import supermarket.kata.constant.RuleIDConstant;

public class SpecialOfferDirectoryTest {
	private List<RuleDTO> offers;

	@Before
	public void setUp() {
		offers = SpecialOfferDirectory.offers;
	}

	@Test
	public void test() {
		RuleDTO ruleDTO = new RuleDTO(RuleIDConstant.GET_FREE_ITEM, "02/26/2018",
				"itemName = A|quantity = 3|freeItem = B");
		offers.add(ruleDTO);
		assertTrue(offers.contains(ruleDTO));

	}

}
