package cn.xiaowenjie.beans;

/**
 * Created by Administrator on 2020/4/14.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.beans
 */

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity(name = "qw_category")
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseEntity{
//      name    parent_id  is_parent    sort
    private String name;
    private Long parent_id;
    private int is_parent; //是否为父节点，0为否，1为是
    private int sort; //排序指数，越小越靠前
}
