
/**
 * 拍照照片切换 重新初始化展示的div-img
 */
$("#fileImage").on("change",function(){

	changImg($("#fileImage"),$("#photo"));
	initdata();
})

/**
 * 初始化 div-img
 * @param fil 
 * @param obj
 */
function changImg(fil,obj){  
	 var fils=fil.get(0).files[0];  
	 var windowURL = window.URL || window.webkitURL;
	 var srcc=windowURL.createObjectURL(fils);
	obj.attr("src",srcc);
	
	
}  

/**
 * 触发隐藏的拍照Input
 */
function tophoto(){
	$("#fileImage").click();
}

/**
 * 上传照片
 */
function initdata(){
	console.info("执行上传。。。");
	var file=$("#fileImage").get(0).files[0];
	var form = new FormData();
	if(file.size>4*1024*1024){
		   photoCompress(file, {
               quality: 0.7//,width:100,height:2000
           }, function(base64Codes){
               var bl = convertBase64UrlToBlob(base64Codes);
               form.append("file", bl, "file_"+Date.parse(new Date())+".jpg"); // 文件对象
               upload(form);
           });
	}else{
		 form.append("file", file); // 文件对象
		 upload(form);
	}

}



function  toback(){
//	window.location.go(-1);
	window.history.back();
}