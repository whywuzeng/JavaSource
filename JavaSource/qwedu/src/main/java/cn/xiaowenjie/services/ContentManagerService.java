package cn.xiaowenjie.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import cn.xiaowenjie.beans.Contentmanager;
import cn.xiaowenjie.beans.PageObjectContent;
import cn.xiaowenjie.boss.form.ContentmanagerForm;
import cn.xiaowenjie.daos.ContentManagerDao;
import cn.xiaowenjie.daos.ContentManagerQueryDao;
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
public class ContentManagerService {

    @Autowired
    ContentManagerDao dao;

    public PageResult<Contentmanager> getAll(int pageNo, int pageSize) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<Contentmanager> page = PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));

        List<Contentmanager> data = (List<Contentmanager>) dao.findAll();
        log.info("getAll end, data size:" + data.size());

        PageInfo<Contentmanager> pageInfo = new PageInfo<>(data);

        return PageTransformer.transform(pageInfo);
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param Contentmanager
     * @return
     */
    public long add(ContentmanagerForm form) {
        // 参数校验
        notNull(form);

        // 校验通过后打印重要的日志
        log.info("add Contentmanager:" + form);
        Contentmanager manager = new Contentmanager();
        manager.setId(0);
        manager.setCategoryname(form.getCategoryname());
        manager.setContentname(form.getContentname());
        manager.setContentdescription(form.getContentdescription());
        manager.setTag(form.getTag());
        manager.setIsrecommend(form.getIsrecommend());
        manager.setAreatext(form.getAreatext());
        manager.setPicurl(form.getPicurl());
        manager.setWeight(form.getWeight());
        manager.setDate(new Date());
        manager.setStatus(form.getStatus());
        manager.setAuthor(form.getAuthor());
        manager.setGrade(form.getGrade());

        Contentmanager favoriteNew = null;

        // 如果没有记录，就新增
        if (favoriteNew == null) {
            // 设置用户id
            favoriteNew = dao.save(manager);
            // 修改操作需要打印操作结果
            log.info("add Contentmanager success, id:" + favoriteNew.getId());
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
        Contentmanager Contentmanager = dao.findOne(id);

        // 参数校验
        check(Contentmanager != null, "id.error", id);

        // 判断是否可以删除
        check(canDelete(Contentmanager), "no.permission");

        dao.delete(id);

        // 修改操作需要打印操作结果
        log.info("delete Contentmanager success, id:" + id);

        return true;
    }


    /**
     * 自己创建的或者管理员可以删除数据
     * 判断逻辑变化可能性大，抽取一个函数
     *
     * @param Contentmanager
     * @return
     */
    private boolean canDelete(Contentmanager Contentmanager) {
//        return UserUtil.getUserId() == Contentmanager.getUserId() || UserUtil.isAdmin();
        return true;
    }

    public Long update(Contentmanager manager) {
        check(manager != null, "advertManager.error", manager.toString());
        notEmpty(manager.getCategoryname(),"Contentmanager getCategoryname not null or empty",manager.getCategoryname());
        notEmpty(manager.getContentname(),"Contentmanager getContentname not null or empty",manager.getContentname());
        Contentmanager save = dao.save(manager);
        save.setUpdateTime(new Date());
        return save.getId();
    }

    @Autowired
    private ContentManagerQueryDao contentManagerQueryDao;

    public PageResult<Contentmanager> getAll(PageObjectContent pageObject) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<Contentmanager> page = PageHelper.startPage(Integer.valueOf(pageObject.getPageNo()), Integer.valueOf(pageObject.getPageSize()));
        ContentmanagerForm contentmanagerForm = new ContentmanagerForm();
        contentmanagerForm.setCategoryname(pageObject.getCategoryname());
        contentmanagerForm.setGrade(pageObject.getGrade());

        List<Contentmanager> data = (List<Contentmanager>) contentManagerQueryDao.selectByQuery(contentmanagerForm);
        log.info("getAll end, data size:" + data.size());

        PageInfo<Contentmanager> pageInfo = new PageInfo<>(data);

        return PageTransformer.transform(pageInfo);
    }
}
