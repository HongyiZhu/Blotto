package Strategies;

public class strategyManager
{
	private final static strategy[] _strategies = new strategy[]{
		new random(),
		new random2(),
		new matching(),
		new matching2()
	};

	public static strategy getStrategy(String name) throws Exception
	{
		for(int index = 0; index < _strategies.length; index ++)
		{
			if(_strategies[index].getName().equals(name))
			{
				strategy instance = _strategies[index].newInstance();
				instance.init();
				return instance;
			}
		}
		throw new Exception("Strategy: '" + name + "' not found!");			
	}

	public static String[] getStrategyList()
	{
		String[] list = new String[_strategies.length];
		for(int index = 0; index < _strategies.length; index ++)
			list[index] = _strategies[index].getName();
		return list;
	}
}
