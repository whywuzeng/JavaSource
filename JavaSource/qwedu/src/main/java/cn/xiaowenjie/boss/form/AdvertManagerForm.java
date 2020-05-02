package cn.xiaowenjie.boss.form;

/**
 * Created by Administrator on 2020/4/29.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie.boss.form
 */

public class AdvertManagerForm {

    private String platform;
    private String position;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "AdvertManagerForm{" +
                "platform='" + platform + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
