package com.sparta.aibusinessproject.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.aibusinessproject.domain.Menu;
import com.sparta.aibusinessproject.domain.request.MenuSearchRequest;
import com.sparta.aibusinessproject.domain.response.MenuFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.sparta.aibusinessproject.domain.QMenu.menu;
import static com.sparta.aibusinessproject.domain.QStore.store;


@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MenuFindResponse> searchMenus(UUID storeId, MenuSearchRequest searchDto, Pageable pageable) {
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);

        QueryResults<Menu> results = queryFactory
                .selectFrom(menu)
                .leftJoin(menu.store, store).fetchJoin() //store 조인
                .where(
                        nameContains(searchDto.getName()),
                        priceBetween(searchDto.getMinPrice(), searchDto.getMaxPrice()),
                        menu.store.id.eq(storeId)
                )
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MenuFindResponse> content = results.getResults().stream()
                .map(MenuFindResponse::fromEntity)
                .collect(Collectors.toList());
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression nameContains(String name) {
        return name != null ? menu.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression priceBetween(Double minPrice, Double maxPrice) {
        if (minPrice != null && maxPrice != null) {
            return menu.price.between(minPrice, maxPrice);
        } else if (minPrice != null) {
            return menu.price.goe(minPrice);
        } else if (maxPrice != null) {
            return menu.price.loe(maxPrice);
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
                        orders.add(new OrderSpecifier<>(direction, menu.createdAt));
                        break;
                    case "updatedAt":
                        orders.add(new OrderSpecifier<>(direction, menu.updatedAt));
                        break;
                    default:
                        break;
                }
            }
        }

        return orders;
    }

}
