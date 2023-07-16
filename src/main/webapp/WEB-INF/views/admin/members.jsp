<%@page import="com.cortles.project.member.model.vo.MemberRole"%>
<%@page import="com.cortles.project.member.model.vo.Member"%>
<%@page import="com.cortles.project.member.model.vo.QuitMember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<Member> members = (List<Member>) request.getAttribute("members");
	List<QuitMember> quitMembers = (List<QuitMember>) request.getAttribute("quitMembers");

	String keyword = request.getParameter("keyword");
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/members.css" />
<section>
  
    <div class="search-container" id="search-name">
   	 <form action="<%= request.getContextPath()%>/admin/searchMember" name="searchMemberFrm">
    	<input type="hidden" name="searchType" value="name"/>
   		 <input type="text" name="searchKeyword" class="search-input" placeholder="이름으로 검색">
   			<span class="btn-wrapper">
    			<button class="btn" type="submit">select</button>
			</span>
   	 </form>
	</div>

	 <table>
        <thead>
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>생년월일</th>
                <th>이메일</th>
                <th>휴대폰</th>
                <th>성별</th>
                <th>선호장르</th>
                <th>가입일</th>
                <th>권한</th>
                <th>탈퇴처리</th>
            </tr>
        </thead>
        <tbody>
       	<% if(members == null || members.isEmpty()) { %>
			<tr>
				<td colspan="10">조회 결과가 없습니다.</td>
			</tr>
<%	
			} 
			else { 
				for(Member member : members) {
					%>
	            <tr>
	                <td><%= member.getMemberId()%></td>
	                <td><%= member.getMemberName() %></td>
	                <td><%= member.getBirthday() %></td>
	                <td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
	                <td><%= member.getPhone() %></td>
	                <td><%= member.getGender() != null ? member.getGender():"" %></td>
	                <td><%= member.getFavoriteGenre() != null ? member.getFavoriteGenre() : ""%></td>
	                <td><%= member.getEnrollDate() %></td>
	                <td>
	                	<select class="member-role" data-member-id="<%= member.getMemberId()%>">
	                		<option value="U" <%= member.getMemberRole() == MemberRole.U ? "selected" : "" %>>일반</option>
	                		<option value="A" <%= member.getMemberRole() == MemberRole.A ? "selected" : "" %>>관리자</option>
	                	</select>
	                </td>
	                <td>
						<input type="radio" name="<%= member.getMemberId() %>" id="yes" value="<%= member.getMemberId() %>"/>
						<label for="yes<%= member.getMemberId() %>">Y</label>
						<input type="radio" name="<%= member.getMemberId() %>" id="no" value="N" checked/>
						<label for="no<%= member.getMemberId() %>">N</label>
	                </td>
	            </tr>
<%
			}
		}
%>
        </tbody>
    </table>
    <br><br>
     <div class="search-container" id="search-name">
   	 <form action="<%= request.getContextPath()%>/admin/quitMember" name="quitMemberFrm">
   			 <span class="btn-wrapper">
    			<button class="btn" id="quit" type="submit">quit</button>
			</span>
   	 </form>
	</div>
	<br><br>
	<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>생년월일</th>
					<th>이메일</th>
					<th>휴대폰</th>
					<th>성별</th>
					<th>권한</th>
					<th>가입일</th>
					<th>탈퇴일</th>
				</tr>
			</thead>
		<tbody>
			<% if(quitMembers == null || quitMembers.isEmpty()) { %>
			<tr>
				<td colspan="10">조회 결과가 없습니다.</td>
			</tr>
<%	
			} 
			else { 
				for(QuitMember quitMember : quitMembers) {
					%>
				<tr>
					<td><%= quitMember.getMemberId()%></td>
					<td><%= quitMember.getName() %></td>
					<td><%= quitMember.getBirthday() %></td>
					<td><%= quitMember.getEmail() %></td>
					<td><%= quitMember.getPhone() %></td>
					<td><%= quitMember.getGender() %></td>
					<td><%= quitMember.getMemberRole() %></td>
					<td><%= quitMember.getEnrollDate() %></td>
					<td><%= quitMember.getQuitDate()%></td>
				</tr>
		</table>
			<%
			}
		}
%>
	</tbody>
</section>
<form
	name="memberRoleUpdateFrm"
	action="<%= request.getContextPath() %>/admin/memberRoleUpdate"
	method="post">
	<input type="hidden" name="memberRole"/>
	<input type="hidden" name="memberId"/>
</form>
<form
	name="delMemberIdFrm"
	action="<%= request.getContextPath() %>/member/memberDelete"
	method="POST">
	<input type="hidden" name="memberId" id="delMemberId" value="">
	<input type="hidden" name="delOnMembers" id="delOnMembers" value="delOnMembers">
</form>
<script>

//강제 탈퇴 
document.querySelector("#yes").onclick = (e) => {
	const memberId = e.target.value;
	const memberName = e.target.name;
	console.log(memberId);
	if (confirm(`\${memberName}님을 탈퇴처리 하시겠습니까?`)){
		document.querySelector("#delMemberId").value = memberId;
		document.delMemberIdFrm.submit();
	} else {
		document.querySelector("#no").checked = true;
	}
};


// 검색 
document.querySelector(".search-container").onsubmit = (e) => {
	const frm = e.target;
	const name = frm.searchKeyword.value;
	// 한글 검사
	if (!/^[가-힣]+$/.test(name)) {
		alert("한글만 입력해주세요.");
		return false;
	}
}
          
// 권한 수정 
document.querySelectorAll(".member-role").forEach((elem)=>{
	elem.addEventListener("change",(e)=>{
		
		if(confirm("회원 권한을 수정하겠습니까?")){
			const memberRoleVal = e.target.value;
			const memberIdVal = e.target.dataset.memberId;
			console.log("memberRoleVal",memberRoleVal);
			console.log("memberIdVal",memberIdVal);
			
			const frm = document.memberRoleUpdateFrm;
			console.log("frm",frm);
			frm.memberRole.value = memberRoleVal;
			frm.memberId.value = memberIdVal;
			frm.submit();
		}else{
			e.target.querySelector("option[selected]").selected = true;
		}
	});
});

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
