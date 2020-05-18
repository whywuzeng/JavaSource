package cn.xiaowenjie.beans;

import lombok.Data;

/**
 * Created by Administrator on 2020/4/29.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.beans
 */
@Data
public class PageObjectContent {
    private int pageNo;
    private int pageSize;
    private String categoryname;
    private String grade;
}
