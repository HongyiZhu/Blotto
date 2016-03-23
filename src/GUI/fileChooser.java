package GUI;

import java.io.File;
import java.text.MessageFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class fileChooser extends JFileChooser
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void approveSelection()
	{  
		File f = getSelectedFile();  
		if (f.exists())
		{  
			String msg = "The file \"{0}\" already exists!\nDo you want to replace it?";  
			msg = MessageFormat.format(msg, new Object[]{f.getName()});  
			String title = getDialogTitle();  
			int option = JOptionPane.showConfirmDialog( this, msg,
					title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );  
			if ( option == JOptionPane.NO_OPTION )
				return;  
		}  
		super.approveSelection();  
	}  
}
