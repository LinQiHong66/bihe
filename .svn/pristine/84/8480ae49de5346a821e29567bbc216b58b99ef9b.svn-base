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
<!--
Template Name: Admin Lab Dashboard build with Bootstrap v2.3.1
Template Version: 1.2
Author: Mosaddek Hossain
Website: http://thevectorlab.net/
-->

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>主页</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<script src="/app/js/jquery-1.8.3.min.js"></script>
<script src="/app/js/layer/layer.js"></script>
<script src="/app/js/header.js"></script>
<link href="/app/assets/bootstrap-table/bootstrap-table.css"
	rel="stylesheet" />
<link href="/app/css/tab.css" rel="stylesheet" />

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
							所有委托记录
							<%--<small>simple form layouts</small>--%>
						</h3>
						<ul class="breadcrumb">
							<li><a href="#"><i class="icon-home"></i></a><span
								class="divider">&nbsp;</span></li>
							<li><a href="#">委托记录管理</a> <span class="divider">&nbsp;</span>
							</li>
							<li><a href="#">所有委托记录</a><span class="divider-last">&nbsp;</span></li>
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
									<i class="icon-reorder"></i>所有委托记录
								</h4>
								<span class="tools"> <a href="javascript:;"
									class="icon-chevron-down"></a> <a href="javascript:;"
									class="icon-remove"></a>
								</span>
							</div>
							<div class="widget-body">
								<form action="" method="POST">
									<div>
										用戶名<select name='userName' id='names' style='width: 20%'></select>状态<select
											style='width: 20%' name='state' id='state'>
											<option value='-1' selected>请选择</option>
											<option value='1'>已完成</option>
											<option value='0'>委托中</option>
											<option value='2'>撤销</option>
										</select>时间段<input type='date' style='width: 20%' name='startDate'
											id='startDate' />---<input style='width: 20%' type='date'
											name='endDate' id='endDate' /> <br /> <input type="button"
											value='查询' onclick='selectEntrust();' /><input type="submit"
											value='导出Excel' />
									</div>
								</form>
								<table id="entrust_all_table" style='width:99%'>

								</table>
							</div>
						</div>
						<!-- END SAMPLE FORM PORTLET-->
					</div>
				</div>
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->

	<!-- BEGIN JAVASCRIPTS -->
	<!-- Load javascripts at bottom, this will reduce page load time -->
	<script src="/app/js/footer.js"></script>
	<script src="/app/js/echarts.min.js"></script>
	<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
	<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<script src="/app/js/bootstrap-table/entrust-tab.js"></script>
	<script type="text/javascript"
		src="/app/assets/bootstrap/js/bootstrap-fileupload.js"></script>
	<script type="text/javascript">
		//获取所有用户
		$.ajax({
			url : '/user/getAllUser.do',
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				if (data.code == 100) {
					var obj = $('#names');
					obj.html('<option value="-1" selected>请选择</option>');
					$.each(data.data, function(a, b) {
						obj.html(obj.html() + '<option value="'+b.user_no+'">'
								+ b.user_no + '--' + b.username + '</option>');
					});
				}
			}
		});
		function selectEntrust() {
			var userName = $('#names').val();
			var state = $('#state').val();
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			
			$("#entrust_all_table").bootstrapTable('destroy');
			$("#entrust_all_table")
					.bootstrapTable(
							{
								// search:true,
								pagination : true,
								pageList : [ 10, 25, 50, 100 ],
								pageSize : 10,
								// sortable: true, //是否启用排序
								// sortOrder: "asc", //排序方式
								url : '/entrust/getAllEntrustRecord.do',
								search : true,// 数据源
								// strictSearch: true,
								// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
								// 设置为limit可以获取limit, offset, search, sort, order
								/*
								 * queryParamsType : "undefined", queryParams: function
								 * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
								 * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
								 * search:params.search }; return param; },
								 */

								queryParams : {
									userName : userName,
									state : state,
									startDate : startDate,
									endDate : endDate
								},
								// server
								sidePagination : "client", // 服务端请求
								idField : 'id',
								cache : false,
								columns : [ [// 列
										{
											title : '用户编号',
											field : 'user_no',
											width : 120,
											align : 'center'
										},
										{
											title : '委托币种',
											field : 'entrust_coin',
											width : 120,
											align : 'center'
										},
										{
											title : '委托类型',
											field : 'entrust_type',
											width : 100,
											align : 'center',
											formatter : function(value) {
												if (value == 0)
													return "买";
												if (value == 1)
													return "卖";
											}
										},
										{
											title : '委托价格',
											field : 'entrust_price',
											width : 100,
											align : 'center',
											formatter : function(value) {
												return value.toFixed(3);
											}
										},
										{
											title : '委托数量(个)',
											field : 'entrust_num',
											width : 100,
											align : 'center'
										},
										{
											title : '成交数量(个)',
											field : 'deal_num',
											width : 100,
											align : 'center'
										},
										{
											title : '手续费',
											field : 'piundatge',
											width : 100,
											align : 'center',
											formatter : function(value) {
												return value.toFixed(3);
											}
										},
										{
											title : '状态',
											field : 'state',
											width : 100,
											align : 'center',
											formatter : function(value) {
												if (value == 0)
													return "<span style='color: blue'>委托中</span>";
												if (value == 1)
													return "<span style='color: green'>已完成</span>";
												if (value == 2)
													return "撤销";
												if (value == 3)
													return "全部";
											}
										},
										{
											title : '委托时间',
											field : 'date',
											width : 100,
											align : 'center'
										},
										{
											title : '操作',
											field : 'id',
											width : 100,
											align : 'center',
											formatter : function(value, row,
													index) {
												var e;
												var id = value;
												if (row.state != 0)
													return e;
												e = '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update" onclick="updateEntrustManage('
														+ id
														+ ');">'
														+ '<i class="icon-pencil icon-white"></i>撤销</button> &nbsp;&nbsp;';
												return e;
											}
										} ] ],

							});
		}
	</script>
</body>

<!-- BEGIN FOOTER -->
<%@include file="../footer.jsp"%>
<!-- END FOOTER -->