package com.sparta.aibusinessproject.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.aibusinessproject.domain.Payment;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.response.PaymentFindResponse;
import com.sparta.aibusinessproject.domain.response.PaymentSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.sparta.aibusinessproject.domain.QPayment.payment;


@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PaymentFindResponse> searchPayments(PaymentSearchRequest searchDto, Pageable pageable, UserRoleEnum role, String userId) {
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);

        QueryResults<Payment> results = queryFactory
                .selectFrom(payment)
                .where(
                        priceBetween(searchDto.getMinPrice(), searchDto.getMaxPrice()),
                        payment.deletedAt.isNull(),
                        payment.user.userId.eq(userId),
                        userCheck(role, userId)
                )
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<PaymentFindResponse> content = results.getResults().stream()
                .map(PaymentFindResponse::fromEntity)
                .collect(Collectors.toList());
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression userCheck(UserRoleEnum role, String userId) {
        return role.equals(UserRoleEnum.CUSTOMER)? payment.createdBy.eq(userId): null; //CUSTOMER는 본인 결제 내역만 확인 가능
    }

    private BooleanExpression priceBetween(Double minPrice, Double maxPrice) {
        if (minPrice != null && maxPrice != null) {
            return payment.payAmount.between(minPrice, maxPrice);
        } else if (minPrice != null) {
            return payment.payAmount.goe(minPrice);
        } else if (maxPrice != null) {
            return payment.payAmount.loe(maxPrice);
        } else {
            return null;
        }
    }

    private List<OrderSpecifier<?>> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if (pageable.getSort() != null) {
            for (Sort.Order sortOrder : pageable.getSort()) {
                com.querydsl.core.types.Order direction = sortOrder.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC;
                switch (sortOrder.getProperty()) {
                    case "createdAt":
                        orders.add(new OrderSpecifier<>(direction, payment.createdAt));
                        break;
                    case "updatedAt":
                        orders.add(new OrderSpecifier<>(direction, payment.modifiedAt));
                        break;
                    case "payAmount":
                        orders.add(new OrderSpecifier<>(direction, payment.payAmount));
                        break;
                    default:
                        break;
                }
            }
        }

        return orders;
    }

}
