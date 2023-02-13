
$(function () {

    $("input[name='checkhasActive']").change(function () {
        var checked = $(this).is(":checked");
        var $hasActive_label = $("#hasActive");
        if (checked){

            $("input[name='hasActive']").val('1');
            $hasActive_label.html('已激活');
        }
        else {
            $hasActive_label.html('未激活');
            $("input[name='hasActive']").val('0');
        }
    });

    $("#webuser-list  .btn-sure").click(function () {

        var selected = $("#userTable").bootstrapTable('getSelections');
        var selected_length = selected.length;
        var ids = "";
        var systemId=$("#systemId").val();
        for (var i = 0; i < selected_length; i++) {
            ids += selected[i].id;
            if (i !== (selected_length - 1)) ids += ",";
        }
        $MB.confirm({
            text: "确定发送消息（默认发送给所有人）？",
            confirmButtonText: "确定发送"
        }, function () {
            $.post(ctx + 'system/send', {"userIds": ids,"systemId":systemId}, function (r) {
                if (r.code === 0) {
                    $MB.n_success(r.msg);
                    closeModa();
                    refresh();
                } else {
                    $MB.n_danger(r.msg);
                }
            });
        });

    });

    $("#webuser-list .btn-close").click(function () {
        closeModa();
    });
});

function closeModa() {
    $MB.closeAndRestModal("webuser-list");
}
