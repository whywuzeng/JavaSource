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

import cn.xiaowenjie.beans.PageObject;
import cn.xiaowenjie.beans.Schoolpic;
import cn.xiaowenjie.boss.form.SchoolPicForm;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.response.PageResult;
import cn.xiaowenjie.services.SchoolPicService;
import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "校区api")
@RequestMapping("/schoolPic")
@RestController
@CrossOrigin
public class SchoolPicController {

    @Autowired
    private SchoolPicService schoolPicService;

    @PostMapping("/all")
    public ResultBean<PageResult<Schoolpic>> getAll(@RequestBody PageObject pageObject) {
        return new ResultBean<PageResult<Schoolpic>>(schoolPicService.getAll(pageObject.getSchoolId(),pageObject.getPageNo(), pageObject.getPageSize()));
    }

    /**
     * 新增配置
     *
     * FIXME 同时支持json格式和表单格式
     *
     * @return
     */
    @PostMapping("/addList")
    public ResultBean<List<Long>> addList(@RequestBody @Valid List<SchoolPicForm> schoolpics) {
        return new ResultBean<List<Long>>(schoolPicService.addList(schoolpics));
    }

    @PostMapping("/add")
    public ResultBean<Long> add(@RequestBody @Valid SchoolPicForm schoolpic) {
        return new ResultBean<Long>(schoolPicService.add(schoolpic));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(schoolPicService.delete(id));
    }

    @PostMapping("/update")
    public ResultBean<Long> update(@RequestBody @Valid Schoolpic school) {
        return new ResultBean<Long>(schoolPicService.update(school));
    }

}
