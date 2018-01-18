<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head runat="server">
    <title></title>
    <script language="javascript" type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script language="javascript" type="text/javascript">
        window.addEventListener('DOMContentLoaded', function () {
            'use strict';
            var video = document.querySelector('video');

            function successCallback(stream) {
                // Set the source of the video element with the stream from the camera
                if (video.mozSrcObject !== undefined) {
                    video.mozSrcObject = stream;
                } else {
                    video.src = (window.URL && window.URL.createObjectURL(stream)) || stream;
                }
                video.play();
            }

            function errorCallback(error) {
                console.error('An error occurred: [CODE ' + error.code + ']');
                // Display a friendly "sorry" message to the user
            }

            navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
            window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;

            // Call the getUserMedia method with our callback functions
            if (navigator.getUserMedia) {
                navigator.getUserMedia({ video: true }, successCallback, errorCallback);
            } else {
                console.log('Native web camera streaming (getUserMedia) not supported in this browser.');
                // Display a friendly "sorry" message to the user
            }
        }, false);

    </script>
</head>
<body>

    <video id="myVideo" autoplay="autoplay" Width="400px" Height="300px"></video>
    <br />
    <input type="button" value="拍照" /><br />
    拍照結果：
    <div id="result">
    </div>
    <script type="text/javascript">
        $(document).ready(init);
        function init() {
            //Google Chrome要用webkitGetUserMedia函式  
            //navigator.webkitGetUserMedia("video", success);  //顯示影像 

            //定義button點選後要做什麼  
            $("input[type='button']").click(function () {
                shoot(); //執行拍照  
            });
        }

        function success(stream) {
            $("#myVideo").attr("src", window.webkitURL.createObjectURL(stream));
        }

        //執行拍照  
        function shoot() {
            var video = $("#myVideo")[0];
            var canvas = capture(video);

            $("#result").empty();
            $("#result").append(canvas); //呈現圖像(拍照結果) 

            var imgData = canvas.toDataURL("image/jpg");
            var base64String = imgData.substr(22); //取得base64字串 
			alert(base64String);
            //上傳，儲存圖片  
           // $.ajax({
              //   url: "Handler.ashx",
              //   type: "post",
               //  data: { data: base64String },
              //   async: true,
               //  success: function (htmlVal) {
                //     alert("另存圖片成功！");
                // }, error: function (e) {
               //      alert(e.responseText); //alert錯誤訊息  
            //    //  }
           //  });
        }

        //從video元素抓取圖像到canvas  
        function capture(video) {
            var canvas = document.createElement('canvas'); //建立canvas js DOM元素  
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
            var ctx = canvas.getContext('2d');
            ctx.drawImage(video, 0, 0);
            return canvas;
        } 

    </script>
</body>
</html>