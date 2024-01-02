package org.com.business.spring.boot.fw.base.common.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.com.business.spring.boot.fw.base.BaseForm;
import org.com.business.spring.boot.fw.persistence.entity.Department;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentListForm extends BaseForm {

    /** 部署ID */
    private String departmentId;

    /** 部署名 */
    private String departmentName;

    /** 責任者 */
    private String responsiblePerson;

    /** 成立日 */
    private Date establishmentDay;

    /**部門リスト*/
    private List<Department> departmentList = new ArrayList<>();

    private String searchBtn;

    private String clearBtn;

}
