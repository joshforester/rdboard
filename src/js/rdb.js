function PadDigits(n, totalDigits){ 
   n = n.toString(); 
   var pd = ''; 
   if (totalDigits > n.length) { 
     for (i=0; i < (totalDigits-n.length); i++) { 
       pd += '0'; 
     } 
   } 
   return pd + n.toString(); 
} 


$(document).ready(function(){

  $('div#submit').click(function(){ 
    $.blockUI({ 
      message: '<h1>Submitting Data.</h1>',
      css: { 
        top: '10px', 
        left: '10px', 
        right: '',
        border: '3px solid #000'
      },
      overlayCSS: { 
        background: '#000000 url(http://lh3.ggpht.com/_Njaf48TXR0g/Szv0iMv_o9I/AAAAAAAAAxc/qsjh0bwnRCw/rdblogo.gif) no-repeat fixed bottom center'
       }
    }); 
  }); 

  $("span.ts").each(function(){
	  $(this).fmttime();
      });

  $('#slider').s3Slider({
	  timeout:3000,
	  fadeTime:500
  });

});
    
$.fn.fmttime = function() {
    var millis = $(this).html().split("|")[0];
    var fmt = $(this).html().split("|")[1];
    var d = new Date(parseInt(millis));
    var mm = PadDigits(d.getMinutes(), 2);
    var HH = PadDigits(d.getHours(), 2);
    var dd = PadDigits(d.getDate(), 2);
    var MM = PadDigits(d.getMonth() + 1, 2);
    var yyyy = PadDigits(d.getFullYear(), 4);
    var dstr = fmt;
    dstr = dstr.replace("mm", mm);
    dstr = dstr.replace("HH", HH);
    dstr = dstr.replace("dd", dd);
    dstr = dstr.replace("MM", MM);
    dstr = dstr.replace("yyyy", yyyy);
    $(this).parent().html(dstr);
};

$.fn.pause = function(milli,type) {
    milli = milli || 1000;
    type = type || "fx";
    return this.queue(type,function(){
	    var self = this;
	    setTimeout(function(){
		    $.dequeue(self);
		},milli);
	});
};

$.fn.clearQueue = $.fn.unpause = function(type) {
    return this.each(function(){
	    type = type || "fx";
	    if(this.queue && this.queue[type]) {
		this.queue[type].length = 0;
	    }
	});
};
