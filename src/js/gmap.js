$(document).ready(function(){

    var $tabs = $("#tabs").tabs({
	    cache: true,
	    ajaxOptions: {
		dataFilter: function(data, type) {
		    return $(data).find('#focal').html();
		}
	    }
    });
    
});
