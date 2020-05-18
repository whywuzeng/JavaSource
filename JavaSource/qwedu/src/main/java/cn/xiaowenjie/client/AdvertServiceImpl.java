package cn.xiaowenjie.client;

import org.springframework.stereotype.Controller;

import java.util.List;

import cn.xiaowenjie.beans.Advert;
import cn.xiaowenjie.beans.Honor;
import cn.xiaowenjie.beans.Teacher;
import cn.xiaowenjie.client.beans.ShoolClient;

/**
 * Created by Administrator on 2020/5/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.client
 */
@Controller("advert/service")
public class AdvertServiceImpl implements AdvertClient {



    @Override
    public List<Advert> getAdverts(String position, String platform) {

        return null;
    }

    @Override
    public List<Teacher> getTeachers() {
        return null;
    }

    @Override
    public List<ShoolClient> getShools() {
        return null;
    }

    @Override
    public List<Honor> getHonors() {
        return null;
    }
}
