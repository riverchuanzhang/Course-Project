<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/main-style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/add.css" />
<title>网上学习平台</title>
<%@ include file="/jsp/common/meta.jsp"%>
<%@ include file="/jsp/common/jquery-ui.jsp"%>
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
						<li><a href="#tab_question">我的提问</a></li>
						<li><a href="#tab_answer">我的回答</a></li>
					</ul>
					<div id="tab_question">
						<div class="h2_style"><h2>我的提问</h2></div>
						<table>
							<tr>
								<th>标题</th>
								<th>时间</th>
								<th>操作</th>
							</tr>
							<s:iterator value="requestPage.result">
								<tr>
									<td>${title}</td>
									<td>${createTime}</td>
									<td><a
										href="${ctx}/learning_group!info.action?learningGroup.id=${id}">查看</a>&nbsp;
										<a
										href="${ctx}/question!deleteForUser.action?question.id=${id}">删除</a>&nbsp;
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div id="tab_answer">
						<div class="h2_style"><h2>我的回答</h2></div>
						<table>
							<tr>
								<th>标题</th>
								<th>提问者</th>
								<th>时间</th>
								<th>操作</th>
							</tr>
							<s:iterator value="responsePage.result">
								<tr>
									<td>${title}</td>
									<td>${creater.userName}</td>
									<td>${createTime}</td>
									<td>
									<a href="${ctx}/learning_group!info.action?learningGroup.id=${id}">查看</a>&nbsp;
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</div>
			</div>
			<div id="content_bottom"></div>

			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>