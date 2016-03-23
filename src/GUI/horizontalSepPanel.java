package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class horizontalSepPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSeparator _separator;

	public horizontalSepPanel(int dimx)
	{
		_separator = new JSeparator(JSeparator.HORIZONTAL);
		_separator.setMinimumSize(new Dimension(dimx, 1));
		_separator.setMaximumSize(new Dimension(dimx, 1));
		_separator.setPreferredSize(new Dimension(dimx, 1));

		setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		setLayout(new GridLayout(1, 1));
		add(_separator);
	}
}
