package cn.xiaowenjie.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import cn.xiaowenjie.beans.AdvertManager;
import cn.xiaowenjie.boss.form.AdvertManagerForm;
import cn.xiaowenjie.daos.AdvertManagerDao;
import cn.xiaowenjie.helper.PageTransformer;
import lombok.extern.slf4j.Slf4j;
import next.framework.page.PageResult;

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
public class AdvertManagerService {

    @Autowired
    AdvertManagerDao dao;

    public PageResult<AdvertManager> getAll(int pageNo, int pageSize) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<AdvertManager> page = PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));

        List<AdvertManager> data = (List<AdvertManager>) dao.findAll();
        log.info("getAll end, data size:" + data.size());

        PageInfo<AdvertManager> pageInfo = new PageInfo<>(data);

        return PageTransformer.transform(pageInfo);
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param form
     * @return
     */
    public long add(AdvertManagerForm form) {
        // 参数校验
        notNull(form);

//        check(AdvertManager.getObjType() > 0);
//        check(AdvertManager.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add AdvertManager:" + form);
        AdvertManager manager = new AdvertManager();
        manager.setId(0);
        manager.setPlatform(form.getPlatform());
        manager.setPosition(form.getPosition());
//        long userId = UserUtil.getUserId();

        // 校验重复
//        AdvertManager favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, AdvertManager.getObjType(), AdvertManager.getObjId());
        AdvertManager favoriteNew = null;

        // 如果没有记录，就新增
        if (favoriteNew == null) {
            // 设置用户id
//            AdvertManager.setUserId(userId);

            favoriteNew = dao.save(manager);

            // 修改操作需要打印操作结果
            log.info("add AdvertManager success, id:" + favoriteNew.getId());
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
        AdvertManager AdvertManager = dao.findOne(id);

        // 参数校验
        check(AdvertManager != null, "id.error", id);

        // 判断是否可以删除
        check(canDelete(AdvertManager), "no.permission");

        dao.delete(id);

        // 修改操作需要打印操作结果
        log.info("delete AdvertManager success, id:" + id);

        return true;
    }


    /**
     * 自己创建的或者管理员可以删除数据
     * 判断逻辑变化可能性大，抽取一个函数
     *
     * @param AdvertManager
     * @return
     */
    private boolean canDelete(AdvertManager AdvertManager) {
//        return UserUtil.getUserId() == AdvertManager.getUserId() || UserUtil.isAdmin();
        return true;
    }

    public Long update(AdvertManager manager) {
        check(manager != null, "advertManager.error", manager.toString());
        notEmpty(manager.getPlatform(),"advertManagerForm paltform not null or empty",manager.getPlatform());
        notEmpty(manager.getPosition(),"advertManagerForm position not null or empty",manager.getPosition());
        AdvertManager save = dao.save(manager);
        save.setUpdateTime(new Date());
        return save.getId();
    }
}
