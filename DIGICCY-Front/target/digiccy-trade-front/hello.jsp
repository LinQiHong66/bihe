<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>国际购物平台管理 - 后台</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
    <script src="/jquery-1.11.1.min.js" > </script>
 
</head>
<body class="templatemo-bg-image-2">
<div class="container">
    <div class="col-md-12">
     
            <div class="row">
                <div class="col-md-12">
                    <input id="submit" type="submit" value="ssssssssssssss"/>
                </div>
            </div>
 
         
    </div>
</div>
 
<script>
    
    $("#submit").click(function () {
    	var userNo=199;
		$.ajax({
			dataType:"json",
		  
			url : "/front/coin/getBalaninfo.do",
			type : "POST",
			data : {
				userNo : userNo
			 
            },
			success : function(data) {
				 console.log(data)
			},
			error : function(data) {
				layer.msg("服务器无响应");
				return false;
			}
		});
    });

   
</script>
</body>
</html>