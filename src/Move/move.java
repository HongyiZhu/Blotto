package Move;

import java.util.Arrays;

public class move
{
	private int[] _myCards;
	private int[] _oppCards;
	private int[] _upCards;
	private int _myScoreAftMove;
	private int _oppScoreAftMove;
	
	public move(int[] myCards, int[] oppCards, int[] upCards,
			int myScoreAftMove, int oppScoreAftMove) {
		_myCards = Arrays.copyOf(myCards, myCards.length);
		_oppCards = Arrays.copyOf(oppCards, oppCards.length);
		_upCards = Arrays.copyOf(upCards, upCards.length);
		_myScoreAftMove = myScoreAftMove;
		_oppScoreAftMove = oppScoreAftMove;
	}
	
	public int[] getMyCards()
	{
		return Arrays.copyOf(_myCards, _myCards.length);
	}
	
	public int[] getOppCards()
	{
		return Arrays.copyOf(_oppCards, _oppCards.length);
	}
	
	public int[] getUpCards()
	{
		return Arrays.copyOf(_upCards, _upCards.length);
	}
	
	public int getMyScore()
	{
		return _myScoreAftMove;
	}
	
	public int getOppScore()
	{
		return _oppScoreAftMove;
	}
	
	public move clone()
	{
		return new move(_myCards, _oppCards, _upCards,
				_myScoreAftMove, _oppScoreAftMove);
	}
}
