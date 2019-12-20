<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<c:import url="/common/HeadTag.jsp" />
<jsp:include page="/common/DataTableScript.jsp"></jsp:include>
<script src="js/demo/datatables_chat.js"></script>
<script type="text/javascript">
	let wsocket;
	$(function() {
		connect();
		$("#createChat").click( function() {
			backAndForth();
		});

		$(window).on("beforeunload", function(){
			disconnect();
	    });
	})
	
		
	const steps = ['방 제목', '최대 인원'];
	const swalQueueStep = Swal.mixin({
	  confirmButtonText: 'Next',
	  cancelButtonText: 'Back',
	  progressSteps: ['1','2'],
	  input: 'text',
	  inputAttributes: {
	    required: true
	  },
	  reverseButtons: true,
	  validationMessage: '필수 입력사항입니다.'
	})

	 async function backAndForth () {
	  const values = [];
	  let currentStep;

	  for (currentStep = 0; currentStep < steps.length;) {
	    const result = await swalQueueStep.fire({
	      title: steps[currentStep],
	      inputValue: values[currentStep],
	      showCancelButton: currentStep > 0,
	      currentProgressStep: currentStep
	    })
	
	    if (result.value) {
	      values[currentStep] = result.value
	      currentStep++
	    } else if (result.dismiss === 'cancel') {
	      currentStep--
	    } else {
	      break
	    }
	  }

	  if (currentStep === steps.length) {
	    let data = { cmd : "createChatRoom"
		    			  , sender : "${sessionScope.userid}"
	    	    		  , name : values[0]
	    	    		  , max : values[1]
	    	    		};
	    wsocket.send(JSON.stringify(data));
	  }
	}

	 function connect() { 
		wsocket = new WebSocket("ws://192.168.6.15:8090/EmpManager/Chat-ws.do");

		wsocket.onopen = onOpen;
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
	}

	function disconnect() {
		wsocket.close();
	}
	
	function onOpen(evt) {
		let data = { cmd : "on" };
		wsocket.send(JSON.stringify(data));
	}
	
	function onMessage(evt) { // "message" 이름의 MessageEvent 이벤트가 발생하면 처리할 핸들러
		var data = JSON.parse(evt.data);
		setChatRooms(data);
		
	}
	
	function onClose(evt) {
	}

	function setChatRooms(data){
		let num = 1;
		$('#dataTable > tbody').empty();
		$.each(JSON.parse(data.rooms), function(index, element){
			let room = $("<tr></tr>");
			room.append("<td>"+(num++)+"</td>");
			room.append("<td>"+element.name+"</td>");
			room.append("<td>"+element.users.length+" / "+element.max+"</td>");
			room.append("<td>"+element.owner+"</td>");
			 $('#dataTable > tbody').append(room);
		})
	}
    
</script>
<style type="text/css">
	.iconColumn {
			width: 100px;
			text-align: center;
		}
</style>
</head>

<body id="page-top">
	<!-- Top -->
	<c:import url="/common/Top.jsp" />
	<div id="wrapper">
		<!-- Left Menu -->
		<c:import url="/common/Left.jsp" />

		<div id="content-wrapper">

			<!-- !! Content !! -->
			<div class="container-fluid">
				<div class="card mb-3">
					<div class="card-header"> <i class="fas fa-comments"></i> 실시간 채팅 </div>
					<div class="card-body">
						<button id="createChat" class="btn btn-primary mb-3" type="button">채팅방 만들기</button>
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th width="10%">NO</th>
                                            <th width="70%">NAME</th>
                                            <th width="10%">USER</th>
                                            <th width="10%">OWNER</th>
                                        </tr>
                                    </thead>
									<tbody>
											
									</tbody>
								</table>
								</div>
							</div>
						</div>

			</div>
	

			<!-- Bottom -->
			<c:import url="/common/Bottom.jsp" />
		</div>
	</div>
</body>

</html>