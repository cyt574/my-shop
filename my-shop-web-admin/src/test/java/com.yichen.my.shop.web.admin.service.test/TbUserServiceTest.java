package com.yichen.my.shop.web.admin.service.test;


import com.yichen.my.shop.domain.TbUser;
import com.yichen.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUser = tbUserService.selectAll();
        for (TbUser user : tbUser) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("Jack Ma");
        tbUser.setPhone("1588888888");
        tbUser.setEmail("15888@888.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete() {
        tbUserService.delete(39L);
    }

    @Test
    public void testGetById() {
        TbUser tbUser = tbUserService.getById(40L);
        System.out.println(tbUser);
    }


    @Test
    public void testUpdate() {
        TbUser tbUser = tbUserService.getById(40L);
        tbUser.setUsername("Jackson Ma");
        tbUserService.update(tbUser);
    }

}
