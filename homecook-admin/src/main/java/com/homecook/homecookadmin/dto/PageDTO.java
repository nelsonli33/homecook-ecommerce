package com.homecook.homecookadmin.dto;

import java.util.List;

public class PageDTO<T>
{
    private int total;
    private int currentPage;
    private int pageSize;
    private int pageCount;
    private List<T> result;

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public List<T> getResult()
    {
        return result;
    }

    public void setResult(List<T> result)
    {
        this.result = result;
    }
}
