package cn.xiaowenjie.response;

import java.util.List;

/**
 * Created by Administrator on 2020/5/3.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.response
 */

public class PageResult<E> {
    private int pageNo;
    private int pageSize;
    private int totalPage;
    private int totalSize;
    private List<E> result;
    public static final String __PARANAMER_DATA = "<init> int,int pageNo,pageSize \nsetPageNo int pageNo \nsetPageSize int pageSize \nsetResult java.util.List result \nsetTotalPage int totalPage \nsetTotalSize int totalSize \n";

    public PageResult() {
        this(1, 10);
    }

    public PageResult(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public List<E> getResult() {
        return this.result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String toString() {
        return "Page {pageNo:" + this.pageNo + ", pageSize:" + this.pageSize + ", totalSize:" + this.totalSize + ", totalPage:" + this.totalPage + '}';
    }
}
