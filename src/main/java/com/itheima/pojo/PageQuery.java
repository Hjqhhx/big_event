package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageQuery {

    private Integer pageNum;
    private Integer pageSize;
    private Integer categoryId;
    private String state;
    private Integer createId;
}
