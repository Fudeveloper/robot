var prefix = "/production/technology";
var allData = null;
//上一个detailRow索引
var lastDetailRowIndex = 0;
$(function () {
    var deptId = '';
    load(deptId);

});

//获取表格所有列宽
function getAllwidth() {
    var firstRow = $("tr")[3];
    var $tds = $($(firstRow).find("td"));
    var allWidth = [];
    //获取每列宽度
    $.each($tds, function (index, td) {
        allWidth[index] = $(td).outerWidth();
    });
    return allWidth;
}

function load(deptId) {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "http://123.207.68.28:8080/plan", // 服务器数据的加载地址
                // showRefresh: true,
                // showToggle : true,
                // showColumns: true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                contentType: "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                // showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParamsType: "",

                // //设置为limit则会发送符合RESTFull格式的参数
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        page: params.pageNumber - 1,
                        size: params.pageSize,
                        // name: $('#searchName').val(),
                        // deptId: deptId
                    };
                },
                detailView: true,
                detailFormatter: function (index, row, detailRow) {
                    if ((index + 1) % 2 == 0) {
                        // var startElement="<a>小计</a>";
                        console.log(allData)
                        console.log(lastDetailRowIndex);
                        var usefulData = allData.slice(lastDetailRowIndex, index + 1);
                        if (usefulData === undefined || usefulData.length === 0) {
                            // alert(1)
                            usefulData = allData
                        }
                        console.log("-------------")
                        console.log(usefulData)
                        //更新此次DetailRowIndex
                        lastDetailRowIndex = index + 1;

                        var jiaoqi = usefulData[0]["shijian"];
                        var shuliang = 0;
                        var zhunbeishijian = 0;
                        $.each(usefulData, function (usefulIndex, oneData) {
                            //交期：按最迟时间算
                            if (oneData["shijian"] > jiaoqi) {
                                jiaoqi = oneData["shijian"];
                            }
                            //数量：累加
                            shuliang += parseInt(oneData["shuliang"]);
                            //准备时间：累加
                            zhunbeishijian += parseInt()
                            //    开始时间：取最早日期
                            //    结束日期：取最迟日期
                            //    执行计划：
                        });

                        var allWidth = getAllwidth();
                        var firstTdWidth = allWidth[0]+allWidth[1]+allWidth[2]-11;
                        var width2 = allWidth[3];
                        console.log(firstTdWidth);
                        var elements = []

                        var firstElement = $("<td>小计</td>").innerWidth(firstTdWidth);
                        var element2 = $("<td>"+jiaoqi+"</td>").innerWidth(width2-5)
                        var element3 =$("<td>"+shuliang+"</td>").innerWidth(allWidth[4]-5)
                        elements[0]=firstElement
                        elements[1]=element2
                        elements[2]=element3

                        return elements;

                    }


                },

                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                responseHandler: function (res) {
                    allData = res.content;
                    console.log(allData);
                    // console.log(res);
                    // console.log(res.totalElements);
                    // console.log(res.content);
                    return {
                        "total": res.totalElements,//总数
                        "rows": res.content   //数据
                    };
                },


                columns: [
                    //第一行表头
                    [
                        {
                            field: 'id',
                            title: '设备',
                            rowspan: 2,
                            valign: "middle",

                            // cellStyle : function (value, row, index) {
                            //     if (index==0){
                            //         return {
                            //             css: {
                            //                 "width": "300px",
                            //                 "word-wrap": "break-word",
                            //                 "word-break": "normal",
                            //
                            //             }
                            //         };
                            //     }else{
                            //         return {
                            //             css: {
                            //                 "width": "100px",
                            //                 "word-wrap": "break-word",
                            //                 "word-break": "normal",
                            //
                            //             }
                            //         };
                            //     }
                            //
                            // },
                        },
                        {
                            field: 'liaohao',
                            title: '料号',
                            rowspan: 2,
                            valign: "middle",

                        },

                        {
                            field: 'shijian',
                            title: '交期',
                            rowspan: 2,
                            valign: "middle",

                        },
                        {
                            field: 'shuliang',
                            title: '生产数量',
                            rowspan: 2,
                            valign: "middle",


                        },

                        {
                            field: '3',
                            title: '准备时间',
                            rowspan: 2,
                            valign: "middle",

                        },
                        {
                            field: 'tuhao',
                            title: '生产工时',
                            rowspan: 2,
                            valign: "middle",

                        },
                        {
                            field: '3',
                            title: '计划时间',
                            colspan: 2,

                        },
                        {
                            field: 'tuhao',
                            title: '执行情况',
                            colspan: 2,
                        },
                    ],
                    //第二行表头
                    [
                        {
                            "title": "开始日期",
                            "halign": "center",
                            "align": "center",

                        },
                        {
                            "title": "结束日期",
                            "halign": "center",
                            "align": "center",

                        },
                        {
                            "title": "1/1",
                            "halign": "center",
                            "align": "center",

                        },
                        {
                            "title": "1/2",
                            "halign": "center",
                            "align": "center",

                        },
                    ],

                ]
                , onPostBody: function (data) {

                    var icons = $(".icon-plus")
                    $.each(icons, function (index, icon) {
                        if ((index + 1) % 2 === 0) {
                            $(icon).click()
                        }
                        $(icon).hide()

                    });


                }
            });

}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    // iframe层
    layer.open({
        type: 2,
        title: '增加用户',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add'
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: "/sys/user/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
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
        type: 2,
        title: '用户修改',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function resetPwd(id) {
    layer.open({
        type: 2,
        title: '重置密码',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['400px', '260px'],
        content: prefix + '/resetPwd/' + id // iframe的url
    });
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
    });
}

function changeWarnStatus(id) {
    alert(id)
    $.ajax({
        url: "/sys/warn/status/" + id,
        type: "POST",
        data: "",
        success: function (r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }

    });
}