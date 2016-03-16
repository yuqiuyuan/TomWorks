package servlet;

import java.io.IOException;

/**
 * ��servlet�ӿڵ���������У�init,service,destroy ��servlet���������ڷ�����
 */
public interface Servlet {
	/**
	 * ��servlet ��ʼ��֮��init�����ᱻservlet���������ã�servletֻ����һ�Σ��Ա���servlet�ѱ����ؽ������С�
	 * init���������ڿ��Խ�������֮ǰִ����ϡ�����Ա����ͨ���������������д��Щ������Ҫ����һ�εķ�������������ݿ�������ֵ��ʼ���ȵȡ�
	 * ����������£��������ͨ���ǿյġ�
	 */
	public void init();

	/**
	 * servletΪ�������������ṩservice������servlet��������һ��ServletRequest�����ServletResponse����
	 * �� ServletRequest��������ͻ��˵�HTTP������Ϣ����ServletResponse�����װservlet����Ӧ��
	 * ��servlet���������У�service����������ö�Ρ�
	 * 
	 * @throws IOException
	 */
	public void service(ServletRequest request, ServletResponse response)
			throws IOException;

	/**
	 * ���ӷ������Ƴ�һ��servletʵ����ʱ��servlet��������destroy������
	 * ��ͨ��������servlet�������رջ���servlet������ҪһЩ�����ڴ��ʱ��
	 * �����������е�servlet�̵߳�service�����Ѿ��˳����߳�ʱ��̭��ʱ��
	 * ����������ű����á���servlet�����Ѿ�������destroy����֮�󣬵�ǰ���servlet���������ٵ���service������
	 * destroy�����ṩ��һ�������������κ��Ѿ���ռ�õ���Դ
	 * �����ڴ桢�ļ���������̡߳���ȷ���κδ��ڳ־�״̬��servlet���ڴ浱ǰ״̬��ͬ����
	 */
	public void destory();

	/**
	 * servlet�������ļ�
	 */
	public void getServletConfig();

	public String getServletInfo();
}
