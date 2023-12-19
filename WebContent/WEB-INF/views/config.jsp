<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>
	<div class="container config">
		<!-- CONTENT -->
		<div class="collection library grey lighten-4 row">
			<form class="new-source vertical-separator-right col s6">
			<h6><b>New source</b></h6>
				<div class="input-field col s12">
          			<input style="width: 100%" id="source" type="text" class="validate">
          			<label for="source">Source</label>
          			<button class="btn btn-small blue darken-1 waves-effect waves-light" type="button" name="action">Add</button>
        		</div>
			</form>
			<div class="sources vertical-separator-left col s6">
				<h6><b>List of sources</b></h6>
				<ul>
					<li>Revista1</li>
					<li>UMU</li>
					<li>Redalyc</li>
				</ul>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$( document ).ready(function() {
	   console.log( "ready!" );
	   $(".title-section").text($("nav #settings span").text()).fadeIn(1000);
	   $('button').click(toggleProgress);
	});
</script>