package main;

import classes.*;
import interfaces.*;

import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;

/**
 *  Class that executes the results displayed in the scoreboard
 * @author Jaime Benavent Alba
 *
 */
public class ScoreBoard{
	
	private static Integer secondsRandom = 10;
	private static Integer secondsPrint = 5;
	private static Integer secondsVar = 50;
	
	private static String header = "Football World Cup";
	
	private static List<Team> teamsNotPlaying 	= null;
	private static List<Match> teamsPlaying 	= null;
	private static List<Match> summary 			= null;
	private static Thread tPrint 				= null;
	private static Thread tStartingMatches 		= null;

	public static void main(String[] arg) {
		
		teamsNotPlaying = createTeams();
		teamsPlaying = new ArrayList<Match>();
		summary = new ArrayList<Match>();
		
	    Thread tPrint = printMatches();
		tStartingMatches = startMatches();
		stopStartingMatches();
		tStartingMatches.start();
		tPrint.start();
		stopMatches();
		
		System.out.println(header);
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
			match.setLastUpdate(new Date());
		}
		return match;
	}
	
	/**
	 *  Method that goes through the list of matches and randomly searches for a team to add a goal to it
	 * @param List<Match>matches
	 */
	public static void goalMatch(List<Match> matches) {
		if(!matches.isEmpty()) {
			Random ran = new Random();
			int i = ran.nextInt(matches.size());
			Match match = matches.get(i);
			int j = ran.nextInt(2);
			int k = ran.nextInt(secondsVar);
			if(match.getTime() > 0) {
				if(j == 1) {				
					match.setHomeScore(match.getHomeScore() + 1);
					match.setLastUpdate(new Date());
					if(k >= 0 && k < 10) {
						match.setRemarks("VAR");
						if(k <= 5) {
							match.setRemarks("Disallowed Goal");
							cancelGoalMatch(match, match.getHomeTeam());
						}
						else {
							match.setRemarks("Goal");
						}
						
					}
				}
				else {
					match.setAwayScore(match.getAwayScore() + 1);
					match.setLastUpdate(new Date());
					if(k >= 40) {
						match.setRemarks("VAR");
						ShowData.printScoreMatch(match);
						if(k >= 45) {
							match.setRemarks("Disallowed Goal");
							ShowData.printScoreMatch(match);
							cancelGoalMatch(match, match.getAwayTeam());
						}
						else {
							match.setRemarks("Goal");
							ShowData.printScoreMatch(match);
						}
					}
				}
			}
			match.setRemarks("");
		}
	}
	
	/**
	 *  Method that annuls a goal in the event that it has gone up on the scoreboard and is not valid
	 * @param Match match
	 * @param Team team
	 */
	public static void cancelGoalMatch(Match match, Team team) {
		if(match != null) {
			if(match.getHomeTeam().getDescription().equals(team.getDescription())) {
				match.setHomeScore(match.getHomeScore() - 1);
				match.setLastUpdate(new Date());
				match.setRemarks("");
			}
			else {
				match.setAwayScore(match.getAwayScore() - 1);
				match.setLastUpdate(new Date());
				match.setRemarks("");
			}
		}
	}
	
	/**
	 *  Method that serves as a match timer and when it reaches the 90th minute the match ends
	 * @param List<match> matches
	 */
	public static void timeMatch(List<Match> matches) {
		for(int i = 0; i < matches.size(); i++) {			
			matches.get(i).setTime(matches.get(i).getTime() + 5);
			if(matches.get(i).getTime() == 45) {
				matches.get(i).setRemarks("Halftime");
				ShowData.printScoreMatch(matches.get(i));
			}
			else {
				matches.get(i).setRemarks("");
			}			
			if(matches.get(i).getTime() > 90) {
				summary.add(new MatchImp(matches.get(i).getHomeTeam(), matches.get(i).getAwayTeam(), matches.get(i).getHomeScore(), matches.get(i).getAwayScore(), matches.get(i).getTime()-5));
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
						ShowData.printScore(teamsPlaying);	
						timeMatch(teamsPlaying);
						Thread.sleep(1000*secondsPrint);
						if(teamsNotPlaying.isEmpty()) {
							goalMatch(teamsPlaying);
						}
					}
					if(teamsPlaying.isEmpty()) {
						ShowData.printScore(summary);
					}
				}
				catch (InterruptedException e){
					System.out.println("Error in printing matches: " + e.getMessage());
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
						int n = ran.nextInt(secondsRandom);
						Thread.sleep(1000*n);
						matchStarting = createMatch(teamsNotPlaying);
						int m = ran.nextInt(secondsRandom);
						Thread.sleep(1000*m);
						goalMatch(teamsPlaying);
					}
				}
				catch (InterruptedException e){
					System.out.println("Error in starting matches: " + e.getMessage());
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
					
				}
			}
		};
		return t;
	}
}
