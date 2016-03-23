package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class comboBoxPanel extends JPanel
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel _label;
	private JComboBox<String> _comboBox;

	public comboBoxPanel(String label, String[] list, int labelDimx,
			int comboDimx, int dimy, int textAlignment, Font font) {
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

		_comboBox = new JComboBox<String>();
		_comboBox.setFont(font);
		_comboBox.setModel(new DefaultComboBoxModel<String>(list));
		_comboBox.setMinimumSize(new Dimension(comboDimx, dimy));
		_comboBox.setMaximumSize(new Dimension(comboDimx, dimy));
		_comboBox.setPreferredSize(new Dimension(comboDimx, dimy));
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_START;
		layout.setConstraints(_comboBox, constraints);
		add(_comboBox);
	}

	public void setList(String[] list)
	{
		_comboBox.setModel(new DefaultComboBoxModel<String>(list));
	}

	public String[] getList()
	{
		String[] list = new String[_comboBox.getItemCount()];
		for(int index = 0; index < _comboBox.getItemCount(); index ++)
			list[index] = _comboBox.getItemAt(index);
		return list;
	}

	public void setSelectedItem(String item)
	{
		if(_comboBox.getItemCount() > 0)
			_comboBox.setSelectedItem(item);
	}

	public String getSelectedItem()
	{
		if(_comboBox.getItemCount() == 0)
			return null;
		else return (String) _comboBox.getSelectedItem();
	}

	public void disable()
	{
		_comboBox.setEnabled(false);
		_label.setForeground(Color.GRAY);
	}

	public void enable()
	{
		_comboBox.setEnabled(true);
		_label.setForeground(Color.BLACK);
	}

	public void addActionListener(ActionListener a)
	{
		_comboBox.addActionListener(a);
	}
}
