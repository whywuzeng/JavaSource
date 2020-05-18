package cn.xiaowenjie.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.xiaowenjie.beans.Honor;
import cn.xiaowenjie.boss.form.HonorForm;
import cn.xiaowenjie.daos.HonorDao;
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
public class HonorService {

    @Autowired
    HonorDao dao;
    //Honor

    public PageResult<Honor> getAll(int schoolId, int pageNo, int pageSize) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<Honor> page = PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));

        List<Honor> data = (List<Honor>) dao.findAll();
        log.info("getAll end, data size:" + data.size());

        PageInfo<Honor> pageInfo = new PageInfo<>(data);

        return PageTransformer.transform(pageInfo);
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param Honor
     * @return
     */
    public long add(HonorForm honorForm) {
        // 参数校验
        notNull(honorForm);

//        check(School.getObjType() > 0);
//        check(School.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add honorForm:" + honorForm);

//        long userId = UserUtil.getUserId();
        Honor manager = new Honor();
        manager.setId(0);
        manager.setPic(honorForm.getPicUrl());
        manager.setCreateTime(new Date());

        // 校验重复
//        School favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, School.getObjType(), School.getObjId());
        Honor favoriteNew = null;

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


    public Long update(Honor manager) {
        check(manager != null, "SchoolPic.error", manager.toString());
        Honor save = dao.save(manager);
        save.setUpdateTime(new Date());
        return save.getId();
    }

    public List<Long> addList(List<HonorForm> honorForms) {
        List<Honor> list = new ArrayList<>();
        // 参数校验
        for (HonorForm schoolpic : honorForms) {
            Honor schoolpic1 = new Honor();
            schoolpic1.setId(0);
            schoolpic1.setCreateTime(new Date());
            schoolpic1.setPic(schoolpic.getPicUrl());
            list.add(schoolpic1);
        }

        List<Long> longs =new ArrayList<>();
        Iterable<Honor> save = dao.save(list);
        for (Honor schoolpic : save) {
            longs.add(schoolpic.getId());
        }

        return longs;
    }

}
