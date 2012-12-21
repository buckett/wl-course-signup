package uk.ac.ox.oucs.vle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

import org.sakaiproject.component.cover.ComponentManager;
import org.sakaiproject.tool.api.ActiveTool;
import org.sakaiproject.tool.api.ActiveToolManager;
import org.sakaiproject.tool.api.Session;
import org.sakaiproject.tool.api.Tool;
import org.sakaiproject.tool.cover.SessionManager;

@Path("/login/")
public class LoginResource {

	public LoginResource(@Context ContextResolver<Object> resolver) {
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
		response.sendRedirect(request.getContextPath()+request.getServletPath()+"/login/dologin");
		return "";
	} 
	
	// This method needs to handle both GET and POST so the designator has been dropped
	@Path("/dologin")
	@Produces({MediaType.APPLICATION_XHTML_XML, MediaType.TEXT_HTML})
	public String getDoLogin(
			@Context final HttpServletRequest request,
		    @Context final HttpServletResponse response) throws ServletException, IOException {
		
		ActiveToolManager toolManager = (ActiveToolManager)ComponentManager.get(org.sakaiproject.tool.api.ActiveToolManager.class);
		ActiveTool tool = toolManager.getActiveTool("sakai.login");
		tool.help(request, response, request.getContextPath()+request.getServletPath()+"/login/dologin", "/login");
		
		return "";
	} 

}
