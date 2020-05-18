package cn.xiaowenjie.daos;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xiaowenjie.beans.Schoolpic;


/**
 *  配置类DAO
 *
 */
public interface SchoolPicDao extends PagingAndSortingRepository<Schoolpic, Long> {

}