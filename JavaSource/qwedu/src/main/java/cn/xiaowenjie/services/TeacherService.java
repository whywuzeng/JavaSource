package cn.xiaowenjie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import cn.xiaowenjie.beans.Teacher;
import cn.xiaowenjie.daos.TeacherDao;
import lombok.extern.slf4j.Slf4j;

import static cn.xiaowenjie.common.utils.CheckUtil.check;
import static cn.xiaowenjie.common.utils.CheckUtil.notNull;

/**
 * 配置业务处理类
 *
 * @author 肖文杰 https://github.com/xwjie/
 */
@Service
@Slf4j
public class TeacherService {

    @Autowired
    TeacherDao dao;

    public Collection<Teacher> getAll(int type) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");

        List<Teacher> data = dao.findAllById((long) type);

        log.info("getAll end, data size:" + data.size());

        return data;
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param Teacher
     * @return
     */
    public long add(Teacher Teacher) {
        // 参数校验
        notNull(Teacher);

//        check(Teacher.getObjType() > 0);
//        check(Teacher.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add Teacher:" + Teacher);

//        long userId = UserUtil.getUserId();

        // 校验重复
//        Teacher favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, Teacher.getObjType(), Teacher.getObjId());
        Teacher favoriteNew = null;

        // 如果没有记录，就新增
        if (favoriteNew == null) {
            // 设置用户id
//            Teacher.setUserId(userId);

            favoriteNew = dao.save(Teacher);

            // 修改操作需要打印操作结果
            log.info("add Teacher success, id:" + favoriteNew.getId());
        }

        return favoriteNew.getId();
    }

    /**
     * 根据id删除配置项
     * <p>
     * 管理员或者自己创建的才可以删除掉
     *
     * @param id
     * @return
     */
    public boolean delete(long id) {
        Teacher Teacher = dao.findOne(id);

        // 参数校验
        check(Teacher != null, "id.error", id);

        // 判断是否可以删除
        check(canDelete(Teacher), "no.permission");

        dao.delete(id);

        // 修改操作需要打印操作结果
        log.info("delete Teacher success, id:" + id);

        return true;
    }


    /**
     * 自己创建的或者管理员可以删除数据
     * 判断逻辑变化可能性大，抽取一个函数
     *
     * @param Teacher
     * @return
     */
    private boolean canDelete(Teacher Teacher) {
//        return UserUtil.getUserId() == Teacher.getUserId() || UserUtil.isAdmin();
        return true;
    }

}
