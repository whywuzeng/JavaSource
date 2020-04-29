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

import cn.xiaowenjie.beans.Category;
import cn.xiaowenjie.common.beans.ResultBean;
import cn.xiaowenjie.services.CategoryService;
import io.swagger.annotations.Api;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.controllers
 */
@Api(description = "分类api")
@RequestMapping("/category")
@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService advertManagerService;

    @GetMapping("/all")
    public ResultBean<Collection<Category>> getAll(@RequestParam int type) {
        return new ResultBean<Collection<Category>>(advertManagerService.getAll(type));
    }

    /**
     * 新增配置
     *
     * FIXME 同时支持json格式和表单格式
     *
     * @param category
     * @return
     */
    @PostMapping("/add")
    public ResultBean<Long> add(@RequestBody @Valid Category category) {
        return new ResultBean<Long>(advertManagerService.add(category));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(@RequestParam long id) {
        return new ResultBean<Boolean>(advertManagerService.delete(id));
    }

}
