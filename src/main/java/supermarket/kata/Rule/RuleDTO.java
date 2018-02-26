package supermarket.kata.Rule;

import supermarket.kata.constant.RuleIDConstant;

/**
 * 
 * @author devkoa1
 *
 */
public class RuleDTO {
	private RuleIDConstant ruleId;

	private String effectiveDate;
	private String customParams;

	public RuleDTO(RuleIDConstant ruleId, String effectiveDate, String customParams) {
		this.ruleId = ruleId;
		this.effectiveDate = effectiveDate;
		this.customParams = customParams;
	}

	public RuleIDConstant getRuleId() {
		return ruleId;
	}

	public void setRuleId(RuleIDConstant ruleId) {
		this.ruleId = ruleId;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getCustomParams() {
		return customParams;
	}

	public void setCustomParams(String customParams) {
		this.customParams = customParams;
	}

}
