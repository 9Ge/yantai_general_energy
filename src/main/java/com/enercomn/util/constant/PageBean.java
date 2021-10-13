package com.enercomn.util.constant;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 功能说明：分页数据处理实体类
 *
 * Created by lzr on 2018/1/30.
 */
public class PageBean<T>  {

    /**
     * 数据总量
     */
    public Long totalCount;

    /**
     * 当前页
     */
    public int currPage;

    /**
     * 当前页条数
     */
    public int currCount;

    /**
     * 当前页数据
     */
    public List<T> dataList;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 下一页
     */
    private int nextPage;
    /**
     * 最后一页
     */
    private int lastPage;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getCurrCount() {
        return currCount;
    }

    public void setCurrCount(int currCount) {
        this.currCount = currCount;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public PageBean(PageInfo<T> pageInfo) {
        totalCount = pageInfo.getTotal();
        currPage = pageInfo.getPageNum();
        currCount = pageInfo.getSize();
        dataList = pageInfo.getList();
        pages = pageInfo.getPages();
        nextPage = pageInfo.getNextPage();
        lastPage = pageInfo.getLastPage();
    }
}
