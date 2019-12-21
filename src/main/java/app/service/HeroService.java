package app.service;


import app.domain.models.service.HeroServiceModel;


import java.util.List;

public interface HeroService {
    void save(HeroServiceModel heroServiceModel);

    List<HeroServiceModel> getAll();

    HeroServiceModel getById(String id);

    void deleteById(String id);

}
