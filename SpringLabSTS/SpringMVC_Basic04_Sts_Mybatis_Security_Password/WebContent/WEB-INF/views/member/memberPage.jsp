<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
	<form  method="post">
		<h2> 마이페이지</h2>
		<h3 class="hidden">방문페이지 로그</h3>
		<p id="breadscrumb" class="block_hlist clear">
			<img alt="Step1 개인정보 등록" src="images/step2.png" />
		</p>
		<h3 class="hidden">회원 정보 수정</h3>
		<div id="join-form" class="join-form margin-large">
			<dl class="join-form-row">
				<dt class="join-form-title">아이디</dt>
				<dd class="join-form-data">
					<input type="text" name="userid" value="${member.userid}" readonly/> 
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">이름</dt>
				<dd class="join-form-data">
					<input type="text" name="name" value="${member.name}" readonly/>
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">성별</dt>
				<dd class="join-form-data">
					<select name="gender" readonly>
						<option>남성</option>
						<option>여성</option>
					</select>
				</dd>
			</dl>
			<dl class="join-form-row birthday">
				<dt class="join-form-title">생년월일</dt>
				<dd class="join-form-data">
					<span> 
						<input type="text" id="year" name="year" readonly/>년 
						<input type="text" id="month" name="month" readonly/>월 
						<input type="text" id="day" name="day" readonly/>일 
						<input type="hidden" name="Birth" id="Birth" readonly />
					</span> 
					<span class="moon"> <input type="radio" name="IsLunar"
						value="Solar" id="IsSolar" checked readonly/>양력 <input type="radio"
						name="IsLunar" value="Lunar" id="IsLunar" readonly/>음력
					</span>
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">핸드폰 번호</dt>
				<dd class="join-form-data">
					<input type="text" name="cphone" value="${member.cphone}" readonly/>
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">이메일</dt>
				<dd class="join-form-data">
					<input type="text" name="email" value="${member.email}" readonly/>
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">취미</dt>
				<dd class="join-form-data habit">
					<input type="checkbox" name="habit" id="music" readonly/>
					<label for="music">음악</label> 
					<input type="checkbox" name="habit" id="movie" readonly/>
					<label for="movie">영화</label> 
					<input type="checkbox" name="habit" id="trip" readonly/>
					<label for="trip">여행</label>
				</dd>
			</dl>
		</div>
		<div id="buttonLine">
			<input class="btn-okay button" type="submit" value="수정" />
		</div>
	</form>

</div>
