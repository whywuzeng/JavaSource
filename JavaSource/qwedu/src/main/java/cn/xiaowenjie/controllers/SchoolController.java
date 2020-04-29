package cn.xiaowenjie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import javax.validation.Valid;

import cn.xiaowenjie.beans.School;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.services.SchoolService;
import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "校区api")
@RequestMapping("/school")
@RestController
@CrossOrigin
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/all")
    public ResultBean<Collection<School>> getAll(@RequestParam int type) {
        return new ResultBean<Collection<School>>(schoolService.getAll(type));
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
    public ResultBean<Long> add(@RequestBody @Valid School favorite) {
        return new ResultBean<Long>(schoolService.add(favorite));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(schoolService.delete(id));
    }

}
