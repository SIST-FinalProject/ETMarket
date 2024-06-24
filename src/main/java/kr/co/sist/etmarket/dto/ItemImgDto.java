package kr.co.sist.etmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemImgDto {
    private Long itemImgId;

    private Long itemId;

    private String itemImg;

    private String itemDeleteImgIds;
}
