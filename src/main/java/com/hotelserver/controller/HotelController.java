package com.hotelserver.controller;

import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelserver.exceptions.FormExceptions;
import com.hotelserver.helper.HotelConstant;
import com.hotelserver.helper.Util;
import com.hotelserver.model.propertylist.FilterCiteriaModel;
import com.hotelserver.model.propertylist.PropertyListViewModel;
import com.hotelserver.model.propertylist.ResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Hotel", tags = "Hotel")
public class HotelController extends BaseController {

	private static final Logger logger = LogManager.getLogger(HotelController.class);
	
	@PostMapping(value = "/fetch-hotels", produces = "application/json")
	@ApiOperation(value = "Fetch Hotels", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token") })
	public ResponseEntity<ResponseModel> fetchHotels(@RequestBody FilterCiteriaModel filterCiteriaModel) {

		if (logger.isInfoEnabled()) {
			logger.info("fetchHotels -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(filterCiteriaModel, HotelConstant.INCOMING, "Fetch Hotels", request);
		try {
			long startTime = System.currentTimeMillis();
			List<PropertyListViewModel> propertyListViewModels = hotelService.fetchHotels(filterCiteriaModel);
			long elapsedTimeNs = System.currentTimeMillis() - startTime;
			System.err.println("Total Time Taken ==>> "+(elapsedTimeNs/1000));
			responseModel.setResponseBody(propertyListViewModels);
			responseModel.setResponseCode(messageUtil.getBundle(HotelConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(HotelConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch Hotels -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchHotels -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(HotelConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(HotelConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, HotelConstant.OUTGOING, "Fetch Hotels", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchHotels -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(HotelConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
