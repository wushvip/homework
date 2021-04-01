package com.chinamobile.cmss.bcse.validate.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with antnest-platform User: chenyuan Date: 12/31/14 Time: 8:49 PM
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private JSONObject body;

	public JSONObject getBody() {
		return body;
	}




	public void setBody(JSONObject body) {
		this.body = body;
	}




	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);

		body = HttpHelper.getBodyString(request);
	}

	

	
	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ByteArrayInputStream bais = new ByteArrayInputStream(body.toString().getBytes("UTF-8"));

		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return bais.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {

			}
		};
	}
}