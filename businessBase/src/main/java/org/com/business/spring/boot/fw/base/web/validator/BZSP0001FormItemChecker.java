package org.com.business.spring.boot.fw.base.web.validator;
import java.util.ArrayList;
import java.util.List;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.common.BZConstants.CharType;
import org.com.business.spring.boot.common.dto.ValidationDto;
import org.com.business.spring.boot.common.utils.BZValidationUtils;
import org.com.business.spring.boot.fw.base.common.form.BZSP0001Form;
import org.com.business.spring.boot.fw.core.i18n.LocaleMessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * @author 
 *
 */
@Component
public class BZSP0001FormItemChecker {
    @Autowired
    LocaleMessageSourceService localeMessageSourceService;

    public void check(BZSP0001Form form, Errors errors) {

        //チェック用リスト
        List<ValidationDto> validationDtoList = new ArrayList<>();
        ValidationDto validationDto = null;

        // ログインID
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("userId")
                .withMinLenth(1)
                .withMaxLenth(15)
                .withCharType(CharType.ALPHA_NUMERIC);
        validationDtoList.add(validationDto);

        // ユーザー名
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("userName")
                .withMinLenth(1)
                .withMaxLenth(20)
                .withCharType(CharType.ALL);
        validationDtoList.add(validationDto);

        // メール
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("email")
                .withMinLenth(1)
                .withMaxLenth(50)
                .withCharType(CharType.ALPHA);
        validationDtoList.add(validationDto);

        // パスワード
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("password")
                .withMinLenth(6)
                .withMaxLenth(15)
                .withCharType(CharType.ALPHA);
        validationDtoList.add(validationDto);

        // 確認パスワード
        validationDto = new ValidationDto();
        validationDto.withRequestFlg(true)
                .withItemName("passwordConf")
                .withMinLenth(0)
                .withMaxLenth(0)
                .withCharType(CharType.ALPHA);
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

