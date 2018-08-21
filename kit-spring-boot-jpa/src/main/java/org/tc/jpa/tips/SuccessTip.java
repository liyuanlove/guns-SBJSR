package org.tc.jpa.tips;

/**
 * 返回给前台的成功提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
public class SuccessTip extends Tip {

    public SuccessTip() {
        super(200, "操作成功");
    }

    public SuccessTip(String message) {
        super(200, message);
    }
}
