package cn.xiaowenjie.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.xiaowenjie.beans.Schoolpic;
import cn.xiaowenjie.boss.form.SchoolPicForm;
import cn.xiaowenjie.daos.BatisSchoolPicDao;
import cn.xiaowenjie.daos.SchoolPicDao;
import cn.xiaowenjie.helper.PageTransformer;
import cn.xiaowenjie.response.PageResult;
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
public class SchoolPicService {

    @Autowired
    SchoolPicDao dao;

    @Autowired
    BatisSchoolPicDao batisSchoolPicDao;

    public PageResult<Schoolpic> getAll(int schoolId, int pageNo, int pageSize) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<Schoolpic> page = PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        SchoolPicForm picForm = new SchoolPicForm();
        picForm.setSchoolId((long) schoolId);

        List<Schoolpic> data = (List<Schoolpic>) batisSchoolPicDao.selectByQuery(picForm);
        log.info("getAll end, data size:" + data.size());

        PageInfo<Schoolpic> pageInfo = new PageInfo<>(data);

        return PageTransformer.transform(pageInfo);
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @return
     */
    public long add(SchoolPicForm schoolPicForm) {
        // 参数校验
        notNull(schoolPicForm);

//        check(School.getObjType() > 0);
//        check(School.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add schoolPicForm:" + schoolPicForm);

//        long userId = UserUtil.getUserId();
        Schoolpic manager = new Schoolpic();
        manager.setId(0);
        manager.setPicUrl(schoolPicForm.getPicUrl());
        manager.setSchoolId(schoolPicForm.getSchoolId());
        manager.setCreateTime(new Date());

        // 校验重复
//        School favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, School.getObjType(), School.getObjId());
        Schoolpic favoriteNew = null;

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
        Schoolpic School = dao.findOne(id);

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
    private boolean canDelete(Schoolpic School) {
//        return UserUtil.getUserId() == School.getUserId() || UserUtil.isAdmin();
        return true;
    }

    public Long update(Schoolpic manager) {
        check(manager != null, "SchoolPic.error", manager.toString());
        check(manager.getSchoolId()>0,"SchoolPic getSchoolId not null or empty",manager.getSchoolId());
        Schoolpic save = dao.save(manager);
        save.setUpdateTime(new Date());
        return save.getId();
    }

    public List<Long> addList(List<SchoolPicForm> schoolpics) {
        List<Schoolpic> list = new ArrayList<>();
        // 参数校验
        for (SchoolPicForm schoolpic : schoolpics) {
            Schoolpic schoolpic1 = new Schoolpic();
            schoolpic1.setId(0);
            schoolpic1.setPicUrl(schoolpic.getPicUrl());
            schoolpic1.setCreateTime(new Date());
            schoolpic1.setPicUrl(schoolpic.getPicUrl());
            list.add(schoolpic1);
        }

        List<Long> longs =new ArrayList<>();
        Iterable<Schoolpic> save = dao.save(list);
        for (Schoolpic schoolpic : save) {
            longs.add(schoolpic.getId());
        }

        return longs;
    }
}
