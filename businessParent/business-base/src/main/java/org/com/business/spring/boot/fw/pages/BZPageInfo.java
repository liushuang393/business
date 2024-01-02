/**
 *
 */
package org.com.business.spring.boot.fw.pages;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * @author liushuang
 *
 */
public class BZPageInfo<T> extends PageInfo<T> {

    public BZPageInfo (){
    }
    public BZPageInfo (List<T> list){
        super(list);
    }
}
