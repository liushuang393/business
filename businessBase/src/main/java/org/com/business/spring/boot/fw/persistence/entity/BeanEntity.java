/**
 *
 */
package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BeanEntity implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;
}
