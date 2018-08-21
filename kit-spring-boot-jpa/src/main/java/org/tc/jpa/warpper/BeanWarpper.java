package org.tc.jpa.warpper;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制器查询结果（值对象）的包装类基类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:49:36
 */
public abstract class BeanWarpper<PO, VO> {

    public List<VO> warpList(List<PO> poList) {
        List<VO> voList = new ArrayList<VO>();
        for (PO user : poList) {
            voList.add(warpBean(user));
        }
        return voList;

    }

    public abstract VO warpBean(PO po);

}
