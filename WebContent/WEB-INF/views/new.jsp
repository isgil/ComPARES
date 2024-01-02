<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/js/new.jspf"%>
<!-- CONTENT -->

<body>
	<div id="new" class="content">
		<!-- CONTENT -->
		<div id="new-test" class="row s12 m12 l12 center">
			<form class="item col s12 m12 l6 offset-l3 grey lighten-4" action="create-test" enctype="multipart/form-data">
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
							<input id="files-source1" class="file-selected" placeholder="Upload document" />
						</div>
						<div class="input-field col s12">
							<select id="source1">
								<option value="0" disabled selected>Source</option>
								<option value="1">New Source...</option>
								<c:set var="i" value="2" />
								<c:forEach items="${sources}" var="source">
									<option value="${i}">${source.name}</option>
								</c:forEach>
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
							<input id="files-source2" class="file-selected" placeholder="Upload document" />
						</div>
						<div class="input-field col s12">
							<select id="source2">
								<option value="0" disabled selected>Source</option>
								<option value="1">New Source...</option>
								<c:set var="i" value="2" />
								<c:forEach items="${sources}" var="source">
									<option value="${i}">${source.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="input-field col s12">
							<input style="width: 100%" id="title-mark2" type="text" class="validate">
          					<label for="title-mark2">Title mark</label>
          				</div>
					</div>
				</div>
				<div class="center col s12">
					<button class="btn btn-small blue darken-1 waves-effect waves-light" type="submit" name="action">Add</button>
				</div>
			</form>
		</div>
		<div class="modal">
			<div class="collection library grey lighten-4 row">
				<form id="form-new-source" class="vertical-separator-right col s6" action="create-source">
					<h6>
						<b>New source</b>
					</h6>
					<div class="input-field col s12">
						<input style="width: 100%" id="input-source" type="text"
							class="validate"> <label for="input-source">Source</label>
						<button
							class="btn btn-small blue darken-1 waves-effect waves-light"
							type="submit" name="action">Add</button>
					</div>
				</form>
				<div class="sources vertical-separator-left col s6">
					<h6>
						<b>List of sources</b>
					</h6>
					<ul>
						<li>Revista1</li>
						<li>UMU</li>
						<li>Redalyc</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		console.log("ready!");
		$(".title-section").text($("nav #new span").text()).fadeIn(1000);
		$('select#source1').formSelect();
		$('select#source2').formSelect();
		$('button').click(toggleProgress);
 		$('select#source1').on('change', function (e) {
 		    var optionSelected = $("option:selected", this);
 		    var valueSelected = this.value;
 		    if (valueSelected == '1') {
 		    	$('select#source1 option:eq(0)').attr('selected',true);
				$("select#source1").val('0');
				$("div.modal").css('display', 'block');	
				$("#new-test").addClass('blurry');
 		    }
 		});
 		$('select#source2').on('change', function (e) {
 		    var optionSelected = $("option:selected", this);
 		    var valueSelected = this.value;
 		    if (valueSelected == '1') {
 		    	$('select#source2 option:eq(0)').attr('selected',true);
				$("select#source2").val('0');
				$("select#source2").formSelect();
 		    }
 		});
 		
 		$("#form-new-source").submit(function (event) {
 			console.log('submitting new source');
 		    var source = $("#input-source").val();
 		    createSource(source);
 		    event.preventDefault();
 		});
		
 		/*
		$('select#source1').formSelect();

		// setup listener for custom event to re-initialize on change
		$('select#source1').on('contentChanged', function() {
		  $(this).formSelect();
		});
		*/
	});
</script>