package cn.xiaowenjie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import cn.xiaowenjie.beans.TDKmanager;
import cn.xiaowenjie.boss.form.TDKmanagerForm;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.response.PageResult;
import cn.xiaowenjie.services.TdkmanagerUrlService;
import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "TDK管理api")
@RequestMapping("/tdkmanager")
@RestController
@CrossOrigin
public class TDKmanagerController {

    @Autowired
    private TdkmanagerUrlService friendUrlService;

    @PostMapping("/all")
    public ResultBean<PageResult<TDKmanager>> getAll(@RequestBody TDKmanagerForm pageObject) {
        return new ResultBean<PageResult<TDKmanager>>(friendUrlService.getAll(pageObject));
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
    public ResultBean<Long> add(@RequestBody @Valid TDKmanagerForm advertForm) {
        return new ResultBean<Long>(friendUrlService.add(advertForm));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(friendUrlService.delete(id));
    }

    @PostMapping("/update")
    public ResultBean<Long> update(@RequestBody @Valid TDKmanager advert) {
        return new ResultBean<Long>(friendUrlService.update(advert));
    }

}
