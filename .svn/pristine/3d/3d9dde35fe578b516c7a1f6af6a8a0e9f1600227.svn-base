$("#apiDetail_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/apiDetail/getApiDetail.do',
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
        {title:'API接口名称',field:'address_no',width:110,align:'center'},
        {title:'用户编号',field:'user_no',width:110,align:'center'},
        {title:'申请状态',field:'state',width:110,align:'center',formatter:function (value) {
            if(value == 0)return "<span style='color: blue'>待审核</span>";
            if(value == 1)return "<span style='color: blue'>已通过</span>";
        }},
        {title:'签名',field:'sign',width:110,align:'center'},
        {title:'时间',field:'date',width:110,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
        	formatter : function(value, row, index) {
                var e;
                if(row.state != 0){
                e = '-';
                }else{
                e = '<button dispaly="" class="btn btn-primary" id="btn_update" onclick="updateState(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;审核</button> ';
                }
                return e;
        }},
    ]]
});
function updateState(id) {
    $.alertable.confirm('你确定要通过该申请吗？').then(function() {
        $.ajax({
            url:"/apiDetail/updateApiDetailState.do",
            type: "post",
            dataType: "json",
            data:{
                id:id
            },
            success: function (msg) {
                alert(msg.desc);
                $("#box_add").css({"display":"none"});
                window.location.reload();
            }
        });
    });
}
