package uk.ac.ox.oucs.vle;
// Generated Aug 17, 2010 10:15:40 AM by Hibernate Tools 3.2.2.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CourseComponentDAO generated by hbm2java
 */
public class CourseComponentDAO  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private int version;
    private String title;
    private String subject;
    private String termcode;
    private Date opens;
    private Date closes;
    private Date starts;
    private Date ends;
    private Date created;
    private boolean bookable;
    private int size;
    private int taken;
    private String componentId;
    private String teacherName;
    private String teacherEmail;
    private String when;
    private String slot;
    private String sessions;
    private String location;
    private String applyTo;
    private Set<CourseSignupDAO> signups = new HashSet<CourseSignupDAO>(0);
    private Set<CourseGroupDAO> groups = new HashSet<CourseGroupDAO>(0);

    public CourseComponentDAO() {
    }

	
    public CourseComponentDAO(String id, boolean bookable) {
        this.id = id;
        this.bookable = bookable;
    }
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
	public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTermcode() {
        return this.termcode;
    }
    
    public void setTermcode(String termcode) {
        this.termcode = termcode;
    }
    public Date getOpens() {
        return this.opens;
    }
    
    public void setOpens(Date opens) {
        this.opens = opens;
    }
    public Date getCloses() {
        return this.closes;
    }
    
    public void setCloses(Date closes) {
        this.closes = closes;
    }
    
    public Date getStarts() {
        return this.starts;
    }
    public void setStarts(Date starts) {
        this.starts = starts;
    }
    
    public Date getEnds() {
        return this.ends;
    }
    public void setEnds(Date ends) {
        this.ends = ends;
    }
    
    public Date getCreated() {
        return this.created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public boolean isBookable() {
        return this.bookable;
    }
    
    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }
    public int getSize() {
        return this.size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    public int getTaken() {
        return this.taken;
    }
    
    public void setTaken(int taken) {
        this.taken = taken;
    }
    public String getComponentId() {
        return this.componentId;
    }
    
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
    public Set<CourseSignupDAO> getSignups() {
        return this.signups;
    }
    
    public void setSignups(Set<CourseSignupDAO> signups) {
        this.signups = signups;
    }
    public Set<CourseGroupDAO> getGroups() {
        return this.groups;
    }
    
    public void setGroups(Set<CourseGroupDAO> groups) {
        this.groups = groups;
    }


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public String getTeacherEmail() {
		return teacherEmail;
	}


	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}


	public String getWhen() {
		return when;
	}


	public void setWhen(String when) {
		this.when = when;
	}


	public String getSlot() {
		return slot;
	}


	public void setSlot(String slot) {
		this.slot = slot;
	}


	public String getSessions() {
		return sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	

	public String getApplyTo() {
		return applyTo;
	}

	public void setApplyTo(String applyTo) {
		this.applyTo = applyTo;
	}

}


