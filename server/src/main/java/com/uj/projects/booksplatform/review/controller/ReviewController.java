package com.uj.projects.booksplatform.review.controller;

import com.uj.projects.booksplatform.review.dto.ReviewDto;
import com.uj.projects.booksplatform.review.entity.Review;
import com.uj.projects.booksplatform.review.mapper.ReviewMapper;
import com.uj.projects.booksplatform.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private ReviewMapper reviewMapper;

    @Autowired
    ReviewController(ReviewService reviewService, ReviewMapper reviewMapper){
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping
    public List<ReviewDto> getAllReviews(){
        return reviewService.getAll().stream().map(reviewMapper::reviewToReviewDto).collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public ReviewDto getReview(@PathVariable(name = "id") Integer id){
         Review review = reviewService.getById(id);
         return reviewMapper.reviewToReviewDto(review);
    }

    @GetMapping(params = "book")
    public List<ReviewDto> getReviewsByBook(@RequestParam(name = "book") Integer bookId){
        return reviewService.getByBook(bookId).stream().map(reviewMapper::reviewToReviewDto).collect(Collectors.toList());
    }

    @GetMapping(params = "user")
    public List<ReviewDto> getReviewsByUser(@RequestParam(name = "user") Integer userId){
        return reviewService.getByUser(userId).stream().map(reviewMapper::reviewToReviewDto).collect(Collectors.toList());
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto){
        reviewDto.setId(0);
        Review review = reviewMapper.reviewDtoToReview(reviewDto);
        Review createdReview = reviewService.create(review);
        return reviewMapper.reviewToReviewDto(createdReview);
    }

    @PostMapping(path = "/{id}")
    public ReviewDto updateReview(@RequestBody ReviewDto reviewDto, @PathVariable("id") Integer id){
        reviewDto.setId(id);
        Review review = reviewMapper.reviewDtoToReview(reviewDto);
        Review updatedReview = reviewService.update(review);
        return reviewMapper.reviewToReviewDto(updatedReview);
    }

    @DeleteMapping(path = "{id}")
    public void deleteReview(@PathVariable(name = "id") Integer id){
        reviewService.delete(id);
    }

}
