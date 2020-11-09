package com.mbrdi.service.mbrdiassignment.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbrdi.service.mbrdiassignment.model.LocationResponse;
import com.mbrdi.service.mbrdiassignment.service.ISearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "Search")
public class SearchApiEndpoint {

	@Autowired
	private ISearchService searchService;
	
    public final class Constants {
        public static final String SEARCH_API_NAME = "/search-service";
        public static final String SEARCH_API_VERSION = "/v1";
        public static final String SEARCH_REQUEST_MAPPING_PATH = SEARCH_API_NAME + SEARCH_API_VERSION;
        private Constants() {
            super();
        }
    }
    
    
    @ApiOperation(value = "Search endpoint to find Parking spots, Charging Stations and Restaurants near to the user provided location.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), 
    @ApiResponse(code = 400, message = "BAD REQUEST"),
    @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = Constants.SEARCH_REQUEST_MAPPING_PATH + "/place", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResponse> findPOIByPlaceName(@RequestParam String place) {
    	LocationResponse response = searchService.findPOIByPlaceName(place);
    	if(response==null) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
    	return ResponseEntity.ok(response);
    }

}

	
	
	
	


