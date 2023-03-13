package classes;

/**
 *  Class to create the Team object
 * @author Jaime Benavent Alba
 *
 */
public class TeamImp implements interfaces.Team {
	
	private String description = null;
	
	/**
	 * Constructor
	 * @param String description
	 */
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