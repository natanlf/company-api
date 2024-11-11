package natan.code.company.api.v1;

import jakarta.validation.Valid;
import natan.code.company.api.v1.assembler.BranchInputDisassembler;
import natan.code.company.api.v1.assembler.BranchModelAssembler;
import natan.code.company.api.v1.model.BranchModel;
import natan.code.company.api.v1.model.input.BranchInput;
import natan.code.company.domain.model.Branch;
import natan.code.company.domain.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchModelAssembler branchModelAssembler;

    @Autowired
    private BranchInputDisassembler branchInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<BranchModel> findAll() {
        List<Branch> branches = branchService.findAll();
        return branchModelAssembler.toCollectionModel(branches);
    }

    @GetMapping(path = "/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BranchModel findById(@PathVariable Long branchId) {
        Branch branch = branchService.findById(branchId);
        return branchModelAssembler.toModel(branch);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BranchModel save(@Valid @RequestBody BranchInput branchInput) {
        Branch branch = branchInputDisassembler.toDomainObject(branchInput);
        branch = branchService.save(branch);
        return branchModelAssembler.toModel(branch);
    }

    @PutMapping(path = "/{branchId}")
    public BranchModel update(@PathVariable Long branchId, @RequestBody BranchInput branchInput) {
        Branch branch = branchService.findById(branchId);
        branchInputDisassembler.copyToDomainObject(branchInput, branch);
        branch = branchService.save(branch);
        return branchModelAssembler.toModel(branch);
    }

    @DeleteMapping(path = "/{branchId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long branchId) {
        branchService.deleteById(branchId);
    }
}
