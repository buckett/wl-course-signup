package uk.ac.ox.oucs.vle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.ext.ContextResolver;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.type.TypeFactory;
import org.sakaiproject.component.cover.ComponentManager;
import org.sakaiproject.tool.api.ActiveTool;
import org.sakaiproject.tool.api.ActiveToolManager;
import org.sakaiproject.tool.api.Session;
import org.sakaiproject.tool.api.Tool;
import org.sakaiproject.tool.cover.SessionManager;
import org.sakaiproject.user.cover.UserDirectoryService;

import uk.ac.ox.oucs.vle.CourseSignupService.Range;

@Path("/login/")
public class LoginResource {

	private CourseSignupService courseService;
	private JsonFactory jsonFactory;
	private ObjectMapper objectMapper;

	public LoginResource(@Context ContextResolver<Object> resolver) {
		this.courseService = (CourseSignupService) resolver.getContext(CourseSignupService.class);
		jsonFactory = new JsonFactory();
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		objectMapper.configure(SerializationConfig.Feature.USE_STATIC_TYPING, true);
		objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	}
	
	@Path("/sethelper")
	@GET
	@Produces({MediaType.APPLICATION_XHTML_XML, MediaType.TEXT_HTML})
	public String getSetHelper(
			@QueryParam("returnUrl") final String returnurl,
			@Context final HttpServletRequest request,
		    @Context final HttpServletResponse response) throws ServletException, IOException {
		
		Session session = SessionManager.getCurrentSession();
		session.setAttribute(Tool.HELPER_DONE_URL, returnurl);
		response.sendRedirect("/course-signup/rest/login/dologin");
		return "";
	} 
	
	@Path("/dologin")
	@GET
	@Produces({MediaType.APPLICATION_XHTML_XML, MediaType.TEXT_HTML})
	public String getDoLogin(
			@Context final HttpServletRequest request,
		    @Context final HttpServletResponse response) throws ServletException, IOException {
		
		ActiveToolManager toolManager = (ActiveToolManager)ComponentManager.get(org.sakaiproject.tool.api.ActiveToolManager.class);
		ActiveTool tool = toolManager.getActiveTool("sakai.login");
		Session session = SessionManager.getCurrentSession();
		String context = (String)session.getAttribute(Tool.HELPER_DONE_URL);
		//request.setAttribute(Tool.NATIVE_URL, false);
		//tool.help(request, response, context, "/login");
		tool.help(request, response, "/portal/xlogin", "/login");
		
		return "";
	} 

}
