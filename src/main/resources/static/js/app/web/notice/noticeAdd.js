var validator;
var $noticeAddForm = $("#notice-add-form");

$(function () {
    validateRule();
    $("#notice-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $noticeAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "notice/add", $noticeAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("noticeTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "notice/update", $noticeAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("noticeTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#notice-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#notice-add-button").attr("name", "save");
    $("#notice-add-modal-title").html('新增公告');
    validator.resetForm();
    $MB.closeAndRestModal("notice-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $noticeAddForm.validate({
        rules: {
            title: {
                required: true,
                maxlength: 30
            },
            content: {
                required: true,
                maxlength: 100
            }
        },
        messages: {
            title: {
                required: icon + "请输入公告标题",
                minlength: icon + "标题名称长度不能超过30个字符",
            },
            content: {
                required: icon + "请输入公告内容",
                minlength: icon + "内容大小不能超过100个字符",
            }
        }
    });
}
