package com.cff.springwork.dictionary.test;

import com.cff.springwork.dictionary.entity.SeAppUserEntity;
import org.apache.ibatis.jdbc.Null;
import org.apache.taglibs.standard.lang.jstl.NullLiteral;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.access.method.P;

import javax.persistence.Query;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import static org.junit.Assert.*;


public class SeAppUserEntityTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() throws Exception {
        //创建配置对象(读取配置文档)
        Configuration config = new Configuration().configure("hibernate/hibernate.cfg.xml");
        //创建会话工厂对象
        sessionFactory = config.buildSessionFactory();
        //会话对象
        session = sessionFactory.openSession();
        //开启事务
        transaction = session.beginTransaction();
    }
    @After
    public void destroy(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
    @Test
    public void testInsertSeAppUserEntity(){
        SeAppUserEntity seAppUserEntity = new SeAppUserEntity();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        seAppUserEntity.setUuid(uuid);
        seAppUserEntity.setUserName("测试id" + Integer.toString((int) (Math.random()*100)));
        seAppUserEntity.setPasswd("123123");
        seAppUserEntity.setUserType("1234");
        seAppUserEntity.setUserNo("NULL");
        session.save(seAppUserEntity);

    }
    @Test
    public void testSelectSeAppUserEntity(){
//        System.out.println("SeAppUserEntityTest.testSelectSeAppUserEntity");
//        System.out.println("123");
//        session.createQuery(s)
        String hql = "FROM SeAppUserEntity";
        Query query = session.createQuery(hql);
        List<SeAppUserEntity> list = query.getResultList();
        for(SeAppUserEntity o : list){
            System.out.println("o = " + o);
            System.out.println("uuid is " + o.getUuid());
            System.out.println("user_name is " + o.getUserName());
            System.out.println("password is " + o.getPasswd());
            System.out.println("user_type is " + o.getUserType());
            System.out.println("user_no is " + o.getUserNo());
            System.out.println();
        }

    }
}