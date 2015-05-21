$(document).ready(function(){
    lastColumn = $("ul.sldr li.sldr div.expdcol");
    maxColWidth = 200;
    minColWidth = 18;	
    lastRow = $("ul.sldr li.sldr div.expdrow");
    maxRowHeight = 200;
    minRowHeight = 18;
    lbdIframe = $("div#lbd");
    lbdHeight = 515;


    $("div#lbd_resize").click(
      function(){
	 $(lbdIframe).each(
	    function( intIndex ){
	      if ($(this).hasClass("expdlbd")) {
		$(this).animate({height: lbdHeight+"px"}, { queue:false, duration:500 });
	      } else {
	        $(this).animate({height: $("div#expdlbdheight").html()+"px"}, { queue:false, duration:500 });
	      }
	      $(this).toggleClass('expdlbd');
	    }
	 );
      }
    );

    $("ul.sldr li.sldr div.sldr").click(
      function(){
	  
	$(lastColumn).each(
	  function ( intIndex ) {
	      if (!$(this).hasClass("expdrowhdr") && !$(this).hasClass("expdtblhdr")) {
	        $(this).animate({width: minColWidth+"px"}, { queue:false, duration:500 });
	      }
	      $(this).toggleClass('expdcol');
          }
        );

	$(lastRow).each(
	  function ( intIndex ) {
	      if (!$(this).hasClass("expdcolhdr") && !$(this).hasClass("expdtblhdr")) {
	        $(this).animate({height: minRowHeight+"px"}, { queue:false, duration:500 });
	      }
	      $(this).toggleClass('expdrow');
          }
        );

	var newId = $(this).attr('id');
	var newR = newId.split("_")[0];
	var newC = newId.split("_")[1];
	//var debug = new jQuery.debug();
	//debug.dump(newId + ":" + newR + "," + newC);

	newColumn = $("div." + newC);

	$(newColumn).each(
	  function ( intIndex ) {
	    $(this).animate({width: maxColWidth+"px"}, { queue:false, duration:500});
	    $(this).toggleClass('expdcol');
          }
	);

	newRow = $("div." + newR);

	$(newRow).each(
	  function ( intIndex ) {
	    $(this).animate({height: maxRowHeight+"px"}, { queue:false, duration:500});
	    $(this).toggleClass('expdrow');
          }
	);
	
	lastColumn = $("ul.sldr li.sldr div.expdcol");
	lastRow = $("ul.sldr li.sldr div.expdrow");
      }
    );

    var $tabs = $("#tabs").tabs({
	    cache: true,
	    ajaxOptions: {
		dataFilter: function(data, type) {
		    return $(data).find('#focal').html();
		}
	    }
    });
    
});
