package ex03.pyrmont.connector.http;

import java.net.Socket;

/*this class used to be called HttpServer*/
/**
 * HttpProcessor ����������connector�б�����������ǰ����HTTP�����׽��֣�����������飺 1������һ��HttpRequest����
 * 2������һ��HttpResponse���� 3������HttpRequest����ĵ�һ�к�ͷ�������ŵ�HttpRequest����
 * 4������HttpRequest��HttpResponse����һ��ServletProcessor����StaticResourceProcessor
 * ����ڶ�����˵�ģ�ServletProcessor���ñ������servlet
 * ��service��������StaticResourceProcessor����һ����̬��Դ�����ݡ�
 * 
 * */
public class HttpProcessor {

	private HttpConnector connector = null;
	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}

	/**
	 * The HttpConnector with which this processor is associated.
	 */
	public void process(Socket socket) {

	}
}
