package cn.xiaowenjie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import cn.xiaowenjie.beans.Honor;
import cn.xiaowenjie.daos.ContentManagerDao;
import cn.xiaowenjie.daos.HonorDao;
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
public class HonorService {

    @Autowired
    HonorDao dao;

    public Collection<Honor> getAll(int type) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");

        List<Honor> data = dao.findAllById((long) type);

        log.info("getAll end, data size:" + data.size());

        return data;
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param Honor
     * @return
     */
    public long add(Honor Honor) {
        // 参数校验
        notNull(Honor);

//        check(Honor.getObjType() > 0);
//        check(Honor.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add Honor:" + Honor);

//        long userId = UserUtil.getUserId();

        // 校验重复
//        Honor favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, Honor.getObjType(), Honor.getObjId());
        Honor favoriteNew = null;

        // 如果没有记录，就新增
        if (favoriteNew == null) {
            // 设置用户id
//            Honor.setUserId(userId);

            favoriteNew = dao.save(Honor);

            // 修改操作需要打印操作结果
            log.info("add Honor success, id:" + favoriteNew.getId());
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
        Honor Honor = dao.findOne(id);

        // 参数校验
        check(Honor != null, "id.error", id);

        // 判断是否可以删除
        check(canDelete(Honor), "no.permission");

        dao.delete(id);

        // 修改操作需要打印操作结果
        log.info("delete Honor success, id:" + id);

        return true;
    }


    /**
     * 自己创建的或者管理员可以删除数据
     * 判断逻辑变化可能性大，抽取一个函数
     *
     * @param Honor
     * @return
     */
    private boolean canDelete(Honor Honor) {
//        return UserUtil.getUserId() == Honor.getUserId() || UserUtil.isAdmin();
        return true;
    }

}
