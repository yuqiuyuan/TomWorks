package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import servlet.RequestDispatcher;
import servlet.ServletInputStream;
import servlet.ServletRequest;


public class Request implements ServletRequest {
	private InputStream inputStream = null;
	private String uri;

	public Request(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 将uri从请求中分离出去
	 */
	public void parse() {
		// read a set of characters from the socket
		StringBuffer request = new StringBuffer(2048);
		int i;
		byte[] buffer = new byte[2048];
		try {
			i = inputStream.read(buffer);
		} catch (Exception e) {
			e.printStackTrace();
			i = -1;
		}
		for (int j = 0; j < i; j++) {
			request.append((char) buffer[j]);
		}
		// System.out.println(request.toString());
		uri = parseUri(request.toString());
	}

	public String parseUri(String requestString) {
		// get uri
		int index1, index2;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1) {
				return requestString.substring(index1 + 1, index2);
			}
		}
		return null;
	}

	public String geturi() {
		return uri;
	}

	@Override
	public Object getAttribute(String attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<Object> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReadPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDispatcher getRequestDispacher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSource() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getConstantLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getConstantType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<Object> getLocals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<Object> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterVlaues(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeAttribute(String attribute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAttribute(String kay, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacterEncoding(String encoding)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	}

}
