package edu.lingnan.service;

public interface FavoriteService {
    boolean isFavorite(String rid, int uid);

    void add(String rid, int uid);

    void deleteFavorite(String rid, int uid);
}
