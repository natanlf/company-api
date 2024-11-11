package natan.code.company.api.v1.assembler;

import natan.code.company.api.v1.BranchController;
import natan.code.company.api.v1.CompanyLinks;
import natan.code.company.api.v1.model.BranchModel;
import natan.code.company.domain.model.Branch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class BranchModelAssembler extends RepresentationModelAssemblerSupport<Branch, BranchModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyLinks companyLinks;

    public BranchModelAssembler() {
        super(BranchController.class, BranchModel.class);
    }

    @Override
    public BranchModel toModel(Branch branch) {
        BranchModel branchModel = createModelWithId(branch.getId(), branch);
        modelMapper.map(branch, branchModel);
        branchModel.add(companyLinks.linkToCompanies("branches"));
        return branchModel;
    }

    @Override
    public CollectionModel<BranchModel> toCollectionModel(Iterable<? extends Branch> branches) {
        CollectionModel<BranchModel> collectionModel = super.toCollectionModel(branches);
        collectionModel.add(companyLinks.linkToCompanies());
        return collectionModel;
    }

}
