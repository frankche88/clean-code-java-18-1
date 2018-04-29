package pe.edu.unmsm.fisi.upg.ads.dirtycode.domain;

public class Session {
	private String title;
	private String description;
	private boolean approved = true;
	
	String[] programLanguage = new String[] { "Cobol", "Punch Cards", "Commodore", "VBScript" };

	public Session(String title, String description) {
		this.title = title;
		this.description = description;
		
		if(title != null && description != null) {
			for (String tech : programLanguage) {
				if(title.contains(tech) || description.contains(tech)) {
					approved = false;
					break;
				}
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		
		if(!approved && title != null) {
			for (String tech : programLanguage) {
				approved = !title.contains(tech);
			}
		}
		
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		
		if(!approved && title != null) {
			for (String tech : programLanguage) {
				approved = !description.contains(tech);
			}
		}
		
		this.description = description;
	}

	public boolean isApproved() {
		return approved;
	}

}