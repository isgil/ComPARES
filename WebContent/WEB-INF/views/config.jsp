<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.List" %>

<body>
	<div class="container config">				
		<form id="form-config" class="item grey lighten-4" action="config.ctrl"
			method="POST">
				<div class="row s12 m12 l12 center">
					<div class="center col s12">
					<h6 class="left-align"><b>TOPs generated</b></h6>
					<p class="left-align">If there are less results than TOPs selected, system will generate the maximum number of tops based on the results.</p>
					<p class="left-align">If no tops are selected, system will generate all the possible tops based on the results.</p>
					<br/>
					<table id="tops-enabled" class="results-table highlight centered">
						<tbody>
							<% List<Integer> tops = (List<Integer>) request.getAttribute("tops"); %>
							<tr>
								<%! Integer[] myValues1 = {10, 50, 100, 200, 300, 400, 500}; %>
								<% for (Integer value : myValues1) { %>
								<% boolean selected = (tops != null) ? tops.contains(value) : false; %>
        						<td <% if (selected) { %> class="selected" <% } %>><input id="<%= value %>" type="checkbox" value="<%= value %>" name="top" <% if (selected) { %> checked <% } %> /><%= value %></td>
        						<% } %>
							</tr>
							<tr>
								<%! Integer[] myValues2 = {750, 1000, 1500, 2000, 2500, 3000, 3500}; %>
								<% for (Integer value : myValues2) { %>
        						<% boolean selected = (tops != null) ? tops.contains(value) : false; %>
        						<td <% if (selected) { %> class="selected" <% } %>><input id="<%= value %>" type="checkbox" value="<%= value %>" name="top" <% if (selected) { %> checked <% } %> /><%= value %></td>
        						<% } %>
							</tr>
							<tr>
								<%! Integer[] myValues3 = {4000, 4500, 5000, 5500, 6000, 6500, 7000}; %>
								<% for (Integer value : myValues3) { %>
        						<% boolean selected = (tops != null) ? tops.contains(value) : false; %>
        						<td <% if (selected) { %> class="selected" <% } %>><input id="<%= value %>" type="checkbox" value="<%= value %>" name="top" <% if (selected) { %> checked <% } %> /><%= value %></td>
        						<% } %>
							</tr>
							<tr>
								<%! Integer[] myValues4 = {7500, 8000, 8500, 9000, 9500, 10000}; %>
								<% for (Integer value : myValues4) { %>
        						<% boolean selected = (tops != null) ? tops.contains(value) : false; %>
        						<td <% if (selected) { %> class="selected" <% } %>><input id="<%= value %>" type="checkbox" value="<%= value %>" name="top" <% if (selected) { %> checked <% } %> /><%= value %></td>
        						<% } %>
								<td id="all">ALL</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<div class="row s12 m12 l12 center">
					<div class="center col s12">
						<button id="save-tops" class="btn btn-small blue darken-1 waves-effect waves-light" type="submit" name="action">Save</button>
					</div>
				</div>
		</form>
		</div>
	
</body>
<script type="text/javascript">
	$(document).ready(function() {
		console.log("ready!");
		$(".title-section").text($("nav #settings span").text()).fadeIn(1000);
		
		$('td').click(function(){
			if ($(this).attr('id') === 'all') {
				var checked = true;
				if (areAllSelected()) {
					checked = false;	
				}
				$('input[type="checkbox"]').prop('checked', checked);
				if (checked) {
					$('td:not(#all)').addClass('selected');
				} else {
					$('td:not(#all)').removeClass('selected');
				}
			} else {
				$(this).toggleClass('selected');
				var input = $(this).find('input');
				$(input).prop('checked', function(_, checked) {
			        return !checked;
			    });
			}
		});
	});
</script>