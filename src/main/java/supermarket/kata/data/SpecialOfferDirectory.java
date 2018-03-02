package supermarket.kata.data;

import java.util.ArrayList;
import java.util.List;

import supermarket.kata.Rule.RuleDTO;
import supermarket.kata.constant.RuleIDConstant;

/**
 * This class keep the record of all the rules applicable in supermarket
 * 
 * @author achyutdev
 *
 */
public class SpecialOfferDirectory {
	public static List<RuleDTO> offers = new ArrayList<>();
	static {
		offers.add(new RuleDTO(RuleIDConstant.BUNDLE_PRICE, "03/26/2019", "itemName = A|quantity = 3|bundlePrice =130"));
		offers.add(new RuleDTO(RuleIDConstant.BUNDLE_PRICE, "03/26/2019", "itemName = B|quantity = 2|bundlePrice =45"));
	}
}
