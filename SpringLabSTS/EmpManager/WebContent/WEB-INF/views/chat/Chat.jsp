<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<c:import url="/common/HeadTag.jsp" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link href="https://fonts.googleapis.com/css?family=Gothic+A1|Hi+Melody|Jua|Nanum+Pen+Script&display=swap"
	rel="stylesheet">
<script type="text/javascript">
	//웹소켓 변수 선언
	var wsocket;

	$(function() {
		connect();
		$('#message').keypress(function(event) {
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if (keycode == '13') {
				send($('#message').val());
			}
			event.stopPropagation();
		});

		$('#sendBtn').click(function() {
			send($('#message').val());
		});

		$('#exitBtn').click(function() {
			disconnect();
		});

		$("#font").change(function() {
			$("#chatMessageArea").attr("style", "font-family:"+$("#font option:selected").val()+"\"");
		});

		  $("#smile").click(function(){
	          var input = $( "#message" );
	          input.val( input.val() + "😃"  );
	       });   

	       $("#heart").click(function(){
	          var input = $( "#message" );
	          input.val( input.val() + "😍"  );
	       });   

	       $("#emoBtn").click(function(){
	          $("#emojiBox").toggle();
	       });
	       
	       $(window).on("beforeunload", function(){
				disconnect();
		    });
	})
	
	function connect() { //입장 버튼 클릭시 작동 함수(웹소켓 생성)
		wsocket = new WebSocket("ws://192.168.6.15:8090/EmpManager/Chat-ws.do");

		//해당 함수 정의
		wsocket.onopen = onOpen;
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
	}
	
	function disconnect() {
		wsocket.close();
	}
	
	function onOpen(evt) {
		let data = { sender : "${sessionScope.userid}"
			, cmd : "joinChatRoom"
			, room : "${room}"	 
			};

		wsocket.send(JSON.stringify(data));
	}

	function onMessage(evt) { // "message" 이름의 MessageEvent 이벤트가 발생하면 처리할 핸들러
		var data = JSON.parse(evt.data);
		console.log(data.sender);
		console.log(data.message);
		appendMessage(data);
	}

	function onClose(evt) {
		$("#chatMessageArea").empty();
		$("#nickname").attr("disabled", false);
		$("#memberArea").empty();
		$("#memberArea").hide();
	}

	function send(message) {
		let data = { message : message
						, cmd : "message"
						, sender :  "${sessionScope.userid}"
						, room : "${room}"
						 };
		
		wsocket.send(JSON.stringify(data));
		$("#message").val("");
	}

	function appendMessage(data) {
		console.log("chat message");
		console.log(data);
		if (data.type == "my") {
			$("#chatMessageArea").append(
					"<span id='msgmyname'><b><i class='far fa-grin-alt'></i> "
							+ data.sender + "&nbsp;&nbsp;&nbsp;</b></span>" + "<br>"
							+ "<span id='msgmy'><b>" + data.message
							+ "&nbsp;&nbsp;&nbsp;</b></span>" + "<br>");

		} else if(data.type == "memberInfo"){
			$("#chatMessageArea").append(
					"<div id='msgsystem' class='text-centet'><b>"
							 + data.message
							+ "&nbsp;&nbsp;&nbsp;</b></div>" + "<br>");
			
			setChattingMember(data.owner, data.users);
		} else {
			$("#chatMessageArea").append(
					"<span id='msgothername'><b>&nbsp;&nbsp;&nbsp;<i class='fas fa-grin-alt'></i> "
							+ data.sender + "</b></span>" + "<br>"
							+ "<span id='msgother'><b>&nbsp;&nbsp;&nbsp;"
							+ data.message + "</b></span>" + "<br>");
		}

		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
	}
	
	function setChattingMember(owner, members){
		$("#memberArea").empty();
		$.each(members, function(index, element){
			let sp = $("<span></span>");
			if(element == "${sessionScope.userid}")
				sp.css("background-color","yellow");
			if(element == owner)
				sp.append("👑");
			
			sp.append(element);
			$("#memberArea").append(sp);
		})
	}
	
</script>
</head>
<style>
#chatArea, #memberArea {
	height: 300px;
	overflow-y: auto;
	border: 1px solid black;
}

#memberArea {
	height: 380px;
	border: 1px solid black;
}

#msgmyname {
	float: right;
	clear: both;
}

#msgmy {
	color: #800040;
	float: right;
	clear: both;
	font-size: 18px;
}

#msgother {
	color: #006699;
	float: left;
	clear: both;
	font-size: 18px;
}

#msgothername {
	float: left;
	clear: both;
}

</style>
<body id="page-top">
	<!-- !! Content !! -->
			<div class="container-fluid">
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-comments"></i> ${room}
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-7">
								<div class="table-responsive">
									<h5>채팅방</h5>
									<div id="chatArea">
										<div id="chatMessageArea"></div>
									</div>
									<br />
									<div id="inputBox">
					                      <input type="button" id="emoBtn" value="이모티콘">
										<input type="text" id="message" style="width: 400px;">
					                      <input type="button" id="sendBtn" value="전송">
					                      <br>
					                      <div id="emojiBox" style="border: 1px solid gray;">
					                      <a type="button" id="smile">&#x1F603;</a> | <a type="button" id="heart">&#x1F60D;</a> | <a>&#x1F62D;</a> | <a>&#x1F620;</a> | <a>&#x1F631;</a>
				                            </div>
									</div>
								</div>
							</div>
							<!-- <div class="col-md-1"></div> -->
							<div class="col-md-4">
								<div class="changebox" style="float: right;">
									<select id="font" style="height: 30px; width: 250px;">
										<option hidden>채팅 글꼴 설정</option>
										<option value="'Nanum Pen Script', cursive; font-size: 20px"
											style="font-family: 'Nanum Pen Script', cursive;">나눔펜체</option>
										<option value="'Jua', sans-serif;" style="font-family: 'Jua', sans-serif;">주아체</option>
										<option value="'Hi Melody', cursive;"
											style="font-family: 'Hi Melody', cursive;">하이멜로디체</option>
										<option value="'Gothic A1', sans-serif;"
											style="font-family: 'Gothic A1', sans-serif;">고딕체</option>
									</select> 
								</div>
							
								<br><br>
								<h5 class="text-right">접속자</h5>
								<div id="memberArea">
										
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
</body>

</html>