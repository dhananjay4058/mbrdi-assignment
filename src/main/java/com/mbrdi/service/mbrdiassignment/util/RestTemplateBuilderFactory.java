package com.mbrdi.service.mbrdiassignment.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.mbrdi.service.mbrdiassignment.exception.ServiceException;

@Component
public class RestTemplateBuilderFactory {

	@Autowired
	RestTemplateBuilder restTemplate;

	@Value("${here.url}")
	private String hereUrl;

	public String getContent() {
		String hereResponse=null; 
		try {
			final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(hereUrl);              
			hereResponse = restTemplate.build().getForObject(builder.build().toUri(), String.class);
		}catch(Exception ex ) {
			throw new ServiceException("500", "Content Provider Service down, please try later");
		}
		return hereResponse;
	}

}
