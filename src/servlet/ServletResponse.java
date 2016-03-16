package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public interface ServletResponse {
	public void flushBuffer() throws IOException;

	public String getCharacterEncoding();

	public Locale getLocale();

	public ServletOutputStream getOutputStream();

	public PrintWriter getWriter() throws IOException;

	public boolean isCommited();

	public void reset();

	public void resetBuffer();

	public void setBufferSize(int size);

	public void setContantLength(int lenght);

	public void setContantType(String type);

	public void setLocale();
}
