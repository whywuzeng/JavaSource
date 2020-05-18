package cn.xiaowenjie.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import cn.xiaowenjie.beans.TDKmanager;


/**
 *  配置类DAO
 *
 */
public interface TdkmanagerDao extends PagingAndSortingRepository<TDKmanager, Long> {

    List<TDKmanager> findAllById(Long id);

    @Query("from qw_tdkmanager where position LIKE CONCAT('%',:position,'%')  and title LIKE CONCAT('%',:title,'%') and keyword LIKE CONCAT('%',:keyword,'%')")
    List<TDKmanager> findFrindUrlByForm(@Param("position") String position, @Param("title") String title, @Param("keyword") String keyword);

//    List<AdvertManager> findAllByObjType(int type);
//
//    AdvertManager findByUserIdAndObjTypeAndObjId(long userId, int objType, long objId);
//
//    int countByObjTypeAndObjId(int objType, long objId);

    // @Query(value = "select t from Config t where t.name like %?1% or t.value like %?1% or t.description like %?1%", nativeQuery = false)
	// Page<Config> findAllByKeyword(String keyword, Pageable pageable);
}