<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <c:import url="/common/HeadTag.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
	//웹소켓 변수 선언
	var wsocket;
	
	function connect() { //입장 버튼 클릭시 작동 함수(웹소켓 생성)
		wsocket = new WebSocket("ws://192.168.6.15:8090/EmpManager/Chat-ws.do");
		
		//해당 함수 정의
		wsocket.onopen = onOpen; 
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
	}
	
	function disconnect() { //나가기 버튼 클릭 시
		send("system", $("#nickname").val()+"님 나가셨습니다.");	
		wsocket.close();
	}
	
	function onOpen(evt) {
		send("system", $("#nickname").val()+"님 접속하셨습니다.");	
	}
	
	function onMessage(evt) { // "message" 이름의 MessageEvent 이벤트가 발생하면 처리할 핸들러
		console.log(evt.data);
		var data = JSON.parse(evt.data);
		console.log(data.sender);
		console.log(data.message);
		appendMessage(data);
	}
	
	function onClose(evt) {
	
	}
	
	function send(cmd, message) {
		let data = { cmd : cmd,
						  sender : $("#nickname").val(),
						  message : message 
						};
		wsocket.send(JSON.stringify(data));
		$("#message").val("");
	}


	function appendMessage(data) {
		console.log(data);
		if (data.auth == "my") {
			$("#chatMessageArea").append(
					"<span id='msgmy' style=''><b>" + data.message + " : "
							+ data.sender + "</b></span>" + "<br>");
		} else if (data.auth == "system") {
			$("#chatMessageArea").append(
					"<span id='msgCenter'><b>" + data.sender + " : "
							+ data.message + "</b></span>" + "<br>");
		} else {
			$("#chatMessageArea").append(
					"<span id='msgother'><b>" + data.message + "</b></span>"
							+ "<br>");
		}

		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
	}

	$(function() {
		$('#message').keypress(function(event) {
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if (keycode == '13') {
				send("message", $("#message").val());
			}
			event.stopPropagation();
		});

		$('#sendBtn').click(function() {
			send("message", $("#message").val());
		});
		$('#enterBtn').click(function() {
			connect();
		});
		$('#exitBtn').click(function() {
			disconnect();
		});
	})
</script>
</head>
<style>
#chatArea {
height: 300px; overflow-y: auto; border: 1px solid black;
}
#memberArea{
	height:380px;
	border: 1px solid black;
}
#msgmy{
color: red;
float: right;
clear: both;
}
#msgother{
color: blue;
float: left;
clear: both;
}
#msgCenter{
color: black;

}
</style>
<body id="page-top">
    <!-- Top -->
    <c:import url="/common/Top.jsp"/>
    <div id="wrapper">
        <!-- Left Menu -->
        <c:import url="/common/Left.jsp"/>

        <div id="content-wrapper">

            <!-- !! Content !! -->
            <div class="container-fluid">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fas fa-user-check"></i> 실시간 채팅
                    </div>
                    <div class="card-body">
                    <div class = "row">
                    <div class = "col-md-9">
                       	<div class="table-responsive">
						    이름 : <input type="text" id="nickname">
							<input type="button" id="enterBtn" value="입장">
							<input type="button" id="exitBtn" value="나가기">
						    <br>
						    <br>
						    <h5>채팅방</h5>
						    <div id="chatArea"><div id="chatMessageArea"></div></div>
						    <br/>
						    <input type="text" id="message">
						    <input type="button" id="sendBtn" value="전송">
                         </div>
                         </div>
                        <div class = "col-md-3">
                        <h5>대화 상대</h5>
                        <div id="memberArea">
                        
                        </div>
                        </div>
                         </div>
                      </div>
                    </div>
                </div>

            <!-- Bottom -->
            <c:import url="/common/Bottom.jsp"/>
        </div>
    </div>
</body>

</html>