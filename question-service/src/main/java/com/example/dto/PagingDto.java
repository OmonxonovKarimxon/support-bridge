package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto<T> {

    private Integer count = 0;
    private Integer page = 0;
    private Integer limit = 0;
    private List<T> items;

    public PagingDto(Integer count, Integer page, Integer limit) {
        this.count = count;
        this.page = page;
        this.limit = limit;
    }

    public List<T> getItems() {
        return this.items;
    }
}
