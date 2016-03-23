package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class textCaptionPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel _label;

	public textCaptionPanel(String label, int labelDimx,
			int dimy, int textAlignment, Font font) {
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		
		_label = new JLabel();
		_label.setFont(font);
		_label.setText(label);
		_label.setMinimumSize(new Dimension(labelDimx, dimy));
		_label.setMaximumSize(new Dimension(labelDimx, dimy));
		_label.setPreferredSize(new Dimension(labelDimx, dimy));
		_label.setHorizontalAlignment(textAlignment);
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_START;
		layout.setConstraints(_label, constraints);
		add(_label);
	}
	
	public void setText(String label)
	{
		_label.setText(label);
	}
	
	public void disable()
	{
		_label.setForeground(Color.GRAY);
	}
	
	public void enable()
	{
		_label.setForeground(Color.BLACK);
	}
}
