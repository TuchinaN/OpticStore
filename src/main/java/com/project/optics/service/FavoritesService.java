package com.project.optics.service;

;
import com.project.optics.models.Favorites;
import com.project.optics.repositories.FavoritesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private FavoritesRepository favRepo;

    @Autowired
    public FavoritesService(FavoritesRepository favRepo) {
        this.favRepo = favRepo;
    }
    public List<Favorites> getAddsByUserId(int userId) {
        return favRepo.findAllByUserId(userId);
    }
    public Favorites getAddByUserIdAndItemId(int userId, int itemId){
        return favRepo.findByUserIdAndItemId(userId, itemId);
    }
    public Favorites getAddById (int id) {
        return favRepo.findById(id);
    }
    public void saveAdd(Favorites add) {
        favRepo.save(add);
    }
    public void deleteAddById(int id) {
        favRepo.deleteById(id);
    }
    public void deleteAdd(Favorites add){
        favRepo.delete(add);
    }
    public void deleteAllByUserId(int userId)
    {
        favRepo.deleteAllByUserId(userId);
    }
}
