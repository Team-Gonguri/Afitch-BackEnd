package com.project.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {

    private static int size = 10;

    public static Pageable normalPaging(int page, Sort.Direction direction) {
        return PageRequest.of(page, size, direction, "id");
    }

}
