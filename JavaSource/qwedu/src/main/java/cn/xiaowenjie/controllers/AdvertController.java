package cn.xiaowenjie.controllers;

import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import cn.xiaowenjie.beans.Advert;
import cn.xiaowenjie.common.beans.ResultBean;
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
    public ResultBean<PageInfo<Advert>> getAll( Integer pageNo,Integer pageSize) {
        return new ResultBean<PageInfo<Advert>>(advertManagerService.getAll(pageNo,pageSize));
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
    public ResultBean<Long> add(@RequestBody @Valid Advert favorite) {
        return new ResultBean<Long>(advertManagerService.add(favorite));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(advertManagerService.delete(id));
    }

}
