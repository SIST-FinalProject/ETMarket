package kr.co.sist.etmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemImgDto {
    private Long itemId;
    private Long itemImgId;
    private Timestamp resistDate;
    private Timestamp updateDate;
    private String itemImg;

    public ItemImgDto(Long itemImgId, String itemImg) {
        this.itemImgId = itemImgId;
        this.itemImg = itemImg;
    }
}
