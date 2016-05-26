package ex03.pyrmont.connector.http;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.naming.Constants;
import org.apache.naming.StringManager;
/**
 * Extends InputStream to be more efficient reading lines during HTTP header
 * processing.
 * 
 */
public class SocketInputStream extends InputStream
{
	// ----------------------------------------------------------- Constants
	/**
	 * CR.
	 */
	private static final byte	CR			= (byte) '\r';
	/**
	 * LF.
	 */
	private static final byte	LF			= (byte) '\n';
	/**
	 * SP.
	 */
	private static final byte	SP			= (byte) ' ';
	/**
	 * HT.
	 */
	private static final byte	HT			= (byte) '\t';
	/**
	 * COLON.
	 */
	private static final byte	COLON		= (byte) ':';
	/**
	 * Lower case offset
	 */
	private static final int	LC_OFFSET	= 'A' - 'a';
	/**
	 * Internal buffer?
	 * 这里为什么不使用字符数组而用字节数组呢？
	 */
	protected byte				buf[];
	/**
	 * Last valid byte.
	 */
	protected int				count;
	/**
	 * Position in the buffer.
	 */
	protected int				pos;
	/**
	 * Underlying input stream.
	 */
	protected InputStream		is;

	// --------------------------------------------------------- Constructors
	/**
	 * Construct a socket input stream associated with the specified socket
	 * input.
	 */
	public SocketInputStream(InputStream is, int bufferSize)
	{
		this.is = is;
		buf = new byte[bufferSize];
	}

	// ----------------------------------------------------------- Variables
	/**
	 * The string manager for this package.
	 */
	protected static StringManager sm = StringManager.getManager(Constants.Package);

	// -------------------------------------------------------- Instance
	// --------------------------------------------------------Variables
	// ------------------------------------------------------- public Methods
	/**
	 * Read the request line,and copies it to given buffer. This function is
	 * meant to be used during the HTTP request header parsing. Do NOT attempt
	 * to read the request body using it.
	 * 
	 * @throws IOException
	 */
	public void readRequestLine(HttpRequestLine requestLine) throws IOException
	{
		//		requestConstant(is);
		// Recycling check
		if (requestLine.methodEnd != 0)
		{
			requestLine.recycle();
		}
		// Checking for a blank line
		int chr = 0;
		do
		{
			try
			{
				chr = read();
			} catch (IOException e)
			{
				chr = -1;
			}
		} while ((chr == CR) || (chr == LF));
		if (chr == -1)
		{
			throw new EOFException(sm.getString("requestStream.readline.error"));
		}
		pos--;
		// Reading the method name
		int maxRead = requestLine.method.length;
		int readStart = pos;
		int readCount = 0;
		boolean space = false;
		while (!space)
		{
			// if the buffer is full,extend it
			if (readCount >= maxRead)
			{
				if ((2 * maxRead) <= HttpRequestLine.MAX_METHOD_SIZE)
				{
					char[] newBuffer = new char[2 * maxRead];
					System.arraycopy(requestLine.method, 0, newBuffer, 0, maxRead);
					requestLine.method = newBuffer;
					maxRead = requestLine.method.length;
				} else
				{
					throw new IOException(sm.getString("requestStream.readline.error"));
				}
			}
			// We're at the end of the internal buffer
			if (pos >= count)
			{
				int val = read();
				if (val == -1)
				{
					throw new IOException(sm.getString("requestStream.readline.error"));
				}
				pos = 0;
				readStart = 0;
			}
			if (buf[pos] == SP)
			{
				space = true;
			}
			requestLine.method[readCount] = (char) buf[pos];
			readCount++;
			pos++;
		}
		requestLine.methodEnd = readCount;
		// Reading URI
		maxRead = requestLine.uri.length;
		readStart = pos;
		readCount = 0;
		space = false;
		boolean eol = false;
		while (!space)
		{
			// if the buffer is full,extend it
			if (readCount >= maxRead)
			{
				if ((2 * maxRead) <= HttpRequestLine.MAX_URI_SIZE)
				{
					char[] newBuffer = new char[2 * maxRead];
					System.arraycopy(requestLine.uri, 0, newBuffer, 0, maxRead);
					requestLine.uri = newBuffer;
					maxRead = requestLine.uri.length;
				} else
				{
					throw new IOException(sm.getString("requestStream.readline.toolong"));
				}
			}
			// We're at the end of the internal buffer.
			if (pos >= count)
			{
				int val = read();
				if (val == -1)
				{
					throw new IOException(sm.getString("requestStream.readline.error"));
				}
				pos = 0;
				readStart = 0;
			}
			if (buf[pos] == SP)
			{
				space = true;
			} else if ((buf[pos] == CR) || (buf[pos] == LF))
			{
				// HTTP/0.9 style request
				eol = true;
				space = true;
			}
			requestLine.uri[readCount] = (char) buf[pos];
			readCount++;
			pos++;
		}
		requestLine.uriEnd = readCount - 1;
		// Reading protocol
		maxRead = requestLine.protocol.length;
		readStart = pos;
		readCount = 0;
		while (!eol)
		{
			// if the buffer is full,extend it
			if (readCount >= maxRead)
			{
				if ((2 * maxRead) <= HttpRequestLine.MAX_METHOD_SIZE)
				{
					char[] newBuffer = new char[2 * maxRead];
					System.arraycopy(requestLine.method, 0, newBuffer, 0, maxRead);
					requestLine.method = newBuffer;
					maxRead = requestLine.method.length;
				} else
				{
					throw new IOException(sm.getString("requestStream.readline.toolong"));
				}
			}
			// We're at the end of the internal buffer
			if (pos >= count)
			{
				int val = read();
				if (val == -1)
				{
					throw new IOException(sm.getString("requestStream.readline.error"));
				}
				pos = 0;
				readStart = 0;
			}
			if (buf[pos] == CR)
			{
			} else if (buf[pos] == LF)
			{
				eol = true;
			} else
			{
				requestLine.protocol[readCount] = (char) buf[pos];
				readCount++;
			}
			pos++;
		}
		requestLine.protocolEnd = readCount;
	}

	/**
	 * Read a header, and copies it to the given buffer.This function is meant to be used during the HTTP request 
	 * header parsing.Do Not attempt to read the request body using it.
	 * @throws IOException 
	 *  
	 */
	public void readHeader(HttpHeader header) throws IOException
	{
		//		Recycling check
		if (header.nameEnd != 0)
		{
			header.recycle();
		}
		//		Checking for a blank line
		int chr = read();
		if ((chr == CR) || (chr == LF))//Skipping CR
		{
			if (chr == CR)
			{
				read();
			}
			header.nameEnd = 0;
			header.valueEnd = 0;
			return;
		} else
		{
			pos--;
		}
		//		Reading the header name
		int maxRead = header.name.length;
		int readStart = pos;
		int readCount = 0;
		boolean colon = false;
		while (!colon)
		{
			//			if buffer is full,extend it
			if ((2 * maxRead) <= HttpHeader.MAX_NAME_SIZE)
			{
				char[] newBuffer = new char[2 * maxRead];
				System.arraycopy(header.name, 0, newBuffer, 0, maxRead);
				header.name = newBuffer;
				maxRead = header.name.length;
			} else
			{
				throw new IOException();
			}
			//			We're at the end of the internal buffer
			if (pos >= pos)
			{
				int val = read();
				if (val == -1)
				{
					throw new IOException();
				}
				pos = 0;
				readStart = 0;
			}
			if (buf[pos] == COLON)
			{
				colon = true;
			}
			char val = (char) buf[pos];
			if ((val >= 'A') && (val <= 'Z'))
			{
				val = (char) (val - LC_OFFSET);
			}
			header.name[readCount] = val;
			readCount++;
			pos++;
		}
		header.nameEnd = readCount - 1;
		//		Reading the header value (which can be spanned over multiple lines)
		maxRead = header.value.length;
		readStart = pos;
		readCount = 0;
		int crPos = -2;
		boolean eol = false;
		boolean validLine = true;
		while (validLine)
		{
			boolean space = true;
			//			Skipping spaces
			//			Note:Only leading white spaces are removed.Trailing white spaces are not.
			while (space)
			{
				//				we're at the end of the internal buffer
				if (pos >= count)
				{
					//					Copying part (or all) of the internal buffer to the line buffer
					int val = read();
					if (val == -1)
					{
						throw new IOException();
					}
					pos = 0;
					readStart = 0;
				}
				if ((buf[pos] == SP) || (buf[pos] == HT))
				{
					pos++;
				} else
				{
					space = false;
				}
			}
			while (!eol)
			{
				//				if the buffer is full,extend it
				if (readCount >= maxRead)
				{
					if ((2 * maxRead) <= HttpHeader.MAX_VALUE_SIZE)
					{
						char[] newBuffer = new char[2 * maxRead];
						System.arraycopy(header.value, 0, newBuffer, 0, maxRead);
						header.value = newBuffer;
						maxRead = header.value.length;
					} else
					{
						throw new IOException();
					}
				}
				//				We're at the end of the internal buffer
				if (pos >= count)
				{
					//					Copying part (or all) of the internal buffer to the line buffer
					int val = read();
					if (val == -1)
					{
						throw new IOException();
					}
					pos = 0;
					readStart = 0;
				}
				if (buf[pos] == CR)
				{
				} else if (buf[pos] == LF)
				{
					eol = true;
				} else
				{
					//					Fixme:Check if binary conversion is working fine 
					int ch = buf[pos] & 0xff;
					header.value[readCount] = (char) ch;
					readCount++;
				}
				pos++;
			}
			int nextChr = read();
			if ((nextChr != SP) && (nextChr != HT))
			{
				pos--;
				validLine = false;
			} else
			{
				eol = false;
				//				if the buffer is full,extend it
				if (readCount >= maxRead)
				{
					if ((2 * maxRead) <= HttpHeader.MAX_VALUE_SIZE)
					{
						char[] newBuffer = new char[2 * maxRead];
						System.arraycopy(header.value, 0, newBuffer, 0, maxRead);
						header.value = newBuffer;
						maxRead = header.value.length;
					} else
					{
						throw new IOException();
					}
				}
				header.value[readCount] = ' ';
				readCount++;
			}
		}
		header.valueEnd = readCount;
	}

	/**
	 * read byte
	 */
	@Override
	public int read() throws IOException
	{
		if (pos >= count)
		{
			fill();
			if (pos >= count)
			{
				return -1;
			}
		}
		return buf[pos++] & 0xff;
	}

	/**
	 * Fill the internal buffer using data from the underlying input stream.
	 * @throws IOException
	 */
	protected void fill() throws IOException
	{
		pos = 0;
		count = 0;
		int nRead = is.read(buf, 0, buf.length);
		if (nRead > 0)
		{
			count = nRead;
		}
	}

	/**
	 * it's used by test!
	 * @param in
	 */
	protected void requestConstant(InputStream in)
	{
		char[] buffer = new char[2048];
		InputStreamReader isr = new InputStreamReader(in);
		try
		{
			isr.read(buffer);
			String requestConteant = new String(buffer, 0, buffer.length);
			System.out.println(requestConteant);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
