package cn.xiaowenjie.boss.form;

import lombok.Data;

/**
 * Created by Administrator on 2020/5/8.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.boss.form
 */
@Data
public class ContentmanagerForm
{
    private Long categoryid;
    private String categoryname;
    private String contentname;
    private String grade;
    private String contentdescription;
    private String tag;
    private String areatext;
    private int isrecommend;
    private int weight;
    private String picurl;
    private String status;
    private String author;
}
