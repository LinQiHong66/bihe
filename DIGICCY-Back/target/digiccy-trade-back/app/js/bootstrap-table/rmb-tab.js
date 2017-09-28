$("#withdraw_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/rmb/getWithdraw.do',
    search: true,// 数据源
    // strictSearch: true,
    // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
    // 设置为limit可以获取limit, offset, search, sort, order
    /*
     * queryParamsType : "undefined", queryParams: function
     * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
     * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
     * search:params.search }; return param; },
     */
    // server
    sidePagination: "client", // 服务端请求
    idField:'id',
    cache:false,
    columns:[[// 列
        {title:'用户名称',field:'attr1',width:120,align:'center'},
        {title:'提现银行',field:'attr2',width:120,align:'center'},
        {title:'提现金额',field:'price',width:100,align:'center'},
        {title:'手续费',field:'poundage',width:100,align:'center'},
        {title:'实际到账',field:'actual_price',width:100,align:'center'},
        {title:'日期',field:'date',width:100,align:'center'},
        {title:'状态',field:'state',width:100,align:'center',
            formatter : function(value,row,index) {
                var str = row.id+','+row.user_no+','+row.price;
                if (value == 0){
                    return '<button class="btn btn-info" ' +
                        'onclick="confirmWithdraw(\''+ str + '\')">' +
                        '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
                }
                if (value == 1){
                   return '已到账';
                }
            }},
    ]],

});

$("#recharge_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/rmb/getRecharge.do',
    search: true,// 数据源
    // strictSearch: true,
    // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
    // 设置为limit可以获取limit, offset, search, sort, order
    /*
     * queryParamsType : "undefined", queryParams: function
     * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
     * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
     * search:params.search }; return param; },
     */
    // server
    sidePagination: "client", // 服务端请求
    idField:'id',
    cache:false,
    columns:[[// 列
        {title:'用户名称',field:'attr1',width:120,align:'center'},
        {title:'充值方式',field:'recharge_type',width:120,align:'center',
            formatter : function(value) {
                if (value == 0){
                    return '网银';
                }
                if (value == 1){
                	return '支付宝';
                }
                if (value == 2){
                	return '微信';
                }
            }
        },
        {title:'充值金额',field:'recharge_price',width:100,align:'center'},
        {title:'订单号',field:'recharge_order',width:100,align:'center'},
        {title:'日期',field:'date',width:100,align:'center'},
        {title:'状态',field:'state',width:100,align:'center', 
            formatter : function(value,row,index) {
            	//充值到账的第一个参数是id不是状态 --刘科领注
//                var str = value+','+row.user_no+','+row.actual_price;
                var str = row.id+','+row.user_no+','+row.recharge_price;
                if (value == 0){
                    return '<button class="btn btn-info" ' +
                        'onclick="confirmRecharge(\''+ str + '\')">' +
                        '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
                }
                if (value == 1){
                    return '已到账';
                }
        }},
    ]],

});

//获取所有用户
/*$.ajax({
	url:'/user/getAllUser.do',
	type:'GET',
	dataType:'json',
	success:function(data){
		if(data.code==100){
			var obj = $('#names');
			obj.html('<option value="-1" selected>请选择</option>');
			console.log(data);
			$.each(data.data,function(a,b){
				obj.html(obj.html()+'<option value="'+b.user_no+'">'+b.user_no+'--'+b.username+'</option>');
			});
		}
	}
});*/

function confirmRecharge(value) {
    var strArr = value.split(",");
    var r=confirm("是否确认到账？");
    alert(strArr[2]);
    if(r==true){
    $.ajax({
        url:"/rmb/doRecharge.do",
        type: "post",
        dataType: "json",
        data:{
            recId:strArr[0],
            name:strArr[1],
            price:strArr[2]
        },
        success: function (msg) {
            // if(msg.code == "100"){
            $.alertable.alert(msg.desc);
            window.location.reload();
            // }
        }
    });
    }
}

function confirmWithdraw(value) {
    var strArr = value.split(",");
    alert(strArr[2]);
    var r=confirm("是否确认到账？");
    if(r==true){
    $.ajax({
        url:"/rmb/doWithdraw.do",
        type: "post",
        dataType: "json",
        data:{
            recId:strArr[0],
            name:strArr[1],
            price:strArr[2]
        },
        success: function (msg) {
            // if(msg.code == "100"){
            window.location.reload();
            // }
        }
    });
 
}
function selectWithdraw(){

	var userName = $('#names').val();
	var state = $('#state').val();
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	
	$("#withdraw_table").bootstrapTable('destroy'); 
	$("#withdraw_table").bootstrapTable({
	    // search:true,
	    pagination:true,
	    pageList: [10, 25, 50, 100],
	    pageSize:10,
	    // sortable: true, //是否启用排序
	    // sortOrder: "asc", //排序方式
	    url:'/rmb/getWithdraw.do',
	    search: true,// 数据源
	    // strictSearch: true,
	    // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
	    // 设置为limit可以获取limit, offset, search, sort, order
	    /*
	     * queryParamsType : "undefined", queryParams: function
	     * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
	     * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
	     * search:params.search }; return param; },
	     */
	    queryParams:{
	    	userName:userName,
	    	state:state,
	    	startDate:startDate,
	    	endDate:endDate
	    },
	    // server
	    sidePagination: "client", // 服务端请求
	    idField:'id',
	    cache:false,
	    columns:[[// 列
	        {title:'用户名称',field:'attr1',width:120,align:'center'},
	        {title:'提现银行',field:'attr2',width:120,align:'center'},
	        {title:'提现金额',field:'price',width:100,align:'center'},
	        {title:'手续费',field:'poundage',width:100,align:'center'},
	        {title:'实际到账',field:'actual_price',width:100,align:'center'},
	        {title:'日期',field:'date',width:100,align:'center'},
	        {title:'状态',field:'state',width:100,align:'center',
	            formatter : function(value,row,index) {
	                var str = row.id+','+row.user_no+','+row.price;
	                if (value == 0){
	                    return '<button class="btn btn-info" ' +
	                        'onclick="confirmWithdraw(\''+ str + '\')">' +
	                        '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
	                }
	                if (value == 1){
	                   return '已到账';
	                }
	            }},
	    ]],

	});
}
function selectRecharge(){
	var userName = $('#names').val();
	var state = $('#state').val();
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	
	$("#recharge_table").bootstrapTable('destroy'); 
	$("#recharge_table").bootstrapTable({
	    // search:true,
	    pagination:true,
	    pageList: [10, 25, 50, 100],
	    pageSize:10,
	    url:'/rmb/getRecharge.do',
	    search: true,
	    queryParams:{
	    	userName:userName,
	    	state:state,
	    	startDate:startDate,
	    	endDate:endDate
	    },
	    // server
	    sidePagination: "client", // 服务端请求
	    idField:'id',
	    cache:false,
	    columns:[[// 列
	        {title:'用户名称',field:'attr1',width:120,align:'center'},
	        {title:'充值方式',field:'recharge_type',width:120,align:'center',
	            formatter : function(value) {
	                if (value == 0){
	                    return '网银';
	                }
	                if (value == 1){
	                	return '支付宝';
	                }
	                if (value == 2){
	                	return '微信';
	                }
	            }
	        },
	        {title:'充值金额',field:'recharge_price',width:100,align:'center'},
	        {title:'订单号',field:'recharge_order',width:100,align:'center'},
	        {title:'日期',field:'date',width:100,align:'center'},
	        {title:'状态',field:'state',width:100,align:'center', 
	            formatter : function(value,row,index) {
	                var str = row.id+','+row.user_no+','+row.recharge_price;
	                alert('recharge_price:'+row.recharge_price);
	                if (value == 0){
	                    return '<button class="btn btn-info" ' +
	                        'onclick="confirmRecharge(\''+ str + '\')">' +
	                        '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
	                }
	                if (value == 1){
	                    return '已到账';
	                }
	        }},
	    ]],

	});
}
}