package Model;

public class SelectableItem
{
	private String _item;
	private boolean _selected;
	
	public SelectableItem(String item, boolean selected)
	{
		_item = item;
		_selected = selected;
	}
	
	public String getItem()
	{
		return _item;
	}
	
	public void select()
	{
		_selected = true;
	}
	
	public void deselect()
	{
		_selected = false;
	}
	
	public boolean isSelected()
	{
		return _selected;
	}
	
	public SelectableItem clone()
	{
		return new SelectableItem(_item, _selected);
	}
}
