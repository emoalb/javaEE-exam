package app.web.beans;

import app.domain.models.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DetailsBean extends BaseBean {
    private HeroViewModel heroViewModel;
    private HeroService heroService;
    private ModelMapper modelMapper;
    public DetailsBean() {

    }

    @Inject
    public DetailsBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        this.heroViewModel = this.modelMapper.map(this.heroService.getById(id),HeroViewModel.class);

    }


    public HeroViewModel getHeroViewModel() {
        return heroViewModel;
    }

    public void setHeroViewModel(HeroViewModel heroViewModel) {
        this.heroViewModel = heroViewModel;
    }
}
