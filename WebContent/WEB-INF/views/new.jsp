<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<body>
	<div class="container new">
		<!-- CONTENT -->
		<div class="collection library grey lighten-4 row">
			<form class="col s12">
				<div class="input-field col s12">
					<input style="width: 100%" id="test-name" type="text" class="validate">
          			<label for="test-name">Test name</label>
          		</div>
				<div class="vertical-separator-right col s6">
					<h6>
						<b>Export source 1</b>
					</h6>
					<div class="input-field col s12">
						<div class="col s12">
          					<div class="upload">
								<input id="upload1" type="file" onchange="actualizarInput(this)" class="upload" name="dataFile"
								accept="application/pdf,text/html,text/htm,text/xml,text/plain" />
							</div>
							<input id="file-selected1" class="file-selected" placeholder="Upload document" />
						</div>
						<div class="input-field col s12">
							<select id="select1">
								<option value="" disabled selected>Source</option>
								<option value="1">UMU</option>
								<option value="2">Redalyc</option>
								<option value="3">Source 3</option>
							</select>
						</div>
						<div class="input-field col s12">
							<input style="width: 100%" id="title-mark1" type="text" class="validate">
          					<label for="title-mark1">Title mark</label>
          				</div>
					</div>
				</div>
				<div class="vertical-separator-left col s6">
					<h6>
						<b>Export source 2</b>
					</h6>
					<div class="input-field col s12">
						<div class="col s12">
          					<div class="upload">
								<input id="upload2" type="file" onchange="actualizarInput(this)" class="upload" name="dataFile"
								accept="application/pdf,text/html,text/htm,text/xml,text/plain" />
							</div>
							<input id="file-selected2" class="file-selected" placeholder="Upload document" />
						</div>
						<div class="input-field col s12">
							<select id="select2">
								<option value="" disabled selected>Source</option>
								<option value="1">UMU</option>
								<option value="2">Redalyc</option>
								<option value="3">Source 3</option>
							</select>
						</div>
						<div class="input-field col s12">
							<input style="width: 100%" id="title-mark2" type="text" class="validate">
          					<label for="title-mark2">Title mark</label>
          				</div>
					</div>
				</div>
				<div class="center col s12">
					<button class="btn btn-small blue darken-1 waves-effect waves-light" type="button" name="action">Add</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		console.log("ready!");
		$(".title-section").text($("nav #new span").text()).fadeIn(1000);
		$('select').formSelect();
		$('button').click(toggleProgress);
	});
</script>