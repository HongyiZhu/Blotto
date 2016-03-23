package Move;

import java.util.ArrayList;

public class history
{
	private ArrayList<ArrayList<move>> _history;

	public history()
	{
		_history = new ArrayList<ArrayList<move>>();	
	}

	public void addNewRound()
	{
		_history.add(new ArrayList<move>());
	}

	public void addNewMove(move newMove)
	{
		_history.get(_history.size() - 1).add(newMove);
	}

	public int getNumOfPastRounds()
	{
		return _history.size() - 1;
	}

	public int getMyScoreOfRound(int round) throws Exception
	{
		if((round > _history.size() - 1) || (round < 0))
			throw new Exception("Round " + round + " is not available!");
		int numOfBets = _history.get(round).size();
		if(numOfBets == 0)
			return 0;
		else
			return _history.get(round).get(numOfBets - 1).getMyScore();
	}
	
	public int getOppScoreOfRound(int round) throws Exception
	{
		if((round > _history.size() - 1) || (round < 0))
			throw new Exception("Round " + round + " is not available!");
		int numOfBets = _history.get(round).size();
		if(numOfBets == 0)
			return 0;
		else
			return _history.get(round).get(numOfBets - 1).getOppScore();
	}
	
	public int getMyTotalScore() throws Exception
	{
		int totalScore = 0;
		for(int round = 0; round < _history.size(); round ++)
			totalScore += getMyScoreOfRound(round); 
		return totalScore;
	}
	
	public int getOppTotalScore() throws Exception
	{
		int totalScore = 0;
		for(int round = 0; round < _history.size(); round ++)
			totalScore += getOppScoreOfRound(round); 
		return totalScore;
	}
	
	public ArrayList<move> getSequenceOfMoves(int round) throws Exception
	{
		if((round > _history.size() - 1) || (round < 0))
			throw new Exception("Round " + round + " is not available!");
		ArrayList<move> sequenceOfMoves = new ArrayList<move>();
		for(int index = 0; index < _history.get(round).size(); index ++)
			sequenceOfMoves.add(_history.get(round).get(index).clone());
		return sequenceOfMoves;
	}
}
