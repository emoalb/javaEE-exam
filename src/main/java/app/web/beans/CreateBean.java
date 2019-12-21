package app.web.beans;

import app.domain.models.binding.HeroCreateBindingModel;
import app.domain.models.service.HeroServiceModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CreateBean extends BaseBean {
    private ModelMapper modelMapper;
    private HeroService heroService;
    private HeroCreateBindingModel heroCreateBindingModel;

    public CreateBean() {
    }

    @Inject
    public CreateBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }
    @PostConstruct
    public void init() {
        this.heroCreateBindingModel = new HeroCreateBindingModel();

    }
    public HeroCreateBindingModel getHeroCreateBindingModel() {
        return heroCreateBindingModel;
    }

    public void setHeroCreateBindingModel(HeroCreateBindingModel heroCreateBindingModel) {
        this.heroCreateBindingModel = heroCreateBindingModel;
    }
    public void create() throws IOException {
        this.heroCreateBindingModel.setHeroClass(this.heroCreateBindingModel.getHeroClass().toUpperCase());
        HeroServiceModel jobApplicationServiceModel = this.modelMapper.map(this.heroCreateBindingModel, HeroServiceModel.class);
        if(jobApplicationServiceModel.getHeroClass()==null){
            this.redirect("/job-add");
            return;
        }else{
            this.heroService.save(jobApplicationServiceModel);
            this.redirect("/home");
            return;
        }
    }
}
