<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/main-style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/add.css" />
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
				<div id="search_top">
					<form action="${ctx}/learning_group!listForSearch.action"
						id="mainForm">
						<input type="hidden" name="page.pageNo" id="pageNo"
							value="${page.pageNo}" /> <input type="hidden"
							name="page.pageSize" id="pageSize" value="${page.pageSize}" /> <input
							type="hidden" name="page.orderBy" id="orderBy"
							value="${page.orderBy}" /> <input type="hidden"
							name="page.order" id="order" value="${page.order}" /> 群组名：<input
							type="text" name="filter_LIKES_name"
							value="${param['filter_LIKES_name']}"> 创建人：<input
							type="text" name="filter_LIKES_creater.userName"
							value="${param['filter_LIKES_creater.userName']}"> <input
							type="submit" value="搜索" />
					</form>
				</div>
				<div id="content_search">
					<table>
						<tr>
							<th>组名</th>
							<th>创建人</th>
							<th>简介</th>
							<th>操作</th>
						</tr>
						<s:iterator value="page.result">
							<tr>
								<td>${name}</td>
								<td>${creater.userName}</td>
								<td>${intro}</td>
								<td><a
									href="${ctx}/learning_group!enter.action?learningGroup.id=${id}&member.userName=${sessionScope.user.userName}">加入</a>
								</td>
							</tr>
						</s:iterator>
					</table>
					<%@ include file="/jsp/common/page.jsp"%>
				</div>



			</div>
			<div id="content_bottom"></div>

			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>


</body>
</html>