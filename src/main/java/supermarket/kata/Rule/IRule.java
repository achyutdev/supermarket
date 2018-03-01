package supermarket.kata.Rule;

import java.util.Map;

import supermarket.kata.constant.Constant;

/**
 * 
 * @author achyutdev
 *
 */
public interface IRule {
	boolean execute(Map<Constant, Object> cartObjectMap);
}
