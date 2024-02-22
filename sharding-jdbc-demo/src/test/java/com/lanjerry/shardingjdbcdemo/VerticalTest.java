package com.lanjerry.shardingjdbcdemo;

import com.lanjerry.shardingjdbcdemo.entity.Order;
import com.lanjerry.shardingjdbcdemo.entity.User;
import com.lanjerry.shardingjdbcdemo.mapper.OrderMapper;
import com.lanjerry.shardingjdbcdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class VerticalTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 垂直分片：插入数据测试
     */
    @Test
    public void testInsertOrderAndUser() {
        User user = new User();
        user.setUname("lanjerry");
        userMapper.insert(user);

        Order order = new Order();
        order.setOrderNo("TEST001");
        order.setUserId(user.getId());
        order.setAmount(new BigDecimal(118));
        orderMapper.insert(order);
    }

    /**
     * 垂直分片：查询数据测试
     */
    @Test
    public void testSelectFromOrderAndUser() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
        Order order = orderMapper.selectById(1L);
        System.out.println(order);
    }
}
