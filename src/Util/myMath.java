package Util;

public class myMath
{
	private static int fact(int n)
	{
		int result = 1;
		for(int x = n; x > 0; x --)
			result *= x;
		return result;
	}
	
	public static int choose(int n, int k)
	{
		return fact(n) / (fact(n - k) * fact(k));
	}
}
