var validator;
var $systemAddForm = $("#system-add-form");


$(function () {
    validateRule();
    $("#systemadd  .btn-save").click(function () {
        var validator = $systemAddForm.validate();
        var flag = validator.form();
        if (flag) {
            $.post(ctx + "system/add", $systemAddForm.serialize(), function (r) {
                if (r.code == 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("systemTable");
                } else $MB.n_danger(r.msg);
            });
        }
    });
    $("#systemadd .btn-close").click(function () {
        closeModal();
    });

});



function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $systemAddForm.validate({
        rules: {
            content: {
                required: true
            }
        },
        messages: {
            content: icon + "消息内容不能为空"
        }
    });
}
function closeModal() {
    $MB.closeAndRestModal("systemadd");
}