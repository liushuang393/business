/**
 *
 */
package org.com.business.spring.boot.common.dto;

/**
 * @author liushuang
 *
 */
public interface ResultEntity<T> {

    /**
     * @param message
     * @return
     */
    static ResultEntity<?> fail(String message) {

        return null;
    }

}
