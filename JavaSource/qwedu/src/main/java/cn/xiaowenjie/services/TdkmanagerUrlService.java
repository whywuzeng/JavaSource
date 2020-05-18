package cn.xiaowenjie.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.xiaowenjie.beans.TDKmanager;
import cn.xiaowenjie.boss.form.TDKmanagerForm;
import cn.xiaowenjie.daos.TdkmanagerDao;
import cn.xiaowenjie.helper.PageTransformer;
import cn.xiaowenjie.response.PageResult;
import lombok.extern.slf4j.Slf4j;

import static cn.xiaowenjie.common.utils.CheckUtil.check;
import static cn.xiaowenjie.common.utils.CheckUtil.notNull;

;

/**
 * 配置业务处理类
 *
 * @author 肖文杰 https://github.com/xwjie/
 */
@Service
@Slf4j
public class TdkmanagerUrlService {

    // 支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg");

    @Autowired
    TdkmanagerDao dao;

    public PageResult<TDKmanager> getAll(TDKmanagerForm pageObject) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<TDKmanager> page = PageHelper.startPage(pageObject.getPageNo(), pageObject.getPageSize());
        List<TDKmanager> data = null;
        data = (List<TDKmanager>) dao.findFrindUrlByForm(pageObject.getPosition(),pageObject.getTitle(),pageObject.getKeyword());
        log.info("getAll end, data size:" + data.size());
        PageInfo<TDKmanager> pageInfo = new PageInfo<>(data);
        return PageTransformer.transform(pageInfo);
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param AdvertManager
     * @return
     */
    public long add(TDKmanagerForm AdvertManager) {
        // 参数校验
        notNull(AdvertManager);

//        check(AdvertManager.getObjType() > 0);
//        check(AdvertManager.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add AdvertManager:" + AdvertManager);

//        long userId = UserUtil.getUserId();

        // 校验重复
//        AdvertManager favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, AdvertManager.getObjType(), AdvertManager.getObjId());
        TDKmanager favoriteNew = null;
        TDKmanager advert = new TDKmanager();
        advert.setDescription(AdvertManager.getDescription());
        advert.setKeyword(AdvertManager.getKeyword());
        advert.setPosition(AdvertManager.getPosition());
        advert.setTitle(AdvertManager.getTitle());
        advert.setCreateTime(new Date());
        // 如果没有记录，就新增
        if (favoriteNew == null) {
            // 设置用户id
//            AdvertManager.setUserId(userId);

            favoriteNew = dao.save(advert);

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
        TDKmanager AdvertManager = dao.findOne(id);

        // 参数校验
        check(AdvertManager != null, "id.error", id);

        // 判断是否可以删除
        check(canDelete(AdvertManager), "no.permission");

        dao.delete(id);

        // 修改操作需要打印操作结果
        log.info("delete AdvertManager success, id:" + id);

        return true;
    }

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    /**
     * 自己创建的或者管理员可以删除数据
     * 判断逻辑变化可能性大，抽取一个函数
     *
     * @param AdvertManager
     * @return
     */
    private boolean canDelete(TDKmanager AdvertManager) {
//        return UserUtil.getUserId() == AdvertManager.getUserId() || UserUtil.isAdmin();
        return true;
    }

    public Long update(TDKmanager advert) {

        check(advert != null, "advertManager.error", advert.toString());
        TDKmanager save = dao.save(advert);
        save.setUpdateTime(new Date());
        return save.getId();
    }
}
