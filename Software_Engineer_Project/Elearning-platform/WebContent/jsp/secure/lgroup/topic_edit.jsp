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
		$("#hiddenTr:first").hide();
		var rowCount = 0;
		for (rowCount = 0; rowCount < 4; rowCount++) {
			addLastRow();
		}
	});
	//初始化生成新行
	function initRow() {
		var $cloneTr = $("#hiddenTr:first").clone(true);
		$cloneTr.show();
		return $cloneTr;
	}

	//在第几行后插入新行
	function insertRow(rowNum) {
		var $row = initRow();
		$row.insertAfter($("#content_topic tr:eq(" + rowNum + ")"));
	}

	//在myObj所在行后插入行
	function insertRowAfter(myObj) {
		var rowNum = getCurrentRowNum(myObj);
		insertRow(rowNum);
	}

	//删除myObj所在行
	function delRow(myObj) {
		var td = myObj.parentNode;
		var tr = td.parentNode;
		$(tr).remove();
	}

	//添加新行到最后
	function addLastRow() {
		var rowNum = $("#contentTable tr").length - 1;
		insertRow(rowNum);
	}

	//在textObj所在行,找到名字为textName的文本框
	function findObjInRow(textObj, textName) {
		var td = textObj.parentNode;
		var tr = td.parentNode;
		var tdNodes = tr.childNodes;
		for ( var i = 0; i < tdNodes.length; i++) {
			var $obj = $(tdNodes[i].firstChild);
			if (textName == $obj.attr("name")) {
				return $obj;
			}

		}
	}
	//相对行数为relativePos,在textObj所在行后面的某行,找到名字为textName的文本框
	function findObjInNextRow(textObj, textName, relativePos) {
		var td = textObj.parentNode;
		var thisTr = td.parentNode;
		var nextTr = thisTr;
		for ( var j = 0; j < relativePos; j++) {
			nextTr = getNextsibling(nextTr);
		}
		var tdNodes = nextTr.childNodes;
		for ( var i = 0; i < tdNodes.length; i++) {
			var $obj = $(tdNodes[i].firstChild);
			if (textName == $obj.attr("name")) {
				return $obj;
			}
		}
	}

	function getNextsibling(n) {
		var x = n.nextSibling;
		while (x.nodeType != 1) {
			x = x.nextSibling;
		}
		return x;
	}
	function getPrevioussibling(n) {
		var x = n.previousSibling;
		while (x.nodeType != 1) {
			x = x.previousSibling;
		}
		return x;
	}
	//textObj所在行的行数
	function getCurrentRowNum(textObj) {
		var td = textObj.parentNode;
		var tr = td.parentNode;
		var table = tr.parentNode;
		for ( var i = 0; i < table.rows.length; i++) {
			var row = table.rows[i];
			if (row == tr) {
				return i;
			}
		}
		return -1;//找不到
	}

	function saveOrUpdatePaper() {
		var $paper_id = $("#paper_id");
		var $paper_tilte = $("#paper_title");
		var $paper_intro = $("#paper_intro");
		var $creater_id = $("#creater_id");
		var $learningGroup_id = $("#learningGroup_id");
		var $content_paper = $("#content_paper");
		if ($paper_tilte.val().length == 0) {
			alert("试卷名称不能为空");
			return;
		}
		if ($paper_intro.val().length == 0) {
			alert("试卷简介不能为空");
			return;
		}
		$.ajax({
			url : "${ctx}/paper!ajaxInput.action",
			dataType : "text",
			type : "post",
			data : {
				paper_id : $paper_id.val(),
				creater_id : $creater_id.val(),
				learningGroup_id : $learningGroup_id.val(),
				paper_title : $paper_tilte.val(),
				paper_intro : $paper_intro.val()
			},
			success : function(data) {
				var json = eval("(" + data + ")");
				var prompt = json.prompt;
				var status = json.status;
				if (status) {
					$("#paper_id").val(json.paper_id);
					$content_paper.css("background-color", "#9AD075");
					alert(prompt);
				} else {
					$content_paper.css("background-color", "#F6AF3A");
					alert(prompt);
				}

			}
		});
	}

	function saveOrUpdateTopic(myObj) {
		var $paper_id = $("#paper_id");
		var $topic_id = findObjInRow(myObj, "topic_id");
		var $topic_detail = findObjInRow(myObj, "topic_detail");
		var $topic_score = findObjInRow(myObj, "topic_score");
		var $topic_answer = findObjInRow(myObj, "topic_answer");
		var $topic_title = findObjInRow(myObj, "topic_title");
		if ($paper_id.val().length == 0) {
			alert("试卷需要先保存");
			return;
		}

		if ($topic_title.val().length == 0) {
			alert("标题不能为空");
			return;
		}
		if ($topic_detail.val().length == 0) {
			alert("内容不能为空");
		}
		var rowNum = getCurrentRowNum(myObj) - 1;
		var $thisRow = $(".hiddenTr").eq(rowNum);

		$.ajax({
			url : "${ctx}/topic!ajaxInput.action",
			dataType : "text",
			type : "post",
			data : {
				paper_id : $paper_id.val(),
				topic_id : $topic_id.val(),
				topic_detail : $topic_detail.val(),
				topic_title : $topic_title.val(),
				topic_score : $topic_score.val(),
				topic_answer : $topic_answer.val()
			},
			success : function(data) {
				var json = eval("(" + data + ")");
				var prompt = json.prompt;
				var status = json.status;
				if (status) {
					$thisRow.css("background-color", "#9AD075");
					$topic_id.val(json.topic_id);
					alert(prompt);
				} else {
					$thisRow.css("background-color", "#F6AF3A");
					alert(prompt);
				}

			}
		});

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
				<h2>制作试卷</h2>
				<div id="content_paper">
					<p>填写试卷信息</p>
					<input id="paper_id" style="display: none;"> <input
						id="creater_id" value="${sessionScope.user.id}"
						style="display: none"> <input id="learningGroup_id"
						value="${learningGroup.id}" style="display: none">
					<div class="form_row">
						<label for="paper_title">名称</label> <input id="paper_title"
							type="text">
					</div>
					<div class="form_row">
						<label for="paper_intro">简介</label> <input id="paper_intro"
							type="text">
					</div>
					<input id="save_paper" type="button" value="保存试卷"
						onclick="saveOrUpdatePaper()">
				</div>

				<div id="content_topic">
					<p>填写题目</p>
					<input id="add_topic" type="button" onclick="addLastRow()"
						value="添加题目">

					<table id="table_topic">
						<tr>
							<th>标题</th>
							<th>内容</th>
							<!-- 	<th>类型</th> -->
							<th>分值</th>
							<th>答案</th>
							<th>操作</th>
						</tr>
					
							<tr id="hiddenTr" class="hiddenTr">
								<td style="display: none;"><input id="topic_id"
									name="topic_id"></td>
								<!-- <td><input id="topic_title" name="topic_title" type="text"></td>
								<td><input id="topic_detail" name="topic_detail" type="text"></td>  -->


								<td><textarea id="topic_title" name="topic_title" cols="20" rows="4"></textarea></td>
								<td><textarea id="topic_detail" name="topic_detail" cols="40" rows="8"></textarea></td>

								<td><select id="topic_score" name="topic_score">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
								</select></td>
								<td><select id="topic_answer" name="topic_answer">
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
								</select></td>
								<td><input id='save_topic' name='save_topic' type='button'
									value='保存' onclick='saveOrUpdateTopic(this)' /> <input
									id='insert_topic' name='insert_topic' type='button' value='插入'
									onclick='insertRowAfter(this)' /> <input id='update_topic'
									name='hidden_topic' type='button' value='隐藏'
									onclick='delRow(this)' /></td>
							</tr>
					</table>
				</div>

			</div>
			<div id="content_bottom"></div>
			<%@ include file="/jsp/include/footer.jsp"%>

		</div>
	</div>
</body>
</html>
