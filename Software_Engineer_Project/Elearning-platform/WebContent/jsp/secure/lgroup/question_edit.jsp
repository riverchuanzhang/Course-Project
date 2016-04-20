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

<script type="text/javascript">
	$(function() {
		$("#submit_content").button().click(function() {
			$("#questionTitle").val($("#ta_title").val());
			$("#questionDetail").val($("#ta_detail").val());
			$("#editForm").submit();
		});
	});
</script>
</head>

<body>
	<div id="container">
		<%@ include file="/jsp/include/header.jsp"%>
		<%@ include file="/jsp/include/lgroup_menu.jsp"%>
		<%@ include file="/jsp/include/lgroup_leftmenu.jsp"%>

		<div id="content">
			<div id="content_top"></div>

			<div id="content_main">

				<div id="editDiv">
					<h1>添加评论</h1>
					<form id="editForm" action="${ctx}/question!inputForLGroup.action"
						method="post">
						<input style="display: none;" id="learningGroupId"
							name="question.learningGroup.id" value="${learningGroup.id}">
						<input style="display: none;" id="createrId"
							name="question.creater.id" value="${sessionScope.user.id}">
						<div class="form_row">
							<h2>标题</h2>
							<input id="questionTitle" name="question.title" type="text"
								style="display: none;">
							<textarea id="ta_title" rows="6" cols="40"></textarea>
						</div>
						<div class="form_row">
							<h2>内容</h2>
							<input id="questionDetail" name="question.detail" type="text"
								style="display: none;">
							<textarea id="ta_detail" rows="14" cols="50"></textarea>
						</div>
						<a id="submit_content">提交</a>
					</form>
				</div>
			</div>


			<div id="content_bottom"></div>
			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>
