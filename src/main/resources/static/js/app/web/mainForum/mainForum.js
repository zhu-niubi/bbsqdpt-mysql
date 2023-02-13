$(function () {
    var $mainForumTableForm = $(".mainForum-table-form");
    var settings = {
        url: ctx + "mainForum/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                title: $mainForumTableForm.find("input[name='title']").val()
            };
        },
        columns: [{
            checkbox: true
        },  {
            field: 'id',
            visible: false
        }, {
            field: 'title',
            title: '分类名'
        }, {
            field: 'info',
            title: '描述'
        },{
            field: 'addDate',
            title: '添加时间'
        },{
            field: 'editDate',
            title: '修改时间'
        },{
        field: 'order',
            title: '序号'
         }
        ]
    };

    $MB.initTable('mainForumTable', settings);
});

function search() {
    $MB.refreshTable('mainForumTable');
}

function refresh() {
    $(".mainForum-table-form")[0].reset();
    $MB.refreshTable('mainForumTable');
}
function deletemainForum() {
    var selected = $("#mainForumTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的分类！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中分类？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'mainForum/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}


function exportmainForumExcel() {
    $.post(ctx + "mainForum/excel", $(".mainForum-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportmainForumCsv() {
    $.post(ctx + "mainForum/csv", $(".mainForum-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}