function updateNotice() {
    var selected = $("#noticeTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的公告！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个公告！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "notice/getNotice", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#notice-add');
            $form.modal();
            var notice = r.msg;
            $("#notice-add-modal-title").html('修改公告');
            $form.find("input[name='id']").val(notice.id);
            $form.find("input[name='content']").val(notice.content);
            $form.find("input[name='title']").val(notice.title);
            $("#notice-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}