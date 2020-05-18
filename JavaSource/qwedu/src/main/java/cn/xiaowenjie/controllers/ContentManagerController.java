package cn.xiaowenjie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import cn.xiaowenjie.beans.Contentmanager;
import cn.xiaowenjie.beans.PageObjectContent;
import cn.xiaowenjie.boss.form.ContentmanagerForm;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.response.PageResult;
import cn.xiaowenjie.services.ContentManagerService;
import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "内容管理api")
@RequestMapping("/content/manager")
@RestController
@CrossOrigin
public class ContentManagerController {

    @Autowired
    private ContentManagerService advertManagerService;

    @PostMapping("/all")
    public ResultBean<PageResult<Contentmanager>> getAll(@RequestBody PageObjectContent pageObject) {
        return new ResultBean<PageResult<Contentmanager>>(advertManagerService.getAll(pageObject));
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
    public ResultBean<Long> add(@RequestBody @Valid ContentmanagerForm advertForm) {
        return new ResultBean<Long>(advertManagerService.add(advertForm));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(advertManagerService.delete(id));
    }

    @PostMapping("/update")
    public ResultBean<Long> update(@RequestBody @Valid Contentmanager advert) {
        return new ResultBean<Long>(advertManagerService.update(advert));
    }

}
