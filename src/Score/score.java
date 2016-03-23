package Score;

public class score
{
	public String _player1Name;
	public String _player2Name;
	public int _numOfRounds;
	public boolean _carryover;
	public int _player1TotalScore;
	public int _player2TotalScore;
	public int _player1TotalWins;
	public int _player2TotalWins;
	public int _player1TotalTies;
	public int _player2TotalTies;
	
	public score(String player1Name, String player2Name,
			int numOfRounds, int player1TotalScore,
			int player2TotalScore, int player1TotalWins,
			int player2TotalWins, int player1TotalTies,
			int player2TotalTies) {
		_player1Name = player1Name;
		_player2Name = player2Name;
		_numOfRounds = numOfRounds;
		_player1TotalScore = player1TotalScore;
		_player2TotalScore = player2TotalScore;
		_player1TotalWins = player1TotalWins;
		_player2TotalWins = player2TotalWins;
		_player1TotalTies = player1TotalTies;
		_player2TotalTies = player2TotalTies;
	}
}
