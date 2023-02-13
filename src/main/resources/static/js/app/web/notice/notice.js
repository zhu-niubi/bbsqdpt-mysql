$(function () {
    var $noticeTableForm = $(".notice-table-form");
    var settings = {
        url: ctx + "notice/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                name: $noticeTableForm.find("input[name='name']").val().trim(),
                title: $noticeTableForm.find("input[name='title']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'name',
            title: '发布者'
        }, {
            field: 'title',
            title: '标题'
        }, {
            field: 'content',
            title: '内容'
        }, {
            field: 'noticeDate',
            title: '发布时间'
        }, {
            field: 'greatNum',
            title: '点赞数',
            /*formatter: function (value, row, index) {
                if (value === '0') return '男';
                else if (value === '1') return '女';
                else return '保密';
            }*/
        }, {
            field: 'replayNum',
            title: '评论数'
        }
        ]
    };

    $MB.initTable('noticeTable', settings);
});

function search() {
    $MB.refreshTable('noticeTable');
}

function refresh() {
    $(".notice-table-form")[0].reset();
    $MB.refreshTable('noticeTable');
}
function deleteNotice() {
    var selected = $("#noticeTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的公告！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中公告？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'notice/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}


function exportNoticeExcel() {
    $.post(ctx + "notice/excel", $(".notice-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportNoticeCsv() {
    $.post(ctx + "notice/csv", $(".notice-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}