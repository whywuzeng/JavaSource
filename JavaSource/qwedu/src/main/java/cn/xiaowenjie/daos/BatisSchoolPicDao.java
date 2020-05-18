package cn.xiaowenjie.daos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import cn.xiaowenjie.beans.Schoolpic;
import cn.xiaowenjie.boss.form.SchoolPicForm;

/**
 * Created by Administrator on 2020/5/3.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.daos
 */
@Mapper
public interface BatisSchoolPicDao{

    List<Schoolpic> selectByQuery(@Param("query") SchoolPicForm query);
}