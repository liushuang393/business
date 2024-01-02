/**
 *
 */
package org.com.business.spring.boot.common.utils;

import java.util.regex.Pattern;

import org.com.business.spring.boot.common.BZConstants.CharType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * @author liushuang
 *
 */
public class BZValidationUtils {

    public static void rejectIfEmptyOrWhitespace(
            Errors errors, String field, @Nullable Object[] errorArgs) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field,
                "item.empty.error",
                errorArgs,
                "");
    }

    public static void rejectCheckRegexp(Errors errors, String field, String regexp,
            @Nullable Object[] errorArgs) {
        Assert.notNull(errors, "Errors object must not be null");
        Object value = errors.getFieldValue(field);
        if (value != null) {
            if (regexp == null || !Pattern.matches(regexp, value.toString())) {
                errors.rejectValue(field, "item.pattern.error", errorArgs, "");
            }
        }
    }

    public static void rejectCheckCharType(
            Errors errors, String field, CharType charType, int minLenth, int maxLenth, @Nullable Object[] errorArgs) {



        Assert.notNull(errors, "Errors object must not be null");
        Object value = errors.getFieldValue(field);
        if (value != null ) {
            String checkValue = value.toString();
            // 文字列
            if (value instanceof String && !StringUtils.isEmpty(checkValue)) {

                if (maxLenth != 0) {
                    String lenPattern = "{" + minLenth + "," + maxLenth + "}";
                    if (!Pattern.matches("[\\s\\S.*]" + lenPattern, checkValue)) {
                        errors.rejectValue(field, "item.lenth.error",
                                new String[] { String.valueOf(minLenth), String.valueOf(maxLenth) }, "");
                    }
                }

                //属性（桁数）チェック
                String error = checkCharType(charType, checkValue);
                if (!StringUtils.isEmpty(error)) {
                    errors.rejectValue(field, "item.invalid." + error + ".error", errorArgs, "");
                }
            }
        }

    }

    private static String checkCharType(CharType charType, String input) {

        if (StringUtils.isEmpty(input)) {
            return "";
        }
        switch (charType) {
        // 全角文字（ひらがな・カタカナ・漢字 etc.）
        case ZENKAKU:
            return Pattern.matches("^[^\\x01-\\x7E\\xA1-\\xDF]+$", input) ? "" : "zenkaku";
        // 全角ひらがな
        case HIRAGANA:
            return Pattern.matches("^[\u3041-\u3096]+$", input) ? "" : "hiragana";
        // 全角カタカナ
        case KATAKANA:
            return Pattern.matches("^[\u30a1-\u30f6]+$", input) ? "" : "katakana";
        // 半角英数字（大文字・小文字）
        case ALPHA_NUMERIC:
            return Pattern.matches("^[0-9a-zA-Z]+$", input) ? "" : "alpha_numeric";
        // 半角数字
        case NUMERIC:
            return Pattern.matches("^[0-9]+$", input) ? "" : "numeric";
        // 半角英字（大文字・小文字）
        case ALPHA_BETIC:
            return Pattern.matches("^[a-zA-Z]+$", input) ? "" : "alpha_betic";
        // 半角英字（大文字のみ）
        case UPPER_ALPHA_BETIC:
            return Pattern.matches("^[A-Z]+$", input) ? "" : "upper_alpha_betic";
        // 半角英字（小文字のみ）
        case LOWER_ALPHA_BETIC:
            return Pattern.matches("^[a-z]+$", input) ? "" : "lower_alpha_betic";
        // 半角記号
        case ALPHA_NUMERIC_SYMBOL:
            return Pattern.matches("^[!-/:-@\\[-`{-~]+$", input) ? "" : "alpha_numeric_symbol";
        // 半角
        case ALPHA:
            return Pattern.matches("^[ -~]+$", input) ? "" : "alpha";
        default:
            return "";
        }

    }

    public static void main(String[] aa) {
        System.out.println(checkCharType(CharType.ALL, " adasdjjF$$%&''2442;+;][}@paaあ"));
        System.out.println(checkCharType(CharType.ZENKAKU, ""));
        System.out.println(checkCharType(CharType.HIRAGANA, ""));
        System.out.println(checkCharType(CharType.KATAKANA, ""));
        System.out.println(checkCharType(CharType.ALPHA_NUMERIC, ""));
        System.out.println(checkCharType(CharType.NUMERIC, ""));
        System.out.println(checkCharType(CharType.UPPER_ALPHA_BETIC, ""));
        System.out.println(checkCharType(CharType.LOWER_ALPHA_BETIC, ""));
        System.out.println(checkCharType(CharType.ALPHA_NUMERIC_SYMBOL, ""));
        System.out.println(checkCharType(CharType.ALPHA, ""));
        System.out.println("==============--");
        System.out.println(checkCharType(CharType.ZENKAKU, "半角"));
        System.out.println(checkCharType(CharType.HIRAGANA, "ひらがな"));
        System.out.println(checkCharType(CharType.KATAKANA, "カタカナ"));
        System.out.println(checkCharType(CharType.ALPHA_NUMERIC, "Aab234"));
        System.out.println(checkCharType(CharType.NUMERIC, "1231214"));
        System.out.println(checkCharType(CharType.UPPER_ALPHA_BETIC, "AAADFGV"));
        System.out.println(checkCharType(CharType.LOWER_ALPHA_BETIC, "adasdjj"));
        System.out.println(checkCharType(CharType.ALPHA_NUMERIC_SYMBOL, "!#&"));
        System.out.println(checkCharType(CharType.ALPHA, "adasdjjF$$%&''2442;+;][}@p"));
        System.out.println("==========wwww====--");
        System.out.println(checkCharType(CharType.ZENKAKU, "あい半角んカタ1"));
        System.out.println(checkCharType(CharType.HIRAGANA, "ひらがな2"));
        System.out.println(checkCharType(CharType.KATAKANA, "カタカナ2"));
        System.out.println(checkCharType(CharType.ALPHA_NUMERIC, "Aab234あ"));
        System.out.println(checkCharType(CharType.NUMERIC, "1231214a"));
        System.out.println(checkCharType(CharType.UPPER_ALPHA_BETIC, "AAADFGVa"));
        System.out.println(checkCharType(CharType.LOWER_ALPHA_BETIC, "adasdjjF"));
        System.out.println(checkCharType(CharType.ALPHA_NUMERIC_SYMBOL, "!asdf1324"));
        System.out.println(checkCharType(CharType.ALPHA, "adasdjjF$$%&''2442;+;][}@paaあ"));
    }
}
