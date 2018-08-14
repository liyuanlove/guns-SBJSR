/**
 * 初始化卖家信息详情对话框
 */
var SellerInfoDlg = {
    sellerInfoData: {}
};

/**
 * 清除数据
 */
SellerInfoDlg.clearData = function () {
    this.sellerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SellerInfoDlg.set = function (key, val) {
    this.sellerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SellerInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SellerInfoDlg.close = function () {
    parent.layer.close(window.parent.Seller.layerIndex);
}

/**
 * 收集数据
 */
SellerInfoDlg.collectData = function () {
    this
        .set('id')
        .set('name')
        .set('addr')
        .set('userId')
        .set('status')
}

/**
 * 提交添加
 */
SellerInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/seller/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Seller.table.refresh();
        SellerInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sellerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SellerInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/seller/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Seller.table.refresh();
        SellerInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sellerInfoData);
    ajax.start();
}

$(function () {

});
