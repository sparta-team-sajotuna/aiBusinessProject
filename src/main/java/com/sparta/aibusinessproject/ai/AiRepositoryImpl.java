package com.sparta.aibusinessproject.ai;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.aibusinessproject.domain.Ai;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static com.sparta.aibusinessproject.domain.QAi.ai;


@RequiredArgsConstructor
public class AiRepositoryImpl implements AiRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<AiSearchListResponse> searchAi(int size, Pageable pageable) {
        // 정렬 기준 설정
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);

        QueryResults<Ai> results = queryFactory
                .selectFrom(ai)
                // 정렬
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                // 페이징 처리
                .offset(pageable.getOffset())
                .limit(size)
                // 결과값 반환
                .fetchResults();

        List<AiSearchListResponse> content = results.getResults().stream()
                .map(Ai::toResponseDto)
                .collect(Collectors.toList());
        long total = results.getTotal();

        // PageImpl 객체를 생성하여 결과를 반환합니다. 이 객체는 페이지 내용, 페이지 정보, 총 결과 수를 포함
        return new PageImpl<>(content, pageable, total);
    }



    private List<OrderSpecifier<?>> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        // pageable 객체에서 정렬 정보가 존재하는지 확인합니다. 만약 정렬정보가 존재한다면
        if (pageable.getSort() != null) {
            // pageable.getSort()로부터 반환된 Sort.Order 객체들을 반복
            for (Sort.Order sortOrder : pageable.getSort()) {

                com.querydsl.core.types.Order direction = sortOrder.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC;
                switch (sortOrder.getProperty()) {
                    case "createdAt":
                        orders.add(new OrderSpecifier<>(direction, ai.createdAt));
                        break;
                    case "updatedAt":
                        orders.add(new OrderSpecifier<>(direction, ai.updatedAt));
                        break;
                    default:
                        break;
                }
            }
        }

        return orders;
    }
}
