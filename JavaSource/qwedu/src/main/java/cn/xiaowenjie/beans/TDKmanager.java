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
@Entity(name = "qw_tdkmanager")
@Data
@EqualsAndHashCode(callSuper = true)
public class TDKmanager extends BaseEntity{
    private String position;
    private String title;
    private String description;
    private String keyword;
}
