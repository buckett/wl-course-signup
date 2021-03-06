package uk.ac.ox.oucs.vle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface CourseComponent {

	public String getPresentationId();
	
	public String getSubject();
	
	public String getTitle();
	
	public int getPlaces();
	
	public int getSize();
	
	public Person getPresenter();
	
	public String getLocation();
	
	public Date getOpens();
	
	public String getOpensText();
	
	public Date getCloses();
	
	public String getClosesText();
	
	public Date getStarts();
	
	public String getStartsText();
	
	public Date getEnds();
	
	public String getEndsText();
	
	public Date getCreated();
	
	/**
	 * Since Oxcap, we can not rely on there being data in any column,
	 * the import sets a baseDate (if possible) as to the best date to 
	 * use for signups availability.  
	 * This is usually either closes or starts.
	 * @return
	 */
	public Date getBaseDate();
	
	public String getSlot();
	
	public String getWhen();
	
	public String getSessions();
	
	public boolean getBookable();
	
	public String getApplyTo();
	
	public String getTeachingDetails();
	
	public String getMemberApplyTo();
	
	public String getAttendanceMode();
	
	public String getAttendanceModeText();
	
	public String getAttendancePattern();
	
	public String getAttendancePatternText();
	
	public String getSource();
	
	public List<CourseComponentSession> getComponentSessions();

	/**
	 * The ID of the component set that this component belongs to.
	 * This is used when there are multiple copies of a component running at once to discover
	 * which ones the user can select from.
	 * @return
	 */ 
	public String getComponentSet();
	
}
