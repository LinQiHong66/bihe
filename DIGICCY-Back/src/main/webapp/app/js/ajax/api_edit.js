/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#editHelpCenter").click(
		function() {
			var id = $("#id").val();
			var name = $("#name").val();
			var apiName = $("#apiName").val();
		    var state = $("#state").val();
			var remark = UE.getEditor('editor').getContent();
		    if(id == '' || name ==''
			    || state == '' || remark == '' 
			    	|| apiName == ''){
			    alert("必填项目不能为空！")
			    return false;
			}
			$.ajax({
				url : "/api/updateApi.do",
				type : "POST",
				dataType : "json",
				data : {
					id : id,
					name : name,
					state : state,
					remark : remark,
					apiName : apiName
				},
				success : function(data) {
					if (data.code == "100") {
						alert('修改成功');
						window.location.href = "/api/gotoApi.do";
					} else {
						alert('修改失败');
						return false;
					}
				},
				error : function(data) {
					alert("服务器无响应");
					return false;
				}
					
		});
			
});

