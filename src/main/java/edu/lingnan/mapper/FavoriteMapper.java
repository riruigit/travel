package edu.lingnan.mapper;

import edu.lingnan.pojo.Favorite;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;

public interface FavoriteMapper {

      /**
     * 根据rid和uid查询收藏信息
     * @param rid
     * @param uid
     * @return
     */
     Favorite findByRidAndUid(@Param("rid")int rid,@Param("uid") int uid);

    /**
     * 根据rid 查询收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(@Param("rid")int rid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(@Param("rid")int rid, @Param("uid") int uid);

    /**
     * 取消收藏的方法
     * @param rid
     * @param uid
     */
    void deleteFavorite(@Param("rid")int rid, @Param("uid") int uid);
}
