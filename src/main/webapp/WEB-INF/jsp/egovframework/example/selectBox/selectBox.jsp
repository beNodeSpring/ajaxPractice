<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
var ajaxinit = function(thisParam){
	var select = {
		displayChildSelectBox : function(list) {
			if(list.length > 0){
				$.each(list, function(i, item) {
					var optionStr = "<option value="+ item.prdCd+ ">"+ item.prdNm + "</option>";
					$("#childSelectBox").append(optionStr);
				});
			} else {
				$("#childSelectBox").append('<option value="">없음</option>');
			}
		}
	};
	
	//var thisParam = this.value; 함수로 빼면서 필요 없어짐
	
	$.ajax({
		//type: "GET",
		url: "childSelectBox.do",
		data: {"param": thisParam},
		success: function(data){
			console.log(data);
			
			select.displayChildSelectBox(data);
		}
	});
	
}

$(document).ready(function() {		
	$("#parentSelectBox").change(function() {
		$("#childSelectBox").children().remove();
		ajaxinit(this.value);
	});
	$("#parentSelectBox").change();
});
</script>
<div class="content">
	<div class="container-fluid">
      	<div class="row">
          	<div class="col-md-12">
              	<div class="card ">
	                <div class="header">
	                    <h4 class="title">셀렉트박스</h4>
	                    <p class="category">ajax 잘 모르고 쓰면 어렵지~</p>
	                </div>
	                <div class="content">
	                	<select id="parentSelectBox" name="parentSelectBox">
	                		<c:forEach items="${parentList}" var="parentList">
		                		<option value="<c:out value="${parentList.brandCd}"/>">
		                			<c:out value="${parentList.brandNm}"/>
		                		</option>
	                		</c:forEach>
	                	</select>
	                	<select id="childSelectBox" name="childSelectBox">
	                		<!-- <option value="">없음</option> -->
	                	</select>
	                </div>
                </div>
            </div>
        </div>
    </div>
</div>