package natan.code.company.domain.exception;

public class BranchNotFoundException extends EntityNotFoundException {

    public BranchNotFoundException(String message) {
        super(message);
    }

    public BranchNotFoundException(Long id) {
        this(String.format("Branch not found for the id %d", id));
    }
}
