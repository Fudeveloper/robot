var prefix = "/sys/warn"
$(function() {
    var deptId = '';
    load(deptId);
});

function load(deptId) {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/warns", // 服务器数据的加载地址
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                singleSelect : false, // 设置为true将禁止多选
                contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParamsType : "",
                silent : true,
                // //设置为limit则会发送符合RESTFull格式的参数
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        pageNumber : params.pageNumber,
                        pageSize : params.pageSize,
                        user : $('#searchUser').val(),
                        // deptId : deptId
                        code:$("#searchCode").val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                responseHandler : function(res){
                    console.log(res);
                    return {
                        "total": res.data.total,//总数
                        "rows": res.data.records   //数据
                    };
                },
                columns : [
                    {
                        checkbox : true
                    },
                    {
                        field : 'id', // 列字段名
                        title : '序号' // 列标题
                    },
                    {
                        field : 'user',
                        title : '触发报警人'
                    },


                    {
                        field : 'info',
                        title : '报警信息',
                        align : 'center',

                    },
                    {
                        title : '开始时间',
                        field : 'startTime',
                        align : 'center',

                    },
                    {
                        title : '结束时间',
                        field : 'endTime',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (Date.parse(value)>=new Date(2000,1,1)){
                                return value
                            }else{
                                return  ;
                            }

                        }
                    },
                    {
                        title : '报警发生地点',
                        field : 'place',
                        align : 'center',

                    },
                    {
                        title : '解除报警用户',
                        field : 'cancelUser',
                        align : 'center',

                    },
                    {
                        title : '报警代码',
                        field : 'code',
                        align : 'center',

                    },
                    {
                        field : 'status',
                        title : '操作',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (Date.parse(row.endTime)>=new Date(2000,1,1)){
                                return "已结束"
                            }else{
                                var d =
                                    '<a class="btn btn-warning btn-sm ' +  '" href="#" title="解除"  mce_href="#" onclick="remove(\''
                                    + row.id
                                    + '\')"><i class="fa fa-remove"></i></a> ';

                                return  d ;
                            }

                        }
                    },
                ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    // iframe层
    layer.open({
        type : 2,
        title : '增加用户',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add'
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : "/sys/user/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}
function edit(id) {
    layer.open({
        type : 2,
        title : '用户修改',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });
}
function resetPwd(id) {
    layer.open({
        type : 2,
        title : '重置密码',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '400px', '260px' ],
        content : prefix + '/resetPwd/' + id // iframe的url
    });
}
function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "ids" : ids
            },
            url : prefix + '/batchRemove',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function() {});
}


function changeWarnStatus(id) {
    alert(id)
    $.ajax({
        url : "/sys/warn/status/"+id,
        type : "POST",
        data:"",
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }

    });
}