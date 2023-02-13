$(function () {
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
            field: 'registerDate',
            title: '创建时间'
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
});

function search() {
    $MB.refreshTable('userTable');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('userTable');
}


function exportWebUserExcel() {
    $.post(ctx + "webuser/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportWebUserCsv() {
    $.post(ctx + "webuser/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}