<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/main-style.css" />
<title>网上学习平台</title>
<%@ include file="/jsp/common/meta.jsp"%>
<%@ include file="/jsp/common/jquery-ui.jsp"%>
</head>

<body>
	<div id="container">
		<%@ include file="/jsp/include/header.jsp"%>
		<%@ include file="/jsp/include/user_menu.jsp"%>
		<%@ include file="/jsp/include/user_leftmenu.jsp"%>


		<div id="content">
			<div id="content_top"></div>
			<div id="content_main">
				<h2>${user.userName}的信息</h2>
				<h2>邮箱：${user.email}</h2>
			</div>
			<div id="content_bottom"></div>

			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>
