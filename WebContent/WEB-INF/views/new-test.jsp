<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/js/new-test.jspf"%>
<!-- CONTENT -->

<body>
	<div id="new-test" class="content">
		<!-- CONTENT -->
		<div id="new-test" class="row s12 m12 l12 center">
			<form class="item col s12 m12 l6 offset-l3 grey lighten-4" action="create-test" enctype="multipart/form-data">
				<div class="input-field col s12">
					<input style="width: 100%" id="test-name" type="text" class="validate">
          			<label for="test-name">Test name</label>
          		</div>
          		
          		<!-- EXPORT SOURCE 1 -->
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
							<tag:SelectSource id="source1" sources="${sources}" />
						</div>
						<div class="input-field col s12">
							<input style="width: 100%" id="title-mark1" type="text" class="validate">
          					<label for="title-mark1">Title mark</label>
          				</div>
					</div>
				</div>
				
				<!-- EXPORT SOURCE 2 -->
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
							<tag:SelectSource id="source2" sources="${sources}" />
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
		<%@ include file="parts/new-source.jspf"%>
	</div>
</body>