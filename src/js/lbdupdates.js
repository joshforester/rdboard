function attemptNoUpdatesState(){ 
    // if no_updates is first element and show if so
    if ( $("p#updates_ctn > span.attval:first").attr('id') == "no_updates") {
	$("p#updates_header").hide();
	$("span#no_updates").show();
    }
} 

$(document).ready(function(){

    // get the event/course/team id for use with ajax
    $("div#updateUrl").each(
      function(){
        var url = $(this).html();
	var updatesInterval = $("div#updatesInterval").html();
	var updatesLookback = $("div#updatesLookback").html();
	var updatesTimeout = parseInt($("div#updatesTimeout").html());

	$("p#updates_ctn span.attval").each(function(){
		if ( $(this).attr('id') != "no_updates") {
		    $(this).pause(updatesTimeout).fadeOut(1000, function() {
			    $(this).remove();
			    attemptNoUpdatesState();
		    });
		}
		
	    });

	setInterval(function(){
	  fetchUpdates(url + '.json?lookback=' + updatesLookback);
	}, updatesInterval);
    });
    
    function fetchUpdates(url){
	var loadingimg = $("div#loadingImg").html();
	var updatesTimeout = parseInt($("div#updatesTimeout").html());
	$("div#loading_updates").html(loadingimg);

      	//$("div#updates").load(url + " div#updates > *");
	$.getJSON(url, function(json){
		if ( $("p#updates_ctn > span.attval:first").attr('id') == "no_updates") {
		  // if not empty and no_updates is first element, hide.
		  if ( json.allLbdUpdates.length > 0 ) {
		      $("span#no_updates").hide();
		      $("p#updates_header").show();
		  }
		}
	  $.each(json.allLbdUpdates, function(i,lbdUpdate){
	    var d = new Date(lbdUpdate.time);
	    $('<span class="attval"><span class="att">' + PadDigits(d.getHours(), 2) + ':' + PadDigits(d.getMinutes(), 2) + '</span> <span class="att">' + lbdUpdate.type + '</span> ' + lbdUpdate.message + '</span>').insertBefore("p#updates_ctn > span.attval:first").fadeIn(1000).pause(updatesTimeout).fadeOut(1000, function() {
 		    $(this).remove(); 
		    attemptNoUpdatesState();
		});
	  });
	});
	$("div#loading_updates").html("");
    };

    
});
