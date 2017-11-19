
if (window.location.href.includes("results")) {
	


	const loadResults = function() {
		$(".loader").css("display", "block");
		var loadProfileList = $.ajax({
			url: 'getStandardCVs',
			success: function(data) {
				cleanResults();
				buildList(data);
				$(".loader").css("display", "none");
			}
		});
	}

	const filterResults = function(text) {
		$(".loader").css("display", "block");
		var loadProfileList = $.ajax({
			url: 'getStandardCVs',
			success: function(data) {
				cleanResults();
				console.log(text);
				var arr = data.filter((x,y) => {
					var t = JSON.stringify(x).toLowerCase();
					return t.includes(text.toLowerCase());
				});
				buildList(arr);
				$(".loader").css("display", "none");
			}
		});
	}

	const cleanResults = function(){
		$("#list-results").empty();
	}

	const buildList = function(list){
		list.forEach(function(prof){
			var str = '<div class="col-lg-4 col-md-6" id=' + prof.personId + '>'
	                	+ '<div class="card user-card"><div class="border-bottom">';
	
	        if(prof.standardTemplate['photoURL']) { var pic = "/OnlineProfileViewer/" + prof.standardTemplate['photoURL']; }
	        else { var pic = "images/user-placeholder.gif"; }
	        console.log(prof.standardTemplate['photoURL']);
	          str = str + '<img src="'+ pic +'" class="float-left">';
	          str = str + '<h5>' + prof.firstName + ' ' + prof.lastName + '</h5>'
	                	+        '<small>' + prof.standardTemplate['appliedPosition'] + '</small><br>'
	                	+  	    '<div class="badge badge-success badge-sm badge-block">' + prof.standardTemplate['status'] + '</div>'
	                	+       '</div><div class="skills"><h6>Skills</h6><div class="skills-elements">';

	        prof.skills.forEach( (sk) => {
	        	str = str + '<div class="badge badge-sm badge-success"><strong>' + sk.skill + '</strong> - ' + sk.level + '</div>';
	        });  
	        str = str + '</div></div><a class="btn btn-outline-info btn-sm btn-block" href="getResumeById?id=' + prof.personId + '">Check</a></div></div>';
	        $("#list-results").append(str);
	        console.log(prof);
		});
	}


	try {
		$.urlParam = function(name){
			var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
			if(!results) {
				return false
			}
			return results[1] || 0;
		}

		if($.urlParam('search')) {
			filterResults($.urlParam('search'));
			$('#searchInput').val($.urlParam('search'));
		} else {
			loadResults();
		}

	} catch(e){}

	$('#clearSearch').on("click", loadResults);
	$('#searchAction').on("click", function() {
		filterResults($('#searchInput').val());
	});



}