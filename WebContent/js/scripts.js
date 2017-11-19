$( document ).ready( function(){
	setTimeout(
		function(){
			$('#loader').addClass('animated fadeOut');

			$('#loader').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
				$('#loader').css('display', 'none');
			});
		}
		,10);


	
});

$("#dynamictemplate-button").on("click", function() {
	$("#feedback-list").css("display", "none");
	$("#template-list").css("display", "block");
	$("#dynamictemplate-button").addClass("active");
	$("#feedback-button").removeClass("active");
});

$("#feedback-button").on("click", function() {

	$("#template-list").css("display", "none");
	$("#feedback-list").css("display", "block");
	$("#feedback-button").addClass("active");
	$("#dynamictemplate-button").removeClass("active");
	
});

// Profile page

// click listener for tabs
const selectProfile = function(){
	$("#btn-profile").addClass("active");
	$("#btn-education").removeClass("active");
	$("#btn-work").removeClass("active");
	$("#btn-skills").removeClass("active");
	$("#btn-lang").removeClass("active");
	$("#btn-other").removeClass("active");
	
	$("#edit-profile").css("display", "flex");
	$("#edit-education").css("display", "none");
	$("#edit-work").css("display", "none");
	$("#edit-skills").css("display", "none");
	$("#edit-lang").css("display", "none");
	$("#edit-other").css("display", "none");
}

const selectEducation = function(){
	$("#btn-profile").removeClass("active");
	$("#btn-education").addClass("active");
	$("#btn-work").removeClass("active");
	$("#btn-skills").removeClass("active");
	$("#btn-lang").removeClass("active");
	$("#btn-other").removeClass("active");
	
	$("#edit-profile").css("display", "none");
	$("#edit-education").css("display", "flex");
	$("#edit-work").css("display", "none");
	$("#edit-skills").css("display", "none");
	$("#edit-lang").css("display", "none");
	$("#edit-other").css("display", "none");
}

const selectWork = function(){
	$("#btn-profile").removeClass("active");
	$("#btn-education").removeClass("active");
	$("#btn-work").addClass("active");
	$("#btn-skills").removeClass("active");
	$("#btn-lang").removeClass("active");
	$("#btn-other").removeClass("active");
	
	$("#edit-profile").css("display", "none");
	$("#edit-education").css("display", "none");
	$("#edit-work").css("display", "flex");
	$("#edit-skills").css("display", "none");
	$("#edit-lang").css("display", "none");
	$("#edit-other").css("display", "none");
}

const selectSkills = function(){
	$("#btn-profile").removeClass("active");
	$("#btn-education").removeClass("active");
	$("#btn-work").removeClass("active");
	$("#btn-skills").addClass("active");
	$("#btn-lang").removeClass("active");
	$("#btn-other").removeClass("active");
	
	$("#edit-profile").css("display", "none");
	$("#edit-education").css("display", "none");
	$("#edit-work").css("display", "none");
	$("#edit-skills").css("display", "flex");
	$("#edit-lang").css("display", "none");
	$("#edit-other").css("display", "none");
}

const selectLangs = function(){
	$("#btn-profile").removeClass("active");
	$("#btn-education").removeClass("active");
	$("#btn-work").removeClass("active");
	$("#btn-skills").removeClass("active");
	$("#btn-lang").addClass("active");
	$("#btn-other").removeClass("active");
	
	$("#edit-profile").css("display", "none");
	$("#edit-education").css("display", "none");
	$("#edit-work").css("display", "none");
	$("#edit-skills").css("display", "none");
	$("#edit-lang").css("display", "flex");
	$("#edit-other").css("display", "none");
}

const selectOther = function(){
	$("#btn-profile").removeClass("active");
	$("#btn-education").removeClass("active");
	$("#btn-work").removeClass("active");
	$("#btn-skills").removeClass("active");
	$("#btn-lang").removeClass("active");
	$("#btn-other").addClass("active");
	
	$("#edit-profile").css("display", "none");
	$("#edit-education").css("display", "none");
	$("#edit-work").css("display", "none");
	$("#edit-skills").css("display", "none");
	$("#edit-lang").css("display", "none");
	$("#edit-other").css("display", "flex");
}

$("#btn-profile").on("click", selectProfile);
$("#btn-education").on("click", selectEducation);
$("#btn-work").on("click", selectWork);
$("#btn-skills").on("click", selectSkills);
$("#btn-lang").on("click", selectLangs);
$("#btn-other").on("click", selectOther);


$.urlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return results[1] || 0;
}

try {
	switch($.urlParam('tab')) {

		case '2':
			selectEducation();
			break;
		case '3':
			selectWork();
			break;
		case '4':
			selectSkills();
			break;
		case '6':
			selectOther();
			break;
		default:
			selectProfile();
	} 
} catch(e){}

$("#add-newEducation-template").on("click", function(){ 
	$("#addEducation").css("display", "flex");
});
$("#cancelEducation").on("click", function(){
	$("#addEducation").css("display", "none");
});

$("#add-newWork-template").on("click", function(){ 
	$("#addWork").css("display", "flex");
});
$("#cancelWork").on("click", function(){
	$("#addWork").css("display", "none");
});