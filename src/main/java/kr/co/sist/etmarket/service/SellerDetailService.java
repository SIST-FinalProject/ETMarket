package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemDetailRepository;
import kr.co.sist.etmarket.dao.ReviewRepository;
import kr.co.sist.etmarket.dao.SellerDetailRepository;
import kr.co.sist.etmarket.dao.TransactionRepository;
import kr.co.sist.etmarket.dto.ItemImgDto;
import kr.co.sist.etmarket.dto.RevieweDto;
import kr.co.sist.etmarket.dto.SimpleItemDto;
import kr.co.sist.etmarket.dto.SimpleSellerDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.ItemImg;
import kr.co.sist.etmarket.entity.Rating;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.etenum.DealStatus;
import kr.co.sist.etmarket.etenum.ItemHidden;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerDetailService {

    private final SellerDetailRepository sellerDetailRepository;
    private final TransactionRepository transactionRepository;
    private final ItemDetailRepository itemDetailRepository;
    private final ReviewRepository reviewRepository;
    private final CommonService commonService;


    public SimpleSellerDto getSimpleSellerInfo(Long sellerId, Long itemId) {

        User seller = sellerDetailRepository.findById(sellerId).orElseThrow(() -> new NoSuchElementException("id값에 해당하는 유저가 없습니다"));

        SimpleSellerDto simpleSellerDto = new SimpleSellerDto();

        simpleSellerDto.setSellerId(seller.getUserId());
        simpleSellerDto.setSellerName(seller.getUserName());
        simpleSellerDto.setSellerImgUrl(seller.getUserImg());
        simpleSellerDto.setTransactionCount(transactionRepository.countBySellerId(seller.getUserId()));
        simpleSellerDto.setSellItemCount(itemDetailRepository.countByUser_UserIdAndItemHiddenAndDealStatus(seller.getUserId(), ItemHidden.보임, DealStatus.판매중));
        simpleSellerDto.setReviewCount(reviewRepository.countByTarget_UserId(seller.getUserId()));

        Pageable pageableItem = PageRequest.of(0, 6);
        List<Item> sixItemList = itemDetailRepository.findRandomItemsByUserAndStatus(seller.getUserId(), DealStatus.판매중, ItemHidden.보임, itemId, pageableItem);
        simpleSellerDto.setSimpleItemDtoList(sixItemList.stream()
                .map(this::convertToSimpleItem)
                .collect(Collectors.toList()));

        Pageable pageableReview = PageRequest.of(0, 2);
        List<Rating> twoReviewList = reviewRepository.findByTarget_UserIdOrderByRatingDateDesc(seller.getUserId(), pageableReview);
        simpleSellerDto.setRevieweDtoList(twoReviewList.stream()
                .map(this::convertToReviewDto)
                .collect(Collectors.toList()));

        return simpleSellerDto;
    }

    private SimpleItemDto convertToSimpleItem(Item item) {
        DecimalFormat formatter = new DecimalFormat("#,###");

        return new SimpleItemDto(item.getItemId(), item.getItemImgs().get(0).getItemImg(),formatter.format(item.getItemPrice()));
    }

    private RevieweDto convertToReviewDto(Rating review) {

        return new RevieweDto(review.getReviewer().getUserId(),
                review.getReviewer().getUserName(),
                review.getReviewer().getUserImg(),
                review.getRating(),
                review.getDeal().getItem().getItemId(),
                review.getComment(),
                commonService.convertTime(review.getRatingDate()));
    }
}
