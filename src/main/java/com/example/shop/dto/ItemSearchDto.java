package com.example.shop.dto;

import com.example.shop.constatnt.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {

    private String searchDateType; // 조회 시간 기준

    private ItemSellStatus searchSellStatus; // 판매상태 기준

    private String searchBy; // 상품명, 등록자아이디

    private String searchQuery = "";
}
