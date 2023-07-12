<%@page import="com.cortles.project.member.model.vo.MemberRole"%>
<%@page import="com.cortles.project.member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%

	List<Member> members = (List<Member>) request.getAttribute("members");

%>
</head>
<style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        
        th {
            background-color: #f2f2f2;
        }
    </style>
<body>
  <h1>members</h1>
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
                <td><%= member.getEmail() %></td>
                <td><%= member.getPhone() %></td>
                <td>
                
                </td>
                <td><%= member.getFavoriteGenre() %></td>
                <td><%= member.getEnrollDate() %></td>
                <td>
                	<select class="member-role" data-member-id="<%= member.getMemberId()%>">
                		<option value="U" <%= member.getMemberRole() == MemberRole.U ? "selected" : "" %>>일반</option>
                		<option value="A" <%= member.getMemberRole() == MemberRole.A ? "selected" : "" %>>관리자</option>
                	</select>
                </td>
            </tr>
           <%
				}
			}
           %>
        </tbody>
    </table>
</body>
</html>