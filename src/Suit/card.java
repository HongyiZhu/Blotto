package Suit;

public class card
{
	private int _value;
	private boolean _used;
	
	public card(int value)
	{
		_value = value;
		_used = false;
	}
	
	public void setUsed()
	{
		_used = true;
	}
	
	private void setUsed(boolean used)
	{
		_used = used;
	}
	
	public int getValue()
	{
		return _value;
	}
	
	public boolean isUsed()
	{
		return _used;
	}
	
	public card clone()
	{
		card c = new card(_value);
		c.setUsed(_used);
		return c;
	}
}
