/**
 * @author Abhideep
 */
package com.hotelserver.model.user;

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
public class InterestModel extends CommonModel {

	
	@JsonProperty("interestId")
	private String interestId;
	
	@JsonProperty("interestName")
	private String interestName;
	
	@JsonProperty("hostVsInterests")
	private List<HostVsInterestModel> hostVsInterestModels;

}