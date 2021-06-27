package edu.lingnan.mapper;

import edu.lingnan.pojo.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 18364
 */
public interface RouteMapper {
    //根据cid获取总记录数，int

    //先根据cid分号，返回每一个类前几条，if 参数 <if>

    /**
     * 根据cid查询总记录数
     */
    int findTotalCount(@Param("cid") int cid, @Param("rname") String rname);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     */
    List<Route> findByPage(@Param("cid") int cid, @Param("start") int start, @Param("pageSize") int pageSize, @Param("rname") String rname);


    /**
     * 查询一条数据
     */
    Route findOne (int rid);
}
