
$("#fileImage").on("change",function(){
	console.info("233443344343");
	changImg($("#fileImage"),$("#photo"));
	initdata();
})

//$("#fileImage").change=function (){
//	console.info("233443344343");
//	changImg($("#fileImage"),$("#photo"));
//	
//	
//}

function tophoto(){

	
	$("#fileImage").click();
//	file=$("#fileImage").val();
//	
//	if(file==null||file.length<=0){
////			alert(file.length);
////			 var fils=$("#fileImage").get(0).files[0];  
////			 var srcc=windowURL.createObjectURL(fils);
////			$("#photo").attr("src",srcc);
//		console.info("123");
//		
//	}else{
//		
////		 var fils=$("#fileImage").get(0).files[0];  
////		var srcc=windowURL.createObjectURL(fils);
////		$("#photo").attr("src",srcc);
////		 changImg($("#fileImage"),$("#photo"));
//	}
	
}


function changImg(fil,obj){  
//	 var fils=$("#fileImage").get(0).files[0];  
	 var fils=fil.get(0).files[0];  
	 var windowURL = window.URL || window.webkitURL;
	 var srcc=windowURL.createObjectURL(fils);
//	 var srcc=window.URL.createObjectURL(file);    
//	$("#photo").attr("src",srcc);
	obj.attr("src",srcc);
	
	
}  
/*
三个参数
file：一个是文件(类型是图片格式)，
w：一个是文件压缩的后宽度，宽度越小，字节越小
objDiv：一个是容器或者回调函数
photoCompress()
 */
function photoCompress(file,w,objDiv){
    var ready=new FileReader();
        /*开始读取指定的Blob对象或File对象中的内容. 当读取操作完成时,readyState属性的值会成为DONE,如果设置了onloadend事件处理程序,则调用之.同时,result属性中将包含一个data: URL格式的字符串以表示所读取文件的内容.*/
        ready.readAsDataURL(file);
        ready.onload=function(){
            var re=this.result;
            canvasDataURL(re,w,objDiv)
        }
}
function canvasDataURL(path, obj, callback){
     var img = new Image();
     img.src = path;
     img.onload = function(){
      var that = this;
      // 默认按比例压缩
      var w = that.width,
       h = that.height,
       scale = w / h;
       w = obj.width || w;
       h = obj.height || (w / scale);
      var quality = 0.7;  // 默认图片质量为0.7
      //生成canvas
      var canvas = document.createElement('canvas');
      var ctx = canvas.getContext('2d');
      // 创建属性节点
      var anw = document.createAttribute("width");
      anw.nodeValue = w;
      var anh = document.createAttribute("height");
      anh.nodeValue = h;
      canvas.setAttributeNode(anw);
      canvas.setAttributeNode(anh); 
      ctx.drawImage(that, 0, 0, w, h);
      // 图像质量
      if(obj.quality && obj.quality <= 1 && obj.quality > 0){
       quality = obj.quality;
      }
      // quality值越小，所绘制出的图像越模糊
      var base64 = canvas.toDataURL('image/jpeg', quality);
      // 回调函数返回base64的值
      callback(base64);
    }
}
/**  
 * 将以base64的图片url数据转换为Blob  
 * @param urlData  
 *            用url方式表示的base64图片数据  
 */  
function convertBase64UrlToBlob(urlData){  
    var arr = urlData.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type:mime});
} 

function initdata(){
	console.info("执行上传。。。");
	//上传
//	var cream=document.getElementById("BotanyDTOcamera");
	var file=$("#fileImage").get(0).files[0];
//	var cream = $("#BotanyDTOcamera");
//	var fliw=cream[0];
	var form = new FormData();
	
	if(file.size>4*1024*1024){
		   photoCompress(file, {
               quality: 0.2
           }, function(base64Codes){
               //console.log("压缩后：" + base.length / 1024 + " " + base);
               var bl = convertBase64UrlToBlob(base64Codes);
               form.append("file", bl, "file_"+Date.parse(new Date())+".jpg"); // 文件对象
               upload(form);
           });
	}else{
		 form.append("file", file); // 文件对象
		 upload(form);
	}
//	form.append('file', compress(file));//上传的文件： 键名，键值

}


function upload(form){
	$.ajax({
        url:"ocr/botany.do",
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
  						var score=value.score.toFixed(4);
//  						var size = (value.size / 1024).toFixed(2);
    						versiontext +='<td class="infoFont">'+value.name+'</td><td class="infoFont">'+score+'</td></tr>';	
    					})
		
    					$("#BotanyDTOtbody").html(versiontext);
      	  }else{
      			alert(obj.message);
      	  }
        }
});	
}
