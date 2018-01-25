<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎页面</title>

 	<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />  
 	<link rel="stylesheet" type="text/css" href="../css/iconfont.css" />  
 	<link rel="stylesheet" type="text/css" href="../css/photo.css" />  
    <script src="../js/jquery-3.2.1.min.js"></script> 
    <script src="../js/bootstrap.min.js"></script> 
    <script src="../js/pictureUtils.js"></script>  
</head>
<body>
<div>
<span class="iconfont infoFonticonback" onclick="toback()">&#xe625;</span>



<div class="photodiv" id="photodiv">
<img alt="" src="../images/photo.png" class="photo" id="photo">

<!--
<button id="shoot" class=" photo"><p class="iconfont cameraImg ">&#xe61b;</p></button>
  <input type="file" accept="image/*" capture="camera" style="visibility: hidden;">-->

</div>

<div class="cameraDiv">
<form id="BotanyDTOcamera" method="post" enctype="multipart/form-data">
 <input type="file" accept="image/*" capture="camera" style="visibility: hidden;" id="fileImage">
 </form>
<button  class="btn btn-default camerabutton" onclick="tophoto()"><span class="iconfont infoFonticon">&#xe61b;</span></button>

</div>
<div class="infoDiv">
<table class="table-condensed table infotable">
<thead>
<tr class="active"><th class="infoFont">名称</th><th class="infoFont">相似度</th></tr>
</thead>
<tbody id="BotanyDTOtbody">
<!--  
<tr class="success"><td class="infoFont">吉娃莲</td><td class="infoFont">90%</td></tr>
<tr class="warning"><td class="infoFont">白菜</td><td class="infoFont">60%</td></tr>
<tr class="danger"><td class="infoFont">小汤山大白菜</td><td class="infoFont">30%</td></tr>-->
</tbody>
	</table>
	

</div>
<!--  
</td>
</tr>
<tr>
<td >类型:</td>
<td ><select  class="form-control">
      <option>植物识别</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select></td>
</tr>
-->

<!-- 
<p>相机</p>
<p><input type="file" accept="image/*" capture="camera"></p>
<p>类型</p>
<p>植物</p> -->
</div>
  <script src="../js/animal.js"></script> 
  <script src="../js/pictureOcr.js"></script>  
</body>

</html>