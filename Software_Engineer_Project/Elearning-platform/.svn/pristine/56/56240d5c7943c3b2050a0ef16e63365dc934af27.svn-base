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
		<%@ include file="/jsp/include/lgroup_menu.jsp"%>
		<%@ include file="/jsp/include/lgroup_leftmenu.jsp"%>

		<div id="content">
			<div id="content_top"></div>



			<div id="content_main">

				<h2>${question.title}</h2>
				<h3>${question.detail}</h3>

				<div id="answer_list">
					<form
						action="${ctx}/answer!listForLGroup.action?question.id=${question.id}"
						id="mainForm">
						<input type="hidden" name="page.pageNo" id="pageNo"
							value="${page.pageNo}" /> <input type="hidden"
							name="page.pageSize" id="pageSize" value="${page.pageSize}" /> <input
							type="hidden" name="page.orderBy" id="orderBy"
							value="${page.orderBy}" /> <input type="hidden"
							name="page.order" id="order" value="${page.order}" />
					</form>
					<table>
						<tr>
							<th>标题</th>
							<th>创建人</th>
							<th>时间</th>
						</tr>
						<s:iterator value="page.result">
							<tr>
								<td>${detail}</td>
								<td>${creater.userName}</td>
								<td>${createTime}</td>
							</tr>
						</s:iterator>
					</table>

					<%@ include file="/jsp/common/page.jsp"%>
				</div>


				<div id="answer_reply">
					<p>编辑回答</p>
					<form id="editForm" action="${ctx}/answer!inputForLGroup.action"
						method="post">
						<input style="display: none;" id="questionId"
							name="answer.question.id" value="${question.id}"> <input
							style="display: none;" id="createrId" name="answer.creater.id"
							value="${sessionScope.user.id}">
						<div class="form_row">
							<label for="answerDetail">内容</label> <input id="answerDetail"
								name="answer.detail" type="text">
						</div>
						<input type="submit">
					</form>
				</div>
			</div>
			<div id="content_bottom"></div>
			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>
