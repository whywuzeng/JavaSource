package cn.xiaowenjie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import cn.xiaowenjie.beans.AdvertManager;
import cn.xiaowenjie.beans.PageObject;
import cn.xiaowenjie.boss.form.AdvertManagerForm;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.services.AdvertManagerService;
import io.swagger.annotations.Api;
import next.framework.page.PageResult;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "广告管理api")
@RequestMapping("/advert/manager")
@RestController
@CrossOrigin
public class AdvertManagerController {

    @Autowired
    private AdvertManagerService advertManagerService;

    @PostMapping("/all")
    public ResultBean<PageResult<AdvertManager>> getAll(@RequestBody PageObject pageObject) {
        return new ResultBean<>(advertManagerService.getAll(pageObject.getPageNo(), pageObject.getPageSize()));
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
    public ResultBean<Long> add(@RequestBody @Valid AdvertManagerForm advertManagerForm) {
        return new ResultBean<Long>(advertManagerService.add(advertManagerForm));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(advertManagerService.delete(id));
    }

    @PostMapping("/update")
    public ResultBean<Long> update(@RequestBody @Valid AdvertManager manager) {
        return new ResultBean<Long>(advertManagerService.update(manager));
    }

}
