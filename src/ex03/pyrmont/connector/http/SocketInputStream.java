package ex03.pyrmont.connector.http;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
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
	 * Internal buffer
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
		requestLine.methodEnd = readCount - 1;
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
			if (buf[pos] == SP)
			{
				space = true;
			}
			requestLine.method[readCount] = (char) buf[pos];
			readCount++;
			pos++;
		}
		requestLine.methodEnd = readCount - 1;
		// Reading URI
		maxRead = requestLine.uri.length;
		readStart = pos;
		readCount = 0;
		space = false;
	}

	public void readHeader()
	{
		//		TODO
	}

	@Override
	public int read() throws IOException
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
