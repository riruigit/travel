package edu.lingnan.service.impl;

import edu.lingnan.mapper.FavoriteMapper;
import edu.lingnan.mapper.RouteImgMapper;
import edu.lingnan.mapper.RouteMapper;
import edu.lingnan.mapper.SellerMapper;
import edu.lingnan.pojo.PageBean;
import edu.lingnan.pojo.Route;
import edu.lingnan.pojo.RouteImg;
import edu.lingnan.pojo.Seller;
import edu.lingnan.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteMapper routeMapper;

    @Autowired
    RouteImgMapper routeImgMapper;

    @Autowired
    SellerMapper sellerMapper;

    @Autowired
    FavoriteMapper favoriteMapper;

    @Override
    public int findTotalCount(int cid, String rname) {
        return routeMapper.findTotalCount(cid, rname);
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {

        return routeMapper.findByPage(cid, start, pageSize, rname);
    }

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeMapper.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = routeMapper.findByPage(cid, start, pageSize, rname);
        pb.setList(list);
        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        Route route = routeMapper.findOne(Integer.parseInt(rid));
        List<RouteImg> routeImgList = routeImgMapper.findByRid(route.getRid());
        route.setRouteImgList(routeImgList);
        Seller seller = sellerMapper.findById(route.getSid());
        route.setSeller(seller);
        //查询收藏次数
        int countByRid = favoriteMapper.findCountByRid(route.getRid());
        route.setCount(countByRid);
        return route;
    }


}
