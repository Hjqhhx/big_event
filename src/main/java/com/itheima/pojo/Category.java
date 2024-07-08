package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {

    //设置校验分组
    public interface Add {
    }

    public interface Update {
    }

    @NotNull(groups = Update.class)
    private Integer id;//主键ID

    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryName;//分类名称

    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryAlias;//分类别名

    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss") //指定返回时转化为json字符串的时间格式
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
