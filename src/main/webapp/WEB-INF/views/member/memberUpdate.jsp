<%@page import="com.cortles.project.member.model.vo.Gender"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/signup.css" />
<%
	 String genre = loginMember.getFavoriteGenre();
	// 선호장르 List로 처리하기
	List<String> genres = null;
	if(genre != null){
		genres = Arrays.asList(genre.split(","));
		System.out.println(genres);
	}
%>
<section id=enroll-container>
	<h2>회원정보 수정</h2>
	<form 
		name="memberEnrollFrm"
		action="<%= request.getContextPath() %>/member/memberUpdate"
		method="POST">
		<table>
		<input type="hidden"  name="memberId" id="memberId" value="<%= loginMember.getMemberId() %>">
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="name" id="name" value="<%= loginMember.getMemberName() %>" required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="<%= loginMember.getBirthday() %>"><br />
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= loginMember.getEmail() %>"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" minlength="11" maxlength="11" value="<%= loginMember.getPhone() %>" required><br>
				</td>
			</tr>
			<tr>
				<th>성별<sup>*</sup></th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M" <%= loginMember.getGender() == Gender.M ? "checked" : "" %>>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F" <%= loginMember.getGender() == Gender.F ? "checked" : "" %>>
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>선호 장르 </th>
				<td>
					<label for="genre0">호러</label>
					<input type="checkbox" name="genre" id="genre0" value="호러" <%= genres != null && genres.contains("호러") ? "checked" : "" %>>
					<label for="genre1">로맨스</label>
					<input type="checkbox" name="genre" id="genre1" value="로맨스" <%= genres != null && genres.contains("로맨스") ? "checked" : "" %>>
					<label for="genre2">코미디</label>
					<input type="checkbox" name="genre" id="genre2" value="코미디" <%= genres != null && genres.contains("코미디") ? "checked" : "" %>>
					<br/>
					<label for="genre3">액션</label>
					<input type="checkbox" name="genre" id="genre3" value="액션" <%= genres != null && genres.contains("액션") ? "checked" : "" %>>
					<label for="genre4">스릴러</label>
					<input type="checkbox" name="genre" id="genre4" value="스릴러" <%= genres != null && genres.contains("스릴러") ? "checked" : "" %>>
					<label for="genre4">SF</label>
					<input type="checkbox" name="genre" id="genre5" value="SF" <%= genres != null && genres.contains("SF") ? "checked" : "" %>>
					<br/>
					<label for="genre4">애니메이션</label>
					<input type="checkbox" name="genre" id="genre6" value="애니메이션" <%= genres != null && genres.contains("애니메이션") ? "checked" : "" %>>
					<label for="genre4">뮤지컬</label>
					<input type="checkbox" name="genre" id="genre7" value="뮤지컬" <%= genres != null && genres.contains("뮤지컬") ? "checked" : "" %>>
					<br/>
					<label for="genre4">판타지</label>
					<input type="checkbox" name="genre" id="genre8" value="판타지" <%= genres != null && genres.contains("판타지") ? "checked" : "" %>>
					<label for="genre4">재난</label>
					<input type="checkbox" name="genre" id="genre9" value="재난" <%= genres != null && genres.contains("재난") ? "checked" : "" %>>
					<label for="genre4">범죄</label>
					<input type="checkbox" name="genre" id="genre10" value="범죄" <%= genres != null && genres.contains("범죄") ? "checked" : "" %>>
				</td>
			</tr>
		</table>
		<input type= "button" value="탈퇴" onclick="deleteMember()">
		<input type="submit" value="수정" >
		<input type="reset" value="취소" onclick="location.href='<%= request.getContextPath() %>';">
	</form>
</section>
<form
	name="delMemberIdFrm"
	action="<%= request.getContextPath() %>/member/memberDelete"
	method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>">
</form>
<script>
deleteMember = () => {
	if(!confirm("탈퇴하면 기능 이용이 제한되는데 정말로 탈퇴하실건가요..?😥")) return;
	document.delMemberIdFrm.submit();
}

// 폼 유효성검사
document.memberEnrollFrm.onsubmit = (e) => {
	const frm = e.target;
	const memberId = e.target.memberId;
	const name = e.target.name;
	const phone = e.target.phone;
	const idValid = document.querySelector("#idValid");

	// 이름 검사 - 한글2글자 이상
	if (!/^[가-힣]{2,}$/.test(name.value)) {
		alert("이름은 한글2글자 이상이어야 합니다.");
		return false;
	}
	// 전화번호 검사 - 01012345678 010으로 시작하고 숫자8자리 여부 확인
	if (!/^010\d{8}$/.test(phone.value)) {
		alert("전화번호는 010으로 시작하고 숫자8자리여야 합니다.");
		return false;
	}
};

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>