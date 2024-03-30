<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<body>
	<div class="container config">				
		<form id="form-config" class="item grey lighten-4" action="config.ctrl"
			method="POST">
				<div class="row s12 m12 l12 center">
					<div class="center col s12">
					<h6 class="left-align"><b>TOPs generated</b></h6>
					<p class="left-align">If there are less results than TOPs selected, system will generate the maximum number of tops based on the results.</p>
					<br/>
					<table id="tops-enabled" class="results-table highlight centered">
						<tbody>
							<tr>
								<td><input id="10" type="checkbox" value="10" name="top" />10</td>
								<td><input id="50" type="checkbox" value="50" name="top" />50</td>
								<td><input id="100" type="checkbox" value="100" name="top" />100</td>
								<td><input id="200" type="checkbox" value="200" name="top" />200</td>
								<td><input id="300" type="checkbox" value="300" name="top" />300</td>
								<td><input id="400" type="checkbox" value="400" name="top" />400</td>
								<td><input id="500" type="checkbox" value="500" name="top" />500</td>
							</tr>
							<tr>
								<td><input id="750" type="checkbox" value="750" name="top" />750</td>
								<td><input id="1000" type="checkbox" value="1000" name="top" />1000</td>
								<td><input id="1500" type="checkbox" value="1500" name="top" />1500</td>
								<td><input id="2000" type="checkbox" value="2000" name="top" />2000</td>
								<td><input id="2500" type="checkbox" value="2500" name="top" />2500</td>
								<td><input id="3000" type="checkbox" value="3000" name="top" />3000</td>
								<td><input id="3500" type="checkbox" value="3500" name="top" />3500</td>
							</tr>
							<tr>
								<td><input id="4000" type="checkbox" value="4000" name="top" />4000</td>
								<td><input id="4500" type="checkbox" value="4500" name="top" />4500</td>
								<td><input id="5000" type="checkbox" value="5000" name="top" />5000</td>
								<td><input id="5500" type="checkbox" value="5500" name="top" />5500</td>
								<td><input id="6000" type="checkbox" value="6000" name="top" />6000</td>
								<td><input id="6500" type="checkbox" value="6500" name="top" />6500</td>
								<td><input id="7000" type="checkbox" value="7000" name="top" />7000</td>
							</tr>
							<tr>
								<td><input id="7500" type="checkbox" value="7500" name="top" />7500</td>
								<td><input id="8000" type="checkbox" value="8000" name="top" />8000</td>
								<td><input id="8500" type="checkbox" value="8500" name="top" />8500</td>
								<td><input id="9000" type="checkbox" value="9000" name="top" />9000</td>
								<td><input id="9500" type="checkbox" value="9500" name="top" />9500</td>
								<td><input id="10000" type="checkbox" value="10000"	name="top" />10000</td>
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
				var numSelected = $('td.selected').length;
				if (numSelected > 0) {
					$('td:not(#all)').removeClass('selected');
					$('input').prop('checked', false);
				} else {
					$('td:not(#all)').addClass('selected');
					$('input').prop('checked', true);
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