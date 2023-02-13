var validator;
var $advertAddForm = $("#advert-add-form");

$(function () {
    validateRule();
    $("#advert-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $advertAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "advert/add", $advertAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("advertTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "advert/update", $advertAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("advertTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#advert-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#advert-add-button").attr("name", "save");
    $("#advert-add-modal-title").html('新增广告图片');
    validator.resetForm();
    $MB.closeAndRestModal("advert-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $advertAddForm.validate({
        rules: {
            photo: {
                required: true,
            }
        },
        messages: {
            photo: {
                required: icon + "请选择要上传图片"
            }
        }
    });
}

$(".input-group").on("change", "input[name=photo1]", function() {
    $("#icon").hide();
    $(this).prev().css("opacity","1")
    var filePath = $(this).val();//读取图片路径
    //将图片的URL赋值与hidden标签内
    var fr = new FileReader();//创建new FileReader()对象
    var imgObj = this.files[0];//获取图片
    fr.readAsDataURL(imgObj);//将图片读取为DataURL
    var obj = $(this).prev()[0];//
    var arr = filePath.split('\\');
    var fileName = arr[arr.length - 1];
    $("#img").removeClass("img");
    $("#img").addClass("img2");
    $(this).parent().next().show();
    fr.onload = function() {
        obj.src = this.result;
        //将base64编码后的图片赋值到hidden标签内
        $("#photo").val(obj.src);
    };
});