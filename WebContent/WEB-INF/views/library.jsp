<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/js/library.jspf"%>

<body>
	<div id="library" class="content">
		<!-- CONTENT -->
		<div class="row">
			<div class="test-list col s12 m12 l4">
				<c:forEach items="${tests}" var="test">
					<div class="test row">
						<div class="item col s12 m12 l12 grey lighten-4">
							<input id="check${test.id}" type="checkbox"	class="custom-checkbox" value="${test.id}" name="test" /> 
							<label for="check${test.id}" class="custom-checkbox"></label> 
							<a class="titulo" href="tests.ctrl?id=${test.id}"> 
								<c:choose>
									<c:when test="${!empty test.name}">${test.name}</c:when>
									<c:otherwise>[Sin título]</c:otherwise>
								</c:choose>
							</a>
							<div class="sources">
								<div class="chip orange lighten-4">Redalyc</div>
								<div class="chip orange lighten-4">UMU</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="results-wrapper col s12 m12 l8">
			<div class="item results row grey lighten-4">
				<b><span>No results to display</span></b>
				<br/>
			    Lorem ipsum
				dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
				incididunt ut labore et dolore magna aliqua. Vivamus arcu felis
				bibendum ut tristique et. Sit amet mattis vulputate enim nulla.
				Commodo odio aenean sed adipiscing diam donec adipiscing tristique
				risus. Diam volutpat commodo sed egestas egestas fringilla phasellus
				faucibus. Pellentesque nec nam aliquam sem et tortor consequat id.
				Nisi porta lorem mollis aliquam. Fames ac turpis egestas sed tempus
				urna et pharetra pharetra. Habitant morbi tristique senectus et
				netus et malesuada fames ac. Dui ut ornare lectus sit amet est
				placerat in egestas. Nibh venenatis cras sed felis eget velit
				aliquet sagittis. Penatibus et magnis dis parturient montes nascetur
				ridiculus mus mauris. Et netus et malesuada fames ac turpis. Mi
				bibendum neque egestas congue quisque egestas. Rhoncus dolor purus
				non enim praesent. Ipsum dolor sit amet consectetur adipiscing elit.
				Praesent elementum facilisis leo vel fringilla. Aliquet bibendum
				enim facilisis gravida neque convallis. Eget aliquet nibh praesent
				tristique magna. Felis imperdiet proin fermentum leo vel orci. Id
				volutpat lacus laoreet non curabitur gravida arcu. Amet purus
				gravida quis blandit turpis cursus in. Imperdiet dui accumsan sit
				amet nulla facilisi morbi. Suspendisse faucibus interdum posuere
				lorem ipsum dolor. Lobortis feugiat vivamus at augue eget arcu. Ac
				turpis egestas integer eget aliquet nibh praesent tristique magna.
				Quis blandit turpis cursus in hac. Morbi non arcu risus quis varius
				quam. Facilisis volutpat est velit egestas dui id ornare arcu.
				Sollicitudin nibh sit amet commodo. Aliquam ultrices sagittis orci a
				scelerisque. Euismod nisi porta lorem mollis aliquam ut. Ultricies
				tristique nulla aliquet enim tortor at auctor. Quisque id diam vel
				quam elementum pulvinar etiam non. Facilisis sed odio morbi quis. Ut
				diam quam nulla porttitor massa id. Etiam erat velit scelerisque in
				dictum non consectetur a. Eget nulla facilisi etiam dignissim. Et
				magnis dis parturient montes nascetur ridiculus. Purus semper eget
				duis at tellus at urna condimentum mattis. Eget est lorem ipsum
				dolor sit amet consectetur adipiscing elit. Purus viverra accumsan
				in nisl nisi scelerisque. Adipiscing elit duis tristique
				sollicitudin. Ullamcorper eget nulla facilisi etiam dignissim diam.
				Posuere urna nec tincidunt praesent. Amet consectetur adipiscing
				elit duis tristique sollicitudin nibh sit amet. Nunc eget lorem
				dolor sed viverra. Nunc id cursus metus aliquam eleifend mi in
				nulla. Pellentesque habitant morbi tristique senectus et. Tortor at
				auctor urna nunc id cursus. Ut venenatis tellus in metus vulputate
				eu scelerisque felis imperdiet. Vulputate ut pharetra sit amet. Nisl
				suscipit adipiscing bibendum est ultricies integer quis. Ac odio
				tempor orci dapibus ultrices in iaculis nunc sed. Interdum varius
				sit amet mattis vulputate enim nulla aliquet. Nulla porttitor massa
				id neque aliquam vestibulum morbi blandit cursus. Curabitur vitae
				nunc sed velit dignissim sodales ut eu. Aenean vel elit scelerisque
				mauris pellentesque pulvinar pellentesque habitant morbi. Laoreet
				non curabitur gravida arcu ac tortor dignissim convallis. Nisi
				scelerisque eu ultrices vitae auctor eu augue ut. Imperdiet proin
				fermentum leo vel orci porta non pulvinar neque. Sollicitudin nibh
				sit amet commodo nulla facilisi nullam. Nisi vitae suscipit tellus
				mauris a diam maecenas sed enim. Risus nullam eget felis eget nunc
				lobortis mattis aliquam faucibus. Sed cras ornare arcu dui vivamus
				arcu. Donec ac odio tempor orci dapibus ultrices in iaculis nunc.
				Sed vulputate mi sit amet. Sed elementum tempus egestas sed sed
				risus pretium quam vulputate. Eget felis eget nunc lobortis mattis.
				Est ante in nibh mauris cursus. Ac turpis egestas maecenas pharetra
				convallis posuere morbi leo. Aenean vel elit scelerisque mauris
				pellentesque pulvinar pellentesque. Ipsum a arcu cursus vitae congue
				mauris rhoncus. At tempor commodo ullamcorper a lacus. Orci nulla
				pellentesque dignissim enim sit amet venenatis urna cursus. Faucibus
				vitae aliquet nec ullamcorper sit amet risus nullam. Risus in
				hendrerit gravida rutrum quisque non tellus. Tellus in metus
				vulputate eu scelerisque felis imperdiet. Cursus metus aliquam
				eleifend mi in nulla posuere sollicitudin. Morbi tempus iaculis urna
				id volutpat lacus laoreet. In ornare quam viverra orci sagittis eu.
				Posuere morbi leo urna molestie. Aliquam vestibulum morbi blandit
				cursus. Tincidunt tortor aliquam nulla facilisi. Rhoncus est
				pellentesque elit ullamcorper dignissim cras. Maecenas volutpat
				blandit aliquam etiam erat velit. Tempus iaculis urna id volutpat
				lacus laoreet non. Orci ac auctor augue mauris augue neque. Enim
				facilisis gravida neque convallis a cras semper auctor. Quisque non
				tellus orci ac. Sit amet nulla facilisi morbi tempus iaculis urna
				id. Tortor id aliquet lectus proin nibh. Augue eget arcu dictum
				varius duis at. Vulputate ut pharetra sit amet aliquam id diam. A
				condimentum vitae sapien pellentesque habitant morbi. Eget duis at
				tellus at urna condimentum mattis pellentesque id. Leo a diam
				sollicitudin tempor id eu nisl nunc. Consectetur lorem donec massa
				sapien faucibus. Vitae ultricies leo integer malesuada nunc vel
				risus. Faucibus et molestie ac feugiat sed lectus vestibulum mattis
				ullamcorper. Feugiat nisl pretium fusce id velit ut tortor pretium.
				Tellus at urna condimentum mattis. Dapibus ultrices in iaculis nunc
				sed augue lacus viverra. Ornare massa eget egestas purus viverra
				accumsan in. Blandit cursus risus at ultrices mi tempus. Non arcu
				risus quis varius quam quisque. Venenatis a condimentum vitae sapien
				pellentesque. Sed id semper risus in hendrerit. Ut ornare lectus sit
				amet est placerat in. Mollis nunc sed id semper risus. Ridiculus mus
				mauris vitae ultricies leo integer. Fusce id velit ut tortor. Montes
				nascetur ridiculus mus mauris vitae ultricies leo integer. Et
				malesuada fames ac turpis egestas. Consectetur lorem donec massa
				sapien. Facilisis magna etiam tempor orci. Tortor at risus viverra
				adipiscing at in tellus. Neque gravida in fermentum et sollicitudin
				ac orci phasellus. Nibh nisl condimentum id venenatis a condimentum
				vitae sapien pellentesque. Vitae auctor eu augue ut lectus arcu
				bibendum at varius. Consectetur adipiscing elit pellentesque
				habitant morbi tristique senectus et netus. Tincidunt vitae semper
				quis lectus. Pellentesque elit ullamcorper dignissim cras tincidunt
				lobortis feugiat. Id semper risus in hendrerit gravida rutrum.
				Auctor neque vitae tempus quam. Eleifend quam adipiscing vitae
				proin. Morbi tristique senectus et netus et malesuada fames ac
				turpis. Ornare massa eget egestas purus viverra accumsan in nisl
				nisi. Dui faucibus in ornare quam viverra. Suscipit adipiscing
				bibendum est ultricies integer quis. Id leo in vitae turpis massa
				sed. Velit dignissim sodales ut eu sem integer vitae justo eget.
				Pellentesque diam volutpat commodo sed egestas egestas fringilla.
				Diam in arcu cursus euismod. Enim neque volutpat ac tincidunt vitae
				semper quis. Vestibulum morbi blandit cursus risus at ultrices mi
				tempus. Semper viverra nam libero justo laoreet sit. Aliquet
				porttitor lacus luctus accumsan tortor posuere ac ut consequat.
				Commodo nulla facilisi nullam vehicula ipsum a arcu. Nec feugiat
				nisl pretium fusce. Lectus arcu bibendum at varius vel pharetra vel.
				Eleifend donec pretium vulputate sapien nec sagittis aliquam.
				Tincidunt id aliquet risus feugiat in ante metus. Odio eu feugiat
				pretium nibh. Bibendum neque egestas congue quisque egestas diam in
				arcu cursus. Semper risus in hendrerit gravida rutrum quisque non.
				Id eu nisl nunc mi ipsum. Dignissim diam quis enim lobortis
				scelerisque fermentum. Tellus at urna condimentum mattis
				pellentesque id. Vitae congue eu consequat ac felis donec et.
				Lacinia at quis risus sed vulputate odio ut enim blandit. Dignissim
				diam quis enim lobortis. Hac habitasse platea dictumst vestibulum
				rhoncus est pellentesque elit ullamcorper. Pretium lectus quam id
				leo in vitae turpis massa sed. Ut ornare lectus sit amet est
				placerat. Quam viverra orci sagittis eu volutpat odio facilisis.
				Ipsum consequat nisl vel pretium lectus. Viverra suspendisse potenti
				nullam ac tortor vitae purus faucibus. Integer malesuada nunc vel
				risus commodo viverra maecenas. Nisl nisi scelerisque eu ultrices.
				Purus non enim praesent elementum facilisis leo vel fringilla. Vel
				quam elementum pulvinar etiam. Volutpat sed cras ornare arcu dui
				vivamus arcu felis. Risus nec feugiat in fermentum posuere urna nec
				tincidunt praesent. Cursus vitae congue mauris rhoncus aenean vel
				elit scelerisque mauris. Massa enim nec dui nunc mattis enim. Mauris
				pharetra et ultrices neque ornare aenean euismod elementum. Ac
				turpis egestas sed tempus. In arcu cursus euismod quis viverra nibh.
				Augue interdum velit euismod in pellentesque massa placerat duis
				ultricies. Quam lacus suspendisse faucibus interdum posuere lorem
				ipsum dolor sit. Bibendum ut tristique et egestas quis ipsum.
				Ultricies tristique nulla aliquet enim tortor. Auctor neque vitae
				tempus quam pellentesque nec nam aliquam sem. Rhoncus est
				pellentesque elit ullamcorper dignissim. Ultricies mi quis hendrerit
				dolor magna. Ullamcorper eget nulla facilisi etiam dignissim diam
				quis enim lobortis. In nisl nisi scelerisque eu ultrices vitae
				auctor. Nam aliquam sem et tortor consequat id porta. Consectetur
				adipiscing elit ut aliquam purus sit amet luctus. Nunc mi ipsum
				faucibus vitae aliquet nec. Et magnis dis parturient montes nascetur
				ridiculus. Imperdiet proin fermentum leo vel orci porta non
				pulvinar. Massa tincidunt dui ut ornare lectus sit. Et ligula
				ullamcorper malesuada proin libero nunc consequat. Quisque sagittis
				purus sit amet. Et tortor at risus viverra adipiscing at in. Nisi
				porta lorem mollis aliquam ut porttitor leo. Feugiat nisl pretium
				fusce id velit. Amet purus gravida quis blandit turpis. Diam
				volutpat commodo sed egestas. Id interdum velit laoreet id donec
				ultrices. Elementum sagittis vitae et leo duis ut diam quam. Hac
				habitasse platea dictumst quisque sagittis purus sit. Ipsum dolor
				sit amet consectetur. Ipsum dolor sit amet consectetur adipiscing
				elit pellentesque habitant. Eleifend donec pretium vulputate sapien
				nec sagittis aliquam malesuada bibendum. Elit ullamcorper dignissim
				cras tincidunt. Dignissim suspendisse in est ante. Mollis nunc sed
				id semper risus in hendrerit gravida. Interdum posuere lorem ipsum
				dolor sit amet consectetur adipiscing elit. Id velit ut tortor
				pretium viverra. A diam sollicitudin tempor id eu nisl nunc mi.
				Nullam ac tortor vitae purus faucibus. Leo duis ut diam quam nulla
				porttitor massa id neque. Scelerisque varius morbi enim nunc. Ut
				etiam sit amet nisl purus in mollis. Consectetur adipiscing elit
				duis tristique sollicitudin nibh sit amet. Malesuada nunc vel risus
				commodo viverra maecenas accumsan. Egestas dui id ornare arcu odio
				ut sem nulla. Diam maecenas sed enim ut sem viverra aliquet. Non
				diam phasellus vestibulum lorem sed. Accumsan in nisl nisi
				scelerisque eu ultrices vitae. Aenean et tortor at risus. Cras
				fermentum odio eu feugiat. Maecenas accumsan lacus vel facilisis
				volutpat. Diam vulputate ut pharetra sit. Massa enim nec dui nunc
				mattis enim ut tellus. Sed viverra ipsum nunc aliquet bibendum enim
				facilisis. Sed elementum tempus egestas sed sed risus pretium. Neque
				aliquam vestibulum morbi blandit cursus. Odio ut sem nulla pharetra
				diam sit amet nisl. Risus nullam eget felis eget nunc. Sed lectus
				vestibulum mattis ullamcorper. Quis varius quam quisque id diam vel
				quam elementum pulvinar. Urna neque viverra justo nec ultrices dui
				sapien eget mi. Nec tincidunt praesent semper feugiat nibh. Quis vel
				eros donec ac odio tempor orci. Eu non diam phasellus vestibulum
				lorem. Habitant morbi tristique senectus et. Cras tincidunt lobortis
				feugiat vivamus at augue eget. Quam nulla porttitor massa id. Rutrum
				quisque non tellus orci ac auctor augue mauris. Venenatis urna
				cursus eget nunc scelerisque. Sagittis vitae et leo duis. Lectus
				urna duis convallis convallis tellus id interdum. Quisque sagittis
				purus sit amet volutpat consequat mauris nunc congue. Adipiscing
				elit duis tristique sollicitudin nibh sit amet commodo nulla.
				Adipiscing elit duis tristique sollicitudin nibh sit. Eget felis
				eget nunc lobortis mattis aliquam faucibus purus in. Eu consequat ac
				felis donec et odio pellentesque diam. Quis blandit turpis cursus in
				hac habitasse platea. Nunc consequat interdum varius sit. Aliquam id
				diam maecenas ultricies mi eget. Feugiat in ante metus dictum at
				tempor commodo ullamcorper. Fermentum odio eu feugiat pretium nibh
				ipsum consequat nisl. Suspendisse sed nisi lacus sed viverra tellus
				in hac habitasse. Dignissim convallis aenean et tortor at risus
				viverra. Donec massa sapien faucibus et. In pellentesque massa
				placerat duis ultricies lacus. Sapien faucibus et molestie ac
				feugiat. Nam at lectus urna duis convallis convallis. Bibendum at
				varius vel pharetra vel turpis nunc eget. Laoreet sit amet cursus
				sit amet dictum sit amet. Feugiat in ante metus dictum at. Placerat
				in egestas erat imperdiet sed euismod nisi. Cursus turpis massa
				tincidunt dui ut. Turpis egestas pretium aenean pharetra magna ac
				placerat vestibulum lectus. Id aliquet lectus proin nibh nisl
				condimentum id. Maecenas ultricies mi eget mauris. Tellus orci ac
				auctor augue mauris augue neque. Adipiscing vitae proin sagittis
				nisl rhoncus mattis rhoncus. Ultrices neque ornare aenean euismod
				elementum nisi quis eleifend quam. Faucibus pulvinar elementum
				integer enim neque volutpat ac tincidunt vitae. Eu lobortis
				elementum nibh tellus. Quis hendrerit dolor magna eget est lorem
				ipsum dolor sit. In metus vulputate eu scelerisque felis imperdiet
				proin. Aliquet bibendum enim facilisis gravida. Tristique senectus
				et netus et malesuada fames. Ut tortor pretium viverra suspendisse
				potenti nullam ac tortor. Pellentesque diam volutpat commodo sed
				egestas. Enim praesent elementum facilisis leo vel fringilla est
				ullamcorper. Amet mauris commodo quis imperdiet massa tincidunt
				nunc. Pretium nibh ipsum consequat nisl vel pretium lectus quam id.
				Praesent elementum facilisis leo vel. Ullamcorper morbi tincidunt
				ornare massa eget egestas purus viverra accumsan. Nisi scelerisque
				eu ultrices vitae auctor eu augue ut. Sollicitudin nibh sit amet
				commodo. Consequat semper viverra nam libero justo laoreet sit amet
				cursus. Aliquam eleifend mi in nulla posuere sollicitudin aliquam
				ultrices. Massa ultricies mi quis hendrerit dolor magna eget est
				lorem. Dolor sit amet consectetur adipiscing elit ut. Pellentesque
				elit ullamcorper dignissim cras tincidunt lobortis feugiat. Sed arcu
				non odio euismod. Massa massa ultricies mi quis hendrerit dolor
				magna eget. Lorem dolor sed viverra ipsum. Tortor pretium viverra
				suspendisse potenti nullam ac tortor vitae purus. Aliquam malesuada
				bibendum arcu vitae elementum curabitur. In mollis nunc sed id
				semper risus in hendrerit gravida. Elit at imperdiet dui accumsan
				sit amet nulla.</div>
				</div>
		</div>
	</div>
</body>