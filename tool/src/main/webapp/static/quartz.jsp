<%@ page import="org.sakaiproject.tool.cover.ToolManager"%>
<%@ page import="org.sakaiproject.component.cover.ServerConfigurationService" %>
<%@ page import="org.sakaiproject.user.cover.UserDirectoryService" %>
<%@ page session="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
pageContext.setAttribute("jobTypes", ServerConfigurationService.getStrings("ses.import.jobs"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- Make the page render as IE8 for wrapping in jstree 
	<meta http-equiv="X-UA-Compatible" content="IE=8" >
	-->
	<title>Module Signup</title>
 
	<link href="<%= ServerConfigurationService.getString("skin.repo", "/library/skin") %>/tool_base.css" type="text/css" rel="stylesheet" media="all" />
	<link href="<%= ServerConfigurationService.getString("skin.repo", "/library/skin") %>/<%= ServerConfigurationService.getString("skin.default", "default") %>/tool.css" type="text/css" rel="stylesheet" media="all" />
	
	<script type="text/javascript" language="JavaScript" src="/library/js/headscripts.js"></script>
	
	<script type="text/javascript">
		
	function jobToRun(radio) {
		
		var html = '<fieldset>';
		html += '<legend id="jobToRun">Run Job Now Confirmation: ';
		html += radio.value;
		html += '<table class="chefEditItem" cellspacing="0">';
		html += '<tbody>';
		html += '<tr>';
		html += '<td class="chefLabel">Are you sure you would like to run the job now?</td>';
		html += '</tr>';
		html += '</tbody>';
		html += '</table>';
		html += '</fieldset>';
		html += '<p>';
		html += '<input type="submit" value="Run Now" />';
		html += '</p>';
		
		document.getElementById("runJob").innerHTML= html;
		setMainFrameHeight(window.name);
	}

	</script>
    
    </head>
    <body onload="setMainFrameHeight(window.name)">
    	<div>
    	
		<div id="messages">
        </div>
		
        <div id="browse">
        	<form method="post">
        		<img alt="" src="/course-signup/static/images/quartz.jpg">
        		<fieldset>
        			<c:forEach var="jobDetailWrapper" items="${jobDetailList}">
        				<c:set var="flag" value="0" />
        				<c:forEach var="jobType" items="${jobTypes}">
        					<c:if test="${jobDetailWrapper.jobDetail.jobDataMap['org.sakaiproject.api.app.scheduler.JobBeanWrapper.jobType'] == jobType}"> 
        						<c:set var="flag" value="1" />
        					</c:if>
        				</c:forEach>
        				<c:choose>
        				<c:when test="${flag == 1}"> 
        					<input type="radio" name="jobName" 
        						value="<c:out value="${jobDetailWrapper.jobDetail.name}" />"
        						onclick="jobToRun(this)"/> 
							<span style="font-weight:bolder">
								<c:out value="${jobDetailWrapper.jobDetail.name}" />
							</span>
						</c:when>
						<c:otherwise>
        					<input type="radio" name="jobName" 
        						value="<c:out value="${jobDetailWrapper.jobDetail.name}" />" disabled>
							<span style="font-weight:lighter">
								<c:out value="${jobDetailWrapper.jobDetail.name}" />
							</span>
						</c:otherwise>
						</c:choose>
						<br>
					</c:forEach>
        		</fieldset>
        		
        		<div id="runJob">
        		</div>
			</form>
		</div>
		
		</div>
    </body>
</html>