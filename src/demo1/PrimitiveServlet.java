package demo1;

import java.io.IOException;
import java.io.PrintWriter;

import servlet.Servlet;
import servlet.ServletRequest;
import servlet.ServletResponse;

/**
 * 19ҳ
 */
public class PrimitiveServlet implements Servlet {

	@Override
	public void init() {
		System.out.println("init");

	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		System.out.println("from services");
		PrintWriter out = response.getWriter();
		out.println("Hello,Roses are red!");
		out.print("Violets are blue!");
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getServletConfig() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
