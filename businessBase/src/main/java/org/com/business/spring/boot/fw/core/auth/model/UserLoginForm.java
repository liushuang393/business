package org.com.business.spring.boot.fw.core.auth.model;

import java.io.Serializable;

import org.com.business.spring.boot.fw.core.annotation.NotEmpty;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginForm implements Serializable {

    private static final long serialVersionUID = 4125095758372084309L;

    /** ユーザID */
    @Length(min = 5, max = 10)
    @NotEmpty(field = "userId")
    private String userId;

    /** ユーザ名 */
    private String name;

    /**　パスワード */
    @NotEmpty(field = "password")
    private String password;

    /** 言語 */
    private String locale;

}
