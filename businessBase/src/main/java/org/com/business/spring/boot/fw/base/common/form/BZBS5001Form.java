package org.com.business.spring.boot.fw.base.common.form;

import java.util.List;

import org.com.business.spring.boot.common.KeyValueBean;
import org.com.business.spring.boot.fw.base.BaseForm;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BZBS5001Form extends BaseForm {

	private static final long serialVersionUID = 1L;

	/** 通知メッセージID */
	private String messageId;

	/** ユーザーID */
	private String userId;

	/** 通知メッセージ題名 */
	private String messageTitle;

	/** 通知メッセージ内容 */
	private String messageBody;

	List<KeyValueBean> userList;
}
