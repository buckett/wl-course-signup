package uk.ac.ox.oucs.vle;
// Generated Aug 17, 2010 10:15:40 AM by Hibernate Tools 3.2.2.GA


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * CourseGroupDAO generated by hbm2java
 */
public class CourseGroupDAO  implements java.io.Serializable {


     private String id;
     private String title;
     private String dept;
     private String administrator;
     private Set<CourseComponentDAO> components = new HashSet<CourseComponentDAO>(0);
     private Set<CourseSignupDAO> signups = new HashSet<CourseSignupDAO>(0);
	private String description;
	private String departmentName;

    public CourseGroupDAO() {
    }

	
    public CourseGroupDAO(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDept() {
        return this.dept;
    }
    
    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getAdministrator() {
        return this.administrator;
    }
    
    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }
    public Set<CourseComponentDAO> getComponents() {
        return this.components;
    }
    
    public void setComponents(Set<CourseComponentDAO> components) {
        this.components = components;
    }
    public Set<CourseSignupDAO> getSignups() {
        return this.signups;
    }
    
    public void setSignups(Set<CourseSignupDAO> signups) {
        this.signups = signups;
    }


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}


