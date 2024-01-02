package org.com.business.spring.boot.fw.base.web.validator;
import java.util.ArrayList;
import java.util.List;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.BZConstants.CharType;
import org.com.business.spring.boot.common.dto.ValidationDto;
import org.com.business.spring.boot.common.utils.BZValidationUtils;
import org.com.business.spring.boot.fw.base.common.form.DepartmentListForm;
import org.com.business.spring.boot.fw.core.i18n.LocaleMessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * @author 
 *
 */
@Component
public class DepartmentListFormItemChecker {
    @Autowired
    LocaleMessageSourceService localeMessageSourceService;

    public void check(DepartmentListForm form, Errors errors) {

        //チェック用リスト
        List<ValidationDto> validationDtoList = new ArrayList<>();
        ValidationDto validationDto = null;

        // 部署ID
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("departmentId")
                .withMinLenth(1)
                .withMaxLenth(5)
                .withCharType(CharType.ALPHA_NUMERIC);
        validationDtoList.add(validationDto);

        // 部署名
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("departmentName")
                .withMinLenth(1)
                .withMaxLenth(20)
                .withCharType(CharType.ZENKAKU);
        validationDtoList.add(validationDto);

        // 責任者
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(false)
                .withItemName("responsiblePerson")
                .withMinLenth(0)
                .withMaxLenth(15)
                .withCharType(CharType.ALL);
        validationDtoList.add(validationDto);

        // 成立日
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(false)
                .withItemName("establishmentDay")
                .withMinLenth(0)
                .withMaxLenth(0)
                .withCharType(CharType.ALL);
        validationDtoList.add(validationDto);

        //チェックを実行
        for (ValidationDto validation : validationDtoList) {

            String itemName = validation.getItemName();
            int minLenth = validation.getMinLenth();
            int maxLenth = validation.getMaxLenth();
            CharType charType = validation.getCharType();

            if (validation.isRequestFlg()) {
                BZValidationUtils.rejectIfEmptyOrWhitespace(errors, itemName,
                        new String[] {
                                localeMessageSourceService.getMessage(BZConstants.BZ_VALIDLABEL + "." + itemName) });
            }

            BZValidationUtils.rejectCheckCharType(errors, itemName, charType,
                    minLenth, maxLenth,
                    new String[] {
                            localeMessageSourceService.getMessage(BZConstants.BZ_VALIDLABEL + "." + itemName) });
        }

    }
}

