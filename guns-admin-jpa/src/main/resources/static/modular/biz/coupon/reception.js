/**
 * 初始化优惠券详情对话框
 */
var Reception = {
    couponInfoData: {},
    validateFields: {
        userTel: {
            validators: {
                notEmpty: {
                    message: '手机号码不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
Reception.clearData = function () {
    this.couponInfoData = {};
}

/**
 * 存储对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Reception.set = function (key, val) {
    this.couponInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Reception.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Reception.close = function () {
    parent.layer.close(window.parent.Coupon.layerIndex);
}

/**
 * 收集数据(要提交的字段)
 */
Reception.collectData = function () {
    this.set('userTel')
        .set('couponId');
}

/**
 * 验证数据是否为空
 */
Reception.validate = function () {
    $('#defaultForm').data("bootstrapValidator").resetForm();
    $('#defaultForm').bootstrapValidator('validate');
    return $("#defaultForm").data('bootstrapValidator').isValid();
};

/**
 * 领取提交
 */
Reception.mylist = function () {
    window.location.href = Reception.get('ctxPath') + "/coupon/mylist?userTel=" + Reception.get("userTel");
}

/**
 * 页面跳转
 */
Reception.receptSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    jQuery.ajax({
        dataType: "json",
        type: "post",
        url: Reception.get('ctxPath') + '/coupon/reception',
        data: Reception.couponInfoData,
        success: function (data) {
            // 登录成功或者失败的提示信息
            if (data.code == 200) {
                alert("领取成功,使用验证码：" + data.message);
            } else {
                alert(data.message);
            }
        },
        error: function () {
            Feng.error("领取失败!");
        }
    });
}

$(function () {
    Feng.initValidator("defaultForm", Reception.validateFields);

});
