package Game;

import java.util.ArrayList;

import Suit.card;
import Suit.suit;

public class hand extends suit
{
	private String _name;
	
	public hand(String name, int numOfCards)
	{
		super(numOfCards);
		_name = name;
	}
	
	public suit getCards()
	{
		ArrayList<card> cards = new ArrayList<card>();
		for(card c : _cards)
			cards.add(c.clone());
		return new suit(cards);
	}
	
	public void useCard(int cardValue) throws Exception
	{
		for(int index = 0; index < _cards.size(); index ++)
		{
			if(_cards.get(index).getValue() == cardValue)
			{
				if(_cards.get(index).isUsed())
					throw new Exception(_name + " is cheating: card " + cardValue + " has been used!");
				else
					_cards.get(index).setUsed();
				return;
			}
		}
		throw new Exception("Card " + cardValue + " does not exist!");
	}
}
