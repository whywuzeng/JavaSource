package cn.xiaowenjie.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import cn.xiaowenjie.beans.Advert;


/**
 *  配置类DAO
 *
 */
public interface AdvertDao extends PagingAndSortingRepository<Advert, Long> {

    List<Advert> findAllById(Long id);

    @Query("from qw_advert where ad_id=:shoolid")
    List<Advert> findByShoolId(@Param("shoolid") int shoolid);

//    List<AdvertManager> findAllByObjType(int type);
//
//    AdvertManager findByUserIdAndObjTypeAndObjId(long userId, int objType, long objId);
//
//    int countByObjTypeAndObjId(int objType, long objId);

    // @Query(value = "select t from Config t where t.name like %?1% or t.value like %?1% or t.description like %?1%", nativeQuery = false)
	// Page<Config> findAllByKeyword(String keyword, Pageable pageable);
}