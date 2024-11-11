package natan.code.company.domain;

import natan.code.company.domain.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>,
        JpaSpecificationExecutor<Branch> {
}
