package cn.xiaowenjie.boss.form;

import java.util.Date;

import lombok.Data;

/**
 * Created by Administrator on 2020/5/2.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.boss.form
 */
@Data
public class AdvertForm {

//    endDate: "2020-6-9"
//    file: ""
//    position: "PC"
//    startDate: "2020-05-04 - 2020-06-09"
//    status: "1"
//    title: "今天11"
//    url: "http://127.0.0.1:8081/upload/QQ浏览器截图20190308114016.png

    private String title;
    private String adId;    //与广告管理外键的关联
    private String position;
    private int statusType;
    private String status;
    private Date startDate;
    private Date endDate;
    private String url;
    private String platform;

    public String getStatus() {
        if (statusType ==1)
        {
            return "上线";
        }else {
            return "下线";
        }
    }
}
