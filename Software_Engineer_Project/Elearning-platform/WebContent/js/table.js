function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#mainForm").submit();
}
function changePageSize(pageSize) {
	$("#pageSize").val(pageSize);
	$("#mainForm").submit();
}

function sort(orderBy, defaultOrder) {
	if ($("#orderBy").val() == orderBy) {
		if ($("#order").val() == "") {
			$("#order").val(defaultOrder);
		} else if ($("#order").val() == "desc") {
			$("#order").val("asc");
		} else if ($("#order").val() == "asc") {
			$("#order").val("desc");
		}
	} else {
		$("#orderBy").val(orderBy);
		$("#order").val(defaultOrder);
	}

	$("#mainForm").submit();
}

function search() {
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");

	$("#mainForm").submit();
}

function checkNum(o,name){
	if(isNaN(o.val())){
		o.addClass("ui-state-error");
		updateTips(name + "必须是数字");
		return false;
	}else{
		return true;
	}	
}

function checkEmpty(o, name){
	if (o.val().length == 0) {
		o.addClass("ui-state-error");
		updateTips(name + "不能为空");
		return false;
	} else {
		return true;
	}
}

function checkEmptyOfSelect(o, name){
	if (o.val().length == 0) {
		updateTips("请选择"+name);
		o.focus();
		return false;
	} else {
		return true;
	}
}

//点击全选checkbox
function selectall(obj) {
    var table = findParentByTagName(obj,"table");
    for(var i=1; i < table.rows.length; i++) {
        selectitem(table, i, obj);
    }
}

function findParentByTagName(obj, tagName) {
    tagName =tagName.toUpperCase();
    var parent =obj;
    while((parent =parent.parentNode) != null) {
        if(parent.tagName.toUpperCase() == tagName) {
            return parent;
        }
    }
    return null;
}

function selectoneitem(objbox) {
    var tr =objbox.parentNode.parentNode;
    tr.className = objbox.checked ? "lineselected" : "";      
}

//更改被选中的数据所在行的背景颜色
function selectitem(table, rowindex, headbox) {	
    var checked = headbox.checked;
	  var tr =table.rows[rowindex];
	  var box = tr.cells[0].firstChild;
		if(box == headbox) return;
		if(box && box.tagName && box.tagName.toUpperCase() == "INPUT" && box.type.toUpperCase() == "CHECKBOX") {	
			 box.checked =checked;
			 selectoneitem(box);
		}
}

function checkLength(o, name, min, max) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips(name + "长度必须在 " + min + " 与 " + max + " 之间");
		return false;
	} else {
		return true;
	}
}

function getSelectedUuidArray(tableId){
	var selectedUuids = [];
	$("#"+tableId+" input:checked").each(function(index){
		if($(this).val()=='on'){
			return;
		}
		selectedUuids.push($(this).val());
	});
	return selectedUuids;
}

function submitForm(formId, action){
	var $mainForm = $("#" + formId);
	$mainForm.attr("action", action);
	$mainForm.submit();
}

function dels(tableId, formId, action){
	if(confirm("确定要删除选中的所有记录嘛？")){
		var selectedUuids = getSelectedUuidArray("contentTable");
		if(selectedUuids.length==0){
			alert("请先选中记录");
		}else{
			$("#editUuid").val(selectedUuids.join(","));
			submitForm(formId, action);
		}
	}
}

//dk
//自动填充
function toAutocomplete(toUrl, $flag) {
	$flag.autocomplete({
		source : function(request, response) {
			$.ajax({
				url : toUrl,
				dataType : "text",
				data : {
					keyword : request.term
				},
				success : function(data) {
					var json = eval("(" + data + ")");
					if(json.error==0){
						response($.map(json.result, function(item) {
						return {
							label : item.label,
							value : item.value
						}
					}));
					}
				}

			});
		}
	});
	 //处理autocomplete自动填充使用输入法的bug
	  $flag.bind( "input.autocomplete", function(){
           $(this).trigger('keydown.autocomplete');
		}); 
}

$(document).ready(function(){
	$(".dataTable tr:even,.detailTable tr:even").removeClass('odd').addClass('even');
	$(".dataTable tr:odd,.detailTable tr:odd").removeClass('even').addClass('odd');
	$(".dataTable tr th").css("background","#E9F1DA");
	$(".dataTable tr,.detailTable tr").mouseenter(function(){
		$(this).addClass("trOver");
	}).mouseout(function(){
		$(this).removeClass("trOver");
	});
	$(".detailTable td:even").css({"font-weight": "bold"});
});