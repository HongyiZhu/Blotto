package Suit;

import java.util.ArrayList;

public class suit
{
	protected ArrayList<card> _cards;
	
	public suit(int numOfCards)
	{
		_cards = new ArrayList<card>();
		for(int index = 1; index <= numOfCards; index ++)
			_cards.add(new card(index));
	}
	
	public suit(ArrayList<card> cards)
	{
		_cards = cards;
	}
	
	public ArrayList<card> listUsedCards()
	{
		ArrayList<card> usedCards = new ArrayList<card>();
		for(int index = 0; index < _cards.size(); index ++)
		{
			if(_cards.get(index).isUsed())
				usedCards.add(_cards.get(index).clone());
		}
		return usedCards;
	}
	
	public ArrayList<card> listUnusedCards()
	{
		ArrayList<card> unusedCards = new ArrayList<card>();
		for(int index = 0; index < _cards.size(); index ++)
		{
			if(!_cards.get(index).isUsed())
				unusedCards.add(_cards.get(index).clone());
		}
		return unusedCards;
	}
	
	public boolean isUsed(int cardValue) throws Exception
	{
		for(int index = 0; index < _cards.size(); index ++)
		{
			if(_cards.get(index).getValue() == cardValue)
				return _cards.get(index).isUsed();
		}
		throw new Exception("Card " + cardValue + " does not exist!");
	}
	
	public int[] pickRandomCards(int num) throws Exception
	{
		ArrayList<card> unusedCards = listUnusedCards();
		if(num > unusedCards.size())
			throw new Exception("Cannot randomly pick " + num
					+ " cards because only " + unusedCards.size()
					+ " cards are left!");
		int[] pickedCards = new int[num];
		for(int index = 0; index < num; index ++)
		{
			int randomIndex = (int) Math.floor(Math.random() * ((double) unusedCards.size()));
			pickedCards[index] = unusedCards.get(randomIndex).getValue();
			unusedCards.remove(randomIndex);
		}
		return pickedCards;
	}
}
