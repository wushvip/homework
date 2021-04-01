package com.chinamobile.cmss.bcse.cleardata;

import java.util.ArrayList;
import java.util.List;

public class LongClearMethod extends ClearMethodBase {

	public LongClearMethod(List<String[]> lineList, ArrayList<String> clearColoum) {
		super(lineList, clearColoum);
	}

	@Override
	protected String clear(String source) throws Exception {

		if (source == "")
			return source;
		Long number = Long.parseLong(source);
		/*
		 * if (number > 2147483647 || number < -2147483648) { throw new
		 * Exception(); } else { source = number + ""; }
		 */
		return number + "";
	}
}