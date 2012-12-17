<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.sakaiproject.component.cover.ComponentManager" %>
<%@ page import="org.sakaiproject.tool.api.ActiveToolManager" %>
<%@ page import="org.sakaiproject.tool.api.ActiveTool" %>
<%@ page import="org.sakaiproject.tool.api.Tool" %>
<% 
	ActiveToolManager toolManager = (ActiveToolManager)ComponentManager.get(org.sakaiproject.tool.api.ActiveToolManager.class);
	ActiveTool tool = toolManager.getActiveTool("sakai.login");
	String context = (String)session.getAttribute(Tool.HELPER_DONE_URL);
	request.setAttribute(Tool.NATIVE_URL, false);
	tool.help(request, response, context, "/login");
%>
