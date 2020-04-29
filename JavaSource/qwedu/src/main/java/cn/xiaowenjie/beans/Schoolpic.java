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
@Entity(name = "qw_schoolpic")
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Schoolpic extends BaseEntity{
    //     schoolId  picUrl
    private Long schoolId;
    private String picUrl;
}
