package demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import pub.Constants;
import servlet.ServletOutputStream;
import servlet.ServletResponse;

/*
 HTTP Response = Status-Line
 * ((general-header|response-header|entity-header)CRLF)
 CRLF 
 [ message-body ]
 Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
 */
public class Response implements ServletResponse {
	public static final int BUFFER_SIZE = 1024;
	private Request request;
	OutputStream output;
	PrintWriter write;

	public Response(OutputStream output) {
		this.output = output;
	}

	public Request getRequet() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(Constants.WEB_ROOT, request.geturi());
			if (file.exists()) {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, BUFFER_SIZE);
				}
			} else {
				// file not found
				/**
				 * // file not found String errorMessage =
				 * "HTTP/1.1 404 File Not Found\r\n" +
				 * "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" +
				 * "\r\n" + "<h1>File Not Found</h1>
				 */
				String errorMessage = "HTTP/1.1 404 File Not Found!\r\n"
						+ "Content-Type:text/html\r\n"
						+ "Content-Length: 23\r\n" + "\r\n"
						+ "<h1>File Not Found</h1>";
				output.write(errorMessage.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		// autoflush is true,println will flush,but print will not.
		write = new PrintWriter(output, true);
		return write;
	}

	@Override
	public boolean isCommited() {
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
