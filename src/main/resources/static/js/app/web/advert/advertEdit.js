function updateadvert() {
    var selected = $("#advertTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的广告图片！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个广告图片！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "advert/getAdvert", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#advert-add');
            $form.modal();
            var advert = r.msg;
            $("#advert-add-modal-title").html('修改广告图片');
            $form.find("input[name='id']").val(advert.id);
            $form.find("input[name='photo']").val(advert.photo);
            $form.find("input[name='orders']").val(advert.orders);
            $("#advert-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}