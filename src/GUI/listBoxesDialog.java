package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EtchedBorder;

import Model.SelectableItem;
import Model.SelectableItemOperator;

public class listBoxesDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int _captionDimx = 200;
	private int _captionDimy = 30;
	private int _listBoxDimx = 200;
	private int _listBoxDimy = 300;
	private int _middleButtonDimx = 60;
	private int _middleButtonDimy = 30;
	private int _bottomButtonDimx = 100;
	private int _bottomButtonDimy = 30;
	private Font _font = new Font("Tahoma", 1, 11);
	private JPanel _rootPanel;
	private JPanel _leftListBoxPanel;
	private JPanel _middleButtonPanel;
	private JPanel _rightListBoxPanel;
	private JPanel _bottomButtonPanel;
	private textCaptionPanel _leftCaption;
	private JList<String> _leftListBox;
	private textCaptionPanel _rightCaption;
	private JList<String> _rightListBox;
	private buttonPanel _addButton;
	private buttonPanel _removeButton;
	private buttonPanel _okButton;
	private buttonPanel _cancelButton;
	
	private SelectableItem[] _model;
	private SelectableItem[] _oldModel;

	public listBoxesDialog(Frame parent, String title, SelectableItem[] model)
	{
		super(parent, title, true);
		_model = new SelectableItem[model.length];
		_oldModel = new SelectableItem[model.length];
		for(int index = 0; index < model.length; index ++)
		{
			_model[index] = model[index].clone();
			_oldModel[index] = model[index].clone();
		}
		
		// Set rootPane
		_rootPanel = new JPanel();
		_rootPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createEtchedBorder()));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		_rootPanel.setLayout(layout);
		setContentPane(_rootPanel);

		// Add left list box panel
		initLeftListBoxPanel();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_leftListBoxPanel, constraints);
		_rootPanel.add(_leftListBoxPanel);
		
		// Add middle button panel
		initMiddleButtonPanel();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_middleButtonPanel, constraints);
		_rootPanel.add(_middleButtonPanel);
		
		// Add right list box panel
		initRightListBoxPanel();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_rightListBoxPanel, constraints);
		_rootPanel.add(_rightListBoxPanel);
		
		// Add bottom button panel
		initBottomButtonPanel();
		constraints.gridx = 0;
		constraints.gridwidth = 3;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_bottomButtonPanel, constraints);
		_rootPanel.add(_bottomButtonPanel);
		
		pack();
		
		// Frame setting
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// Frame location setting
		int width = _rootPanel.getWidth();
		int height = _rootPanel.getHeight();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - width) / 2;
		int y = (screenSize.height - height) / 2;
		setLocation(x, y);
	}
	
	public void initLeftListBoxPanel()
	{
		_leftListBoxPanel = new JPanel();
		_leftListBoxPanel.setBorder(BorderFactory.createEmptyBorder());
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		_leftListBoxPanel.setLayout(layout);
		
		// Add left caption
		_leftCaption = new textCaptionPanel("Available", _captionDimx,
				_captionDimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_leftCaption, constraints);
		_leftListBoxPanel.add(_leftCaption);
		
		// Add left list box 
		_leftListBox = new JList<String>(SelectableItemOperator.createAvailableModel(_model));
		JScrollPane scrollPane = new JScrollPane(_leftListBox);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		scrollPane.setMaximumSize(new Dimension(_listBoxDimx, _listBoxDimy));
		scrollPane.setMinimumSize(new Dimension(_listBoxDimx, _listBoxDimy));
		scrollPane.setPreferredSize(new Dimension(_listBoxDimx, _listBoxDimy));
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(scrollPane, constraints);
		_leftListBoxPanel.add(scrollPane);
	}
	
	public void initMiddleButtonPanel()
	{
		_middleButtonPanel = new JPanel();
		_middleButtonPanel.setBorder(BorderFactory.createEmptyBorder());
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		_middleButtonPanel.setLayout(layout);
		
		// Add add button
		_addButton = new buttonPanel(">>", _middleButtonDimx,
				_middleButtonDimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_addButton, constraints);
		_middleButtonPanel.add(_addButton);
		_addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ListModel<String> availableModel = _leftListBox.getModel();
				int[] selectedIndices = _leftListBox.getSelectedIndices();
				for(int index = 0; index < selectedIndices.length; index ++)
					SelectableItemOperator.select(_model, (String) availableModel.getElementAt(selectedIndices[index]));
				_leftListBox.clearSelection();
				_leftListBox.setListData(SelectableItemOperator.createAvailableModel(_model));
				_rightListBox.clearSelection();
				_rightListBox.setListData(SelectableItemOperator.createSelectedModel(_model));
			}			
		});
		
		// Add remove button
		_removeButton = new buttonPanel("<<", _middleButtonDimx,
				_middleButtonDimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_removeButton, constraints);
		_middleButtonPanel.add(_removeButton);
		_removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ListModel<String> selectedModel = _rightListBox.getModel();
				int[] selectedIndices = _rightListBox.getSelectedIndices();
				for(int index = 0; index < selectedIndices.length; index ++)
					SelectableItemOperator.deselect(_model, (String) selectedModel.getElementAt(selectedIndices[index]));
				_leftListBox.clearSelection();
				_leftListBox.setListData(SelectableItemOperator.createAvailableModel(_model));
				_rightListBox.clearSelection();
				_rightListBox.setListData(SelectableItemOperator.createSelectedModel(_model));
			}			
		});
	}
	
	public void initRightListBoxPanel()
	{
		_rightListBoxPanel = new JPanel();
		_rightListBoxPanel.setBorder(BorderFactory.createEmptyBorder());
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		_rightListBoxPanel.setLayout(layout);
		
		// Add right caption
		_rightCaption = new textCaptionPanel("Selected", _captionDimx,
				_captionDimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_rightCaption, constraints);
		_rightListBoxPanel.add(_rightCaption);
		
		// Add right list box
		_rightListBox = new JList<String>(SelectableItemOperator.createSelectedModel(_model));
		JScrollPane scrollPane = new JScrollPane(_rightListBox);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		scrollPane.setMaximumSize(new Dimension(_listBoxDimx, _listBoxDimy));
		scrollPane.setMinimumSize(new Dimension(_listBoxDimx, _listBoxDimy));
		scrollPane.setPreferredSize(new Dimension(_listBoxDimx, _listBoxDimy));
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(scrollPane, constraints);
		_rightListBoxPanel.add(scrollPane);
	}	

	public void initBottomButtonPanel()
	{
		_bottomButtonPanel = new JPanel();
		_bottomButtonPanel.setBorder(BorderFactory.createEmptyBorder());
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		_bottomButtonPanel.setLayout(layout);
		
		// Add add button
		_okButton = new buttonPanel("OK", _bottomButtonDimx,
				_bottomButtonDimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_okButton, constraints);
		_bottomButtonPanel.add(_okButton);
		_okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
			}
		});
		
		// Add remove button
		_cancelButton = new buttonPanel("Cancel", _bottomButtonDimx,
				_bottomButtonDimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(_cancelButton, constraints);
		_bottomButtonPanel.add(_cancelButton);
		_cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				for(int index = 0; index < _model.length; index ++)
					_model[index] = _oldModel[index].clone();
				setVisible(false);
			}
		});
	}
	
	public SelectableItem[] getSelectedModel()
	{
		SelectableItem[] selectedModel = new SelectableItem[_model.length];
		for(int index = 0; index < _model.length; index ++)
			selectedModel[index] = _model[index].clone();
		return selectedModel;
	}
}
