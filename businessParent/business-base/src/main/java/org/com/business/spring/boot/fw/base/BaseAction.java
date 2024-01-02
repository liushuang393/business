package org.com.business.spring.boot.fw.base;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.fw.pages.BZPageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.alibaba.druid.util.StringUtils;

@Component
public class BaseAction {

    @Value(value = "${pagehelper.pageSize}")
    private String pageSizeValue;

    protected <T> T initForm(Class<T> type) throws Exception {
        return type.newInstance();
    }

    protected <T> void setPageInfo(Model model, BZPageInfo<T> pageInfo, String pageChgUrl) {
        model.addAttribute(BZConstants.PAGE_SIZE, pageInfo.getPageSize());
        model.addAttribute(BZConstants.PAGE_NUM, pageInfo.getPageNum());
        model.addAttribute(BZConstants.STA_PAGE_NUM, pageInfo.getPageNum() < 3 ? 1 : pageInfo.getPageNum() - 2);
        model.addAttribute(BZConstants.IS_FIRST_PAGE, pageInfo.isIsFirstPage());
        model.addAttribute(BZConstants.TOTAL_PAGES, pageInfo.getPages());
        model.addAttribute(BZConstants.IS_LAST_PAGE, pageInfo.isIsLastPage());
        model.addAttribute(BZConstants.TOTAL_TOTAL, pageInfo.getTotal());
        model.addAttribute(BZConstants.PAGE_CHG_URL, pageChgUrl);

    }

    protected int getDefaultPageSize(int defaultValue) {
        if (defaultValue == 0) {
            return StringUtils.isEmpty(pageSizeValue) ? BZConstants.PAGE_SIZE_DEFAULT_VALUE
                    : Integer.parseInt(pageSizeValue);
        } else if (BZConstants.PAGE_SIZE_MAX_VALUE < defaultValue) {
            return BZConstants.PAGE_SIZE_DEFAULT_VALUE;
        } else {
            return defaultValue;
        }
    }
}
