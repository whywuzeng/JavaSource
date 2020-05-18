package cn.xiaowenjie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import cn.xiaowenjie.beans.Honor;
import cn.xiaowenjie.beans.PageObject;
import cn.xiaowenjie.boss.form.HonorForm;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.response.PageResult;
import cn.xiaowenjie.services.HonorService;
import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "荣誉api")
@RequestMapping("/honor")
@RestController
@CrossOrigin
public class HonorController {

    @Autowired
    private HonorService honorService;


    @PostMapping("/all")
    public ResultBean<PageResult<Honor>> getAll(@RequestBody PageObject pageObject) {
        return new ResultBean<PageResult<Honor>>(honorService.getAll(pageObject.getSchoolId(),pageObject.getPageNo(), pageObject.getPageSize()));
    }

    @PostMapping("/addList")
    public ResultBean<List<Long>> addList(@RequestBody @Valid List<HonorForm> schoolpics) {
        return new ResultBean<List<Long>>(honorService.addList(schoolpics));
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
    public ResultBean<Long> add(@RequestBody @Valid HonorForm schoolpic) {
        return new ResultBean<Long>(honorService.add(schoolpic));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(honorService.delete(id));
    }

    @PostMapping("/update")
    public ResultBean<Long> update(@RequestBody @Valid Honor school) {
        return new ResultBean<Long>(honorService.update(school));
    }

}
