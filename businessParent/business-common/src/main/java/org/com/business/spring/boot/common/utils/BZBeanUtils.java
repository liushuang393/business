package org.com.business.spring.boot.common.utils;

import org.springframework.cglib.beans.BeanCopier;

public class BZBeanUtils {

    public static void copyBeanProperties(Object origBean, Object destBean) {

        if (destBean == null || origBean == null) {
            return;
        }
        //BeanUtils.copyProperties(origBean, destBean);
        BeanCopier beanCopier = BeanCopier.create(origBean.getClass(),destBean.getClass(),false);
        beanCopier.copy(origBean,destBean,null);
    }
}
