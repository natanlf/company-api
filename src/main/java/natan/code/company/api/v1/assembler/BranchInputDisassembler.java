package natan.code.company.api.v1.assembler;

import natan.code.company.api.v1.model.input.BranchInput;
import natan.code.company.domain.model.Branch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BranchInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Branch toDomainObject(BranchInput branchInput) {
        return modelMapper.map(branchInput, Branch.class);
    }

    public void copyToDomainObject(BranchInput branchInput, Branch branch) {
        modelMapper.map(branchInput, branch);
    }

}
