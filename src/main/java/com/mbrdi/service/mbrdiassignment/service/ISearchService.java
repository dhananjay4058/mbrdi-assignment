package com.mbrdi.service.mbrdiassignment.service;

import com.mbrdi.service.mbrdiassignment.model.LocationResponse;


public interface ISearchService {
    /**
     * It will return the POI's based on the place name
     *
     * @param place place name e.g. Berlin
     * @return search result form provider like HERE map 
     */
	public LocationResponse findPOIByPlaceName(String place);

    /**
     * @param log longitude of the location
     * @param lat latitude of the location
     * @return search result form provider like HERE map
     */
    public LocationResponse findPOIByPlaceLocation(String log, String lat);
}
