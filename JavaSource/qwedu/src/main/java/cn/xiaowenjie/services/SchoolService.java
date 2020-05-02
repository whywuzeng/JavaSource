package cn.xiaowenjie.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import cn.xiaowenjie.beans.School;
import cn.xiaowenjie.boss.form.SchoolForm;
import cn.xiaowenjie.daos.SchoolDao;
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
public class SchoolService {

    @Autowired
    SchoolDao dao;

    public PageResult<School> getAll(int pageNo, int pageSize) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<School> page = PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));

        List<School> data = (List<School>) dao.findAll();
        log.info("getAll end, data size:" + data.size());

        PageInfo<School> pageInfo = new PageInfo<>(data);

        return PageTransformer.transform(pageInfo);
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param School
     * @return
     */
    public long add(SchoolForm School) {
        // 参数校验
        notNull(School);

//        check(School.getObjType() > 0);
//        check(School.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add School:" + School);

//        long userId = UserUtil.getUserId();
        School manager = new School();
        manager.setId(0);
        manager.setPhoneNum(School.getPhoneNum());
        manager.setSchoolName(School.getSchoolName());
        manager.setSchoolPosition(School.getSchoolPosition());
        manager.setSortNumber(School.getSortNumber());


        // 校验重复
//        School favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, School.getObjType(), School.getObjId());
        School favoriteNew = null;

        // 如果没有记录，就新增
        if (favoriteNew == null) {
            // 设置用户id
//            School.setUserId(userId);

            favoriteNew = dao.save(manager);

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

    public Long update(School manager) {
        check(manager != null, "School.error", manager.toString());
        notEmpty(manager.getSchoolName(),"School position not null or empty",manager.getSchoolName());
        School save = dao.save(manager);
        save.setUpdateTime(new Date());
        return save.getId();
    }
}
