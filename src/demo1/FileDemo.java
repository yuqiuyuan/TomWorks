package demo1;

import java.io.File;
import java.io.IOException;

public class FileDemo {
	public static void main(String[] args) {
		FileDemo fileD = new FileDemo();
		try {
			System.out.println("当前类的加载目录" + fileD.getFileDirectory());
			System.out.println("当前类所在的工程目录" + fileD.getFileDirectory2());
			System.out.println("项目路径为：" + fileD.getFileDirectory3() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前类的加载目录
	 */
	public File getFileDirectory() {
		File file = new File(this.getClass().getResource("").getPath());
		return file;
	}

	/**
	 * 获取当前类所在的工程路径
	 */
	public File getFileDirectory2() {
		File file = new File(this.getClass().getResource("/").getPath());
		return file;
	}

	public String getFileDirectory3() throws IOException {
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
		return courseFile;
	}
}
