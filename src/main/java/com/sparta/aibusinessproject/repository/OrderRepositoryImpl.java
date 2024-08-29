package com.sparta.aibusinessproject.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.aibusinessproject.domain.Order;
import com.sparta.aibusinessproject.domain.OrderStatusEnum;
import com.sparta.aibusinessproject.domain.QOrder;
import com.sparta.aibusinessproject.domain.UserRoleEnum;
import com.sparta.aibusinessproject.domain.request.OrderSearchRequest;
import com.sparta.aibusinessproject.domain.response.OrderFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sparta.aibusinessproject.domain.QMenu.menu;
import static com.sparta.aibusinessproject.domain.QOrder.order;
import static com.sparta.aibusinessproject.domain.QOrderMenu.orderMenu;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderFindResponse> searchOrders(OrderSearchRequest searchDto, Pageable pageable, UserRoleEnum role, String userId) {

        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);

        QueryResults<Order> results = queryFactory
                .selectFrom(order)
                .leftJoin(order.orderMenuList, orderMenu).fetchJoin() //orderMenu 조인
                .leftJoin(orderMenu.menu, menu).fetchJoin() //menu 조인
                .where(
                        statusEq(searchDto.getStatus()),
                        userCheck(role, userId)
                )
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<OrderFindResponse> content = results.getResults().stream()
                .map(OrderFindResponse::fromEntity)
                .collect(Collectors.toList());
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression statusEq(OrderStatusEnum status) {
        return status != null ? order.status.eq(status) : null;
    }
    private BooleanExpression userCheck(UserRoleEnum role, String userId) {
        if(role.equals(UserRoleEnum.CUSTOMER)){ // 고객 > 자신의 주문 내역만 조회 가능
            return order.user.userId.eq(userId); // 가게 주인 >  자신의 가게 주문 내역 조회 가능
        }
//        else if(role.equals(UserRoleEnum.OWNER)){ TODO
//            return order.store.user.userId.eq(userId);
//        }
        else{
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
                        orders.add(new OrderSpecifier<>(direction, order.createdAt));
                        break;
                    case "modifiedAt":
                        orders.add(new OrderSpecifier<>(direction, order.updatedAt));
                        break;
                    default:
                        break;
                }
            }
        }

        return orders;
    }
}
