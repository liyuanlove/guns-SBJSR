/**
 * 优惠券管理初始化
 */
var Coupon = {
    id: "CouponTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Coupon.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '优惠券ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '商家', field: 'sellername', visible: true, align: 'center', valign: 'middle'},
        {title: '起始日期', field: 'beginDay', visible: true, align: 'center', valign: 'middle'},
        {title: '结束日期', field: 'endDay', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号码', field: 'userTel', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
        {title: '领取时间', field: 'receptDay', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createtime', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 日期格式化
 */
// Coupon.formatter = function (value, row, index) {
//     if (value == null) {
//         return "-";
//     }
//     var date = value.substring(0, 10);
//     return date;
// };

/**
 * 检查是否选中
 */
Coupon.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Coupon.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加优惠券
 */
Coupon.openAddCoupon = function () {
    var index = layer.open({
        type: 2,
        title: '添加优惠券',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/coupon/coupon_add'
    });
    this.layerIndex = index;
};

/**
 * 使用优惠券
 */
Coupon.openUseCoupon = function () {
    if (this.check()) {
        var code = window.prompt("请输入验证码", "");
        if (code != null && code != "") {
            //提交信息
            var ajax = new $ax(Feng.ctxPath + "/coupon/use", function (data) {
                Feng.success("操作成功!");
                Coupon.table.refresh();
            }, function (data) {
                Feng.error("操作失败!" + data.responseJSON.message + "!");
            });
            //配置提交的数据
            ajax.set("userTel", this.seItem.userTel);
            ajax.set("code", code);
            ajax.start();
        } else {
            Feng.info("请输入验证码！");
        }
    }
};

/**
 * 打开查看二维码
 */
Coupon.openCouponDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码',
            area: ['600', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/coupon/qrcode/' + Coupon.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除优惠券
 */
Coupon.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/coupon/delete", function (data) {
            Feng.success("删除成功!");
            Coupon.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("couponId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询优惠券列表
 */
Coupon.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Coupon.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Coupon.initColumn();
    var table = new BSTable(Coupon.id, "/coupon/list", defaultColunms);
    table.setPaginationType("server");
    Coupon.table = table.init();
});

function init() {

    var BootstrapTable = $.fn.bootstrapTable.Constructor;
    BootstrapTable.prototype.onSort = function (event) {
        var $this = event.type === "keypress" ? $(event.currentTarget) : $(event.currentTarget).parent(),
            $this_ = this.$header.find('th').eq($this.index()),
            sortName = this.header.sortNames[$this.index()];

        this.$header.add(this.$header_).find('span.order').remove();

        if (this.options.sortName === $this.data('field')) {
            this.options.sortOrder = this.options.sortOrder === 'asc' ? 'desc' : 'asc';
        } else {
            this.options.sortName = sortName || $this.data('field');
            this.options.sortOrder = $this.data('order') === 'asc' ? 'desc' : 'asc';
        }
        this.trigger('sort', this.options.sortName, this.options.sortOrder);

        $this.add($this_).data('order', this.options.sortOrder);

        // Assign the correct sortable arrow
        this.getCaret();

        if (this.options.sidePagination === 'server') {
            this.initServer(this.options.silentSort);
            return;
        }

        this.initSort();
        this.initBody();
    };
    BootstrapTable.prototype.getCaret = function () {
        var that = this;

        $.each(this.$header.find('th'), function (i, th) {
            var sortName = that.header.sortNames[i];
            $(th).find('.sortable').removeClass('desc asc').addClass((sortName || $(th).data('field')) === that.options.sortName ? that.options.sortOrder : 'both');
        });
    };
}