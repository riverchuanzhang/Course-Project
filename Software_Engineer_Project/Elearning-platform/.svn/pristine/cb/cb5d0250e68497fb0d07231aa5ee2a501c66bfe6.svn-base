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
<script type="text/javascript">
	$(function() {
		$("#practice_complete").click(caculate);
		$(".topic_answer").hide();
		$("#th_answer").hide();

	});

	//计算成绩
	function caculate() {
		var $trs = $(".topic_tr");//所有行
		var rowNum = 0;
		var parctice_score = 0;
		var topic_score = 0;
		var $currentRow, $selectedOption, $topic_answer, $topic_score;
		for (rowNum = 0; rowNum < $trs.length; rowNum++) {
			$currentRow = $trs.eq(rowNum);
			$selectedOption = $currentRow.find("input:radio:checked");
			$topic_answer = $currentRow.find(".topic_answer");
			if ($selectedOption.val() == $topic_answer.val()) {
				$topic_score = $currentRow.find(".topic_score");
				topic_score = parseInt($topic_score.attr("title"));
				parctice_score = parctice_score + topic_score;
			} else {
				$currentRow.css("background-color", "#F6AF3A");
				$topic_answer.show();
			}
		}
		$("#practice_score").val(parctice_score);
		$("#th_answer").show();
		$("#practice_complete").attr("disabled", "disabled");
	}
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
				<h1>${paper.title}试卷</h1>
				<h2>出题人：${paper.creater.userName},简介：${paper.intro}</h2>
				<div id="content_topic">
					<table id="table_topic" >
						<tr>
							<th>题目</th>
							<th>内容</th>
							<th>分值</th>
							<th >答案</th>
							<th id="th_answer" >正确答案</th>
						</tr>
						<s:iterator value="page.result" status="st">
							<tr class="topic_tr">
								<td><textarea cols="23" rows="5">${title}</textarea></td>
								<td><textarea rows="8" cols="30">${detail}</textarea></td>
								<td><label class="topic_score" title="${score}">${score}</label></td>
								<td>A<input type="radio" name="answer_option${st.index}"
									value="A"> B<input type="radio"
									name="answer_option${st.index}" value="B">C <input
									type="radio" name="answer_option${st.index}" value="C">D<input
									type="radio" name="answer_option${st.index}" value="D"></td>
								<td ><input class="topic_answer" value="${answer}"
									type="text" readonly="readonly"></td>

							</tr>
						</s:iterator>
					</table>
				</div>

				<form action="${ctx}/paper!savePractice.action" method="post">
					<input name="learningGroup.id" value="${learningGroup.id}"
						style="display: none;" /> <input id="paper_id"
						name="practice.paper.id" value="${paper.id}"
						style="display: none;" /> <input id="practice_id"
						name="practice.id" value="${practice.id}" style="display: none;" />
					<input name="practice.creater.id" value="${sessionScope.user.id}"
						style="display: none;" />
					<button id="practice_complete">完成</button>
					<input id="practice_score" readonly="readonly"
						name="practice.score" /> <input type="submit" value="提交" />
				</form>
			</div>

			<div id="content_bottom"></div>
			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>
