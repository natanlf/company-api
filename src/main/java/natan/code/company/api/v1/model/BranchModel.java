package natan.code.company.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "branches")
public class BranchModel extends RepresentationModel<BranchModel> {

    private Long id;

    private String name;

}
