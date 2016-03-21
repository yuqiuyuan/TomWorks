package ex02.pyrmont;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import servlet.ServletOutputStream;
import servlet.ServletResponse;
import demo1.Response;

public class ResponseFacade implements ServletResponse {
	ServletResponse response;

	public ResponseFacade(Response response) {
		this.response = response;
	}

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCommited() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBufferSize(int size) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContantLength(int lenght) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContantType(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLocale() {
		// TODO Auto-generated method stub

	}
}
