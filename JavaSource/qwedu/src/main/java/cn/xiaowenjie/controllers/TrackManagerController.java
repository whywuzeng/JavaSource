package cn.xiaowenjie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import cn.xiaowenjie.beans.PageObject;
import cn.xiaowenjie.beans.Trackmanager;
import cn.xiaowenjie.boss.form.TrackmanagerForm;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.services.TrackManagerService;
import io.swagger.annotations.Api;
import next.framework.page.PageResult;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "线索管理api")
@RequestMapping("/track/manager")
@RestController
@CrossOrigin
public class TrackManagerController {

    @Autowired
    private TrackManagerService trackManagerService;

    @PostMapping("/all")
    public ResultBean<PageResult<Trackmanager>> getAll(@RequestBody PageObject pageObject) {
        return new ResultBean<PageResult<Trackmanager>>(trackManagerService.getAll(pageObject.getPageNo(),pageObject.getPageSize()));
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
    public ResultBean<Long> add(@RequestBody @Valid TrackmanagerForm favorite) {
        return new ResultBean<Long>(trackManagerService.add(favorite));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(trackManagerService.delete(id));
    }

    @PostMapping("/update")
    public ResultBean<Long> update(@RequestBody @Valid Trackmanager advert) {
        return new ResultBean<Long>(trackManagerService.update(advert));
    }
}
