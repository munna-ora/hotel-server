package com.hotelserver.model.review;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotelserver.model.CommonModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class RatingModel extends CommonModel {
	
	@JsonProperty("ratingId")
	private String ratingId;
	
	@JsonProperty("ratingName")
	private String ratingName;
	
	@JsonProperty("ratingFlag")
	private String ratingFlag;
	
	@JsonProperty("bookingVsRatings")
	private List<BookingVsRatingModel> bookingVsRatingModels;

}
