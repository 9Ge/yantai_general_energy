package com.enercomn.util.constant;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangcheng
 */
@Data
@NoArgsConstructor
public class PageObject<T> {
    private int currPage=1 ;
    private int pageSize=10 ;
    private T data;

    public PageObject(int currPage, int pageSize) {
        this.currPage = currPage;
        this.pageSize = pageSize;
    }
}
