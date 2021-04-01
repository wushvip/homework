package com.chinamobile.cmss.bcse.cleardata;

import java.util.ArrayList;
import java.util.List;

public class UniqueKeyClearMethod extends ClearMethodBase {

	public UniqueKeyClearMethod(List<String[]> lineList, ArrayList<String> clearColoum) {
		super(lineList, clearColoum);
	}

	@Override
	protected String clear(String source) throws Exception {
		if (source.equals("")) {
			throw new Exception();
		} else {
			return source;
		}

	}
}
