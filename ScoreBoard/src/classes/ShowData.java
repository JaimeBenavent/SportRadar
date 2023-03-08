package classes;

import java.util.List;

import interfaces.Match;

public class ShowData {
	
	public static void printScore(List<Match> matches) {
		clearScreen();
		if(!matches.isEmpty()) {
			matches.sort((e1,e2)->e1.getLastUpdate().compareTo(e2.getLastUpdate()));
			for(Match match : matches) {
				if(match.getLastUpdate() != null) {
					System.out.println(match.getHomeTeam().getDescription() + " - " + match.getAwayTeam().getDescription() + ": " +
						match.getHomeScore() + " - " + match.getAwayScore());
				}
			}
		}
		
	}
	
	public static void clearScreen() {  
		for (int i = 0; i < 50; ++i) 
			System.out.println();
	}  

}
