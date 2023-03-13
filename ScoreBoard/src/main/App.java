package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import classes.MatchImp;
import classes.TeamImp;

import interfaces.Match;
import interfaces.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Jaime Benavent Alba
 *
 */
public class App {

	private JFrame frame 				= null;
	private JLabel lblImage 			= null;
	private static JLabel lblHomeTeam 	= null;
	private static JLabel lblAwayTeam	= null;
	private static JLabel lblHomeScore 	= null;
	private static JLabel lblAwayScore 	= null;
	private static JLabel lblRemarks 	= null;
	private static JTextArea textArea 	= null;
	
	private static Integer secondsPrint = 10;
	private static Integer secondsGoal = 30;
	private static Integer secondsVar = 50;
	
	private static String title = "Football World Cup";
	
	private static List<Team> teamsNotPlaying 	= null;
	private static List<Match> teamsPlaying 	= null;
	private static List<Match> summary 			= null;
	private static Thread tPrint 				= null;
	private static Thread tStartingMatches 		= null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App app = new App();
					app.frame.setVisible(true);
					
					teamsNotPlaying = createTeams();
					teamsPlaying = new ArrayList<Match>();
					summary = new ArrayList<Match>();
					
				    Thread tPrint = printMatches();
					tStartingMatches = startMatches();
					
					stopStartingMatches();	
					
					tStartingMatches.start();
					tPrint.start();
					
					stopMatches();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(title);
		frame.setBounds(0, 0, 592, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		JLayeredPane layered = new JLayeredPane();
		
		ImageIcon image = new ImageIcon(getClass().getResource("Sportradar.png"));
		
		textArea = new JTextArea();
		textArea.setTabSize(5);
		textArea.setRows(5);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 20));
		textArea.setBounds(48, 319, 469, 166);
		layered.add(textArea);
		
		lblRemarks = new JLabel("");
		layered.setLayer(lblRemarks, 0);
		lblRemarks.setForeground(Color.WHITE);
		lblRemarks.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblRemarks.setBackground(Color.BLUE);
		lblRemarks.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemarks.setBounds(48, 249, 469, 49);
		lblRemarks.setOpaque(true);
		layered.add(lblRemarks);
		
		lblAwayScore = new JLabel("0");
		lblAwayScore.setForeground(Color.WHITE);
		lblAwayScore.setBackground(Color.BLUE);
		lblAwayScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayScore.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblAwayScore.setBounds(350, 158, 113, 80);
		lblAwayScore.setOpaque(true);
		layered.add(lblAwayScore);
		
		lblHomeScore = new JLabel("0");
		lblHomeScore.setForeground(Color.WHITE);
		lblHomeScore.setBackground(Color.BLUE);
		lblHomeScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomeScore.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblHomeScore.setBounds(102, 158, 113, 80);
		lblHomeScore.setOpaque(true);
		layered.add(lblHomeScore);
		
		lblAwayTeam = new JLabel("");
		lblAwayTeam.setForeground(Color.WHITE);
		lblAwayTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayTeam.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblAwayTeam.setBackground(Color.BLUE);
		lblAwayTeam.setBounds(296, 98, 221, 49);
		lblAwayTeam.setOpaque(true);
		layered.add(lblAwayTeam);
		
		lblHomeTeam = new JLabel("");
		lblHomeTeam.setForeground(Color.WHITE);
		lblHomeTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomeTeam.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblHomeTeam.setBackground(Color.BLUE);
		lblHomeTeam.setBounds(48, 98, 221, 49);
		lblHomeTeam.setOpaque(true);
		layered.add(lblHomeTeam);
		
		lblImage = new JLabel();
		lblImage.setIcon(image);
		lblImage.setSize(image.getIconWidth(), image.getIconHeight());
		lblImage.setOpaque(true);
		layered.add(lblImage);	
		
		frame.getContentPane().add(layered);
	}
	
	/**
	 *  Method to create a list of teams added by hand
	 * @return List<Team>
	 */
	public static List<Team> createTeams() {
		
		List<Team> teams = new ArrayList<Team>();
		teams.add(new TeamImp("Mexico"));
		teams.add(new TeamImp("Canada"));
		teams.add(new TeamImp("Spain"));
		teams.add(new TeamImp("Brazil"));
		teams.add(new TeamImp("Germany"));
		teams.add(new TeamImp("France"));
		teams.add(new TeamImp("Uruguay"));
		teams.add(new TeamImp("Italy"));
		teams.add(new TeamImp("Argentina"));
		teams.add(new TeamImp("Australia"));
		
		return teams;
	}
	
	/**
	 *  Method to create the pairing of matches initialized to zero
	 * @param List<Team> teams
	 * @return Match
	 */
	public static Match createMatch(List<Team> teams) {
		Match match = null;
		if(!teams.isEmpty()) {
			Random ran = new Random();
			int n = ran.nextInt(teams.size());
			
			Team homeTeam = teams.get(n);
			teams.remove(n);
			n = ran.nextInt(teams.size());
			
			Team awayTeam = teams.get(n);
			teams.remove(n);
			
			match = new MatchImp(homeTeam, awayTeam, 0 , 0, 0);
			printScoreMatch(match);
			
			match.setLastUpdate(new Date());
			
		}
		return match;
	}
	
	/**
	 *  Method that goes through the list of matches and randomly searches for a team to add a goal to it
	 * @param List<Match> matches
	 */
	public static void goalMatch(List<Match> matches) {
		
		if(!matches.isEmpty()) {
			Random ran = new Random();
			int i = ran.nextInt(matches.size());			
			int j = ran.nextInt(2);
			int k = ran.nextInt(secondsVar);
			
			Match match = matches.get(i);
			match.setRemarks("Min: " + match.getTime());
			
			if(match.getTime() > 0) {
				if(j == 1) {				
					match.setHomeScore(match.getHomeScore() + 1);
					printScoreMatch(match);
					match.setLastUpdate(new Date());
					
					if(k >= 0 && k < 10) {
						match.setRemarks("VAR");
						printScoreMatch(match);
						
						if(k <= 5) {
							match.setRemarks("VAR - Cancel Goal");
							printScoreMatch(match);
							cancelGoalMatch(match, match.getHomeTeam());
						}
						else {
							match.setRemarks("VAR - Goal");
							printScoreMatch(match);
						}
						
					}
				}
				else {
					match.setAwayScore(match.getAwayScore() + 1);
					printScoreMatch(match);
					match.setLastUpdate(new Date());
					
					if(k >= 40) {
						match.setRemarks("VAR");
						printScoreMatch(match);
						
						if(k >= 45) {
							match.setRemarks("VAR - Cancel Goal");
							printScoreMatch(match);
							cancelGoalMatch(match, match.getAwayTeam());
						}
						else {
							match.setRemarks("VAR - Goal");
							printScoreMatch(match);
						}
					}
				}
			}
		}
	}
	
	/** 
	 * Method that annuls a goal in the event that it has gone up on the scoreboard and is not valid
	 * @param Match match
	 * @param Team team
	 */
	public static void cancelGoalMatch(Match match, Team team) {
		
		if(match != null) {
			match.setRemarks("Min: " + match.getTime());
			
			if(match.getHomeTeam().getDescription().equals(team.getDescription())) {
				match.setHomeScore(match.getHomeScore() - 1);
				match.setLastUpdate(new Date());
				printScoreMatch(match);
			}
			else {
				match.setAwayScore(match.getAwayScore() - 1);
				match.setLastUpdate(new Date());
				printScoreMatch(match);
			}
		}
	}
	
	/**
	 *  Method that serves as a match timer and when it reaches the 90th minute the match ends
	 * @param List<Match> matches
	 */
	public static void timeMatch(List<Match> matches) {
		
		for(int i = 0; i < matches.size(); i++) {			
			matches.get(i).setTime(matches.get(i).getTime() + 5);
			matches.get(i).setRemarks("Min: " + matches.get(i).getTime());
			
			if(matches.get(i).getTime() == 45) {
				matches.get(i).setRemarks("Halftime");
				printScoreMatch(matches.get(i));
			}
			
			if(matches.get(i).getTime() == 90) {
				matches.get(i).setRemarks("Finish");
				summary.add(new MatchImp(matches.get(i).getHomeTeam(), matches.get(i).getAwayTeam(), matches.get(i).getHomeScore(), matches.get(i).getAwayScore(), matches.get(i).getTime()));
				matches.get(i).setLastUpdate(new Date());
				teamsPlaying.remove(i);
			}
		}
	}
	
	/**
	 *  Thread to print the list of teams on the screen, start the match time and add the goals to the teams of each match, and when they finish it adds them to a summary list.
	 * @return Thread
	 */
	public static Thread printMatches() {
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(1000*secondsPrint);
					while(!teamsPlaying.isEmpty())
					{					
						printScore(teamsPlaying);	
						timeMatch(teamsPlaying);
						Thread.sleep(1000*secondsPrint);
						if(teamsNotPlaying.isEmpty()) {
							goalMatch(teamsPlaying);
						}
					}
					if(teamsPlaying.isEmpty()) {
						printScore(summary);
					}
				}
				catch (InterruptedException e){
					System.out.println("Error in printMatches: " + e.getMessage());
				}
			}
		};
		return t;
	}
	
	/**
	 *  Thread in charge of initiating random matches from time to time and adding a goal if the match has already started
	 * @return Thread
	 */
	public static Thread startMatches() {
		Thread t = new Thread() {
			public void run() {
				try {
					Match matchStarting = createMatch(teamsNotPlaying);
					while(matchStarting!=null)
					{
						if(matchStarting != null){
							teamsPlaying.add(matchStarting);
						}
						Random ran = new Random();
						int n = ran.nextInt(secondsPrint);
						Thread.sleep(1000*n);						
						matchStarting = createMatch(teamsNotPlaying);
						
						int m = ran.nextInt(secondsGoal);
						Thread.sleep(1000*m);
						goalMatch(teamsPlaying);
					}
				}
				catch (InterruptedException e){
					System.out.println("Error in startMatches: " + e.getMessage());
				}
			}
		};
		return t;
	}
	
	/**
	 *  Thread in charge of stopping the thread that initializes the matches when there are none left to start
	 * @return Thread
	 */
	public static Thread stopStartingMatches() {
		Thread t = new Thread() {
			public void run() {
				try {
					if(teamsNotPlaying.isEmpty()) {
						tStartingMatches.stop();
					}
					Thread.sleep(100);
				}
				catch (InterruptedException e){
					System.out.println("Error in stopStartingMatches: " + e.getMessage());
				}
			}
		};
		return t;
	}
	
	/**
	 *  Thread in charge of for all matches when they are over
	 * @return Thread
	 */
	public static Thread stopMatches() {
		Thread t = new Thread() {
			public void run() {
				try {
					if(teamsPlaying.isEmpty()) {
						tPrint.stop();
					}
					Thread.sleep(100);
				}
				catch (InterruptedException e){
					System.out.println("Error in stopMatches: " + e.getMessage());
				}
			}
		};
		return t;
	}
	
	public static void printScore(List<Match> matches) {
		String print = "";
		String printTextArea = "";
		//clearScreen();
		if(!matches.isEmpty()) {
			matches.sort((e1,e2)->e1.getLastUpdate().compareTo(e2.getLastUpdate()));
			for(Match match : matches) {
				if(match.getLastUpdate() != null) {
					if(match.getRemarks() == null) match.setRemarks("Min: " + match.getTime());
					print = match.getHomeTeam().getDescription() + " - " + match.getAwayTeam().getDescription() + ": " +
							match.getHomeScore() + " - " + match.getAwayScore() +  " -  " + "Min: " + match.getTime();
					
					lblHomeTeam.setText(match.getHomeTeam().getDescription());
					lblAwayTeam.setText(match.getAwayTeam().getDescription());
					lblHomeScore.setText(match.getHomeScore().toString());
					lblAwayScore.setText(match.getAwayScore().toString());
					if(lblRemarks.getText().equals("VAR") ||  lblRemarks.getText().equals("VAR - Goal") || lblRemarks.getText().equals("VAR - Cancel Goal") || lblRemarks.getText().equals("Halftime") || lblRemarks.getText().equals("Finish")) {
						lblRemarks.setText(match.getRemarks());
					}
					else {
						lblRemarks.setText("Min: " + match.getTime());
					}
					
					System.out.println(print);
					
					printTextArea = printTextArea + "\n" + match.getHomeTeam().getDescription() + ": " + match.getHomeScore() + " - " +
							match.getAwayTeam().getDescription() + ": " + match.getAwayScore();
					if(teamsPlaying.isEmpty()) {
						textArea.setText(printTextArea);
					}
				}
			}
		}
	}
	
	/**
	 *  Method to display the result of a match on the screen
	 * @param Match match
	 * @return String
	 */
	public static void printScoreMatch(Match match) {
		String print = "";		
		if(match.getRemarks() == null) match.setRemarks("");
		print = match.getHomeTeam().getDescription() + " - " + match.getAwayTeam().getDescription() + ": " +
				match.getHomeScore() + " - " + match.getAwayScore() +  " - " + "Min: " + match.getTime();
		
		lblHomeTeam.setText(match.getHomeTeam().getDescription());
		lblAwayTeam.setText(match.getAwayTeam().getDescription());
		lblHomeScore.setText(match.getHomeScore().toString());
		lblAwayScore.setText(match.getAwayScore().toString());
		if(lblRemarks.getText().equals("VAR") ||  lblRemarks.getText().equals("VAR - Goal") || lblRemarks.getText().equals("VAR - Cancel Goal") || lblRemarks.getText().equals("Halftime") || lblRemarks.getText().equals("Finish")) {
			lblRemarks.setText(match.getRemarks());
		}
		else {
			lblRemarks.setText("Min: " + match.getTime());
		}
		
		System.out.println(print);
	}
	
	/**
	 *  Method to separate the results with line breaks each time they are displayed on the screen
	 */
	public static void clearScreen() {  
		for (int i = 0; i < 2; ++i) 
			System.out.println();
	}  
}
