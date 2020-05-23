package com.little.g.springcloud.admin.common;

/**
 * 基础信息
 *
 */
public class BaseEntity {

    private Long id;


    private Integer page = 1;


    private Integer pageCount = 20;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
