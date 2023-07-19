<%@page import="com.cortles.project.member.model.vo.Gender"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header2.jsp" %>
<!DOCTYPE html>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/memberUpdate.css" />
<%
	 String genre = loginMember.getFavoriteGenre();
	// 선호장르 List로 처리하기
	List<String> genres = null;
	if(genre != null){
		genres = Arrays.asList(genre.split(","));
		System.out.println(genres);
	}
%>
<div id="blocksection">
<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
<section id=enroll-container>
	<h2>Modifying Membership</h2>
	<form 
		name="memberEnrollFrm"
		action="<%= request.getContextPath() %>/member/memberUpdate"
		method="POST">
		<table>
		<input type="hidden"  name="memberId" id="memberId" value="<%= loginMember.getMemberId() %>">
			<tr>
				<th>Name<sup>*</sup></th>
				<td>	
				<input type="text"  name="name" id="name" value="<%= loginMember.getMemberName() %>" required><br>
				</td>
			</tr>
			<tr>
				<th>Birthday</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="<%= loginMember.getBirthday() %>"><br />
				</td>
			</tr> 
			<tr>
				<th>E-mail</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= loginMember.getEmail() %>"><br>
				</td>
			</tr>
			<tr>
				<th>Phone<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" minlength="11" maxlength="11" value="<%= loginMember.getPhone() %>" required><br>
				</td>
			</tr>
			<tr>
				<th>Gender<sup>*</sup></th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M" <%= loginMember.getGender() == Gender.M ? "checked" : "" %>>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F" <%= loginMember.getGender() == Gender.F ? "checked" : "" %>>
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>PreferGenre</th>
				<td>
					<input type="checkbox" name="genre" id="genre0" value="호러" <%= genres != null && genres.contains("호러") ? "checked" : "" %>>
					<label for="genre0">Horror</label>
					<input type="checkbox" name="genre" id="genre9" value="가족" <%= genres != null && genres.contains("가족") ? "checked" : "" %>>
					<label for="genre4">Family</label>
					<input type="checkbox" name="genre" id="genre2" value="미스터리" <%= genres != null && genres.contains("미스터리") ? "checked" : "" %>>
					<label for="genre2">Mystery</label>
					<input type="checkbox" name="genre" id="genre1" value="로맨스" <%= genres != null && genres.contains("로맨스") ? "checked" : "" %>>
					<label for="genre1">Romance</label>
					<br/>
					<input type="checkbox" name="genre" id="genre4" value="스릴러" <%= genres != null && genres.contains("스릴러") ? "checked" : "" %>>
					<label for="genre4">Thriller</label>
					<input type="checkbox" name="genre" id="genre7" value="드라마" <%= genres != null && genres.contains("드라마") ? "checked" : "" %>>
					<label for="genre4">Drama</label>
					<input type="checkbox" name="genre" id="genre6" value="코메디" <%= genres != null && genres.contains("코메디") ? "checked" : "" %>>
					<label for="genre4">Comedy</label>
					<input type="checkbox" name="genre" id="genre5" value="SF" <%= genres != null && genres.contains("SF") ? "checked" : "" %>>
					<label for="genre4">SF</label>
					<br/>
					<input type="checkbox" name="genre" id="genre8" value="판타지" <%= genres != null && genres.contains("판타지") ? "checked" : "" %>>
					<label for="genre4">Fantasy</label>
					<input type="checkbox" name="genre" id="genre10" value="범죄" <%= genres != null && genres.contains("범죄") ? "checked" : "" %>>
					<label for="genre4">Crime&nbsp;</label>
					<input type="checkbox" name="genre" id="genre3" value="액션" <%= genres != null && genres.contains("액션") ? "checked" : "" %>>
					<label for="genre3">Action</label>
				</td>
			</tr>
		</table>
		<input type= "button" value="quit" onclick="deleteMember()">
		<input type="submit" value="update" style="border: 1px solid #df0b0b;">
		<input type="reset" value="cacel" onclick="location.href='<%= request.getContextPath() %>';" style="border: 1px solid #df0b0b;">
	</form>
</section>
<form
	name="delMemberIdFrm"
	action="<%= request.getContextPath() %>/member/memberDelete"
	method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>">
</form>
</div>
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