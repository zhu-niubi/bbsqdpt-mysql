function updatemainForum() {
    var selected = $("#mainForumTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的分类！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个分类！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "mainForum/getMainForum", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#mainForum-add');
            $form.modal();
            var mainForum = r.msg;
            $("#mainForum-add-modal-title").html('修改分类');
            $form.find("input[name='id']").val(mainForum.id);
            $form.find("input[name='order']").val(mainForum.order);
            $form.find("input[name='info']").val(mainForum.info);
            $form.find("input[name='title']").val(mainForum.title);
            $("#mainForum-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}