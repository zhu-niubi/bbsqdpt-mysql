function updateSystem() {
    var selected = $("#systemTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的用户！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个用户！');
        return;
    }
    var systemId = selected[0].id;
    $.post(ctx + "system/getSystem", {"id": systemId}, function (r) {
        if (r.code === 0) {
            $(".row").show();
            var $form = $('#system-add');
            //var $deptTree = $('#deptTree');
            $form.modal();
            var system = r.msg;
            $form.find(".system_password").hide();
            $("#system-add-modal-title").html('修改用户');
            $form.find("input[name='systemname']").val(system.systemname).attr("readonly", true);
            $form.find("input[name='oldsystemname']").val(system.systemname);
            $form.find("input[name='id']").val(system.id);
            $form.find("input[name='email']").val(system.email).attr("readonly", true);
            $form.find("input[name='oldemail']").val(system.email);
            $form.find("input[name='mobile']").val(system.mobile);
            var hasActive = $form.find("input[name='checkhasActive']");

        } else {
            $MB.n_danger(r.msg);
        }
    });
}