package cn.xiaowenjie.boss.form;

import lombok.Data;

/**
 * Created by Administrator on 2020/5/16.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.boss.form
 */
@Data
public class FriendUrlForm {
    private String friendkeyword;
    private String friendurl;
    private int sort;
    private int pageNo;
    private int pageSize;
}
