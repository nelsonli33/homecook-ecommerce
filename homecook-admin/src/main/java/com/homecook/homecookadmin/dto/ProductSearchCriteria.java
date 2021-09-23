package com.homecook.homecookadmin.dto;

import org.springframework.data.domain.Sort;

public class ProductSearchCriteria
{
    private Integer page;
    private Integer limit;
    private Sort.Direction direction;
    private String sort;
    private Integer status;

    public Integer getPage()
    {
        return page;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public Sort.Direction getDirection()
    {
        return direction;
    }

    public String getSort()
    {
        return sort;
    }

    public Integer getStatus()
    {
        return status;
    }


    public static final class Builder
    {
        private Integer page;
        private Integer limit;
        private Sort.Direction direction;
        private String sort;
        private Integer status;

        private Builder()
        {
        }

        public static Builder newBuilder()
        {
            return new Builder();
        }

        public Builder setPage(Integer page)
        {
            this.page = page;
            return this;
        }

        public Builder setLimit(Integer limit)
        {
            this.limit = limit;
            return this;
        }

        public Builder setDirection(Sort.Direction direction)
        {
            this.direction = direction;
            return this;
        }

        public Builder setSort(String sort)
        {
            this.sort = sort;
            return this;
        }

        public Builder setStatus(Integer status)
        {
            this.status = status;
            return this;
        }

        public ProductSearchCriteria build()
        {
            ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria();
            productSearchCriteria.limit = this.limit;
            productSearchCriteria.sort = this.sort;
            productSearchCriteria.page = this.page;
            productSearchCriteria.status = this.status;
            productSearchCriteria.direction = this.direction;
            return productSearchCriteria;
        }
    }
}
