<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.sakaiproject.tool.api.Tool" %>
<% 
	String context = request.getHeader("referer");
	session.setAttribute(Tool.HELPER_DONE_URL, context);
	response.sendRedirect("dologin.jsp");
%>
