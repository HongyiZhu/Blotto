package GUI;

import java.io.File;
import java.io.FileFilter;

public class csvFileFilter implements FileFilter
{
	@Override
	public boolean accept(File f)
	{
		if (f.getName().toLowerCase().endsWith(".csv"))
			return true;
		return false;
	}
}
