package classes;

// Class to create the Team object
public class TeamImp implements interfaces.Team {
	
	private String description = null;
	
	public TeamImp(String description) {
		super();
		this.description = description;
	}	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}