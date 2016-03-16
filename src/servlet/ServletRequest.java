package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;


public interface ServletRequest {
	/*
	 * Implementation of ServletRequest
	 */
	public Object getAttribute();

	public Enumeration<Object> getAttributeNames();

	public String getReadPath(String path);

	public RequestDispatcher getRequestDispacher(String path);

	public boolean isSource();

	public String getCharacterEncoding();

	public int getConstantLength();

	public String getConstantType();

	public ServletInputStream getInputStream() throws IOException;

	public Locale getLocal();

	public Enumeration<Object> getLocals();

	public String getParameter(String name);

	public Map<String, Object> getParameterMap();

	public Enumeration<Object> getParameterNames();

	public String[] getParameterVlaues(String parameter);

	public String getProtocol();

	public BufferedReader getReader() throws IOException;

	public String getRemoteAddr();

	public String getRemoteHost();

	public String getScheme();

	public String getServerName();

	public int getServerPort();

	public void removeAttribute(String attribute);

	public void setAttribute(String kay, Object value);

	public void setCharacterEncoding(String encoding)
			throws UnsupportedEncodingException;
}
