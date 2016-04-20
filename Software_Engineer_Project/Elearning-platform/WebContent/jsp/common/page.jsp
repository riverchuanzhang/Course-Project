<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link href="${ctx}/css/page-green.css" type="text/css" rel="stylesheet" />
<!--翻页代码 -->
<script>  
function is_number(e){  
    //IE 中 Event 对象使用 keyCode 获得键入字符的 Unicode 编码  
    //DOM 中 Event 对象 使用 charCode 获得键入字符的 Unicode 编码  
    var char_code = e.charCode ? e.charCode : e.keyCode;  
    //Unicode 编码中， 0~9 的编码的十进制 是 48~57 之间 ， 0为 48， 9 为57  ,Backspace为 8
    if(char_code!=8&&char_code<48 || char_code >57){  
      //  alert("只允许输入数字");     
        return false;  
    }  
    else{  
        return true;      
    }  
}  
</script>  
<span>
	每页显示：
	<s:iterator begin="10" step="20" end="100" status="index">
		<s:if test="page.pageSize == (#index.index + 1)*20">
			<span class="current"><s:property value="(#index.index + 1)*20" /></span>
		</s:if>
		<s:if test="page.pageSize != (#index.index + 1)*20">
			<a href="javascript:changePageSize(<s:property value="(#index.index + 1)*20" />)">
				<s:property value="(#index.index + 1)*20" /></a>
		</s:if>
	</s:iterator>
</span>
<div class="scott">
<s:if test="page.totalPages != 0">
	<span class="disabled">
		共<s:property value="page.totalPages" />页<s:property value="page.totalCount" />条&nbsp;&nbsp; 
	</span>
</s:if>
<s:if test="page.totalPages <= 15">
	<s:iterator begin="1" end="page.totalPages" status="index">
		<s:if test="page.pageNo == #index.index+1">
			<span class="current"><s:property value="page.pageNo" /></span>
		</s:if>
		<s:if test="page.pageNo != #index.index+1">
			<a href="javascript:jumpPage(<s:property value='#index.index+1'/>)">
				<s:property value="#index.index+1" /></a>
		</s:if>
	</s:iterator>
</s:if>
<s:else>
	<s:if test="page.pageNo <= 8">
		<s:iterator begin="1" end="15" status="index">
			<s:if test="page.pageNo == #index.index+1">
				<span class="current"><s:property value="page.pageNo" /></span>
			</s:if>
			<s:if test="page.pageNo != #index.index+1">
				<a href="javascript:jumpPage(<s:property value='#index.index+1'/>)">
					<s:property value="#index.index+1" /></a>
			</s:if>
		</s:iterator>
		<a href="javascript:jumpPage(<s:property value='page.nextPage'/>)">下一页</a>
		<a href="javascript:jumpPage(<s:property value='page.totalPages'/>)">尾页</a>
	</s:if>
	<s:elseif test="page.pageNo >= page.totalPages - 7">
		<a href="javascript:jumpPage(1)">首页</a>
		<a href="javascript:jumpPage(<s:property value='page.prePage'/>)">上一页</a>
		<s:iterator begin="page.totalPages - 14" end="page.totalPages"
			status="index">
			<s:if test="page.pageNo == page.totalPages - 14 + #index.index">
				<span class="current"><s:property value="page.pageNo" /></span>
			</s:if>
			<s:if test="page.pageNo != page.totalPages - 14 + #index.index">
				<a href="javascript:jumpPage(<s:property value='page.totalPages - 14 + #index.index'/>)">
					<s:property value="page.totalPages - 14 + #index.index" /></a>
			</s:if>
		</s:iterator>
	</s:elseif>
	<s:else>
		<a href="javascript:jumpPage(1)">首页</a>
		<a href="javascript:jumpPage(<s:property value='page.prePage'/>)">上一页</a>
		<s:iterator begin="page.pageNo - 7" end="page.pageNo + 7"
			status="index">
			<s:if test="page.pageNo == page.pageNo - 7 + #index.index">
				<span class="current"><s:property value="page.pageNo" /></span>
			</s:if>
			<s:if test="page.pageNo != page.pageNo - 7 + #index.index">
				<a href="javascript:jumpPage(<s:property value='page.pageNo - 7 + #index.index'/>)">
					<s:property value="page.pageNo - 7 + #index.index" /></a>
			</s:if>
		</s:iterator>
		<a href="javascript:jumpPage(<s:property value='page.nextPage'/>)">下一页</a>
		<a href="javascript:jumpPage(<s:property value='page.totalPages'/>)">尾页</a>
	</s:else>
	<input type="text"   id="jumpPageNo" name="jumpPageNo" onpaste="return false"  style="ime-mode:disabled" onkeypress="return is_number(event)" value="<s:property value='page.pageNo' />" size="1"/>
	<input type="button" value="跳转" onclick="javascript:jumpPage($('#jumpPageNo').val());"/>
	
</s:else>
<s:if test="page.totalPages == 0">
	<br />
	<span class="disabled">暂无记录</span>
	<br />
	<br />
</s:if>
</div>