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
import javax.swing.JPanel;

public class busyDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int BUSY = 0;
	public static final int PROGRESS = 1;
	
	private int _messageDimx = 200;
	private int _progressBarDimx = 200;
	private int _buttonDimx = 100;
	private int _dimy = 25;
	private Font _font = new Font("Tahoma", 1, 11);
	private JPanel _rootPanel;
	private textCaptionPanel _message;
	private progressBarPanel _progressBar;
	private buttonPanel _cancelButton;
	private boolean _cancelled = false;

	public busyDialog(Frame parent, String title)
	{
		super(parent, title, true);

		// Set rootPane
		_rootPanel = new JPanel();
		_rootPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createEtchedBorder()));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		_rootPanel.setLayout(layout);
		setContentPane(_rootPanel);

		// Add text
		_message = new textCaptionPanel("", _messageDimx,
				_dimy, JLabel.CENTER, _font);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		layout.setConstraints(_message, constraints);
		_rootPanel.add(_message);

		// Add progress bar
		_progressBar = new progressBarPanel(_progressBarDimx, _dimy, _font);
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;;
		constraints.anchor = GridBagConstraints.PAGE_START;
		layout.setConstraints(_progressBar, constraints);
		_rootPanel.add(_progressBar);

		// Add cancel button
		_cancelButton = new buttonPanel("Cancel", _buttonDimx,
				_dimy, JLabel.CENTER, _font);
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		layout.setConstraints(_cancelButton, constraints);
		_cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				_cancelled = true;
				_cancelButton.disable();
			}
		});
		_rootPanel.add(_cancelButton);

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

	public void setMessage(String message)
	{
		_message.setText(message);
	}

	public void reset(int mode)
	{
		if(mode == busyDialog.BUSY)
		{
			_progressBar.setProgress(0);
			_progressBar.setVisible(false);
			_cancelButton.setVisible(false);
		}
		else
		{
			_progressBar.setProgress(0);
			_progressBar.setVisible(true);
			_cancelButton.setVisible(true);
		}
		pack();
		int width = _rootPanel.getWidth();
		int height = _rootPanel.getHeight();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - width) / 2;
		int y = (screenSize.height - height) / 2;
		setLocation(x, y);
		_cancelled = false;
		_cancelButton.enable();
	}

	public boolean isCancelled()
	{
		return _cancelled;
	}
	
	public boolean setProgress(String message, int progress)
	{
		_message.setText(message);
		_progressBar.setProgress(progress);
		return _cancelled;
	}
}
