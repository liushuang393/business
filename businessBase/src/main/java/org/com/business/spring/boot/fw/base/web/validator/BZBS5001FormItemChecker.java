package org.com.business.spring.boot.fw.base.web.validator;
import java.util.ArrayList;
import java.util.List;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.BZConstants.CharType;
import org.com.business.spring.boot.common.dto.ValidationDto;
import org.com.business.spring.boot.common.utils.BZValidationUtils;
import org.com.business.spring.boot.fw.base.common.form.BZBS5001Form;
import org.com.business.spring.boot.fw.core.i18n.LocaleMessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * @author 
 *
 */
@Component
public class BZBS5001FormItemChecker {
    @Autowired
    LocaleMessageSourceService localeMessageSourceService;

    public void check(BZBS5001Form form, Errors errors) {

        //チェック用リスト
        List<ValidationDto> validationDtoList = new ArrayList<>();
        ValidationDto validationDto = null;

        // ユーザーID
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("userId")
                .withMinLenth(1)
                .withMaxLenth(15)
                .withCharType(CharType.ALPHA_NUMERIC);
        validationDtoList.add(validationDto);

        // 通知メッセージ題名
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(false)
                .withItemName("messageTitle")
                .withMinLenth(0)
                .withMaxLenth(30)
                .withCharType(CharType.ALL);
        validationDtoList.add(validationDto);

        // 通知メッセージ内容
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("messageBody")
                .withMinLenth(1)
                .withMaxLenth(200)
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

