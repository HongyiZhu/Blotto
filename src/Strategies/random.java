package Strategies;

public class random extends strategy
{
	public random()
	{
		// Name your strategy
		super("Random1");
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
		return new random();
	}
}
