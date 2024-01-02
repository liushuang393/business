/**
 *
 */
package org.com.business.spring.boot.fw.base;

import java.io.Serializable;

/**
 * @author liushuang
 *
 */
public class BaseForm implements Serializable {
    private static final long serialVersionUID = 5125096758372084309L;

    /**イベント名*/
    private String eventName;

    /**
     * イベント名を取得
     * @return イベント名
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * イベント名を設定
     * @param eventName イベント名
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
