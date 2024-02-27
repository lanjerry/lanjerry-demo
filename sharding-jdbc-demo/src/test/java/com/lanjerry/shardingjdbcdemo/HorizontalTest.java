package com.lanjerry.shardingjdbcdemo;

import com.lanjerry.shardingjdbcdemo.entity.Order;
import com.lanjerry.shardingjdbcdemo.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * <p>
 * 水平分片测试
 * </p>
 *
 * @author linjierong
 * @since 2024-02-23
 */
@SpringBootTest
public class HorizontalTest {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 水平分片：插入数据测试
     */
    @Test
    public void testInsertOrder() {
        Order order = new Order();
        order.setOrderNo("LANJERRY001");
        order.setUserId(1L);
        order.setAmount(new BigDecimal(118));
        orderMapper.insert(order);
    }

    /**
     * 水平分片：分库插入数据测试
     */
    @Test
    public void testInsertOrderDatabaseStrategy() {
        for (long i = 4; i < 8; i++) {
            Order order = new Order();
            order.setOrderNo("LANJERRY003");
            order.setUserId(i + 1);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }

    /**
     * 水平分片：分表插入数据测试
     */
    @Test
    public void testInsertOrderTableStrategy() {
        for (long i = 1; i < 5; i++) {
            Order order = new Order();
            order.setOrderNo("LANJERRY" + i);
            order.setUserId(i + 1);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
        for (long i = 5; i < 9; i++) {
            Order order = new Order();
            order.setOrderNo("LANJERRY" + i);
            order.setUserId(i + 1);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }
}
