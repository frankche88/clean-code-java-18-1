package pe.edu.unmsm.fisi.upg.ads.dirtycode.domain;

import java.util.Arrays;
import java.util.List;

import pe.edu.unmsm.fisi.upg.ads.dirtycode.exceptions.NoSessionsApprovedException;
import pe.edu.unmsm.fisi.upg.ads.dirtycode.exceptions.SpeakerDoesntMeetRequirementsException;

public class Speaker {
	private static final int MIN_BROWSER_VERSION = 9;
	private static final int MIN_CERTIFICATES = 3;
	private static final int MIN_YEAR_OF_EXPERIENCE = 10;
	private static final List<String> domains = Arrays.asList("aol.com", "hotmail.com", "prodigy.com", "compuserve.com");
	private static final List<String> employers = Arrays.asList("Pluralsight", "Microsoft", "Google", "Fog Creek Software", "37Signals", "Telerik");
	
	private String firstName;
	private String lastName;
	private String email;
	private int experience;
	private boolean hasBlog;
	private String blogURL;
	private WebBrowser browser;
	private List<String> certifications;
	private String employer;
	private int registrationFee;
	private List<Session> sessions;

	public Integer register(IRepository repository) throws Exception {
		Integer speakerId = null;		
		
		if (this.firstName.isEmpty()) {
			throw new IllegalArgumentException("First Name is required");
		}
		
		if (this.lastName.isEmpty()) {
			throw new IllegalArgumentException("Last name is required.");
		}
		
		if (this.email.isEmpty()) {
			throw new IllegalArgumentException("Email is required.");
		}
					
		if (!isGoodSpeaker()) {
			throw new SpeakerDoesntMeetRequirementsException("Speaker doesn't meet our abitrary and capricious standards.");
		}
		
		if (this.sessions.size() == 0) {
			throw new IllegalArgumentException("Can't register speaker with no sessions to present.");
		}
						
		if (!isSessionApproved()) {
			throw new NoSessionsApprovedException("No sessions approved.");
		}


		this.registrationFee = repository.getRegistrationFee(this.experience);
		

		speakerId = repository.saveSpeaker(this);


		return speakerId;
	}

	private boolean isGoodSpeaker() {
		boolean isGoodSpeaker;
		boolean haveExperience = this.experience > MIN_YEAR_OF_EXPERIENCE;
		boolean haveMinCertificates = this.certifications.size() > MIN_CERTIFICATES;

		isGoodSpeaker = ((haveExperience || this.hasBlog || haveMinCertificates || employers.contains(this.employer)));
		
		if (!isGoodSpeaker) {
			
			String[] splitted = this.email.split("@");
			String emailDomain = splitted[splitted.length - 1];

			boolean isValidBrowser = !(browser.getName() == WebBrowser.BrowserName.InternetExplorer && browser.getMajorVersion() < MIN_BROWSER_VERSION);
			
			if (!domains.contains(emailDomain) && isValidBrowser)
			{
				isGoodSpeaker = true;
			}
		}
		return isGoodSpeaker;
	}

	private boolean isSessionApproved() {
		boolean isApproved = true;
		for (Session session : sessions) {
			if(!session.isApproved()){
				isApproved = false;
				break;
			}
			
		}
		return isApproved;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExp() {
		return experience;
	}

	public void setExp(int exp) {
		this.experience = exp;
	}

	public boolean isHasBlog() {
		return hasBlog;
	}

	public void setHasBlog(boolean hasBlog) {
		this.hasBlog = hasBlog;
	}

	public String getBlogURL() {
		return blogURL;
	}

	public void setBlogURL(String blogURL) {
		this.blogURL = blogURL;
	}

	public WebBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(WebBrowser browser) {
		this.browser = browser;
	}

	public List<String> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<String> certifications) {
		this.certifications = certifications;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public int getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(int registrationFee) {
		this.registrationFee = registrationFee;
	}
}