package cn.xiaowenjie.client;

import java.util.List;

import cn.xiaowenjie.beans.Advert;
import cn.xiaowenjie.beans.Honor;
import cn.xiaowenjie.beans.Teacher;
import cn.xiaowenjie.client.beans.ShoolClient;

/**
 * Created by Administrator on 2020/5/9.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.client
 */

public interface AdvertClient {

    //获取首页广告
    List<Advert> getAdverts(String position,String platform);

    //获取老师列表
    List<Teacher> getTeachers();

    //获取校区和其图片s
    List<ShoolClient> getShools();

    //获取荣誉和其图片s
    List<Honor> getHonors();

    //友情链接
}
