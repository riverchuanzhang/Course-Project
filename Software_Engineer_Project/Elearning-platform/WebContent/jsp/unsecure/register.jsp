<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/jsp/include/header.jsp"%>

	<form action="${ctx}/user!register.action" method="post">
		<div id="register_form">
			<div class="form_row">
				<label for="userName">用户名</label> <input id="userName" type="text"
					name="user.userName">
			</div>
			<div class="form_row">
				<label for="password">密码</label> <input id="password"
					name="user.password" type="password">
			</div>
			<div class="form_row">
				<label for="sex">性别</label> <select id="sex" name="user.sex">
					<option value="0">男</option>
					<option value="1">女</option>
				</select>
			</div>
			<div class="form_row">
				<label for="email">邮箱</label> <input id="email" name="user.email"
					type="text">
			</div>
			<button id="submit" type="submit">
				<span>登录</span>
			</button>
		</div>
	</form>

</body>
</html>