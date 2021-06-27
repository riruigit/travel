package edu.lingnan.service;

import edu.lingnan.pojo.PageBean;
import edu.lingnan.pojo.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteService {
    /**
     * 根据cid查询总记录数
     */
    int findTotalCount(int cid, String rname);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     */
    List<Route> findByPage( int cid,  int start,  int pageSize,  String rname);

    PageBean<Route> pageQuery( int cid,  int currentPage,  int pageSize,  String rname);

    Route findOne(String rid);

}
