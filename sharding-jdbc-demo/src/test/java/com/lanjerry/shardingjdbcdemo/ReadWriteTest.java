package com.lanjerry.shardingjdbcdemo;

import com.lanjerry.shardingjdbcdemo.entity.User;
import com.lanjerry.shardingjdbcdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 读写分离测试
 * </p>
 *
 * @author linjierong
 * @since 2024-02-23
 */
@SpringBootTest
public class ReadWriteTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 读写分离：写入数据的测试
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUname("张三丰");
        userMapper.insert(user);
    }

    /**
     * 读写分离：事务测试
     * 结论：
     * 为了保证主从库间的事务一致性，避免跨服务的分布式事务，ShardingSphere-JDBC的`主从模型中，事务中的数据读写均用主库`
     * 不添加@Transactional：insert对主库操作，select对从库操作
     * 添加@Transactional：则insert和select均对主库操作
     * 注意：
     * 在JUnit环境下的@Transactional注解，默认情况下就会对事务进行回滚（即使在没加注解@Rollback，也会对事务回滚）
     */
    @Test
    @Transactional
    public void testTrans() {
        User user = new User();
        user.setUname("铁锤");
        userMapper.insert(user);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectAll() {
        List<User> users1 = userMapper.selectList(null);
        List<User> users2 = userMapper.selectList(null);
        List<User> users3 = userMapper.selectList(null);
        List<User> users4 = userMapper.selectList(null);
    }
}
