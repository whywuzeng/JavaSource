package cn.xiaowenjie.beans;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Administrator on 2020/5/16.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.beans
 */
@Entity(name = "qw_friendurl")
@Data
@EqualsAndHashCode(callSuper = true)
public class FriendUrl extends BaseEntity {
    private String friendkeyword;
    private String friendurl;
    private int sort;
}
