package org.com.business.spring.boot.common;

/**
 * フレームワーク用定数
 */
public class BZConstants {

    public static final String PROFILES_ACTIVE_DEV = "dev";
    //プロッパテイ名　メール関連
    public static final String PROPERTIESNAME_EMAIL = "email.properties";
    public static final String HOSTNAME = "host.name";
    public static final String POP_USERNAME = "pop.user.name";
    public static final String USERNAME = "user.name";
    public static final String POP_PASSWORD = "pop.password";
    public static final String CODING = "mail.coding";
    public static final String PORT = "host.port";

    //session中Key
    public static final String SESSION_PROFILE = "_PROFILE_";
    public static final String SESSION_FORM_NAME = "userLoginForm";

    public static final String ADMINISTRATOR = "admin";
    public static final String __SYSTEM__ = "XXSYSTEMXX";

    public static final String ADMINTYPE = "1";

    public static final String REQATTR_PAGE = "page";
    public static final String REQATTR_MESSAGE = "_MESSAGE_";
    public static final String REQATTR_CALLBACKTYPE = "_CALLBACKTYPE_";
    public static final String REQATTR_FORWARDURL = "_FORWARDURL_";
    public static final String REQATTR_NAVTABID = "_NAVTABID_";
    public static final String REQATTR_AJAX = "_AJAX_";
    public static final String REQATTR_JSON = "_JSON_";

    public static final String STATUS_ENABLE = "0";
    public static final String STATUS_DISABLE = "1";

    public static final String USER_ISLOGIN = "1";
    public static final String USER_UNLOGIN = "0";

    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final String ENCODING_GB18030 = "GB18030";
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String ENCODING_GBK = "GBK";
    public static final String ENCODING_ISO8859 = "ISO8859-1";

    public static final String CURRENCY_CNY = "CNY";
    public static final String CURRENCY_JPY = "JPY";
    public static final String CURRENCY_EUR = "EUR";
    public static final String CURRENCY_CAD = "CAD";
    public static final String CURRENCY_AUD = "AUD";
    public static final String CURRENCY_GBP = "GBP";
    public static final String CURRENCY_CHF = "CHF";
    public static final String BZ_MESSAGES = "errorMessages";

    /** ===== ページ情報*/
    public static final String PAGE_NUM_DEFAULT_VALUE = "1";
    public static final int PAGE_SIZE_DEFAULT_VALUE = 10;
    public static final int PAGE_SIZE_MAX_VALUE = 10000;
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE_NUM = "pageNum";
    public static final String STA_PAGE_NUM = "staPageNum";
    public static final String IS_FIRST_PAGE = "isFirstPage";
    public static final String TOTAL_PAGES = "totalPages";
    public static final String TOTAL_TOTAL = "total";
    public static final String IS_LAST_PAGE = "isLastPage";
    public static final String PAGE_CHG_URL = "pageChgUrl";

    public static final String BZ_TOKEN = "bzToken";
    public static final String BZ_VALIDLABEL = "validLabel";

    public static enum CharType {
        ZENKAKU, //全角文字（ひらがな・カタカナ・漢字 ETC.）
        HIRAGANA, //全角ひらがな
        KATAKANA, //全角カタカナ
        ALPHA_NUMERIC, //半角英数字（大文字・小文字）
        NUMERIC, //半角数字
        ALPHA_BETIC, //半角英字（大文字・小文字）
        UPPER_ALPHA_BETIC, //半角英字（大文字のみ）
        LOWER_ALPHA_BETIC, //半角英字（小文字のみ）
        ALPHA_NUMERIC_SYMBOL, //半角(半角英数字と記号)
        ALPHA, //半角(半角英数字と記号)
        ALL, //半角(半角英数字と記号)
    }
}
