package com.codeseita.productsearchservice.model.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Search<T> {

    private List<Filter> filters;

    public Search() {
        this.filters = new ArrayList<>();
    }

    public Search addFilter(Filter filter) {
        this.filters.add(filter);
        return this;
    }

    public Specification<T> build() {
        if(CollectionUtils.isEmpty(this.filters)) {
            return null;
        }
        List<Specification> specs = this.filters.stream()
                .map(x -> getSpecification(x))
                .collect(Collectors.toList());
        Specification result = specs.get(0);

        for (int i = 1; i < this.filters.size(); i++) {
            result = Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }

    private Specification<T> getSpecification(Filter filter){
        return (root, criteriaQuery, criteriaBuilder) -> getPredicate(filter, root, criteriaBuilder);
    }

    private Predicate getPredicate(Filter filter, Root<T> root, CriteriaBuilder builder) {
        if(filter.getProperty() == null || filter.getValue() == null || filter.getOperation() == null) {
            return null;
        }
        if (filter.getOperation() == Filter.Operation.GREATER_THAN_OR_EQUAL_TO) {
            return builder.greaterThanOrEqualTo(
                    getExpression(root, filter), filter.getValue().toString());
        }
        else if (filter.getOperation() == Filter.Operation.LESS_THAN_OR_EQUAL_TO) {
            return builder.lessThanOrEqualTo(
                    getExpression(root, filter), filter.getValue().toString());
        }
        else if (filter.getOperation() == Filter.Operation.LESS_THAN_OR_EQUAL_TO) {
            return builder.lessThanOrEqualTo(
                    getExpression(root, filter), filter.getValue().toString());
        }
        else if (filter.getOperation() == Filter.Operation.EQUALS) {
            return builder.equal(
                    getExpression(root, filter), filter.getValue());
        }
        else if (filter.getOperation() == Filter.Operation.IN) {
            if(filter.getValue() instanceof Collection) {
                Object[] values = ((Collection)filter.getValue()).stream().toArray(Object[] :: new);
                return getExpression(root, filter).in(values);
            }
        }
        return null;
    }

    private Expression getExpression(Root<T> root, Filter filter){
        String[] joins = filter.getProperty().split("\\.");
        if(joins.length == 1){
            return root.<String> get(filter.getProperty());
        }
        Join join = root.join(joins[0]);
        for(int i=1;i<joins.length-1;i++){
            join = join.join(joins[i]);
        }
        return join.<String> get(joins[joins.length-1]);
    }

}

