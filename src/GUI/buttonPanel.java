package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class buttonPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton _button;

	public buttonPanel(String buttonText, int buttonDimx,
			int dimy, int textAlignment, Font font) {
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		
		_button = new JButton();
		_button.setFont(font);
		_button.setMinimumSize(new Dimension(buttonDimx, dimy));
		_button.setMaximumSize(new Dimension(buttonDimx, dimy));
		_button.setPreferredSize(new Dimension(buttonDimx, dimy));
		_button.setHorizontalAlignment(textAlignment);
		_button.setText(buttonText);
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_START;
		layout.setConstraints(_button, constraints);
		add(_button);
	}
	
	public void disable()
	{
		_button.setEnabled(false);
	}
	
	public void enable()
	{
		_button.setEnabled(true);
	}
	
	public void addActionListener(ActionListener a)
	{
		_button.addActionListener(a);
	}
}
