package classes;

import java.util.Date;

import interfaces.Team;

public class MatchImp implements interfaces.Match {
	
	private String headerScoreBoard	= null;
	private Team homeTeam 			= null;
	private Team awayTeam 			= null;
	private Integer homeScore		= null;
	private Integer awayScore		= null;
	private Integer time			= null;
	private String remarks			= null;
	private Boolean onPlay			= null;
	private Date lastUpdate			= null;


	public MatchImp(Team homeTeam, Team awayTeam, Integer homeScore, Integer awayScore) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.time = 0;
		this.lastUpdate = new Date();
	}
	
	public String getHeaderScoreBoard() {
		return headerScoreBoard;
	}

	public void setHeaderScoreBoard(String headerScoreBoard) {
		this.headerScoreBoard = headerScoreBoard;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team team) {
		this.homeTeam = team;		
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team team) {
		this.awayTeam = team;		
	}

	public Integer getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Integer score) {
		this.homeScore = score;		
	}
	
	public Integer getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(Integer score) {
		this.awayScore = score;		
	}
	
	public Integer getTime() {
		return time;
	}
	
	public void setTime(Integer time) {
		this.time = time;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Boolean getOnPlay() {		
		return onPlay;
	}
	
	public void setStatus(Boolean onPlay) {
		this.onPlay = onPlay;
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}