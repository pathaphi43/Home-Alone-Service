package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.HouseRepository;
import com.csproject.homealoneservice.dao.RentingHouseRepository;
import com.csproject.homealoneservice.dao.ReviewsImageRepository;
import com.csproject.homealoneservice.dao.ReviewsRepository;
import com.csproject.homealoneservice.dto.*;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.entity.ReviewsEntity;
import com.csproject.homealoneservice.entity.ReviewsImageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewsService {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    @Autowired
    ReviewsImageRepository reviewsImageRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    RentingHouseRepository rentingHouseRepository;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    FileUpload fileUpload;

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

    public ReviewsEntity saveReview(ReviewsEntity reviewBody, MultipartFile[] files){
        ReviewsEntity reviewsEntity = new ReviewsEntity();
        List<ReviewsImageEntity> reviewsImage = new ArrayList<>();
        ResponseEntity<UploadMultipartFileDTO> response = null ;

        reviewsEntity.setReviewsScore(reviewBody.getReviewsScore());
        reviewsEntity.setReviewsText(reviewBody.getReviewsText());
        reviewsEntity.setReviewsStatus(1);
        reviewsEntity.setRid(reviewBody.getRid());
        reviewsEntity.setTid(reviewBody.getTid());
        reviewsEntity.setReviewsDate(reviewBody.getReviewsDate());
        ReviewsEntity reviews = reviewsRepository.save(reviewsEntity);
        if(reviews != null){
            if(files != null){
                response =  fileUpload.uploadMultipartFile(files,reviewsEntity.getId());
                if(response.getStatusCode() == HttpStatus.OK){
                    for (String path:response.getBody().getImgPath()){
                        ReviewsImageEntity imageEntity= new ReviewsImageEntity();
                        imageEntity.setReviewsImage(path);
                        imageEntity.setRid(reviewsEntity.getId());
                        reviewsImage.add(imageEntity);
                    }
                    reviewsImageRepository.saveAll(reviewsImage);
                }
            }
        }



        return reviews;
    }


}
