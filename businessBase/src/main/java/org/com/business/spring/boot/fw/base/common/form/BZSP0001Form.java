package org.com.business.spring.boot.fw.base.common.form;

import org.com.business.spring.boot.fw.base.BaseForm;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BZSP0001Form extends BaseForm {

    /** ログインID */
    private String userId;

    /** ユーザー名 */
    private String userName;

    /** メール */
    private String email;

    /** パスワード */
    private String password;

    /** 確認パスワード */
    private String passwordConf;

}
