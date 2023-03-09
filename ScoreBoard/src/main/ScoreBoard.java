package main;

import classes.*;
import interfaces.*;

import java.util.*;


public class ScoreBoard{
	
	private static Integer startingMatchesSeconds = 5;
	
	private static String header = "Football World Cup";
	private static Integer score = 0;
	
	private static List<Team> teamsNotPlaying = null;
	private static List<Match> teamsPlaying = null;
	private static Thread tStartingMatches = null;
	
	private static boolean scoreInitialized = false;
	
	public static void main(String[] arg) {		

		teamsNotPlaying = createTeams();
		teamsPlaying = new ArrayList<Match>();
		
	    Thread tPrint = printMatches();
		//Thread tPrint = new PrintMatches(teamsNotPlaying, teamsPlaying);
		tStartingMatches = startMatches();
		//tStartingMatches = new StartMatches(teamsNotPlaying, teamsPlaying);
		Thread tStopStartingMatches = stopStartingMatches();
		tStartingMatches.start();
		tPrint.start();
		//tStopStartingMatches.start();
		
		System.out.println("Test printing");
	}
	
	public static Integer goal() {
		int num = 1;
		return num;
	}
	
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
			match = new MatchImp(homeTeam, awayTeam, 0 , 0);
		}
		return match;
	}
	
	public static List<Team> createTeams() {
		
		List<Team> teams = new ArrayList<Team>();
		teams.add(new TeamImp("Mexico", 0));
		teams.add(new TeamImp("Canada", 0));
		teams.add(new TeamImp("Spain", 0));
		teams.add(new TeamImp("Brazil", 0));
		teams.add(new TeamImp("Germany", 0));
		teams.add(new TeamImp("France", 0));
		teams.add(new TeamImp("Uruguay", 0));
		teams.add(new TeamImp("Italy", 0));
		teams.add(new TeamImp("Argentina", 0));
		teams.add(new TeamImp("Australia", 0));
		
		return teams;
	}
	
	public static Thread printMatches() {
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
					while(!teamsPlaying.isEmpty())
					{					
						ShowData.printScore(teamsPlaying);					
						Thread.sleep(1000);
					}
				}
				catch (InterruptedException e){
					System.out.println("Error in printing matches: " + e.getMessage());
				}
			}
		};
		return t;
	}
	
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
						int n = ran.nextInt(startingMatchesSeconds);
						Thread.sleep(1000*10);
						matchStarting = createMatch(teamsNotPlaying);
					}
				}
				catch (InterruptedException e){
					System.out.println("Error in starting matches: " + e.getMessage());
				}
			}
			protected void finalize() throws Throwable {
				
			}
		};
		return t;
	}
	
	public static Thread stopStartingMatches() {
		Thread t = new Thread() {
			public void run() {
				try {
					if(teamsNotPlaying.isEmpty()) {
						tStartingMatches.stop();;
					}
					Thread.sleep(100);
				}
				catch (InterruptedException e){
					
				}
			}
		};
		return t;
	}
		
		//App scoreBoard = new App();

}
