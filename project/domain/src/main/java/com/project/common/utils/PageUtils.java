package com.project.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageUtils extends PageRequest {

    private PageUtils(int page, int size) {
        super(page, size, Sort.by("id").descending());
    }

    public static PageUtils of(int page) {
        return new PageUtils(page, 5);
    }
}
