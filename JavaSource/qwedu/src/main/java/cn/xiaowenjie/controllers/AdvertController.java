package cn.xiaowenjie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.validation.Valid;

import cn.xiaowenjie.beans.Advert;
import cn.xiaowenjie.beans.PageObject;
import cn.xiaowenjie.boss.form.AdvertForm;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.response.PageResult;
import cn.xiaowenjie.services.AdvertService;
import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "广告api")
@RequestMapping("/advert")
@RestController
@CrossOrigin
public class AdvertController {

    @Autowired
    private AdvertService advertManagerService;

    @PostMapping("/all")
    public ResultBean<PageResult<Advert>> getAll(@RequestBody PageObject pageObject) {
        return new ResultBean<PageResult<Advert>>(advertManagerService.getAll(pageObject.getPageNo(), pageObject.getPageSize(),pageObject.getSchoolId()));
    }

    /**
     * 新增配置
     *
     * FIXME 同时支持json格式和表单格式
     *
     * @param favorite
     * @return
     */
    @PostMapping("/add")
    public ResultBean<Long> add(@RequestBody @Valid AdvertForm advertForm) {
        return new ResultBean<Long>(advertManagerService.add(advertForm));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(advertManagerService.delete(id));
    }

    /**
     * 上传图片功能
     * @param file
     * @return
     */
    @PostMapping("image")
    public ResultBean<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResultBean<String>(this.advertManagerService.upload(file));
    }

    @PostMapping("/update")
    public ResultBean<Long> update(@RequestBody @Valid Advert advert) {
        return new ResultBean<Long>(advertManagerService.update(advert));
    }

}
