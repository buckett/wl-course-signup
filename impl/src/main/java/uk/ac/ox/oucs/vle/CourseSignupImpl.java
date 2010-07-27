package uk.ac.ox.oucs.vle;

import java.util.HashSet;
import java.util.Set;

import uk.ac.ox.oucs.vle.CourseSignupService.Status;
import uk.ac.ox.oucs.vle.proxy.User;

public class CourseSignupImpl implements CourseSignup {

	private CourseSignupDAO dao;
	private CourseSignupServiceImpl service;
	
	public CourseSignupImpl(CourseSignupDAO dao, CourseSignupServiceImpl service) {
		this.dao = dao;
		this.service = service;
	}
	
	public String getId() {
		return dao.getId();
	}

	public Person getUser() {
		User user = service.loadUser(dao.getUserId());
		Person person = null;
		if (user != null) {
			person = new PersonImpl(user.getId(), user.getName(), user.getEmail());
		}
		return person;
	}

	public Person getSupervisor() {
		User user = service.loadUser(dao.getSupervisorId());
		Person person = null;
		if (user != null) {
			person = new PersonImpl(user.getId(), user.getName(), user.getEmail());
		}
		return person;	}

	public String getNotes() {
		return dao.getProperties().get("notes");
	}

	public Status getStatus() {
		return dao.getStatus();
	}

	public Set<CourseComponent> getComponents() {
		Set<CourseComponentDAO> componentDaos = dao.getComponents();
		Set<CourseComponent> components = new HashSet<CourseComponent>(componentDaos.size());
		for(CourseComponentDAO componentDao: componentDaos) {
			components.add(new CourseComponentImpl(componentDao, service));
		}
		return components;
	}

	public CourseGroup getGroup() {
		return new CourseGroupImpl(dao.getGroup(), service);
	}

}