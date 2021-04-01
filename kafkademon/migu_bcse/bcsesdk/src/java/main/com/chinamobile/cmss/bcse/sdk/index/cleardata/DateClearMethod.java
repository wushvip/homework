package com.chinamobile.cmss.bcse.sdk.index.cleardata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateClearMethod extends ClearMethodBase {

	public DateClearMethod(List<String[]> lineList, ArrayList<String> clearColoum) {
		super(lineList, clearColoum);
	}

	@Override
	protected String clear(String source) throws Exception {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			Date date = sdf.parse(source);
			return source;
		} catch (Exception e) {

		}
		if (source.equals(""))
			return source;

		if (source.indexOf('-') < 0) {
			throw new Exception();
		}
		if (source.split(":").length < 3) {
			if (source.indexOf(':') > 0) {
				source = source.substring(0, 16) + ":00";
			} else {
				source = source.substring(0, 10) + " 00:00:00";
			}
		}

		if (source.length() != 19) {
			throw new Exception();
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date date = sdf1.parse(source);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		source = sdf.format(date);
		return source;
	}

}
