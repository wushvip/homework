package com.chinamobile.cmss.bcse.cleardata;

import java.util.ArrayList;
import java.util.List;

/**
 * 清洗int类型
 * 
 * @author jinjing
 * 
 */
public class IntClearMethod extends ClearMethodBase {

	public IntClearMethod(List<String[]> lineList, ArrayList<String> clearColoum) {
		super(lineList, clearColoum);
	}

	@Override
	protected String clear(String source) throws Exception {

		if (source == "")
			return source;
		int number = Integer.parseInt(source);
		if (number > 2147483647 || number < -2147483648) {
			throw new Exception();
		} else {
			source = number + "";
		}
		return source;
	}
}
