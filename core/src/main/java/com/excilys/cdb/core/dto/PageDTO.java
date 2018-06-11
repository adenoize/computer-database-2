package com.excilys.cdb.core.dto;

import java.util.List;

/**
 * @author Aurelien Denoize Excilys 2018
 */
public class PageDTO<T> {

    private List<T> page;

    public List<T> getPage() {
        return page;
    }

    public void setPage(List<T> page) {
        this.page = page;
    }
}
