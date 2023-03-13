package interfaces;

import java.util.Date;

import interfaces.Team;

// Interface to create the Match object
public interface Match {	

	String getHeaderScoreBoard();
	void setHeaderScoreBoard(String headerScoreBoard);	
	
	Team getHomeTeam();
	void setHomeTeam(Team team);
	
	Team getAwayTeam();
	void setAwayTeam(Team team);	
	
	Integer getHomeScore();
	void setHomeScore(Integer score);
	
	Integer getAwayScore();
	void setAwayScore(Integer score);	
	
	Integer getTime();
	void setTime(Integer time);
	
	String getRemarks();
	void setRemarks(String remarks);
	
	Date getLastUpdate();
	void setLastUpdate(Date lastUpdate);
	
}