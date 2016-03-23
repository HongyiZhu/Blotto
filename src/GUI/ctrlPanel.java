package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import Game.table;
import Model.SelectableItem;
import Model.SelectableItemOperator;
import Score.score;
import Strategies.strategyManager;
import Util.myFile;
import Util.myMath;

public class ctrlPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PVP = "P1 vs. P2";
	private static final String TOUR = "Tournament";
	private mainFrame _superFrame;
	private int _dimx = 120;
	private int _dimy = 25;
	private Font _font = new Font("Lucida Fax", 1, 11);

	// GUI components
	private horizontalSepPanel _separator;
	private comboBoxPanel _playerSelector1;
	private comboBoxPanel _playerSelector2;
	private buttonPanel _playerSelectionButton;
	private radioButtonGroupPanel _tournamentSelector;
	private comboBoxPanel _cardNumSelector;
	private comboBoxPanel _upCardNumSelector;
	private comboBoxPanel _roundNumSelector;
	private buttonPanel _playButton;
	private buttonPanel _saveButton;
	private String[] _tournamentChoice = new String[]{PVP, TOUR};
	private String[] _cardNumChoice = new String[]{"13"};
	private String[] _upCardNumChoice = new String[]{"3"};
	private String[] _roundNumChoice = new String[]{"1", "2", "3", "5",
			"10", "20", "30", "50", "100", "200", "400", "500", "1000"};
	private SelectableItem[] _selectedPlayers;
	private ArrayList<String> _tournamentResults = null;

	public ctrlPanel(mainFrame superFrame)
	{
		_superFrame = superFrame;
		String[] players = strategyManager.getStrategyList();
		_selectedPlayers = new SelectableItem[players.length];
		for(int index = 0; index < players.length; index ++)
			_selectedPlayers[index] = new SelectableItem(players[index], true);

		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createEtchedBorder()));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		// Add separator
		_separator = new horizontalSepPanel(_dimx * 2);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_separator, constraints);
		add(_separator);		

		// Add tournament selector
		_tournamentSelector = new radioButtonGroupPanel(
				_tournamentChoice, 0, _dimx - _dimy, _dimy, _dimy,
				radioButtonGroupPanel.HORIZONTAL, JLabel.LEFT, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_tournamentSelector, constraints);
		add(_tournamentSelector);
		_tournamentSelector.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if(_tournamentSelector.selectedItem().equals(PVP))
				{
					_playerSelector1.enable();
					_playerSelector2.enable();
					_playerSelectionButton.disable();
					_saveButton.disable();
				}
				else
				{
					_playerSelector1.disable();
					_playerSelector2.disable();
					_playerSelectionButton.enable();
					_saveButton.enable();
				}
			}
		});

		// Add player selector 1
		_playerSelector1 = new comboBoxPanel(
				"Player 1", strategyManager.getStrategyList(),
				_dimx, _dimx, _dimy, JLabel.LEFT, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_playerSelector1, constraints);
		add(_playerSelector1);

		// Add player selector 2
		_playerSelector2 = new comboBoxPanel(
				"Player 2", strategyManager.getStrategyList(),
				_dimx, _dimx, _dimy, JLabel.LEFT, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_playerSelector2, constraints);
		add(_playerSelector2);

		// Add set tournament button
		_playerSelectionButton = new buttonPanel("Select Tournament Players",
				2 * _dimx, _dimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_playerSelectionButton, constraints);
		add(_playerSelectionButton);
		_playerSelectionButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				listBoxesDialog dialog = new listBoxesDialog(_superFrame,
						"Select Tournament Players", _selectedPlayers);
				dialog.setVisible(true);
				_selectedPlayers = dialog.getSelectedModel();
				dialog.dispose();
			}
		});

		// Add separator
		_separator = new horizontalSepPanel(_dimx * 2);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_separator, constraints);
		add(_separator);

		// Add card number selector
		_cardNumSelector = new comboBoxPanel(
				"Cards per Suit", _cardNumChoice,
				_dimx, _dimx, _dimy, JLabel.LEFT, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_cardNumSelector, constraints);
		add(_cardNumSelector);

		// Add up card number selector
		_upCardNumSelector = new comboBoxPanel(
				"Num. of Up Cards", _upCardNumChoice,
				_dimx, _dimx, _dimy, JLabel.LEFT, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_upCardNumSelector, constraints);
		add(_upCardNumSelector);

		// Add round selector
		_roundNumSelector = new comboBoxPanel(
				"Num. of Rounds", _roundNumChoice,
				_dimx, _dimx, _dimy, JLabel.LEFT, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_roundNumSelector, constraints);
		add(_roundNumSelector);

		// Add separator
		_separator = new horizontalSepPanel(_dimx * 2);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_separator, constraints);
		add(_separator);

		// Add play button
		_playButton = new buttonPanel("Start the Game!",
				2 * _dimx, _dimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_playButton, constraints);
		add(_playButton);
		_playButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				playButtonClicked();
			}
		});

		// Add separator
		_separator = new horizontalSepPanel(_dimx * 2);
		constraints.weightx = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_separator, constraints);
		add(_separator);

		// Add export button
		_saveButton = new buttonPanel("Save Tournament Results",
				2 * _dimx, _dimy, JLabel.CENTER, _font);
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(_saveButton, constraints);
		add(_saveButton);
		_saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(_tournamentResults == null)
				{
					JOptionPane.showMessageDialog(_superFrame,
							"No result available. Please run tournament first!");
				}
				else
				{
					String filePath;
					fileChooser fc = new fileChooser();
					FileFilter ff = new FileFilter() {
						@Override
						public boolean accept(File f) {
							if (f.getName().toLowerCase().endsWith(".csv"))
								return true;
							return false;
						}
						@Override
						public String getDescription() {
							return "Comma separated values (*.csv)";
						}
					};
					fc.setFileFilter(ff);
					fc.setSelectedFile(new File("Tournament Results.csv"));
					int returnVal = fc.showSaveDialog(_superFrame);
					if(returnVal == fileChooser.APPROVE_OPTION)
					{
						filePath = fc.getSelectedFile().getAbsolutePath();
						try {
							myFile.deleteFile(filePath);
							myFile.writeFile(_tournamentResults, filePath);
						} catch (Exception exception) {
							JOptionPane.showMessageDialog(_superFrame, exception.getMessage());
							return;
						}
						JOptionPane.showMessageDialog(_superFrame, "File saved.");
					}
					else ;
				}
			}
		});

		disableAll();
	}

	private void playButtonClicked()
	{
		if(_tournamentSelector.selectedItem().equals(PVP))
		{
			disableAll();
			_superFrame.getBusyDialog().reset(busyDialog.PROGRESS);
			(new pvpThread(_superFrame)).start();
			_superFrame.getBusyDialog().setVisible(true);
		}
		else
		{
			disableAll();
			_superFrame.getBusyDialog().reset(busyDialog.PROGRESS);
			(new tournamentThread(_superFrame)).start();
			_superFrame.getBusyDialog().setVisible(true);
		}
	}

	public void disableAll()
	{
		_playerSelector1.disable();
		_playerSelector2.disable();
		_playerSelectionButton.disable();
		_tournamentSelector.disable();
		_cardNumSelector.disable();
		_roundNumSelector.disable();
		_playButton.disable();
		_saveButton.disable();
	}

	public void enableAll()
	{
		if(_tournamentSelector.selectedItem().equals(PVP))
		{
			_playerSelector1.enable();
			_playerSelector2.enable();
		}
		else
			_playerSelectionButton.enable();
		_tournamentSelector.enable();
		_cardNumSelector.enable();
		_roundNumSelector.enable();
		_playButton.enable();
		if(_tournamentSelector.selectedItem().equals(TOUR))
			_saveButton.enable();
	}

	private class pvpThread extends Thread
	{
		private mainFrame _superFrame;

		public pvpThread(mainFrame superFrame)
		{
			_superFrame = superFrame;
		}

		public void run()
		{
			try {
				int numOfCards = Integer.parseInt(_cardNumSelector.getSelectedItem());
				int numOfUpcards = Integer.parseInt(_upCardNumSelector.getSelectedItem());
				int numOfRounds = Integer.parseInt(_roundNumSelector.getSelectedItem());
				_tournamentResults = null;
				_superFrame.getOutputPanel().setText(null);
				String player1 = _playerSelector1.getSelectedItem();
				String player2 = _playerSelector2.getSelectedItem();
				table t = new table(numOfCards, numOfUpcards, numOfRounds,
						strategyManager.getStrategy(player1),
						strategyManager.getStrategy(player2), _superFrame);
				t.play(true);
			} catch(Exception e) {
				_tournamentResults = null;
				JOptionPane.showMessageDialog(_superFrame, e.getMessage());
			} finally {
				enableAll();
				_superFrame.getBusyDialog().setVisible(false);
			}
		}
	}

	private class tournamentThread extends Thread
	{
		private mainFrame _superFrame;

		public tournamentThread(mainFrame superFrame)
		{
			_superFrame = superFrame;
		}

		public void run()
		{
			try {
				int numOfCards = Integer.parseInt(_cardNumSelector.getSelectedItem());
				int numOfUpcards = Integer.parseInt(_upCardNumSelector.getSelectedItem());
				int numOfRounds = Integer.parseInt(_roundNumSelector.getSelectedItem());
				_tournamentResults = null;
				_superFrame.getOutputPanel().setText(null);
				String[] selectedPlayers = SelectableItemOperator.createSelectedModel(_selectedPlayers);
				if(selectedPlayers.length < 2)
					throw new Exception("Please select at least 2 players!");

				// Initialize tournament results table
				_tournamentResults = new ArrayList<String>();
				_tournamentResults.add("Player 1,Points 1,Wins 1,Ties 1,Player 2,Points 2,Wins 2,Ties 2");

				// Initialize the hash table of total score
				Hashtable<String, Double> cumulatedScoreTable = new Hashtable<String, Double>();
				for(int index = 0; index < selectedPlayers.length; index ++)
					cumulatedScoreTable.put(selectedPlayers[index], new Double(0.0));

				// Initialize the hash table of total wins
				Hashtable<String, Integer> cumulatedWinsTable = new Hashtable<String, Integer>();
				for(int index = 0; index < selectedPlayers.length; index ++)
					cumulatedWinsTable.put(selectedPlayers[index], new Integer(0));

				// Initialize the hash table of total ties
				Hashtable<String, Integer> cumulatedTiesTable = new Hashtable<String, Integer>();
				for(int index = 0; index < selectedPlayers.length; index ++)
					cumulatedTiesTable.put(selectedPlayers[index], new Integer(0));

				int current = 0;
				int total = myMath.choose(selectedPlayers.length, 2);
				for(int index1 = 0; index1 < selectedPlayers.length; index1 ++)
				{
					for(int index2 = index1 + 1; index2 < selectedPlayers.length; index2 ++)
					{
						String player1 = selectedPlayers[index1];
						String player2 = selectedPlayers[index2];
						int progress = (int) (100.00 * (double) current / (double) total);
						_superFrame.getBusyDialog().setProgress(player1 + " vs. " + player2 + "...", progress);
						table t = new table(numOfCards, numOfUpcards, numOfRounds,
								strategyManager.getStrategy(player1),
								strategyManager.getStrategy(player2), _superFrame);
						score s = t.play(false);

						// Register the result of each game
						_tournamentResults.add(s._player1Name + "," + s._player1TotalScore + "," + s._player1TotalWins + "," + s._player1TotalTies + ","
								+ s._player2Name + "," + s._player2TotalScore + "," + s._player2TotalWins + "," + s._player2TotalTies);

						// Cumulate the score of each involved player
						cumulatedScoreTable.put(player1, new Double(cumulatedScoreTable.get(player1).doubleValue() + s._player1TotalScore));
						cumulatedScoreTable.put(player2, new Double(cumulatedScoreTable.get(player2).doubleValue() + s._player2TotalScore));

						// Cumulate the wins of each involved player
						cumulatedWinsTable.put(player1, new Integer(cumulatedWinsTable.get(player1).intValue() + s._player1TotalWins));
						cumulatedWinsTable.put(player2, new Integer(cumulatedWinsTable.get(player2).intValue() + s._player2TotalWins));

						// Cumulate the ties of each involved player
						cumulatedTiesTable.put(player1, new Integer(cumulatedTiesTable.get(player1).intValue() + s._player1TotalTies));
						cumulatedTiesTable.put(player2, new Integer(cumulatedTiesTable.get(player2).intValue() + s._player2TotalTies));

						current ++;
					}
				}

				// Sort the total score in descending order
				ArrayList<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(cumulatedScoreTable.entrySet());
				Collections.sort(list, new Comparator<Map.Entry<String, Double>>(){
					@Override
					public int compare(Map.Entry<String, Double> arg0, Map.Entry<String, Double> arg1)
					{
						if(arg0.getValue() > arg1.getValue())
							return 1;
						else if(arg0.getValue() < arg1.getValue())
							return -1;
						else return 0;
					}
				});

				// Append the total score to the end of tournament results table
				_tournamentResults.add(" , , , , , , , , , , , ");
				_tournamentResults.add("Player,Points,Wins,Ties, , , , , , , , ");
				for(int index = 0; index < list.size(); index ++)
				{
					String player = list.get(index).getKey();
					Double score = list.get(index).getValue();
					Integer wins = cumulatedWinsTable.get(player);
					Integer ties = cumulatedTiesTable.get(player);
					_tournamentResults.add(player + "," + score.toString() + "," + wins.toString()
							+ "," + ties.toString() + ", , , , , , , , ");
				}
			} catch(Exception e) {
				_tournamentResults = null;
				JOptionPane.showMessageDialog(_superFrame, e.getMessage());
			} finally {
				enableAll();
				_superFrame.getBusyDialog().setVisible(false);
			}
		}
	}
}
