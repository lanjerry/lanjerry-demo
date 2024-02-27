package com.lanjerry.shardingjdbcdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lanjerry.shardingjdbcdemo.entity.Order;
import com.lanjerry.shardingjdbcdemo.entity.OrderItem;
import com.lanjerry.shardingjdbcdemo.mapper.OrderItemMapper;
import com.lanjerry.shardingjdbcdemo.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

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

    @Autowired
    private OrderItemMapper orderItemMapper;

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

    /**
     * 水平分片：查询所有记录
     * 查询了两个数据源，每个数据源中使用UNION ALL连接两个表
     */
    @Test
    public void testShardingSelectAll() {
        List<Order> orders = orderMapper.selectList(null);
        orders.forEach(System.out::println);
    }

    /**
     * 水平分片：根据user_id查询记录
     * 查询了一个数据源，每个数据源中使用UNION ALL连接两个表
     */
    @Test
    public void testShardingSelectByUserId() {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id", 2L);
        List<Order> orders = orderMapper.selectList(orderQueryWrapper);
        orders.forEach(System.out::println);
    }

    /**
     * 测试关联表插入
     */
    @Test
    public void testInsertOrderAndOrderItem() {
        for (long i = 1; i < 3; i++) {
            Order order = new Order();
            order.setOrderNo("LANJERRY" + i);
            order.setUserId(1L);
            orderMapper.insert(order);
            for (long j = 1; j < 3; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderNo("LANJERRY" + i);
                orderItem.setUserId(1L);
                orderItem.setPrice(new BigDecimal(10));
                orderItem.setCount(2);
                orderItemMapper.insert(orderItem);
            }
        }

        for (long i = 5; i < 7; i++) {
            Order order = new Order();
            order.setOrderNo("LANJERRY" + i);
            order.setUserId(2L);
            orderMapper.insert(order);
            for (long j = 1; j < 3; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderNo("LANJERRY" + i);
                orderItem.setUserId(2L);
                orderItem.setPrice(new BigDecimal(1));
                orderItem.setCount(3);
                orderItemMapper.insert(orderItem);
            }
        }
    }
}