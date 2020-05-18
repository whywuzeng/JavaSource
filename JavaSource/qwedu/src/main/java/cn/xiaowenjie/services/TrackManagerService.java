package cn.xiaowenjie.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import cn.xiaowenjie.beans.Trackmanager;
import cn.xiaowenjie.boss.form.TrackmanagerForm;
import cn.xiaowenjie.daos.TrackManagerDao;
import cn.xiaowenjie.helper.PageTransformer;
import cn.xiaowenjie.response.PageResult;
import lombok.extern.slf4j.Slf4j;

import static cn.xiaowenjie.common.utils.CheckUtil.check;
import static cn.xiaowenjie.common.utils.CheckUtil.notEmpty;
import static cn.xiaowenjie.common.utils.CheckUtil.notNull;

/**
 * 配置业务处理类
 *
 * @author 肖文杰 https://github.com/xwjie/
 */
@Service
@Slf4j
public class TrackManagerService {

    @Autowired
    TrackManagerDao dao;

    public PageResult<Trackmanager> getAll(int pageNo, int pageSize) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<Trackmanager> page = PageHelper.startPage(pageNo, pageSize);

        List<Trackmanager> data = (List<Trackmanager>) dao.findAll();
        log.info("getAll end, data size:" + data.size());
        PageInfo<Trackmanager> pageInfo = new PageInfo<>(data);
        return PageTransformer.transform(pageInfo);
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param Trackmanager
     * @return
     */
    public long add(TrackmanagerForm Trackmanager) {
        // 参数校验
        notNull(Trackmanager);

//        check(Trackmanager.getObjType() > 0);
//        check(Trackmanager.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add Trackmanager:" + Trackmanager);

//        long userId = UserUtil.getUserId();
        Trackmanager trackmanager = new Trackmanager();
        trackmanager.setId(0);
        trackmanager.setParentName(Trackmanager.getParentName());
        trackmanager.setPhoneNum(Trackmanager.getPhoneNum());
        trackmanager.setGrade(Trackmanager.getGrade());
        trackmanager.setCreateTime(new Date());


        // 校验重复
//        Trackmanager favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, Trackmanager.getObjType(), Trackmanager.getObjId());
        Trackmanager favoriteNew = null;

        // 如果没有记录，就新增
        if (favoriteNew == null) {
            // 设置用户id
//            Trackmanager.setUserId(userId);

            favoriteNew = dao.save(trackmanager);

            // 修改操作需要打印操作结果
            log.info("add Trackmanager success, id:" + favoriteNew.getId());
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
        Trackmanager Trackmanager = dao.findOne(id);

        // 参数校验
        check(Trackmanager != null, "id.error", id);

        // 判断是否可以删除
        check(canDelete(Trackmanager), "no.permission");

        dao.delete(id);

        // 修改操作需要打印操作结果
        log.info("delete Trackmanager success, id:" + id);

        return true;
    }


    /**
     * 自己创建的或者管理员可以删除数据
     * 判断逻辑变化可能性大，抽取一个函数
     *
     * @param Trackmanager
     * @return
     */
    private boolean canDelete(Trackmanager Trackmanager) {
//        return UserUtil.getUserId() == Trackmanager.getUserId() || UserUtil.isAdmin();
        return true;
    }

    public Long update(Trackmanager manager) {
        check(manager != null, "advertManager.error", manager.toString());
        notEmpty(manager.getGrade(),"Trackmanager Grade not null or empty",manager.getGrade());
        notEmpty(manager.getParentName(),"Trackmanager ParentName not null or empty",manager.getParentName());
        Trackmanager save = dao.save(manager);
        save.setUpdateTime(new Date());
        return save.getId();
    }
}
