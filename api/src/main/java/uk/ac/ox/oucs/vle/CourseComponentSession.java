package uk.ac.ox.oucs.vle;

import java.util.Date;

public interface CourseComponentSession {

	public int getCourceComponentMuid();
	
	public Date getSessionStart();
	
	public String getSessionStartText();
	
	public Date getEndStart();
	
	public String getSessionEndText();

}
