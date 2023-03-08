package classes;

import java.util.List;

import interfaces.Match;
import interfaces.Team;

public class PrintMatches extends Thread {
	public List<Team> teamsNotPlaying;
	public List<Match> teamsPlaying;
	

    public PrintMatches(List<Team> teamsNotPlaying, List<Match> teamsPlaying) {
        this.teamsNotPlaying = teamsNotPlaying;
        this.teamsPlaying = teamsPlaying;
    }
    
    public void run() {
    	synchronized (teamsNotPlaying) {
    		try {
    			ShowData.printScore(teamsPlaying);
				Thread.sleep(500);
			}
			catch (InterruptedException e){
				
			}
		}
    }
}
