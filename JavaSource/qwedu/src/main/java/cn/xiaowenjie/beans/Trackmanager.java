package cn.xiaowenjie.beans;

/**
 * Created by Administrator on 2020/4/14.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.beans
 */

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity(name = "qw_trackmanager")
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Trackmanager extends BaseEntity{
//      parentName  grade   phoneNum
    private String parentName;
    private String grade;
    private BigInteger phoneNum;
}
