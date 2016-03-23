package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class radioButtonGroupPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] _labelArray;
	private JRadioButton[] _radioButtonArray;
	private ButtonGroup _radioButtonGroup;
	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;

	public radioButtonGroupPanel(String[] labelArray, int defaultSelectedItem,
			int labelDimx, int buttonDimx, int dimy, int orientation,
			int textAlignment, Font font) {
		int numOfButtons = labelArray.length;
		_labelArray = new JLabel[numOfButtons];
		_radioButtonArray = new JRadioButton[numOfButtons];
		_radioButtonGroup = new ButtonGroup();

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		if(orientation == radioButtonGroupPanel.VERTICAL)
		{
			for(int index = 0; index < numOfButtons; index ++)
			{
				_labelArray[index] = new JLabel();
				_labelArray[index].setFont(font);
				_labelArray[index].setText(labelArray[index]);
				_labelArray[index].setMinimumSize(new Dimension(labelDimx, dimy));
				_labelArray[index].setMaximumSize(new Dimension(labelDimx, dimy));
				_labelArray[index].setPreferredSize(new Dimension(labelDimx, dimy));
				_labelArray[index].setHorizontalAlignment(textAlignment);
				constraints.gridx = 0;
				constraints.gridy = index;
				constraints.anchor = GridBagConstraints.FIRST_LINE_START;
				layout.setConstraints(_labelArray[index], constraints);
				add(_labelArray[index]);

				_radioButtonArray[index] = new JRadioButton();
				_radioButtonArray[index].setFont(font);
				_radioButtonArray[index].setMinimumSize(new Dimension(buttonDimx, dimy));
				_radioButtonArray[index].setMaximumSize(new Dimension(buttonDimx, dimy));
				_radioButtonArray[index].setPreferredSize(new Dimension(buttonDimx, dimy));
				_radioButtonArray[index].setActionCommand(labelArray[index]);
				constraints.gridx = 1;
				constraints.gridy = index;
				constraints.anchor = GridBagConstraints.FIRST_LINE_END;
				layout.setConstraints(_radioButtonArray[index], constraints);
				add(_radioButtonArray[index]);

				if(index == defaultSelectedItem)
					_radioButtonArray[index].setSelected(true);
				_radioButtonGroup.add(_radioButtonArray[index]);
			}
		}
		else
		{
			for(int index = 0; index < numOfButtons; index ++)
			{
				_labelArray[index] = new JLabel();
				_labelArray[index].setFont(font);
				_labelArray[index].setText(labelArray[index]);
				_labelArray[index].setMinimumSize(new Dimension(labelDimx, dimy));
				_labelArray[index].setMaximumSize(new Dimension(labelDimx, dimy));
				_labelArray[index].setPreferredSize(new Dimension(labelDimx, dimy));
				_labelArray[index].setHorizontalAlignment(textAlignment);
				constraints.gridx = GridBagConstraints.RELATIVE;
				constraints.gridy = 0;
				constraints.anchor = GridBagConstraints.LINE_START;
				layout.setConstraints(_labelArray[index], constraints);
				add(_labelArray[index]);

				_radioButtonArray[index] = new JRadioButton();
				_radioButtonArray[index].setFont(font);
				_radioButtonArray[index].setMinimumSize(new Dimension(buttonDimx, dimy));
				_radioButtonArray[index].setMaximumSize(new Dimension(buttonDimx, dimy));
				_radioButtonArray[index].setPreferredSize(new Dimension(buttonDimx, dimy));
				_radioButtonArray[index].setActionCommand(labelArray[index]);
				constraints.gridx = GridBagConstraints.RELATIVE;
				constraints.gridy = 0;
				constraints.anchor = GridBagConstraints.LINE_START;
				layout.setConstraints(_radioButtonArray[index], constraints);
				add(_radioButtonArray[index]);

				if(index == defaultSelectedItem)
					_radioButtonArray[index].setSelected(true);
				_radioButtonGroup.add(_radioButtonArray[index]);
			}
		}
	}

	public String selectedItem()
	{
		for(JRadioButton b : _radioButtonArray)
		{
			if(b.isSelected())
				return b.getActionCommand();
		}
		return _radioButtonArray[0].getActionCommand();
	}

	public void enable()
	{
		for(int index = 0; index < _radioButtonArray.length; index ++)
		{
			_radioButtonArray[index].setEnabled(true);
			_labelArray[index].setForeground(Color.BLACK);
		}
	}

	public void disable()
	{
		for(int index = 0; index < _radioButtonArray.length; index ++)
		{
			_radioButtonArray[index].setEnabled(false);
			_labelArray[index].setForeground(Color.GRAY);
		}
	}
	
	public void addActionListener(ActionListener a)
	{
		for(int index = 0; index < _radioButtonArray.length; index ++)
		{
			_radioButtonArray[index].addActionListener(a);
		}
	}
}
