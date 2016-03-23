package Strategies;

import java.util.Arrays;

public class matching2 extends strategy
{
	public matching2()
	{
		super("Matching2");
	}

	@Override
	public int[] nextPlay(int round, int[] upCards) throws Exception
	{
		return Arrays.copyOf(upCards, upCards.length);
	}

	@Override
	public void init()
	{
		return;
	}

	@Override
	public strategy newInstance()
	{
		return new matching2();
	}
}
