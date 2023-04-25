package com.example.tradeintechniqueapp.database.querydsl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//
//public class QPredicate {
//    private final List<Predicate> predicates = new ArrayList<>();
//
//    public static QPredicate builder() {
//        return new QPredicate();
//    }
//
//    public <T> QPredicate add(T object, Function<T, Predicate> function) {
//        if (object != null) {
//            predicates.add(function.apply(object));
//        }
//        return this;
//    }
//
//    public Predicate build() {
//        return ExpressionUtils.allOf(predicates);
//    }
//
//    public Predicate buildOr() {
//        return ExpressionUtils.anyOf(predicates);
//    }
//
//
//}
