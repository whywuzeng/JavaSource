package cn.xiaowenjie.daos;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import cn.xiaowenjie.beans.Category;


/**
 *  配置类DAO
 *
 */
public interface CategoryDao extends PagingAndSortingRepository<Category, Long> {

    List<Category> findAllById(Long id);

//    List<AdvertManager> findAllByObjType(int type);
//
//    AdvertManager findByUserIdAndObjTypeAndObjId(long userId, int objType, long objId);
//
//    int countByObjTypeAndObjId(int objType, long objId);

    // @Query(value = "select t from Config t where t.name like %?1% or t.value like %?1% or t.description like %?1%", nativeQuery = false)
	// Page<Config> findAllByKeyword(String keyword, Pageable pageable);
}