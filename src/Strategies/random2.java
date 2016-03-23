package Strategies;

public class random2 extends strategy
{
	public random2()
	{
		// Name your strategy
		super("Random2");
	}
	
	@Override
	public int[] nextPlay(int round, int[] upCards) throws Exception
	{
		return myHand.pickRandomCards(upCards.length);
	}
	
	@Override
	public void init()
	{
		return;
	}
	
	@Override
	public strategy newInstance()
	{
		return new random2();
	}
}
