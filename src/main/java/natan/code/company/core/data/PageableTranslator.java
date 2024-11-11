package natan.code.company.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageableTranslator {

    public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapped) {
        List<Sort.Order> list = pageable.getSort().stream()
                .filter(order -> fieldsMapped.containsKey(order.getProperty()))
                .map(order -> new Sort.Order(order.getDirection(),
                        fieldsMapped.get(order.getProperty())))
                .collect(Collectors.toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(list));
    }

}
