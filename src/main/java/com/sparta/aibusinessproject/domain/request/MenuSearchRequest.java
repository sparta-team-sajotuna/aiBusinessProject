package com.sparta.aibusinessproject.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuSearchRequest {
    private String name;
    private String price;
    private Double minPrice;
    private Double maxPrice;
}
