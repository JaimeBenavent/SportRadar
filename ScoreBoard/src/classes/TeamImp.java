package classes;

public class TeamImp implements interfaces.Team {
	
	private String description = null;
	private Integer result = null;
	
	public TeamImp(String description, Integer result) {
		super();
		this.description = description;
		this.result = result;
	}	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
}