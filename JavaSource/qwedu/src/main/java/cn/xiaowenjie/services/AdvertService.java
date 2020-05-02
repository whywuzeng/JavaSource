package cn.xiaowenjie.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import cn.xiaowenjie.beans.Advert;
import cn.xiaowenjie.boss.form.AdvertForm;
import cn.xiaowenjie.daos.AdvertDao;
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
public class AdvertService {

    // 支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg");

    @Autowired
    AdvertDao dao;

    public PageResult<Advert> getAll(int pageNo, int pageSize) {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        Page<Advert> page = PageHelper.startPage(pageNo, pageSize);

        List<Advert> data = (List<Advert>) dao.findAll();
        log.info("getAll end, data size:" + data.size());
        PageInfo<Advert> pageInfo = new PageInfo<>(data);
        return PageTransformer.transform(pageInfo);
    }

    /**
     * 增加配置，需要管理员权限
     *
     * @param AdvertManager
     * @return
     */
    public long add(AdvertForm AdvertManager) {
        // 参数校验
        notNull(AdvertManager);

//        check(AdvertManager.getObjType() > 0);
//        check(AdvertManager.getObjId() > 0L);

        // 校验通过后打印重要的日志
        log.info("add AdvertManager:" + AdvertManager);

//        long userId = UserUtil.getUserId();

        // 校验重复
//        AdvertManager favoriteNew = dao.findByUserIdAndObjTypeAndObjId(userId, AdvertManager.getObjType(), AdvertManager.getObjId());
        Advert favoriteNew = null;
        Advert advert = new Advert();
        advert.setAdId("5");
        advert.setTitle(AdvertManager.getTitle());
        advert.setPosition(AdvertManager.getPosition());
        advert.setStatus(AdvertManager.getStatus());
        advert.setStatusType(AdvertManager.getStatusType());
        advert.setStartDate(AdvertManager.getStartDate());
        advert.setEndDate(AdvertManager.getEndDate());
        advert.setUrl(AdvertManager.getUrl());
        advert.setPlatform(AdvertManager.getPlatform());
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
        Advert AdvertManager = dao.findOne(id);

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
    private boolean canDelete(Advert AdvertManager) {
//        return UserUtil.getUserId() == AdvertManager.getUserId() || UserUtil.isAdmin();
        return true;
    }

    public String upload(MultipartFile file) throws IOException {
        // 1、图片信息校验
        // 1)校验文件类型
        String type = file.getContentType();
        if (!suffixes.contains(type)) {
            log.info("上传失败，文件类型不匹配：{}", type);
            return null;
        }
        // 2)校验图片内容
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (image == null) {
            log.info("上传失败，文件内容不符合要求");
            return null;
        }

        String property = "D:/pics/";
        File dir = new File(property);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 2.2、保存图片
        file.transferTo(new File(dir, file.getOriginalFilename()));

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String projectUrl = getRequestPrefix(request);

        // 2.3、拼接图片地址  去掉两个点
//        staticAccessPath.lastIndexOf()  	int indexOf(int ch)
        if (!(staticAccessPath.indexOf('*')<0))
        {
            staticAccessPath= staticAccessPath.substring(0,staticAccessPath.indexOf('*'));
        }
        String url = projectUrl+staticAccessPath + file.getOriginalFilename();
        notEmpty(url,"pic url not empty",file.getOriginalFilename());
        return url;
    }

    /**
     * 获取url请求前缀
     * @explain http://localhost:8080/test
     * @param request request对象
     * @return
     */
    public static String getRequestPrefix (HttpServletRequest request) {
        // 网络协议
        String networkProtocol = request.getScheme();
        // 网络ip
        String ip = request.getServerName();
        // 端口号
        int port = request.getServerPort();
        // 项目发布名称
        String webApp = request.getContextPath();
        String urlPrefix = networkProtocol + "://" + ip + ":" + port + webApp;
        return urlPrefix;
    }

    public Long update(Advert advert) {

        check(advert != null, "advertManager.error", advert.toString());
        notEmpty(advert.getPlatform(),"advertManagerForm paltform not null or empty",advert.getPlatform());
        notEmpty(advert.getPosition(),"advertManagerForm position not null or empty",advert.getPosition());
        advert.setAdId("5");
        Advert save = dao.save(advert);
        save.setUpdateTime(new Date());
        return save.getId();
    }
}
