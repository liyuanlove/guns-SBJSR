/**
 * 卖家信息管理初始化
 */
var Seller = {
    id: "SellerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Seller.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '商家ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '地址', field: 'addr', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Seller.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Seller.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加卖家信息
 */
Seller.openAddSeller = function () {
    var index = layer.open({
        type: 2,
        title: '添加卖家信息',
        area: ['600px', '320px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/seller/seller_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看卖家信息详情
 */
Seller.openSellerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '卖家信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/seller/seller_edit/' + Seller.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除卖家信息
 */
Seller.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/seller/delete", function (data) {
            Feng.success("删除成功!");
            Seller.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sellerId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询卖家信息列表
 */
Seller.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Seller.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Seller.initColumn();
    var table = new BSTable(Seller.id, "/seller/list", defaultColunms);
    table.setPaginationType("server");
    Seller.table = table.init();
});
