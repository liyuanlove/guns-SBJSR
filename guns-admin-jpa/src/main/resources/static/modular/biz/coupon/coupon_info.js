/**
 * 初始化优惠券详情对话框
 */
var CouponInfoDlg = {
    couponInfoData: {},
    sellerZtree: null,
    validateFields: {
        sellername: {
            validators: {
                notEmpty: {
                    message: '卖家不能为空'
                }
            }
        },
        beginDay: {
            validators: {
                notEmpty: {
                    message: '起始时间不能为空'
                }
            }
        },
        endDay: {
            validators: {
                notEmpty: {
                    message: '结束时间不能为空'
                }
            }
        },
        num: {
            validators: {
                notEmpty: {
                    message: '数量不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CouponInfoDlg.clearData = function () {
    this.couponInfoData = {};
}

/**
 * 存储对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponInfoDlg.set = function (key, val) {
    this.couponInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CouponInfoDlg.close = function () {
    parent.layer.close(window.parent.Coupon.layerIndex);
}

/**
 * 收集数据(要提交的字段)
 */
CouponInfoDlg.collectData = function () {
    this.set('sellerid')
        .set('beginDay')
        .set('endDay')
        .set('num');
}

/**
 * 验证数据是否为空
 */
CouponInfoDlg.validate = function () {
    $('#sellerInfoForm').data("bootstrapValidator").resetForm();
    $('#sellerInfoForm').bootstrapValidator('validate');
    return $("#sellerInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 提交添加
 */
CouponInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/coupon/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Coupon.table.refresh();
        CouponInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    //配置提交的数据
    ajax.set(this.couponInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CouponInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/coupon/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Coupon.table.refresh();
        CouponInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    //配置提交的数据
    ajax.set(this.couponInfoData);
    ajax.start();
}

/**
 * 显示卖家的树
 *
 * @returns
 */
CouponInfoDlg.showSellerNameSelectTree = function () {
    Feng.showInputTree("sellername", "sellerContent");
};

/**
 * 点击父级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
CouponInfoDlg.onClickNode = function (e, treeId, treeNode) {
    $("#sellername").attr("value", CouponInfoDlg.sellerZtree.getSelectedVal());
    $("#sellerid").attr("value", treeNode.id);
};

$(function () {
    Feng.initValidator("sellerInfoForm", CouponInfoDlg.validateFields);

    var sellerTree = new $ZTree("sellerTree", "/seller/tree");
    sellerTree.bindOnClick(CouponInfoDlg.onClickNode);
    sellerTree.init();
    CouponInfoDlg.sellerZtree = sellerTree;
});
