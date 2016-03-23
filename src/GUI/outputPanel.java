package GUI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class outputPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font _font = new Font("Courier New", 1, 14);
	private JScrollPane _scrollPane;
	private JTextArea _textArea;
	
	public outputPanel()
	{
		_textArea = new JTextArea();
		_textArea.setLineWrap(false);
		_textArea.setEditable(false);
		_textArea.setFont(_font);
		_scrollPane = new JScrollPane(_textArea);
		_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(1, 1));
		add(_scrollPane);
	}
	
	public void setText(String text)
	{
		_textArea.setText(text);
	}
	
	public void appendText(String text)
	{
		_textArea.append(text);
	}

	public String getText()
	{
		return _textArea.getText();
	}
	
	public void disableAll()
	{
		_textArea.setEnabled(false);
	}
	
	public void enableAll()
	{
		_textArea.setEnabled(true);
	}
}
