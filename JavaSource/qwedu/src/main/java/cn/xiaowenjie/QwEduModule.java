package cn.xiaowenjie;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2020/4/10.
 * <p>
 * by author wz
 * <p>
 * cn.xiaowenjie
 */
@Component
public class QwEduModule {
    @PostConstruct
    public  void init(){
        System.out.println("-----------------------------------------------");
        System.out.println("--                                                                     --");
        System.out.println("--          QwEduModule Module Loaded                 --");
        System.out.println("--                                                                     --");
        System.out.println("-----------------------------------------------");
    }
}
