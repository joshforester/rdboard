$(document).ready(function(){

    var $tabs = $("#tabs").tabs({
	    cache: false,
	    ajaxOptions: {
		dataFilter:function(data, type) {
		    return $(data).find('#focal').html();
		},
		complete:function() {
		    $("span.ts").each(function(){
			    $(this).fmttime();
		    });
		}
	    }
    });

});
