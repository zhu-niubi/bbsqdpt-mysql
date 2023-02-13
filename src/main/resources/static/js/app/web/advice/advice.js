$(function () {
    var $adviceTableForm = $(".advice-table-form");
    var settings = {
        url: ctx + "advice/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                userName: $adviceTableForm.find("input[name='userName']").val().trim(),
                title: $adviceTableForm.find("input[name='title']").val(),
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'Id',
            visible: false
        }, {
            field: 'userName',
            title: '反馈者'
        }, {
            field: 'content',
            title: '意见内容'
        }, {
            field: 'title',
            title: '反馈标题'
        }, {
            field: 'advicedate',
            title: '创建日期'
        }
        ]
    };

    $MB.initTable('adviceTable', settings);
    $MB.calenders('input[name="adviceDate"]', false, false);
});

function search() {
    $MB.refreshTable('adviceTable');
}

function refresh() {
    $(".advice-table-form")[0].reset();
    $MB.refreshTable('adviceTable');
}

function deleteAdvice() {
    var selected = $("#adviceTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的反馈信息！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
        if (userName === selected[i].username) contain = true;
    }

    $MB.confirm({
        text: "确定删除选中数据？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'advice/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}
