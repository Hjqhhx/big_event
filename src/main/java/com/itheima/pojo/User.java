package com.itheima.pojo;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class User {
    @NotNull //限制该值不能为null
    private Integer id;//主键ID

    @Pattern(regexp = "^\\S{5,16}$")
    private String username;//用户名

    @JsonIgnore //该注解作用：让springmvc把当前对象转换为json字符串格式时，忽略password，所以返回给前端的数据就不会有这个属性
    private String password;//密码

    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称

    @NotEmpty //该注解表示的字段不能为null且不能为空
    @Email //该注解标识的字段需要满足邮箱格式
    private String email;//邮箱

    private String userPic;//用户头像地址

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间
}
