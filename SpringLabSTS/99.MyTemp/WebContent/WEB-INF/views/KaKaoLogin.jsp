<html lang="ko">
<head>
<title>카카오 로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
<a id="kakao-login-btn"></a>
<a href="http://developers.kakao.com/logout"></a>
<script type='text/javascript'>
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('5d151c02cc241d9ba7a8373a8051d79d');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
         console.log("success");
        alert(JSON.stringify(authObj));
      },
      fail: function(err) {
    	  console.log("error");
         alert(JSON.stringify(err));
      }
    });
  //]]>
</script>

</body>
</html>