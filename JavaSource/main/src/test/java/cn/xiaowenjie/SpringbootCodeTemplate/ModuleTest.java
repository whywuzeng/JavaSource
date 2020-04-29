package cn.xiaowenjie.SpringbootCodeTemplate;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xiaowenjie.SpringbootCodeTemplateApplication;
import cn.xiaowenjie.common.daos.UserDao;

/**
 * Created by Administrator on 2020/4/11.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.SpringbootCodeTemplate
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCodeTemplateApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class ModuleTest {

    @Autowired
    UserDao userDao;

    @Test
    public void test01_object(){
        System.out.println(userDao);
    }
}
