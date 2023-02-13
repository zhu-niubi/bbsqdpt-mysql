var validator;
var $userAddForm = $("#user-add-form");

$(function () {

    validateRule();
    $("input[name='checkhasActive']").change(function () {
        var checked = $(this).is(":checked");
        var $hasActive_label = $("#hasActive");
        if (checked){

            $("input[name='hasActive']").val('1');;
            $hasActive_label.html('已激活');
        }
        else {
            $hasActive_label.html('未激活');
            $("input[name='hasActive']").val('0');;
        }
    });

    $("#webuser-add  .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $userAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "webuser/add", $userAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("userTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "webuser/update", $userAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("userTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#webuser-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#user-add-button").attr("name", "save");
    validator.resetForm();
    $userAddForm.find("input[name='username']").removeAttr("readonly");
    $userAddForm.find("input[name='email']").removeAttr("readonly");
    $userAddForm.find(".user_password").show();
    $userAddForm.find("input[name='hasActive']").prop("checked", true);
    $("#user-add-modal-title").html('新增用户');
    $("#hasActive").html('已激活');
    $MB.closeAndRestModal("webuser-add");
    $("#checkhasActive").hide();
    $("#level").hide();
    $("#id").val("");

}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $userAddForm.validate({
        rules: {
            username: {
                required: true,
                minlength: 2,
                maxlength: 10,
                remote: {
                    url: "webuser/checkUserByName",
                    type: "get",
                    dataType: "json",
                    data: {
                        username: function () {
                            return $("input[name='username']").val().trim();
                        },
                        id:function () {
                            return $("input[name='id']").val().trim();
                        }
                    }
                }
            },
            email: {
                required: true,
                email: true,
                remote: {
                    url: "webuser/checkUserByEmail",
                    type: "get",
                    dataType: "json",
                    data: {
                        email: function () {
                            return $("input[name='email']").val().trim();
                        },
                        id:function () {
                            return $("input[name='id']").val().trim();
                        }
                    }
                }
            },
            mobile: {
                checkPhone: true
            },
            sex: {
                required: true
            }
        },
        errorPlacement: function (error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages: {

            username: {
                required:icon + "请输入用户名",
                minlength: icon + "用户名长度3到10个字符",
                remote: icon + "用户名已经存在"
            },
            email:{
                required:icon + "邮箱格式不正确",
                remote:icon + "邮箱已注册"
             },
            roles: icon + "请选择用户角色",

            sex: icon + "请选择性别"
        }
    });
}

