var validator;
var $mainForumAddForm = $("#mainForum-add-form");

$(function () {
    validateRule();
    $("#mainForum-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $mainForumAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "mainForum/add", $mainForumAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("mainForumTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "mainForum/update", $mainForumAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("mainForumTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#mainForum-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#mainForum-add-button").attr("name", "save");
    $("#mainForum-add-modal-title").html('新增分类');
    validator.resetForm();
    $MB.closeAndRestModal("mainForum-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $mainForumAddForm.validate({
        rules: {
            order:{
                required: true,
                number: true
            },
            title: {
                required: true,
                maxlength: 10
            },
            info: {
                required: true,
                maxlength: 50
            }
        },
        messages: {
            order:{
                required: icon + "请输入分类序号",
                number: icon + "标题序号只能为数字"
            },
            title: {
                required: icon + "请输入分类标题",
                minlength: icon + "标题名称长度不能超过10个字符"
            },
            info: {
                required: icon + "请输入分类内容",
                minlength: icon + "内容大小不能超过50个字符"
            }
        }
    });
}
