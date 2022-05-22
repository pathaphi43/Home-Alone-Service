package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.HouseRepository;
import com.csproject.homealoneservice.dao.RentingHouseRepository;
import com.csproject.homealoneservice.dao.ReviewsImageRepository;
import com.csproject.homealoneservice.dao.ReviewsRepository;
import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.dto.PreReviewDTO;
import com.csproject.homealoneservice.dto.ReviewsDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.entity.ReviewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewsService {

    @Autowired
    ReviewsImageRepository reviewsImageRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    RentingHouseRepository rentingHouseRepository;

    @Autowired
    HouseRepository houseRepository;


    public List<ReviewsDTO> queryReviewByHid(Integer id){
        List<ReviewsDTO> reviewsDTOS = new ArrayList<>();
       List<RentingHouseEntity> rentingHouseEntities =  rentingHouseRepository.findByHid(id);
       for(RentingHouseEntity rents: rentingHouseEntities){
         List<ReviewsEntity> reviews= reviewsRepository.findByRid(rents.getRid());
         for(ReviewsEntity review:reviews){
             reviewsDTOS.add(new ReviewsDTO(review.getId(),review.getRid(),review.getTid(),review.getReviewsText(),
                     review.getReviewsScore(),review.getReviewsDate(),review.getReviewsStatus(),
                     reviewsImageRepository.findByRid(review.getId())));
         }
       }
       return reviewsDTOS;
    }

    public List<PreReviewDTO> queryHousePreReviewByTid(Integer tid,Integer status){
       List<PreReviewDTO>  preReviewDTO=new ArrayList<>();
      List<RentingHouseEntity> rentings =  rentingHouseRepository.findAllByTidAndRentingStatus(tid,status);
      for (RentingHouseEntity renting:rentings){
       HouseEntity house =  houseRepository.findById(renting.getHid()).get();
       preReviewDTO.add(new PreReviewDTO(house,renting));
      }
      return  preReviewDTO;
    }

    public ReviewsEntity saveReview(ReviewsEntity reviewBody, MultipartFile file){
        ReviewsEntity reviewsEntity = new ReviewsEntity();
        reviewsEntity.setReviewsScore(reviewBody.getReviewsScore());
        reviewsEntity.setReviewsText(reviewBody.getReviewsText());
        reviewsEntity.setReviewsStatus(1);
        reviewsEntity.setRid(reviewBody.getRid());
        reviewsEntity.setTid(reviewBody.getTid());
        reviewsEntity.setReviewsDate(reviewBody.getReviewsDate());
        return reviewsEntity;
    }


}
