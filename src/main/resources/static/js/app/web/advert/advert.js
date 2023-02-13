$(function () {
    var $advertTableForm = $(".advert-table-form");
    var settings = {
        url: ctx + "advert/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                orders: $advertTableForm.find("input[name='orders']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'photo',
            title: '图片'
        }, {
            field: 'orders',
            title: '序号'
        }, {
            field: 'addDate',
            title: '添加时间'
        }, {
            field: 'editDate',
            title: '修改时间'
        }
        ]
    };
    $MB.initTable('advertTable', settings);
});

function search() {
    $MB.refreshTable('advertTable');
}

function refresh() {
    $(".advert-table-form")[0].reset();
    $MB.refreshTable('advertTable');
}
function deleteadvert() {
    var selected = $("#advertTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的图片信息！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中图片信息？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'advert/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}


function exportadvertExcel() {
    $.post(ctx + "advert/excel", $(".advert-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportadvertCsv() {
    $.post(ctx + "advert/csv", $(".advert-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}