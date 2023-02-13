$(function () {
    var $systemTableForm = $(".system-table-form");
    var settings = {
        url: ctx + "system/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                createrName: $systemTableForm.find("input[name='createrName']").val().trim(),
                content: $systemTableForm.find("input[name='content']").val().trim(),
                isSend: $systemTableForm.find("select[name='isSend']").val()

            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'content',
            title: "内容"
        }, {
            field: 'createrName',
            title: '创建人'
        }, {
            field: 'date',
            title: '创建时间'
        }, {
            field: 'updateTime',
            title: '修改时间'
        }, {
            field: 'isSend',
            title: '状态',
            formatter: function (value, row, index) {
                if (value == 1) return '<span class="badge badge-success">已发送</span>';
                if (value == 0) return '<button class="badge badge-info" onclick="send(this)" value='+row.id+'>发送</button>';

            }
        }
        ]
    };
    $MB.initTable('systemTable', settings);
});

function search() {
    $MB.refreshTable('systemTable');
}

function refresh() {
    $(".system-table-form")[0].reset();
    $MB.refreshTable('systemTable');
}


function exportSystemExcel() {
    $.post(ctx + "system/excel", $(".system-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportSystemCsv() {
    $.post(ctx + "system/csv", $(".system-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });

}
function send(obj){
        $("#systemId").val($(obj).val());
        var $form = $('#webuser-list');
        $form.modal();
        var $userTableForm = $(".user-table-form");
        var settings = {
            url: ctx + "webuser/list",
            pageSize: 10,
            queryParams: function (params) {
                return {
                    pageSize: params.limit,
                    pageNum: params.offset / params.limit + 1,
                    username: $userTableForm.find("input[name='username']").val().trim(),
                    email: $userTableForm.find("input[name='email']").val(),
                    sex: $userTableForm.find("select[name='sex']").val(),
                    hasActive: $userTableForm.find("select[name='hasActive']").val()
                };
            },
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                visible: false
            }, {
                field: 'username',
                title: '用户名'
            }, {
                field: 'email',
                title: '邮箱'
            }, {
                field: 'phone',
                title: '手机'
            }, {
                field: 'sex',
                title: '性别'
            }, {
                field: 'level',
                title: '等级',
                formatter: function (value, row, index) {
                    if (value === 2) return '<span class="badge badge-warning">禁止发帖</span>';
                    if (value === 1) return '<span class="badge badge-warning">禁止登录</span>';
                    if (value === 3) return '<span class="badge badge-warning">禁止评论</span>';
                    if (value === 0) return '<span class="badge badge-success">正常</span>';
                }
            }, {
                field: 'hasActive',
                title: '激活状态',
                formatter: function (value, row, index) {
                    if (value === 1) return '<span class="badge badge-success">已激活</span>';
                    if (value === 0) return '<span class="badge badge-warning">未激活</span>';
                }
            }

            ]
        };

        $MB.initTable('userTable', settings);
    }

    function searchuser() {
        $MB.refreshTable('userTable');
    }

    function refreshuser() {
        $(".user-table-form")[0].reset();
        $MB.refreshTable('userTable');
    }
function deleteSystems() {
    var selected = $("#systemTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的消息！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";

    }

    $MB.confirm({
        text: "确定删除选中消息？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'system/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}