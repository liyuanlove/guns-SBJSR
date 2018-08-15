$(function () {
    //生成100*100(宽度100，高度100)的二维码
    //生成前景色为红色背景色为白色的二维码
    jQuery('#qrcode').qrcode({
        render: "canvas", //也可以替换为table
        // render: "table",
        width: 150,
        height: 150,
        foreground: "#000000",
        background: "#FFFFFF",
        text: $("#address").val()
    });
});
