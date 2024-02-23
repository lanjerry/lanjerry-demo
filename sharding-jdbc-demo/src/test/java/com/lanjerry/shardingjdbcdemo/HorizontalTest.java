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

    @Test
    public void testInsertOrder() {
        Order order = new Order();
        order.setOrderNo("LANJERRY001");
        order.setUserId(1L);
        order.setAmount(new BigDecimal(118));
        orderMapper.insert(order);
    }
}
