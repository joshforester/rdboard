$(document).ready(function(){

  // collapse all advanced options
  $("div.adv_form").hide();
  
  //toggle the componenet with class msg_body
  $("div.basic_form div.adv_tab").click(function(){
     $(this).toggleClass('clicked');
     $(this).parent("div.row").parent("div.basic_form").next("div.adv_form").slideToggle(500);
   });

  // enable tooltip for "download" element. use the "slide" effect 
  $("img.rdb_err").tooltip({ effect: 'slide'});    

  
  var isAcquiredToggleStatus = function(){

      var id = $(this).attr('id');
      var i = id.split("]")[0];
      var i = i.split("[")[1];
      
      if ($(this).is(':checked')) {
	  $('#batch' + i + '\\.arrival').attr('disabled','disabled');
	  $('#batch' + i + '\\.departure').attr('disabled','disabled');
	  $('#batch\\[' + i + '\\]\\.isSkipped1').attr('disabled','disabled');
      } else {
	  $('#batch' + i + '\\.arrival').removeAttr('disabled');
	  $('#batch' + i + '\\.departure').removeAttr('disabled');
	  $('#batch\\[' + i + '\\]\\.isSkipped1').removeAttr('disabled');
      }   
      
  };
  
  var isSkippedToggleStatus = function(){

      var id = $(this).attr('id');
      var i = id.split("]")[0];
      var i = i.split("[")[1];
      
      if ($(this).is(':checked')) {
	  $('#batch\\[' + i + '\\]\\.isAcquired1').attr('disabled','disabled');
	  $('#batch' + i + '\\.arrival').attr('disabled','disabled');
	  $('#batch' + i + '\\.departure').attr('disabled','disabled');
      } else {
	  $('#batch\\[' + i + '\\]\\.isAcquired1').removeAttr('disabled');
	  $('#batch' + i + '\\.arrival').removeAttr('disabled');
	  $('#batch' + i + '\\.departure').removeAttr('disabled');
      }   
      
   };

   $("input.isAcquired:not(:disabled)").each(isAcquiredToggleStatus);
   $("input.isAcquired").change(isAcquiredToggleStatus);
   $("input.isSkipped:not(:disabled)").each(isSkippedToggleStatus);
   $("input.isSkipped").change(isSkippedToggleStatus);

});


