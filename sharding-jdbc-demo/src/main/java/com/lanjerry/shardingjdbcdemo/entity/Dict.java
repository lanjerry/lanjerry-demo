package com.lanjerry.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author linjierong
 * @since 2024-02-28
 */
@Data
@TableName("t_dict")
public class Dict {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String dictType;
}
