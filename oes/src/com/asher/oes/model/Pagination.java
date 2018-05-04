package com.asher.oes.model;

import com.asher.oes.util.StringUtil;

public class Pagination {

    private int totalCount;

    private int pageSize;
    private int pageCount;

    private int currentPage;
    private int offSet;

    private String orderBy;
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getOffSet() {
        offSet = (getCurrentPage() - 1) * pageSize;
        return offSet;
    }

    public int getCurrentPage() {

        if (currentPage < 1) {
            currentPage = 1;
        }

        return currentPage;
    }

    public int getPageCount() {

        if (totalCount < 1) {
            pageCount = 0;

            return pageCount;
        }

        pageCount = (totalCount - 1) / getPageSize() + 1;
        return pageCount;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    public String getOrderBy() {
        if(StringUtil.isNull(orderBy)) {
            orderBy = "ASC";
        }
        return orderBy;
    }

}