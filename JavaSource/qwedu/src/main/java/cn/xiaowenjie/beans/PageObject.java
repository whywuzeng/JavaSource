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
public class PageObject {
    private int pageNo;
    private int pageSize;

    private int schoolId;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
