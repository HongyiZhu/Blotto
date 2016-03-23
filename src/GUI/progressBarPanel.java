package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class progressBarPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar _progressBar;
	private int _min = 0;
	private int _max = 100;

	public progressBarPanel(int barDimx, int dimy, Font font)
	{
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		
		_progressBar = new JProgressBar(_min, _max);
		_progressBar.setFont(font);
		_progressBar.setMinimumSize(new Dimension(barDimx, dimy));
		_progressBar.setMaximumSize(new Dimension(barDimx, dimy));
		_progressBar.setPreferredSize(new Dimension(barDimx, dimy));
		_progressBar.setStringPainted(true);
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_START;
		layout.setConstraints(_progressBar, constraints);
		add(_progressBar);
	}
	
	public void setProgress(int progress)
	{
		_progressBar.setValue(progress);
	}
}
