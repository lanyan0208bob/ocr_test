

function upload(form){
	$.ajax({
        url:"../ocr/food.do",
        type:"post",
        data:form,
        processData:false,
        contentType:false,
        success:function(obj){
      	  if (obj.code == '1') {// 成功
//				alert("成功");
      		  var versiontext = '';
      		  $
    			.each(
    					obj.data,
    					function(index, value) {
    						if (index%4 == 0) {//
  							versiontext += '<tr class="success">'
  						} else if((index%4 == 1)) {// 
  							versiontext += '<tr class="danger">'
  						}else if((index%4 == 2)) {// 
  							versiontext += '<tr class="active">'
  						}else {// 
  							versiontext += '<tr class="warning">'
  						}
//  						var score=value.probability.toFixed(4);
//  						var calorie=value.calorie.toFixed(4);
//  						var size = (value.size / 1024).toFixed(2);
    						versiontext +='<td class="infoFont">'+value.name+'</td><td class="infoFont">'+value.calorie+'</td><td class="infoFont">'+value.probability+'</td></tr>';	
    					})
		
    					$("#BotanyDTOtbody").html(versiontext);
      	  }else{
      			alert(obj.message);
      	  }
        }
});	
}
