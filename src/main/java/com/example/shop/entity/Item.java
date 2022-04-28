package com.example.shop.entity;

import com.example.shop.constatnt.ItemSellStatus;
import com.example.shop.dto.ItemDto;
import com.example.shop.dto.ItemFormDto;
import com.example.shop.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id // entity로 선언한 클래스는 반드시 기본키를 가져야함 @Id 어노테이션은 기본키임
    @Column(name="item_id") // 메핑될 컬럼의 이름
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성 전략을 auto로 지정
    private Long id; // 상품코드

    @Column(nullable = false, length = 50)
    private String itemNm; // 상품명

    @Column(nullable = false, name="price")
    private int price; // 가격`

    @Column(nullable = false)
    private int stockNumber; // 재고수량

    @Lob // 문자형 대용량 데이터 타입
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.stockNumber + ")" );
        }
        this.stockNumber =restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }

}
