package cn.xiaowenjie.boss.form;

import java.math.BigInteger;

import lombok.Data;

/**
 * Created by Administrator on 2020/5/2.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.boss.form
 */
@Data
public class SchoolForm {
    private String schoolName;
    private String schoolPosition;
    private BigInteger phoneNum;
    private int sortNumber;
}
