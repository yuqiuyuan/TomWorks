package demo1;

import java.io.File;
import java.io.IOException;
public class FileDemo
{
	public static void main(String[] args)
	{
		FileDemo fileD = new FileDemo();
		try
		{
			System.out.println("��ǰ��ļ���Ŀ¼" + fileD.getFileDirectory());
			System.out.println("��ǰ�����ڵĹ���Ŀ¼" + fileD.getFileDirectory2());
			System.out.println("��Ŀ·��Ϊ��" + fileD.getFileDirectory3() + "");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ��ǰ��ļ���Ŀ¼
	 */
	public File getFileDirectory()
	{
		File file = new File(this.getClass().getResource("").getPath());
		return file;
	}

	/**
	 * ��ȡ��ǰ�����ڵĹ���·��
	 */
	public File getFileDirectory2()
	{
		File file = new File(this.getClass().getResource("/").getPath());
		return file;
	}

	public String getFileDirectory3() throws IOException
	{
		File directory = new File("");// ����Ϊ��
		String courseFile = directory.getCanonicalPath();
		return courseFile;
	}
}
