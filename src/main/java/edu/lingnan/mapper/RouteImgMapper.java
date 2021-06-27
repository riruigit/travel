package edu.lingnan.mapper;

import edu.lingnan.pojo.RouteImg;

import java.util.List;

public interface RouteImgMapper {
    List<RouteImg> findByRid(int rid);
}
