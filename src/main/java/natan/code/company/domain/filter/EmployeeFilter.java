package natan.code.company.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class EmployeeFilter {

    private String name;
    private String email;
    private OffsetDateTime dateOfBirth;
    private String cellphone;
    private Long branchId;

}
