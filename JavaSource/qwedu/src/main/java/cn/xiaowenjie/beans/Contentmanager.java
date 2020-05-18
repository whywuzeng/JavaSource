package cn.xiaowenjie.beans;

/**
 * Created by Administrator on 2020/4/14.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.beans
 */

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity(name = "qw_contentmanager")
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Contentmanager extends BaseEntity{
//      categoryid  contentname  contentdescription  tag     isrecommend  weight  date    status  author
    private Long categoryid;
    private String categoryname;
    private String grade;
    private String contentname;
    @Column(nullable = false, columnDefinition = "varchar(1000)")
    private String areatext;
    private String contentdescription;
    private String tag;
    private int isrecommend;
    private int weight;
    private Date date;
    private String picurl;
    private String status;
    private String author;
}
