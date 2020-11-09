package com.mbrdi.service.mbrdiassignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import com.mbrdi.service.mbrdiassignment.exception.ServiceException;
import com.mbrdi.service.mbrdiassignment.model.LocationResponse;
import com.mbrdi.service.mbrdiassignment.model.Place;
import com.mbrdi.service.mbrdiassignment.util.RestTemplateBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * @author dhananjay
 *
 */
@Component
public class SearchServiceImpl implements ISearchService{
	
	private static final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);
	public static final String RESTAURANT="restaurant";
	public static final String MALL="mall";
	public static final String HOTEL="hotel";
	public static final String SHOP="shop";

	   @Autowired
	   RestTemplateBuilderFactory restTemplateBuilderFactory;
	   

	@Cacheable("place")
	public LocationResponse findPOIByPlaceName(String place) {
		LocationResponse response=null;	
		final String hereResponse = restTemplateBuilderFactory.getContent();
		log.info("Getting hereResponse  :{}", hereResponse);
		final JSONObject responseJsonObj = JSONObject.fromObject(hereResponse);
		if ((responseJsonObj.optJSONObject("search") != null)) {
			final JSONObject cityName = responseJsonObj.optJSONObject("search").optJSONObject("context").optJSONObject("location").optJSONObject("address");
			JSONArray itemArray =responseJsonObj.optJSONObject("results").getJSONArray("items");
			log.info("Items response :{}",itemArray);
			response= mapLocationResponse(itemArray,cityName,place);
		}
		log.info("LocationResponse :{}", response);
		return response;
	}
	
/**
 * Mapping location Response
 * 
 * @param getArray
 * @param cityName
 * @param place
 * @return
 */
	private LocationResponse mapLocationResponse(JSONArray itemArray, JSONObject cityName, String place) {
		LocationResponse response=null;
		List<Place> listPlace=new ArrayList<>();
		Map<Integer, LocationResponse> map= new TreeMap<>();
		if(itemArray !=null&&!itemArray.isEmpty()) {
			for(int i = 0; i < itemArray.size(); i++){
				JSONObject items = itemArray.getJSONObject(i);
				if(items !=null && cityName !=null && place.equalsIgnoreCase(cityName.getString("city"))) {
					String category =  items.optJSONObject("category").getString("id");
					if(category.equalsIgnoreCase(RESTAURANT) || category.equalsIgnoreCase(HOTEL) || category.equalsIgnoreCase(MALL)
							||category.equalsIgnoreCase(SHOP)) {
						log.info("inside loop  category:{}", category);
						response=new LocationResponse();
						Place places =new Place();
						int dist = items.getInt("distance");
						places.setName(items.getString("title"));
						places.setDistance(dist);
						places.setAverageRating(items.getInt("averageRating"));
						places.setType(items.optJSONObject("category").getString("title"));
						listPlace.add(places);
						response.setPlace(listPlace);
						response.setCity(cityName.getString("city"));
						response.setCountry(cityName.getString("country"));

						map.put(dist, response);

					}
				}else {
					log.error("placename:{} is not correct,Please provide correct city name, ex: Berlin", place);
					throw new ServiceException("400", "placename is not correct,Please provide correct city name, ex: Berlin"
							+ " **IMPORTANT NOTE**: Now the content provider giving only data for Berlin City");
				}
			}
		}
		//sort the response for nearest place
		Map<Integer, LocationResponse> sortedmap =map.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.limit(3)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, TreeMap::new));

		LocationResponse locationresponse=  setFinalLocationResponse(sortedmap);
		return locationresponse;
	}
/**
 * setting the response based on distance
 * @param myNewMap
 * @return
 */
	private LocationResponse setFinalLocationResponse(Map<Integer, LocationResponse> myNewMap) {
		LocationResponse result=null;
		List<Place> listPlace=new ArrayList<>();
		if(myNewMap !=null && !myNewMap.isEmpty()) {
			for (Map.Entry<Integer, LocationResponse> entry : myNewMap.entrySet()) {
				LocationResponse value = entry.getValue();
				Integer key = entry.getKey();
				for(Place pl:value.getPlace()) {
					if(key.equals(pl.getDistance())) {
						result=new LocationResponse();
						Place places =new Place();
						result.setCity(value.getCity());
						result.setCountry(value.getCountry());
						places.setAverageRating(pl.getAverageRating());
						places.setDistance(pl.getDistance());
						places.setName(pl.getName());
						places.setType(pl.getType());
						listPlace.add(places);
					}
				}
				result.setPlace(listPlace);
			}
		}
		log.info("map result:{}", result);
		return result;
}

	@Override
	public LocationResponse findPOIByPlaceLocation(String log, String lat) {
		return null;
	}

}
