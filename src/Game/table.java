package Game;

import java.util.ArrayList;
import java.util.Arrays;

import GUI.mainFrame;
import Move.move;
import Score.score;
import Strategies.strategy;
import Suit.card;

public class table
{
	private strategy _player1;
	private strategy _player2;
	private int _numOfCards;
	private int _numOfUpcards;
	private int _numOfRounds;
	private hand _player1Hand;
	private hand _player2Hand;
	private hand _middleHand;

	private int _player1Score;
	private int _player2Score;

	private int _player1TotalScore;
	private int _player2TotalScore;

	private int _player1TotalWins;
	private int _player2TotalWins;
	
	private int _player1TotalTies;
	private int _player2TotalTies;
	
	private int[] _upCards;

	private int[] _player1Move;
	private int[] _player2Move;

	private mainFrame _superFrame;

	public table(int numOfCards, int numOfUpcards,
			int numOfRounds, strategy player1,
			strategy player2, mainFrame superFrame
			) throws Exception {
		_player1 = player1;
		_player2 = player2;
		_numOfCards = numOfCards;
		_numOfUpcards = numOfUpcards;
		_numOfRounds = numOfRounds;
		_superFrame = superFrame;
	}

	public score play(boolean updateProgress) throws Exception
	{
		_player1.numOfRounds = _numOfRounds;
		_player2.numOfRounds = _numOfRounds;

		// Clear total scores
		clearTotalScores();

		for(int round = 0; round < _numOfRounds; round ++)
		{
			// Update progress and check state
			if(updateProgress)
			{
				int progress = (int) (100.00 * (double) round / (double) _numOfRounds);
				_superFrame.getBusyDialog().setProgress(_player1.getName() + " vs. " + _player2.getName() + "...", progress);
			}
			if(_superFrame.getBusyDialog().isCancelled()) throw new Exception("Game is aborted!");

			// Snapshot new rounds
			snapshotNewRound();
			summaryNewRound(round);

			// Clear scores
			clearScores();

			// Initialize hands
			initHands();

			for(int move = 0; move < _numOfCards / _numOfUpcards + 1; move ++)
			{
				// Snapshot the hands information
				snapshotHands();

				// Reveal the next up card and set pot
				updateUpCards();

				// Call users' strategy
				updateMove(round, move);

				// Update scores
				updateScores();
				updateTotalScores();
				summarizeLastMove(move);

				// Snapshot bets
				snapshotMoves();
			}
			
			updateWinsAndTies();
		}

		// Summary
		summarizeLastTable();

		// Return score
		return new score(_player1.getName(), _player2.getName(),
				_numOfRounds, _player1TotalScore, _player2TotalScore,
				_player1TotalWins, _player2TotalWins, _player1TotalTies,
				_player2TotalTies);
	}

	private void clearTotalScores()
	{
		_player1TotalScore = 0;
		_player2TotalScore = 0;
	}

	private void snapshotNewRound()
	{
		_player1.allHistory.addNewRound();
		_player2.allHistory.addNewRound();
	}

	private void summaryNewRound(int round)
	{
		output("\n" + _player1.getName() + " vs. " + _player2.getName() + ", round " + round + ".\n");
	}

	private void clearScores()
	{
		_player1Score = 0;
		_player2Score = 0;
	}

	private void initHands()
	{
		_player1Hand = new hand(_player1.getName(), _numOfCards);
		_player2Hand = new hand(_player2.getName(), _numOfCards);
		_middleHand = new hand("Middle Deck", _numOfCards);
	}

	private void snapshotHands()
	{
		_player1.myHand = _player1Hand.getCards();
		_player1.middleHand = _middleHand.getCards();
		_player2.myHand = _player2Hand.getCards();
		_player2.middleHand = _middleHand.getCards();
	}

	private void updateUpCards() throws Exception
	{
		ArrayList<card> unusedUpcards = _middleHand.listUnusedCards();
		if(unusedUpcards.size() >= _numOfUpcards)
			_upCards = _middleHand.pickRandomCards(_numOfUpcards);
		else _upCards = _middleHand.pickRandomCards(unusedUpcards.size());
		for(int cardIndex = 0; cardIndex < _upCards.length; cardIndex ++)
			_middleHand.useCard(_upCards[cardIndex]);
	}

	private void updateMove(int round, int move) throws Exception
	{
		_player1Move = _player1.nextPlay(round, Arrays.copyOf(_upCards, _upCards.length));
		for(int cardIndex = 0; cardIndex < _player1Move.length; cardIndex ++)
			_player1Hand.useCard(_player1Move[cardIndex]);
		_player2Move = _player2.nextPlay(round, Arrays.copyOf(_upCards, _upCards.length));
		for(int cardIndex = 0; cardIndex < _player2Move.length; cardIndex ++)
			_player2Hand.useCard(_player2Move[cardIndex]);
	}

	private void updateScores()
	{
		for(int cardIndex = 0; cardIndex < _upCards.length; cardIndex ++)
		{
			if(_player1Move[cardIndex] > _player2Move[cardIndex])
				_player1Score += _upCards[cardIndex];
			else if(_player1Move[cardIndex] < _player2Move[cardIndex])
				_player2Score += _upCards[cardIndex];
			else;
		}
	}

	private void updateTotalScores()
	{
		for(int cardIndex = 0; cardIndex < _upCards.length; cardIndex ++)
		{
			if(_player1Move[cardIndex] > _player2Move[cardIndex])
				_player1TotalScore += _upCards[cardIndex];
			else if(_player1Move[cardIndex] < _player2Move[cardIndex])
				_player2TotalScore += _upCards[cardIndex];
			else;
		}
	}
	
	private void updateWinsAndTies()
	{
		if(_player1Score > _player2Score)
			_player1TotalWins ++;
		if(_player1Score < _player2Score)
			_player2TotalWins ++;
		if(_player1Score == _player2Score)
		{
			_player1TotalTies ++;
			_player2TotalTies ++;
		}
	}

	private void summarizeLastMove(int move)
	{
		// Line 1: move number
		output("\nMove " + move + ".\n");
		
		// Line 2: list moves and results
		String text = "";
		for(int cardIndex = 0; cardIndex < _upCards.length; cardIndex ++)
		{
			text += "Battle " + (cardIndex + 1) + ": ";
			text += "up card is " + _upCards[cardIndex] + ", ";
			text += _player1.getName() + " bets " + _player1Move[cardIndex] + ", ";
			text += _player2.getName() + " bets " + _player2Move[cardIndex] + ". ";
			if(_player1Move[cardIndex] > _player2Move[cardIndex])
				text += _player1.getName() + " wins " + _upCards[cardIndex] + " points.\n";
			else if(_player1Move[cardIndex] < _player2Move[cardIndex])
				text += _player2.getName() + " wins " + _upCards[cardIndex] + " points.\n";
			else text += "It is a tie.\n";
		}
		text += _player1.getName() + "'s current round score: " + _player1Score + ", ";
		text += _player2.getName() + "'s current round score: " + _player2Score + ".\n";
		output(text);
	}

	private void snapshotMoves()
	{
		_player1.allHistory.addNewMove(new move(
				Arrays.copyOf(_player1Move, _player1Move.length),
				Arrays.copyOf(_player2Move, _player2Move.length),
				Arrays.copyOf(_upCards, _upCards.length),
				_player1Score, _player2Score));
		_player2.allHistory.addNewMove(new move(
				Arrays.copyOf(_player2Move, _player2Move.length),
				Arrays.copyOf(_player1Move, _player1Move.length),
				Arrays.copyOf(_upCards, _upCards.length),
				_player2Score, _player1Score));
	}

	private void summarizeLastTable()
	{
		output("\nSummary\n" + _player1.getName() + " vs. " + _player2.getName() + " for " + _numOfRounds + " rounds.\n");
		output(_player1.getName() + "'s total score: " + _player1TotalScore + ", total wins: " + _player1TotalWins + ", total ties: " + _player1TotalTies + "\n");
		output(_player2.getName() + "'s total score: " + _player2TotalScore + ", total wins: " + _player2TotalWins + ", total ties: " + _player2TotalTies);
	}

	private void output(String text)
	{
		_superFrame.getOutputPanel().appendText(text);
	}
}
