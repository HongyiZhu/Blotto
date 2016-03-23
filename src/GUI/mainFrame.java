package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class mainFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private ctrlPanel _ctrlPanel;
	private outputPanel _outputPanel;
	private busyDialog _busy;

	public mainFrame(String title)
	{
		super(title);
		_busy = new busyDialog(this, "Please wait...");

		/*
		 * Initialize the main frame
		 */
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.getWidth() * 1.0);
		int height = (int) (screenSize.getHeight() * 0.95);
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setExtendedState(MAXIMIZED_BOTH);
		
		// Set the base panel
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		
		// Initialize the control area
		_ctrlPanel = new ctrlPanel(this);
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.VERTICAL;
		layout.setConstraints(_ctrlPanel, constraints);
		add(_ctrlPanel);
		
		// Initialize visualization area
		_outputPanel = new outputPanel();
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		layout.setConstraints(_outputPanel, constraints);
		add(_outputPanel);
		
		_ctrlPanel.enableAll();
		setVisible(true);
	}
	
	public busyDialog getBusyDialog()
	{
		return _busy;
	}
	
	public outputPanel getOutputPanel()
	{
		return _outputPanel;
	}
}
