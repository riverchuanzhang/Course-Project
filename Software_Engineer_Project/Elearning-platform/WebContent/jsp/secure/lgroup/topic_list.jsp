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
				<h2>做试卷</h2>
				<h2>名称：${paper.title},出题人：${paper.creater.userName}</h2>
				<h3>简介：${paper.intro}</h3>
				<div id="topic_list">
					<table>
						<tr>
							<th>标题</th>
							<th>类型</th>
							<th>内容</th>
							<th>答案</th>
						</tr>
						<s:iterator value="page.result">
							<tr>
								<td>${title}</td>
								<td>${type}</td>
								<td>${detail}</td>
								<td>${answer}</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>

			<div id="content_bottom"></div>
			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>
