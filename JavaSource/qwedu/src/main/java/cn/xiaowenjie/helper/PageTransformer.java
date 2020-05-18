package cn.xiaowenjie.helper;

import com.github.pagehelper.PageInfo;

import cn.xiaowenjie.response.PageResult;


/**
 * Description: 分页对象转换
 */
public class PageTransformer {

    public static <T> PageResult<T> transform(PageInfo<T> page) {
        PageResult<T> result = new PageResult<>(page.getPageNum(), page.getPageSize());
        result.setTotalPage(page.getPages());
        result.setTotalSize((int) page.getTotal());
        result.setResult(page.getList());
        return result;
    }

    public static <T> PageResult<T> emptyPage(int pageNo, int pageSize) {
        return new PageResult<>(pageNo, pageSize);
    }
}
