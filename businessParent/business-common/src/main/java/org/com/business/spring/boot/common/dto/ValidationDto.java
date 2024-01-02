/**
 *
 */
package org.com.business.spring.boot.common.dto;

import org.com.business.spring.boot.common.BZConstants.CharType;

import lombok.Data;

/**
 * @author liushuang
 *
 */
@Data
public class ValidationDto {
    boolean requestFlg;
    String itemName;
    int minLenth;
    int maxLenth;
    CharType charType;

    public ValidationDto withRequestFlg(boolean requestFlg) {
        this.setRequestFlg(requestFlg);
        return this;
    }

    public ValidationDto withItemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public ValidationDto withMinLenth(int minLenth) {
        this.setMinLenth(minLenth);
        return this;
    }

    public ValidationDto withMaxLenth(int maxLenth) {
        this.setMaxLenth(maxLenth);
        return this;
    }

    public ValidationDto withCharType(CharType charType) {
        this.setCharType(charType);
        return this;
    }

}
