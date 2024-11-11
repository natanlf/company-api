package natan.code.company.domain.services;

import natan.code.company.domain.BranchRepository;
import natan.code.company.domain.exception.BranchNotFoundException;
import natan.code.company.domain.exception.EntityInUseException;
import natan.code.company.domain.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BranchService {

    private static final String MSG_BRANCH_IN_USE  =
            "Branch with id %d cannot be removed, because is in use";

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    public Branch findById(Long id) {
        return branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));
    }

    @Transactional
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            branchRepository.deleteById(id);
            branchRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new BranchNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MSG_BRANCH_IN_USE, id));
        }
    }

}
