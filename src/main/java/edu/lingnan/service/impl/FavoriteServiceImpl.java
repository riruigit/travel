package edu.lingnan.service.impl;

import edu.lingnan.mapper.FavoriteMapper;
import edu.lingnan.pojo.Favorite;
import edu.lingnan.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 18364
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteMapper favoriteMapper;


    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteMapper.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite != null;

    }

    @Override
    public void add(String rid, int uid) {
        favoriteMapper.add(Integer.parseInt(rid),uid);
    }

    @Override
    public void deleteFavorite(String rid, int uid) {
        favoriteMapper.deleteFavorite(Integer.parseInt(rid),uid);
    }
}
