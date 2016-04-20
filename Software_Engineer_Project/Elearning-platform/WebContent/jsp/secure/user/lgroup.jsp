<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/main-style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/add.css" />
<title>网上学习平台</title>
<%@ include file="/jsp/common/meta.jsp"%>
<%@ include file="/jsp/common/jquery-ui.jsp"%>
</head>
<script type="text/javascript">
	$(function() {
		$("#content_main_tabs").tabs({
			beforeLoad : function(event, ui) {
				ui.jqXHR.error(function() {
					ui.panel.html("加载失败,请稍后再尝试");
				});
			}

		});
	});
</script>
</head>
<body>
	<div id="container">
		<%@ include file="/jsp/include/header.jsp"%>
		<%@ include file="/jsp/include/user_menu.jsp"%>
		<%@ include file="/jsp/include/user_leftmenu.jsp"%>

		<div id="content">
			<div id="content_top"></div>
			<div id="content_main">
				<div id="content_main_tabs">
					<ul>
						<li><a href="#tab_lgroup_list">查看</a></li>
						<li><a href="#tab_lgroup_create">添加</a></li>
					</ul>
					<div id="tab_lgroup_list">

						<form id="mainForm"
							action="${ctx}/learning_group!listForUser.action">
							<input type="hidden" name="page.pageNo" id="pageNo"
								value="${page.pageNo}" /> <input type="hidden"
								name="page.pageSize" id="pageSize" value="${page.pageSize}" />
							<input type="hidden" name="page.orderBy" id="orderBy"
								value="${page.orderBy}" /> <input type="hidden"
								name="page.order" id="order" value="${page.order}" />

							<table>
								<tr>
									<th>组名</th>
									<th>创建人</th>
									<th>简介</th>
									<th>操作</th>
									<th></th>
								</tr>
								<s:iterator value="page.result">
									<tr>
										<td>${name}</td>
										<td>${creater.userName}</td>
										<td>${intro}</td>
										<td>
										<a href="${ctx}/learning_group!info.action?learningGroup.id=${id}">进入</a>&nbsp;
										</td>
										<c:if test="${sessionScope.user.id!=creater.id}">
											<td><a
												href="${ctx}/learning_group!leave.action?learningGroup.id=${id}&member.id=${sessionScope.user.id}">退出</a>&nbsp;
											</td>
										</c:if>
										<c:if test="${sessionScope.user.id==creater.id}">
											<td><a
												href="${ctx}/learning_group!delete.action?learningGroup.id=${id}">解散</a>&nbsp;
											</td>
										</c:if>
									</tr>
								</s:iterator>
							</table>

							<%@ include file="/jsp/common/page.jsp"%>

						</form>

					</div>
					<!-- 添加群组 -->
					<div id="tab_lgroup_create">
						<div id="groupEditDiv" title="填写">
							<form action="${ctx}/learning_group!input.action" id="editForm">
								<input style="display: none;" id="groupId"
									name="learningGroup.id">
								<div class="form_row">
									<label for="groupName">组名</label> <input id="groupName"
										name="learningGroup.name" type="text">
								</div>
								<div class="form_row">
									<label for="groupIntro">简介</label> <input id="groupIntro"
										name="learningGroup.intro" type="text">
								</div>
								<input style="display: none;" id="createrId"
									name="learningGroup.creater.id" value="${sessionScope.user.id}">
								<input type="submit" value="提交">
							</form>
						</div>

					</div>
				</div>
			</div>
			<div id="content_bottom"></div>

			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>