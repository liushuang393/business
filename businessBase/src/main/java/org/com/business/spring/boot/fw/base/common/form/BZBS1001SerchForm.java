package org.com.business.spring.boot.fw.base.common.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.com.business.spring.boot.fw.base.BaseForm;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BZBS1001SerchForm extends BaseForm {

    /** ポストID */
    private String positId;

    /** ポスト名 */
    private String positName;

    /** 説明 */
    private String remarks;

}
