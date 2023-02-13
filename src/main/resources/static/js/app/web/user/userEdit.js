function updateWebUser() {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的用户！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个用户！');
        return;
    }
    var userId = selected[0].id;
    $.post(ctx + "webuser/getUser", {"id": userId}, function (r) {
        if (r.code === 0) {
            $(".row").show();
            var $form = $('#webuser-add');
            //var $deptTree = $('#deptTree');
            $form.modal();
            var user = r.msg;
            $form.find(".user_password").hide();
            $("#user-add-modal-title").html('修改用户');
            $form.find("input[name='username']").val(user.username).attr("readonly", true);
            $form.find("input[name='oldusername']").val(user.username);
            $form.find("input[name='id']").val(user.id);
            $form.find("input[name='email']").val(user.email).attr("readonly", true);
            $form.find("input[name='oldemail']").val(user.email);
            $form.find("input[name='mobile']").val(user.mobile);
            // var roleArr = [];
            // for (var i = 0; i < user.roleIds.length; i++) {
            //     roleArr.push(user.roleIds[i]);
            // }
           // $form.find("select[name='rolesSelect']").multipleSelect('setSelects', roleArr);
           // $form.find("input[name='roles']").val($form.find("select[name='rolesSelect']").val());
            var hasActive = $form.find("input[name='checkhasActive']");
            if (user.hasActive === 1) {
                hasActive.prop("checked", true);
                hasActive.parent().next().html('已激活');
                $form.find("input[name='hasActive']").val('1');
            } else {
                hasActive.prop("checked", false);
                hasActive.parent().next().html('未激活');
                $form.find("input[name='hasActive']").val('0');
            }
            $("input:radio[value='" + user.sex + "']").prop("checked", true);
            $("input:radio[value='" + user.level + "']").prop("checked", true);
           // $deptTree.jstree().open_all();
           // $deptTree.jstree('select_node', user.deptId, true);
            $("#user-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}