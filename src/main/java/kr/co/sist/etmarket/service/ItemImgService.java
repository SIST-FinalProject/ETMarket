package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemImgDao;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.ItemImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ItemImgService {
    private final ItemImgDao itemImgDao;
    private final S3Uploader s3Uploader;

    // ItemImg DB Insert
    public void insertItemImg(ArrayList<MultipartFile> itemImgUpload, Item item) {
        ArrayList<String> itemImgUrls = new ArrayList<>();

        for(MultipartFile  itemImg:itemImgUpload) {
            try {
                itemImgUrls.add(s3Uploader.upload(itemImg, "itemImg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (String itemImgUrl:itemImgUrls) {
            ItemImg itemImg = ItemImg.builder()
                    .item(item)
                    .itemImg(itemImgUrl)
                    .build();

            itemImgDao.save(itemImg);
        }
    }
}
