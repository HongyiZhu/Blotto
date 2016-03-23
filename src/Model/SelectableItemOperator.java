package Model;

public class SelectableItemOperator
{
	public static int getAvailableItemCount(SelectableItem[] model)
	{
		int availableItemCount = 0;
		for(int index = 0; index < model.length; index ++)
		{
			if(!model[index].isSelected())
				availableItemCount ++;
		}
		return availableItemCount;
	}
	
	public static String[] createAvailableModel(SelectableItem[] model)
	{
		String[] availableModel = new String[getAvailableItemCount(model)];
		int index = 0;
		for(int i = 0; i < model.length; i ++)
		{
			if(!model[i].isSelected())
			{
				availableModel[index] = model[i].getItem();
				index ++;
			}
		}
		return availableModel;
	}
	
	public static int getSelectedItemCount(SelectableItem[] model)
	{
		int selectedItemCount = 0;
		for(int index = 0; index < model.length; index ++)
		{
			if(model[index].isSelected())
				selectedItemCount ++;
		}
		return selectedItemCount;
	}
	
	public static String[] createSelectedModel(SelectableItem[] model)
	{
		String[] selectedModel = new String[getSelectedItemCount(model)];
		int index = 0;
		for(int i = 0; i < model.length; i ++)
		{
			if(model[i].isSelected())
			{
				selectedModel[index] = model[i].getItem();
				index ++;
			}
		}
		return selectedModel;
	}
	
	public static void select(SelectableItem[] model, String item)
	{
		for(int index = 0; index < model.length; index ++)
		{
			if(model[index].getItem().equals(item))
				model[index].select();
		}
	}
	
	public static void deselect(SelectableItem[] model, String item)
	{
		for(int index = 0; index < model.length; index ++)
		{
			if(model[index].getItem().equals(item))
				model[index].deselect();
		}
	}
}
