package cn.xiaowenjie.daos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import cn.xiaowenjie.beans.Contentmanager;
import cn.xiaowenjie.boss.form.ContentmanagerForm;

/**
 * Created by Administrator on 2020/5/3.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.daos
 */
@Mapper
public interface ContentManagerQueryDao {

    List<Contentmanager> selectByQuery(@Param("query") ContentmanagerForm query);
}