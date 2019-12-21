package app.web.beans;

import app.domain.models.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class DeleteBean extends BaseBean {
    private HeroService heroService;
    private ModelMapper modelMapper;

    @Inject
    public DeleteBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    public DeleteBean() {
    }

    public HeroViewModel getHero(String id) {
        return this.modelMapper.map(this.heroService.getById(id), HeroViewModel.class);
    }

    public void deleteHero() throws IOException {
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        this.heroService.deleteById(id);
        this.redirect("/home");
    }
}
