package uk.ac.ox.oucs.vle;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.ac.ox.oucs.vle.CourseSignupService.Status;


public class TestCourseSignupServiceSignup extends TestOnSampleData {
	
	public void testSignupGood() {
		service.signup("course-1", Collections.singleton("comp-1"), "test.user.1@dept.ox.ac.uk", null);
	}
	
	public void testSignupGoodSet() {
		Set<String> componentIds = new HashSet<String>();
		componentIds.add("comp-1");
		componentIds.add("comp-3");
		service.signup("course-1", componentIds, "test.user.1@dept.ox.ac.uk", null);
	}
	
	public void testSignupMultiple() {
		// TODO This affects all further tests.
		for (int i = 1; i<=15; i++) {
			proxy.setCurrentUser(proxy.findUserById("id"+i));
			service.signup("course-1", Collections.singleton("comp-3"), "test.user.1@dept.ox.ac.uk", null);
		}
	}

	public void testSignupSignupSingle() {
		service.signup("course-1", Collections.singleton("comp-1"), "test.user.1@dept.ox.ac.uk", null);
		dao.flush();
		List<CourseSignup> signups = service.getMySignups(Collections.singleton(Status.PENDING));
		assertEquals(1, signups.size());
		try {
			service.signup("course-1", Collections.singleton("comp-1"), "test.user.2@dept.ox.ac.uk", null);
			fail("Shouldn't be able to signup twice.");
		} catch (Exception e) {}
		dao.flush();
		signups = service.getMySignups(Collections.singleton(Status.PENDING));
		assertEquals("test.user.1@dept.ox.ac.uk", signups.get(0).getSupervisor().getEmail());
	}
	
	public void testSignupWithdrawSignupSingle() {
		service.signup("course-1", Collections.singleton("comp-1"), "test.user.1@dept.ox.ac.uk", null);
		dao.flush();
		List<CourseSignup> signups = service.getMySignups(Collections.singleton(Status.PENDING));
		assertEquals(1, signups.size());
		service.withdraw(signups.get(0).getId());
		dao.flush();
		service.signup("course-1", Collections.singleton("comp-1"), "test.user.2@dept.ox.ac.uk", null);
		dao.flush();
		signups = service.getMySignups(Collections.singleton(Status.PENDING));
		assertEquals("test.user.2@dept.ox.ac.uk", signups.get(0).getSupervisor().getEmail());
	}
	
	public void testSignupWithdrawSignupMultiple() {
		
	}
	
	public void testSignupFuture() {
		try{
			// Isn't open yet.
			service.signup("course-1", Collections.singleton("comp-5"), "test.user.1@dept.ox.ac.uk", null);
			fail("Should throw exception");
		} catch (IllegalStateException ise) {}
	}
	
	public void testSignupPast() {
		try {
			// Is now closed.
			service.signup("course-1", Collections.singleton("comp-6"), "test.user.1@dept.ox.ac.uk", null);
			fail("Should throw exception");
		} catch (IllegalStateException ise) {}
	}
	
	public void testSignupBadUser() {
		try {
			service.signup("course-1", Collections.singleton("comp-1"), "nosuchuser@ox.ac.uk", null);
			fail("Should throw exception");
		} catch (IllegalArgumentException iae) {}
	}

	public void testSignupFull() {
		try {
			service.signup("course-1", Collections.singleton("comp-8"), "test.user.1@dept.ox.ac.uk", null);
			fail("Should throw exception");
		} catch (IllegalStateException ise) {}
	}
	
	public void testSignupWrongCourse() {
		try {
			service.signup("course-3", Collections.singleton("comp-1"), "test.user.1@dept.ox.ac.uk", null);
			fail("Should throw exception");
		} catch (IllegalArgumentException iae) {}
	}
}
