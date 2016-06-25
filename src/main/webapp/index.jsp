<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<title>index</title>
</head>
<body>
	<c:if test="${users==null }">
		<a href="user/all.htmls">all</a>
	</c:if>
	<br />
	<c:forEach items="${users }" var="item">
nick_name=${item.nickName }
,telephone=${item.telephone } 
,register_time=${item.registerTime }



<form action="../user/showInfo.htmls" method="post">
			<input type="hidden" name="id" value="${item.id}" /> <input
				type="submit" value="查询全部信息" />
		</form>
		<br />
	</c:forEach>
	<br />
	<c:if test="${userInfo!=null }">
用户信息   <br />   
昵称： ${userInfo.nickName} 
用户id：${userInfo.id}  
用户电话:${userInfo.telephone }  
注册时间： <fmt:formatDate value="${userInfo.registerTime }"
			pattern="yyyy-MM-dd HH:mm:ss" />
 角色：[<c:forEach items="${ userInfo.acctRoles}" var="role">
${role.name } &nbsp; 
权限[<c:forEach items="${ role.acctAuthorities}" var="authority">
${authority.name } 
</c:forEach> ]
</c:forEach>]
<br />
		<br />
ajax显示全部用户信息：
	<div id="show_all_user"></div>
		<script type="text/javascript">
			$.ajax({
				type : "get",
				url : "showInfos.htmls",
				dataType : "json",
				success : function(data) {
					$(data).each(
							function(i, user) {
								var p = "<p>昵称:" + user.nickName + "    电话:"
										+ user.telephone + "    注册时间:"
										+ user.registerTime + "    id:"
										+ user.id + "</p>";
								$("#show_all_user").append(p);
							});
				},
				async : true
			});
		</script>
	</c:if>

</body>

</html>