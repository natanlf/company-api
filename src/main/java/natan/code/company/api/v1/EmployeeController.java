package natan.code.company.api.v1;

import natan.code.company.core.data.PageableTranslator;
import natan.code.company.domain.filter.EmployeeFilter;
import natan.code.company.domain.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/employess")
public class EmployeeController {

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<Employee> search(EmployeeFilter filter,
//                                 @PageableDefault(size = 10) Pageable pageable) {
//
//        pageable = translatePageable(pageable);
//    }

    private Pageable translatePageable(Pageable apiPageable) {
        Map<String, String> map = Map.of(
                "name", "name",
                "branchName", "branch.name",
                "email", "email",
                "dateOfBirth", "dateOfBirth",
                "cellphone", "cellphone");

        return PageableTranslator.translate(apiPageable, map);
    }

}
