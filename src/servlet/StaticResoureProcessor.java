package servlet;

import demo1.Request;
import demo1.Response;

/**
 * ��̬��Դ������
 */
public class StaticResoureProcessor {
	public void process(Request request, Response response) {
		// TODO
		try {
			response.sendStaticResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
