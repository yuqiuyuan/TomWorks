package ex03.pyrmont.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
public class HttpResponse implements HttpServletResponse
{
	//	the default buffer size
	private static final int	BUFFER_SIZE		= 1024;
	private OutputStream		output;
	private HttpRequest			request;
	PrintWriter					writer;
	protected byte[]			buffer			= new byte[BUFFER_SIZE];
	protected int				bufferCount		= 0;
	protected int				contentCount	= -1;

	public HttpResponse(OutputStream output)
	{
		this.output = output;
	}

	public void setRequest(HttpRequest request)
	{
		this.request = request;
	}

	public void write(int b) throws IOException
	{
		if (bufferCount >= buffer.length)
			flushBuffer();
		buffer[bufferCount++] = (byte) b;
		contentCount++;
	}

	public void write(byte b[]) throws IOException
	{
		write(b, 0, b.length);
	}

	public void write(byte b[], int off, int len) throws IOException
	{
		//		If the whole thing fits in the buffer,just put it there
		if (len == 0)
			return;
		if (len < (buffer.length - bufferCount))
		{
			System.arraycopy(b, off, buffer, bufferCount, len);
			bufferCount++;
			contentCount++;
			return;
		}
		//		Flush the buffer and start writing full-buffer-size chunks
		flushBuffer();
		int iterations = len / buffer.length;
		int leftoverStart = iterations * buffer.length;
		int leftoverLen = len - leftoverStart;
		for (int i = 0; i < iterations; i++)
			write(b, off + (i * buffer.length), buffer.length);
		//	write the remainder (guaranteed to fit in the buffer)
		if (leftoverLen > 0)
			write(b, off + leftoverStart, leftoverLen);
	}

	@Override
	public void flushBuffer() throws IOException
	{
		// TODO Auto-generated method stub
	}

	@Override
	public int getBufferSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCharacterEncoding()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCommitted()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void resetBuffer()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setBufferSize(int arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setContentLength(int arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setContentType(String arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setLocale(Locale arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void addCookie(Cookie arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void addDateHeader(String arg0, long arg1)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void addHeader(String arg0, String arg1)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void addIntHeader(String arg0, int arg1)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public boolean containsHeader(String arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String encodeRedirectURL(String arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectUrl(String arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeURL(String arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeUrl(String arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendError(int arg0) throws IOException
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void sendError(int arg0, String arg1) throws IOException
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void sendRedirect(String arg0) throws IOException
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setDateHeader(String arg0, long arg1)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setHeader(String arg0, String arg1)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setIntHeader(String arg0, int arg1)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setStatus(int arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setStatus(int arg0, String arg1)
	{
		// TODO Auto-generated method stub
	}
}
