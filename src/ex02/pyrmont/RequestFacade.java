package ex02.pyrmont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import servlet.RequestDispatcher;
import servlet.ServletInputStream;
import servlet.ServletRequest;
import demo1.Request;

public class RequestFacade implements ServletRequest {

	private ServletRequest request;

	public RequestFacade(Request request) {
		this.request = request;
	}

	@Override
	public Object getAttribute(String attribute) {
		return request.getAttribute(attribute);
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
