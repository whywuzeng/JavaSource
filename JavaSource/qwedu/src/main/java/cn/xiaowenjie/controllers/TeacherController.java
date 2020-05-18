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
import cn.xiaowenjie.beans.Teacher;
import cn.xiaowenjie.boss.form.TeacherForm;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.response.PageResult;
import cn.xiaowenjie.services.TeacherService;
import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "教师api")
@RequestMapping("/teacher")
@RestController
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService schoolService;

    @PostMapping("/all")
    public ResultBean<PageResult<Teacher>> getAll(@RequestBody PageObject pageObject) {
        return new ResultBean<PageResult<Teacher>>(schoolService.getAll(pageObject.getPageNo(), pageObject.getPageSize()));
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
    public ResultBean<Long> add(@RequestBody @Valid TeacherForm teacherForm) {
        return new ResultBean<Long>(schoolService.add(teacherForm));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(schoolService.delete(id));
    }

    @PostMapping("/update")
    public ResultBean<Long> update(@RequestBody @Valid Teacher advert) {
        return new ResultBean<Long>(schoolService.update(advert));
    }

    @PostMapping("/subscribe")
    public ResultBean<Boolean> subscribe(@RequestBody @Valid Teacher advert) {
        return new ResultBean<Boolean>(schoolService.update(advert));
    }

}
