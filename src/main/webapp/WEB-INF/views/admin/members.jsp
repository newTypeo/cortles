<%@page import="com.cortles.project.member.model.vo.MemberRole"%>
<%@page import="com.cortles.project.member.model.vo.Member"%>
<%@page import="com.cortles.project.member.model.vo.QuitMember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<Member> members = (List<Member>) session.getAttribute("members");
	List<QuitMember> quitMembers = (List<QuitMember>) session.getAttribute("quitMembers");
	String keyword = request.getParameter("keyword");
			
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/members.css" />
<section>
    <div class="search-container" id="search-name">
        <form action="<%= request.getContextPath()%>/admin/searchMembers" name="searchMemberFrm">
            <input type="hidden" name="searchType" value="name"/>
            <input type="text" name="searchKeyword" class="search-input" placeholder="Search by name">
            <span class="btn-wrapper">
                <button class="btn" type="submit" style="margin:5px;">select</button>
            </span>
        </form>
    </div>
    <table>
        <thead>
            <tr>
                <th id="boarderz">ID</th>
                <th id="boarderz">Name</th>
                <th id="boarderz">BirthDate</th>
                <th id="boarderz">Email</th>
                <th id="boarderz">phone</th>
                <th id="boarderz">Gender</th>
                <th id="boarderz">preferredGenre</th>
                <th id="boarderz">RegDate</th>
                <th id="boarderz">authority</th>
                <th id="boarderz">Withdrawal</th>
            </tr>
        </thead>
       <tbody>
       <% if(members == null || members.isEmpty()) { %>
           <tr>
               <td id="boarderz" colspan="10">No lookup results.</td>
           </tr>
       <%	
           } 
           else { 
               for(Member member : members) {
       %>
	            <tr>
	                <td id="boarderz"><%= member.getMemberId()%></td>
	                <td id="boarderz"><%= member.getMemberName() %></td>
	                <td id="boarderz"><%= member.getBirthday() %></td>
	                <td id="boarderz"><%= member.getEmail() != null ? member.getEmail() : "" %></td>
	                <td id="boarderz"><%= member.getPhone() %></td>
	                <td id="boarderz"><%= member.getGender() != null ? member.getGender():"" %></td>
	                <td id="boarderz"><%= member.getFavoriteGenre() != null ? member.getFavoriteGenre() : ""%></td>
	                <td id="boarderz"><%= member.getEnrollDate() %></td>
	                <td id="boarderz">
	                    <select class="member-role" data-member-id="<%= member.getMemberId()%>">
	                        <option value="U" <%= member.getMemberRole() == MemberRole.U ? "selected" : "" %>>User</option>
	                        <option value="A" <%= member.getMemberRole() == MemberRole.A ? "selected" : "" %>>Admin</option>
	                    </select>
	                </td>
	                <td id="boarderz">
	                    <input type="radio" name="<%= member.getMemberId() %>" id="yes<%= member.getMemberId() %>" value="<%= member.getMemberId() %>" class="yes"/>
	                    <label for="yes<%= member.getMemberId() %>">Y</label>
	                    <input type="radio" name="<%= member.getMemberId() %>" id="no<%= member.getMemberId() %>" value="N" checked/>
	                    <label for="no<%= member.getMemberId() %>">N</label>
	                </td>
	            </tr>
       <%	
               } // for
           } // else
       %>
       </tbody>
    </table>
    <div class="search-container" id="search-name">
        <form name="quitMemberFrm">
            <span class="btn-wrapper">
                <button class="btn" id="quit" type="button">quit</button>
            </span>
        </form>
    </div>
    
    <table id="quitTable" style="display: none;">
        <thead>
            <tr>
                <th id="boarderz">ID</th>
                <th id="boarderz">Name</th>
                <th id="boarderz">BirthDate</th>
                <th id="boarderz">Email</th>
                <th id="boarderz">phone</th>
                <th id="boarderz">Gender</th>
                <th id="boarderz">Authority</th>
                <th id="boarderz">RegDate</th>
                <th id="boarderz">WithdrawalDate</th>
            </tr>
        </thead>
        <tbody>
            <% if(quitMembers == null || quitMembers.isEmpty()) { %>
            <tr>
                <td id="boarderz" colspan="10">조회 결과가 없습니다.</td>
            </tr>
            <% 
                } else { 
                    for(QuitMember quitMember : quitMembers) {   
            %>
            <tr>
                <td id="boarderz"><%= quitMember.getMemberId()%></td>
                <td id="boarderz"><%= quitMember.getName() %></td>
                <td id="boarderz"><%= quitMember.getBirthday() %></td>
                <td id="boarderz"><%= quitMember.getEmail() %></td>
                <td id="boarderz"><%= quitMember.getPhone() %></td>
                <td id="boarderz"><%= quitMember.getGender() %></td>
                <td id="boarderz"><%= quitMember.getMemberRole() %></td>
                <td id="boarderz"><%= quitMember.getEnrollDate() %></td>
                <td id="boarderz"><%= quitMember.getQuitDate()%></td>
            </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
</section>
<script>
const quitButton = document.querySelector("#quit");
const quitTable = document.querySelector("#quitTable");

let isTableVisible = false;

quitButton.addEventListener('click', (e) => {
    if (!isTableVisible) {
        quitTable.style.display = "table";
        isTableVisible = true;
    } else {
        quitTable.style.display = "none";
        isTableVisible = false;
    }
});

</script>
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

<% if(members != null) { %>
	// 관리자가 회원 강제 탈퇴 
	[...document.querySelectorAll(".yes")].forEach((yesRadio) => {
		yesRadio.onclick = (e) => {
		    const memberId = e.target.value;
		    const memberName = e.target.name;
		    // console.log(memberId);
		    if (confirm(`\${memberName}님을 탈퇴처리 하시겠습니까?`)){
		        document.querySelector("#delMemberId").value = memberId;
		        document.delMemberIdFrm.submit();
		    } else {
		    	e.target.nextSibling.nextElementSibling.nextElementSibling.checked = true;
		    };
		};
	});
	
	
<% } %>

// 검색 
document.querySelector(".search-container").onsubmit = (e) => {
    const frm = e.target;
    const name = frm.searchKeyword.value;
    // 한글 검사
    //if (!/^[가-힣]+$/.test(name)) {
    //     alert("한글만 입력해주세요.");
    //     return false;
    // }
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