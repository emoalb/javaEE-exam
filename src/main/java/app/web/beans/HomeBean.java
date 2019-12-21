package app.web.beans;

import app.domain.models.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped

public class HomeBean extends BaseBean {
    private ModelMapper modelMapper;
    private HeroService heroService;
    private List<HeroViewModel> heroViewModels;

    public HomeBean() {
        this.heroViewModels = new ArrayList<>();
    }

    @Inject
    public HomeBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
        this.heroViewModels = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        this.setHeroViewModels(this.heroService.getAll().stream().map(job -> this.modelMapper.map(job, HeroViewModel.class)).collect(Collectors.toList()));
        this.getHeroViewModels().forEach(jobApplicationViewModel1 -> jobApplicationViewModel1.setHeroClass(jobApplicationViewModel1.getHeroClass().toLowerCase()));
    }


    public List<HeroViewModel> getHeroViewModels() {
        return heroViewModels;
    }

    public void setHeroViewModels(List<HeroViewModel> heroViewModels) {
        this.heroViewModels = heroViewModels;
    }
}
