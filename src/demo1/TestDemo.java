package demo1;

public class TestDemo
{
	public static void main(String[] args)
	{
		//	
		System.out.println(getMoney(26));
	}

	static private String getMoney(int old)
	{
		int count = old - 26;
		int now = 3000;
		int countMoney = 0;
		int bf_d = 1000;
		int af_d = 2000;
		countMoney = now * 2;
		now = now + af_d;
		countMoney = countMoney + 4000 * 6;
		now = now + bf_d;
		for (int i = 1; i < count; i++)
		{
			countMoney = countMoney + now * 6;
			int ymoney = now * 6;
			now = now + af_d;
			countMoney = countMoney + now * 6;
			ymoney = ymoney + now * 6;
			now = now + bf_d;
			if (i == 14)
			{
				bf_d = 0;
				af_d = 0;
			}
			if (countMoney > 1000000)
			{
				return "年龄：" + (26 + i) + "当前月工资：" + now + "当前年薪：" + ymoney;
			}
		}
		return "存款：" + countMoney + "现在工资：" + now + "元";
	}
}
