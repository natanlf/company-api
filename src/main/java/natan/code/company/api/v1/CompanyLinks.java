package natan.code.company.api.v1;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CompanyLinks {

    public Link linkToCompanies(String rel) {
        return linkTo(BranchController.class).withRel(rel);
    }

    public Link linkToCompanies() {
        return linkToCompanies(IanaLinkRelations.SELF.value());
    }

}
