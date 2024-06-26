package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Rating, Long> {

    int countByTarget_UserId(Long userId);

    List<Rating> findByTarget_UserIdOrderByRatingDateDesc(Long userId, Pageable pageable);
}

