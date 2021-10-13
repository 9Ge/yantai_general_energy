package com.enercomn.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 手动分页工具类
 */
public class PageUtil {

    public static PageInfo pageInfoSetListBySelf(PageInfo pageInfo, List list){
        //计算起始值
        int startNum = 0;
        if (pageInfo.getPageNum() -1 > 0){
            startNum =  (pageInfo.getPageNum() -1) * pageInfo.getPageSize();
        }
        int endNum = pageInfo.getPageNum() * pageInfo.getPageSize();
        List pageList;

        if(endNum > list.size()){
            pageList = list.subList(startNum, list.size());
        }else{
            pageList = list.subList(startNum,endNum);
        }

        pageInfo.setList(pageList);
        return pageInfo;
    }
}
