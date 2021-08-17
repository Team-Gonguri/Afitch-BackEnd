package com.project.common.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageDto<T> {
    private int currentPage;
    private int totalPages;
    private List<T> contents;

    public PageDto(Page<T> pages) {
        this.currentPage = pages.getNumber();
        this.totalPages = pages.getTotalPages();
        this.contents = pages.getContent();
    }

    public PageDto(List<T> contents, int currentPage, int totalPages) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.contents = contents;
    }
}
