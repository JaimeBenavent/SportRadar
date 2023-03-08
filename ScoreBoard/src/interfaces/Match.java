package interfaces;

import java.util.Date;

import interfaces.Team;

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
	
	String getRemarks();
	void setRemarks(String remarks);
	
	Date getLastUpdate();
	void setLastUpdate(Date lastUpdate);
	
}