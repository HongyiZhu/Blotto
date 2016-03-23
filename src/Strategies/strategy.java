package Strategies;

import Move.history;
import Suit.suit;

public abstract class strategy
{
	private String _name;
	public history allHistory;
	public int numOfRounds;
	public suit myHand;
	public suit middleHand;

	public strategy(String name)
	{
		_name = name;
		allHistory = new history();
	}
	
	public String getName()
	{
		return _name;
	}

	/*
	 * Override this function with your own
	 * Round starts from 0 not 1
	 * Card value starts from 1
	 * Be careful that the returned value has to be an integer array
	 * the same length as the integer array of upCardValue
	 */
	abstract public int[] nextPlay(int round, int[] upCards) throws Exception;
	
	/*
	 * Override this function with your own
	 * Put your initialization procedure here
	 */
	abstract public void init();
	
	/*
	 * Override this function with your own
	 */
	abstract public strategy newInstance();
}
