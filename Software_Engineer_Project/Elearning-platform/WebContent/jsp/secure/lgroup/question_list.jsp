<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

				<div id="search_question">
					<form action="${ctx}/question!listForLGroup.action" id="mainForm">

						<input type="hidden" name="page.pageNo" id="pageNo"
							value="${page.pageNo}" /> <input type="hidden"
							name="page.pageSize" id="pageSize" value="${page.pageSize}" /> <input
							type="hidden" name="page.orderBy" id="orderBy"
							value="${page.orderBy}" /> <input type="hidden"
							name="page.order" id="order" value="${page.order}" /> <input
							type="text" name="learningGroup.id" value="${learningGroup.id}"
							style="display: none;"> 标题<input type="text"
							name="filter_LIKES_title" value="${param['filter_LIKES_title']}">
						提问者<input type="text" name="filter_LIKES_creater.userName"
							value="${param['filter_LIKES_creater.userName']}"> <input
							type="submit" value="搜索" /> <a
							href="${ctx}/question!toInput.action?learningGroup.id=${learningGroup.id}">添加问题</a>
					</form>
				</div>
			 <br>
				<div id="question_list">
					<table style="width: 680px">
						<tr>
							<th>标题</th>
							<th>创建人</th>
							<th>时间</th>
							<th>操作</th>
						</tr>
						<s:iterator value="page.result">
							<tr>
								<td>${title}</td>
								<td>${creater.userName}</td>
								<td>${createTime}</td>
								<td><a
									href="${ctx}/answer!listForLGroup.action?question.id=${id}">查看</a>&nbsp;
									<c:if test="${creater.id==sessionScope.user.id}">
										<a
											href="${ctx}/question!deleteForLGroup.action?question.id=${id}&learningGroup.id=${learningGroup.id}">删除</a>&nbsp;
									</c:if></td>
							</tr>
						</s:iterator>
					</table>
					<br> <br>
					<%@ include file="/jsp/common/page.jsp"%>
				</div>

			</div>

			<div id="content_bottom"></div>
			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>
