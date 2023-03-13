package classes;

import java.util.List;

import interfaces.Match;

/**
 *  Class to display the results
 * @author Jaime Benavent Alba
 *
 */
public class ShowData {
	
	/**
	 *  Method to display the results of the matches on the screen
	 * @param List<Match> matches
	 */
	public static void printScore(List<Match> matches) {
		String print = "";
		clearScreen();
		if(!matches.isEmpty()) {
			matches.sort((e1,e2)->e2.getLastUpdate().compareTo(e1.getLastUpdate()));
			for(Match match : matches) {
				if(match.getLastUpdate() != null) {
					if(match.getRemarks() == null) match.setRemarks("");
					print = match.getHomeTeam().getDescription() + " - " + match.getAwayTeam().getDescription() + ": " +
							match.getHomeScore() + " - " + match.getAwayScore() +  " - Time: " + match.getTime() + " " + match.getRemarks();
					System.out.println(print);
				}
			}
		}
	}
	
	/**
	 *  Method to display the result of a match on the screen
	 * @param Match match
	 * @return String
	 */
	public static String printScoreMatch(Match match) {
		String print = "";		
		if(match.getRemarks() == null) match.setRemarks("");
		print = match.getHomeTeam().getDescription() + " - " + match.getAwayTeam().getDescription() + ": " +
				match.getHomeScore() + " - " + match.getAwayScore() +  " - Time: " + match.getTime() + " " + match.getRemarks();
		System.out.println(print);
		return print;
	}
	
	/**
	 *  Method to separate the results with line breaks each time they are displayed on the screen
	 */
	public static void clearScreen() {  
		for (int i = 0; i < 2; ++i) 
			System.out.println();
	}  

}
