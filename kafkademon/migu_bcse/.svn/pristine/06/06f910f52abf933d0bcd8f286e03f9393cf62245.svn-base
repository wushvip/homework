package com.chinamobile.cmss.bcse.cleardata;

import java.util.ArrayList;
import java.util.List;

public class FloatClearMethod extends ClearMethodBase {

	public FloatClearMethod(List<String[]> lineList, ArrayList<String> clearColoum) {
		super(lineList, clearColoum);
	}

	@Override
	protected String clear(String source) throws Exception {
		if (source == "")
			return source;
		return Float.parseFloat(source) + "";
	}

}
