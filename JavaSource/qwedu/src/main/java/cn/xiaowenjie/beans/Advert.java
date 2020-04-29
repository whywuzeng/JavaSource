package cn.xiaowenjie.beans;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Administrator on 2020/4/14.
 * <p>
 * by author wz
 * 广告实体类
 * <p>
 * cn.xiaowenjie
 */
@Entity(name = "qw_advert")
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Advert extends BaseEntity {
//    id  title    ad_id  position  statusType  status  start_date  end_date  url     platform
    private String title;
    private String adId;    //与广告管理外键的关联
    private String position;
    private int statusType;
    private String status;
    private Date startDate;
    private Date endDate;
    private String url;
    private String platform;
}
