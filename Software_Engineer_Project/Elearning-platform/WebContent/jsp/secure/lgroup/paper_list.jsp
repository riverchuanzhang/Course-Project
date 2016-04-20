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
		<%@ include file="/jsp/include/lgroup_menu.jsp"%>
		<%@ include file="/jsp/include/lgroup_leftmenu.jsp"%>

		<div id="content">
			<div id="content_top"></div>

			<div id="content_main">

				<div class=h2_style><h2>试卷</h2></div>

				<a id="add_paper"
					href="${ctx}/topic!toInput.action?learningGroup.id=${learningGroup.id}">添加试卷</a>

				<form
					action="${ctx}/paper!listForLGroup.action?learningGroup.id=${learningGroup.id}"
					id="mainForm">
					<input type="hidden" name="page.pageNo" id="pageNo"
						value="${page.pageNo}" /> <input type="hidden"
						name="page.pageSize" id="pageSize" value="${page.pageSize}" /> <input
						type="hidden" name="page.orderBy" id="orderBy"
						value="${page.orderBy}" /> <input type="hidden" name="page.order"
						id="order" value="${page.order}" />
				</form>

				<div id="content_paper">
					<table>
						<tr>
							<th>标题</th>
							<th>创建人</th>
							<th>操作</th>
						</tr>
						<s:iterator value="page.result">
							<tr>
								<td>${title}</td>
								<td>${creater.userName}</td>
								<td><a href="${ctx}/topic!toPractice.action?paper.id=${id}">进入</a>&nbsp;
									<c:if test="${creater.id==sessionScope.user.id}">
										<a
											href="${ctx}/paper!deleteForLGroup.action?paper.id=${id}&learningGroup.id=${learningGroup.id}">删除</a>&nbsp;
									</c:if></td>
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
