package me.zhengjie.modules.eladmin2.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("account")
public class Account {
    private Long id;
    private String email;
    private String name;
}
