package cn.xiaowenjie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import cn.xiaowenjie.beans.School;
import cn.xiaowenjie.daos.HonorDao;
import cn.xiaowenjie.daos.SchoolDao;
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
public class SchoolService {

    @Autowired
    SchoolDao dao;

    public Collection<School> getAll(int type) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");

        List<School> data = dao.findAllById((long) type);

        log.info("getAll end, data size:" + data.size());

        return data;
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param School
     * @return
     */
    public long add(School School) {
        // 参数校验
        notNull(School);

//        check(School.getObjType() > 0);
//        check(School.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add School:" + School);

//        long userId = UserUtil.getUserId();

        // 校验重复
//        School favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, School.getObjType(), School.getObjId());
        School favoriteNew = null;

        // 如果没有记录，就新增
        if (favoriteNew == null) {
            // 设置用户id
//            School.setUserId(userId);

            favoriteNew = dao.save(School);

            // 修改操作需要打印操作结果
            log.info("add School success, id:" + favoriteNew.getId());
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
        School School = dao.findOne(id);

        // 参数校验
        check(School != null, "id.error", id);

        // 判断是否可以删除
        check(canDelete(School), "no.permission");

        dao.delete(id);

        // 修改操作需要打印操作结果
        log.info("delete School success, id:" + id);

        return true;
    }


    /**
     * 自己创建的或者管理员可以删除数据
     * 判断逻辑变化可能性大，抽取一个函数
     *
     * @param School
     * @return
     */
    private boolean canDelete(School School) {
//        return UserUtil.getUserId() == School.getUserId() || UserUtil.isAdmin();
        return true;
    }

}
