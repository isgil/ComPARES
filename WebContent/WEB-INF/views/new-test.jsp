<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/js/new-test.jspf"%>
<!-- CONTENT -->

<body>
	<div id="new-test" class="content">
		<div id="modal-loader" class="modal">
			<div class="preloader-wrapper-bg">
				<div class="preloader-wrapper big">
					<div class="spinner-layer spinner-blue-only">
						<div class="circle-clipper left">
							<div class="circle"></div>
						</div>
						<div class="gap-patch">
							<div class="circle"></div>
						</div>
						<div class="circle-clipper right">
							<div class="circle"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- CONTENT -->
		<div id="new-test" class="row s12 m12 l12 center">
			<form id="form-new-test" class="item col s12 m12 l6 offset-l3 grey lighten-4" action="new-test.ctrl" enctype="multipart/form-data" method="POST">
				<div class="input-field col s12">
					<input style="width: 100%" id="test-name" name="test-name" type="text" class="validate" required>
          			<label for="test-name">Test name</label>
          		</div>
          		
          		<div class="row s12 m12 l12 center">
          			<div class="col s6">
	          			<h6>
							<b><em>Source 1</em></b>
						</h6>
					</div>
					<div class="col s6">
	          			<h6>
							<b><em>Source 2</em></b>
						</h6>
					</div>
				</div>
          		
          		<div class="par row s12 m12 l12 center">
          			<div class="input-list col s6 center-align">
          				<% session.setAttribute("inputFileId", "1"); %>
          				<%@ include file="/WEB-INF/views/parts/input-file.jspf"%>
					</div>
					<div class="input-list col s6 center-align">
						<% session.setAttribute("inputFileId", "2"); %>
          				<%@ include file="/WEB-INF/views/parts/input-file.jspf"%>
					</div>
				</div>
				
				<div class="row s12 m12 l12 center">
					<div class="col s6">
						<a id="add-input1" class="btn-floating btn-small add-file blue darken-1 waves-effect waves-light left"><i class="material-icons">add</i></a>
					</div>
					<div class="col s6">
						<a id="add-input2" class="btn-floating btn-small add-file blue darken-1 waves-effect waves-light left"><i class="material-icons">add</i></a>
					</div>
          		</div>
          		
          		<div class="row s12 m12 l12 center">
          			<div class="input-field col s6">
						<tag:SelectSource id="source1" sources="${sources}" />
					</div>
					<div class="input-field col s6">
						<tag:SelectSource id="source2" sources="${sources}" />
					</div>
				</div>
						
				<div class="row s12 m12 l12 center">
					<div class="input-field col s6">
						<input style="width: 100%" id="title-mark1" name="title-mark1" type="text" class="validate">
	          			<label for="title-mark1">Title mark</label>
	          		</div>
	          		<div class="input-field col s6">
						<input style="width: 100%" id="title-mark2" name="title-mark2" type="text" class="validate">
	          			<label for="title-mark2">Title mark</label>
	          		</div>
				</div>
				<div class="row s12 m12 l12 center">
					<div class="center col s12">
						<button id="create-test" class="btn btn-small blue darken-1 waves-effect waves-light" type="submit" name="action">Create</button>
					</div>
				</div>
			</form>
		</div>
		<%@ include file="parts/new-source.jspf"%>
	</div>
</body>