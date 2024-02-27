package com.lanjerry.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 垂直分片测试-订单表
 * </p>
 *
 * @author linjierong
 * @since 2024-02-22
 */
@Data
@TableName("t_order")
public class Order {

    // 当配置了shardingsphere-jdbc的分布式序列时，自动使用shardingsphere-jdbc的分布式序列
    // 当没有配置shardingsphere-jdbc的分布式序列时，自动依赖数据库的主键自增策略
    @TableId(type = IdType.AUTO)
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal amount;
}
