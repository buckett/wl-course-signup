<%@ page import="org.sakaiproject.component.cover.ServerConfigurationService" %>
<%@ page import="org.sakaiproject.user.cover.UserDirectoryService" %>
<%@ page session="false" %>
<%
if (UserDirectoryService.getAnonymousUser().equals(UserDirectoryService.getCurrentUser())) {
	String redirectURL = "login.jsp";
    response.sendRedirect(redirectURL);
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Module Signup</title>

	<link href="<%= ServerConfigurationService.getString("skin.repo", "/library/skin") %>/tool_base.css" type="text/css" rel="stylesheet" media="all" />
	<link href="<%= ServerConfigurationService.getString("skin.repo", "/library/skin") %>/<%= ServerConfigurationService.getString("skin.default", "default") %>/tool.css" type="text/css" rel="stylesheet" media="all" />

	<link rel="stylesheet" type="text/css" href="lib/jqmodal-r14/jqModal.css" />
	<link rel="stylesheet" type="text/css" href="lib/dataTables-1.7/css/demo_table_jui.css"/>
	<link rel="stylesheet" type="text/css" href="lib/jquery-ui-1.8.4.custom/css/smoothness/jquery-ui-1.8.4.custom.css"/>
	<link rel="stylesheet" type="text/css" href="lib/tool.css" />
	
	<script type="text/javascript" src="lib/jquery/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="lib/jstree-1.0rc/_lib/jquery.cookie.js"></script>
	<script type="text/javascript" src="lib/jstree-1.0rc/jquery.jstree.js"></script>
	<script type="text/javascript" src="lib/jqmodal-r14/jqModal.js"></script>
	<script type="text/javascript" src="lib/trimpath-template-1.0.38/trimpath-template.js"></script>
	<script type="text/javascript" src="lib/dataTables-1.7/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="lib/dataTables.reloadAjax.js"></script>
	<script type="text/javascript" src="lib/signup.js"></script>
	<script type="text/javascript" src="lib/Text.js"></script>
	<script type="text/javascript" src="lib/serverDate.js"></script>
		<script type="text/javascript">

		$(function(){

			var aSelected = [];
			
			/**
		 	 * jQuery plugin to make a checkbox table.
			 */
			$.fn.checkboxTable = function(url, isAdmin, allowChangeStatus){
				allowChangeStatus = allowChangeStatus || false;
		        var element = this;
		        var table = this.dataTable({
		            "bJQueryUI": true,
		            "sPaginationType": "full_numbers",
		            "bProcessing": true,
		            "sAjaxSource": url,
		            "bAutoWidth": false,
		            "aaSorting": [[1, "desc"]],
		            "aoColumns": [{
		                "sTitle": "",
		                "bVisible": false,
		               "bUseRendered": false
		            }, {
		                "sTitle": "Created",
		                "fnRender": function(aObj){
		                    if (aObj.aData[1]) {
		                        return Signup.util.formatDuration($.serverDate() - aObj.aData[1]) + " ago";
		                    }
		                    else {
		                        return "unknown";
		                    }
		                },
		                "bUseRendered": false
		            }, {
		                "sTitle": "Student",
		                "sWidth": "20%"
		            }, {
		                "sTitle": "Module"
		            }, {
		                "sTitle": "Supervisor"
		            }, {
		                "sTitle": "Notes",
		                "sWidth": "20%",
		                "sClass": "signup-notes"
		            }, {
		                "sTitle": "Status",
						"fnRender": function(aObj) {
							return allowChangeStatus?Signup.signup.selectStatus(aObj.aData[0], aObj.aData[6]):aObj.aData[6];
						}
		            }, {
		                "sTitle": "<input type='checkbox' class='toggle-checkboxes' /><br />Select",
		                "bSortable": false,
		                "fnRender": function(aObj){
		                	return "<input type='checkbox' name='"+aObj.aData[0]+"' />";
		            	}
		            }],
		            "fnServerData": function(sSource, aoData, fnCallback){
		                jQuery.ajax({
		                    dataType: "json",
		                    type: "GET",
							cache: false,
		                    url: sSource,
		                    success: function(result){
		                        var data = [];
		                        $.each(result, function(){
		                            var course = ['<span class="course-group">' + this.group.title + "</span>"].concat($.map(this.components.concat(), 
		                            		function(component){
		                            			var size = component.size;
		                            			var limit = size*placesWarnPercent/100;
		                            			var componentPlacesClass;
		                            			if (placesErrorLimit >= component.places) {
		                            				componentPlacesClass = "course-component-error";
		                            			} else if (limit >= component.places) {
		                            				componentPlacesClass = "course-component-warn";
		                            			} else {
		                            				componentPlacesClass = "course-component";
		                            			}
		                                		return '<span class="course-component">' + component.title + " " + component.slot + " in " + component.when + ' <span class='+componentPlacesClass+'>'+ Signup.signup.formatPlaces(component.places, isAdmin)+'</span></span>';
		                            		})).join("<br>");
		                            
		                            var closes = 0;
		                            $.each(this.components, 
		                            		function(){
		                            			if (closes != 0 && this.closes > closes) {
		                            				return;
		                            			}
		                            			closes = this.closes;
		                            });
		                            var actions = Signup.signup.formatActions(Signup.signup.getActions(this.status, this.id, closes, true));
		                            data.push([this.id, (this.created) ? this.created : "", Signup.user.render(this.user, this.group, this.components), course, Signup.supervisor.render(this.supervisor, this, isAdmin), Signup.signup.formatNotes(this.notes), this.status, "" ]);
		                            
		                        });
		                        fnCallback({
		                            "aaData": data
		                        });
		                    }
		                });
		            },
					// This is useful as when loading the data async we might want to handle it later.
					"fnInitComplete": function() {
						table.trigger("tableInit");
					},

		            "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
		    			if ( jQuery.inArray(aData.DT_RowId, aSelected) !== -1 ) {
		    				$(nRow).addClass('row_selected');
		    			}
		    			return nRow;
		    		}
		    	});
			}
		    
			// Support sorting based on a user.
			$.fn.dataTableExt.oSort["user-asc"] = function(x, y) {
				return $.fn.dataTableExt.oSort["string-asc"](x.name, y.name);
			};
			$.fn.dataTableExt.oSort["user-desc"] = function(x, y) {
				return $.fn.dataTableExt.oSort["string-desc"](x.name, y.name);
			}

			var table = $("#pending-table").checkboxTable("../rest/signup/approve", true);
			Signup.util.autoresize();
				
			// Need to see if we have an anchor in the URL and if so just display that row
			if (location.hash) {
				var signupId = location.hash.substr(1); // Trim the leading #
				console.log(signupId);
				table.fnFilter(signupId,0);
				// Problem is that datatables doesn't fire off events once it's loaded, so initially there won't be any data.
				// It has callbacks... and so we can fire the events in the class
				table.bind("tableInit", function() {
					var length = $("tbody tr td", table).length;
					if (length > 1) {
						table.one("reload", function() {
							table.fnFilter("",0);
						});
					} else {
						alert("Couldn't find signup. This could be because it has already been accepted or has been withdrawn.");
						table.fnFilter("",0);
					}
				});
			}
				
			$("input.toggle-checkboxes", this).die().live("click", function(e){  	
				if (this.checked == false) {
					$("INPUT[type='checkbox']").each(function(){
		        		this.checked = false;
					});
				} else {
					$("INPUT[type='checkbox']").each(function(){
		        		this.checked = true;
					});
				}
        	});

			$("input.confirm-selected", this).die().live("click", function(e){
				var table = $("#pending-table").dataTable();
				$("tr:gt(0)").filter(':has(:checkbox:checked)').each(function(){
					var id = $(this).find('td:last input').attr("name"); 
					var aPos = table.fnGetPosition(this);
					
					$.ajax({
						"url": "../rest/signup/"+id+"/confirm",
						"type": "POST",
						"async": false,
						"traditional": true,
						"success": function(data) {
							table.fnDeleteRow(aPos);
							table.trigger("reload");
						}
					});
				});
			});

			$("input.reject-selected", this).die().live("click", function(e){
				var table = $("#pending-table").dataTable();
				$("tr:gt(0)").filter(':has(:checkbox:checked)').each(function(){
					var id = $(this).find('td:last input').attr("name"); 
					var aPos = table.fnGetPosition(this);
					$.ajax({
						"url": "../rest/signup/"+id+"/reject",
						"type": "POST",
						"async": false,
						"traditional": true,
						"success": function() {
							table.fnDeleteRow(aPos);
							table.trigger("reload");
						}
					});
				});
			}); 	 	
		});
			
		</script>

    </head>
    <body>
    	<div id="toolbar" >
        	<ul class="navIntraTool actionToolBar">
            <li><span><a href="index.jsp">Module Signup</a></span></li>
			<li><span><a href="search.jsp">Module Search</a></span></li>
            <li><span><a href="my.jsp">My Modules</a></span></li>
            <li><span><a href="pending.jsp">Pending Acceptances</a></span></li>
            <li><span>Pending Confirmations</span></li>
            <li><span><a href="admin.jsp">Module Administration</a></span></li>
			</ul>
        </div>
		<div>
			<p>These students have signed up for a module that needs your approval.
			<span style="float:right; margin-right:4%;">
			<input type='button' class="reject-selected" value="Reject Selected" name="name"></input>
			<input type='button' class="confirm-selected" value="Confirm Selected" name="name"></input>
			</span>
			</p>
		</div>
		<div style="margin:2%" >
		<table id="pending-table" class="display">
		</table>
		</div>
		
		
    </body>
</html>