package org.com.business.spring.boot.fw.base.common.form;

import java.util.Date;

import org.com.business.spring.boot.fw.base.BaseForm;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentForm extends BaseForm {

    private String departmentId;

    private String departmentName;

    private String responsiblePerson;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date establishmentDate;

    private Date updatetime;

    private boolean updateFlag;

}
