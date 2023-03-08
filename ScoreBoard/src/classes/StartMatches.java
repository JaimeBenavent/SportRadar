package classes;

import java.util.List;
import java.util.Random;

import interfaces.Match;
import interfaces.Team;
import main.ScoreBoard;

public class StartMatches extends Thread {
	public List<Team> teamsNotPlaying;
	public List<Match> teamsPlaying;
	

    public StartMatches(List<Team> teamsNotPlaying, List<Match> teamsPlaying) {
        this.teamsNotPlaying = teamsNotPlaying;
        this.teamsPlaying = teamsPlaying;
    }
    
    public void run() {
    	synchronized (teamsNotPlaying) {
    		try {
				Match matchStarting = ScoreBoard.createMatch(teamsNotPlaying);
				if(matchStarting != null){
					teamsPlaying.add(matchStarting);
				}
				//Random ran = new Random();
				//int n = ran.nextInt(startingMatchesSeconds);
				Thread.sleep(100*5);
			}
			catch (InterruptedException e){
				
			}
		}
    }
}


