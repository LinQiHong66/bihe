/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#addApi").click(
		function() {
			var name = $("#name").val();
			var apiName = $("#apiName").val();
		    var state = $("#state").val();
			var remark = UE.getEditor('editor').getContent();
		    if(name == '' || state =='' || remark == '' || apiName == ''){
			    alert("必填项目不能为空！")
			    return false;
			}
			$.ajax({
					url : "/api/addApi.do",
					type : "POST",
					dataType : "json",
					data : {
						name : name,
						state : state,
						remark : remark,
						apiName : apiName
					},
					success : function(data) {
						if (data.code == "100") {
							alert('添加成功');
							window.location.href = "/api/gotoApi.do";
						} else {
							alert('添加失败');
							return false;
						}
					},
					error : function(data) {
						alert("服务器无响应");
						return false;
					}
					
		});
			
});

