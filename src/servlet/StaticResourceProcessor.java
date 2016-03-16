package servlet;

import java.io.IOException;

import demo1.Request;
import demo1.Response;

/**
 * ���������ṩ��̬����������
 */
public class StaticResourceProcessor {
	/**
	 * �÷�����process�����������ṩ��̬����ķ���
	 * @param request
	 * @param response
	 */
	public void process(Request request, Response response) {
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
