<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>主页</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<script src="/app/js/header.js"></script>
<link href="/app/assets/bootstrap-table/bootstrap-table.css"
	rel="stylesheet" />
<link href="/app/css/tab.css" rel="stylesheet" />
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">

	<!-- BEGIN HEADER -->
	<%@include file="../header.jsp"%>
	<!-- BEGIN CONTAINER -->
	<div id="container" class="row-fluid">
		<!-- BEGIN SIDEBAR -->
		<%@include file="../left.jsp"%>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div id="main-content">
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							后台参数
							<%--<small>simple form layouts</small>--%>
						</h3>
						<ul class="breadcrumb">
							<li><a href="#"><i class="icon-home"></i></a><span
								class="divider">&nbsp;</span></li>
							<li><a href="#">后台参数</a> <span class="divider">&nbsp;</span></li>
							<li><a href="#">后台银行信息</a><span class="divider-last">&nbsp;</span></li>
						</ul>
					</div>
				</div>
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12 sortable">
						<!-- BEGIN SAMPLE FORMPORTLET-->
						<div class="widget">
							<div class="widget-title">
								<h4>
									<i class="icon-reorder"></i>后台登录限制设置
								</h4>
								<span class="tools"> <a href="javascript:;"
									class="icon-chevron-down"></a> <a href="javascript:;"
									class="icon-remove"></a>
								</span>
							</div>
							<div class="widget-body">

								<table id="recharge_table" style="width: 99%">
									<tbody align="center" style="width: 100%">
										<input type="hidden" id="limitloginid" />
										<tr style="width: 100%">
											<td style="text-align: center; width: 50%">限制时间(分钟)</td>
											<td style="text-align: center; width: 50%"><input
												type="number" id="limittime" name="limittime"
												oninput="if(value.length>4)value=value.slice(0,4)" /></td>
										</tr>
										<tr style="width: 100%">
											<td style="text-align: center; width: 50%">限制次数</td>
											<td style="text-align: center; width: 50%"><input
												type="number" id="time" name="time"
												oninput="if(value.length>4)value=value.slice(0,4)" /></td>
										</tr>
										<span id="username" class="username" hidden="true"> <sec:authentication
												property="name" />
										</span>
										<span id="ip" hidden="true"> <script
												type="text/javascript">
											$('#ip').text(returnCitySN["cip"]);
										</script>
										</span>
										<tr style="width: 100%">
											<td colspan="2" style="width: 100%; text-align: center">
												<button onclick="saveLoginSet()">保存</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- END SAMPLE FORM PORTLET-->
						</div>
					</div>
				</div>
				<!-- END PAGE CONTAINER-->
			</div>
			<!-- END PAGE -->
		</div>
		<%@include file="../footer.jsp"%>
		<!-- END FOOTER -->
		<!-- BEGIN JAVASCRIPTS -->
		<!-- Load javascripts at bottom, this will reduce page load time -->
		<script src="/app/js/footer.js"></script>
		<script src="/app/js/echarts.min.js"></script>
		<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
		<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
		<script src="/app/js/ajax/backinfo.js"></script>
</body>
<!-- END BODY -->
</html>
