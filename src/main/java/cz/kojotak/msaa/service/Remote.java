/**
 * 
 */
package cz.kojotak.msaa.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cz.kojotak.msaa.to.Account;
import cz.kojotak.msaa.to.Project;

@Service
public class Remote {
	
	static final String API = "https://cloud.memsource.com/web/api/v3/";
	
	RestTemplate rest = new RestTemplate();
	Logger logger = LoggerFactory.getLogger(Remote.class);

	public List<Project> fetchProjects(Account account){
		String token = fetchToken(account);
		if(token!=null){
			MultiValueMap<String, Object> multipart = new LinkedMultiValueMap<>();
			multipart.add("token", token);
			List<Map<String, Object>> list = postForList(projectsUrl(), multipart);
			List<Project> projects = new ArrayList<>();
			for(Map<String, Object> item : list){
				Project project = new Project();
				project.setName((String)item.get("name"));
				project.setSource((String)item.get("sourceLang"));
				project.setStatus((String)item.get("status"));
				project.setTargets((List<String>)item.get("targetLangs"));
				logger.debug("fetched: " + project);
				projects.add(project);
			}
			return projects;
		}else{
			logger.info("no access token acquired");
			return Collections.emptyList();
		}
	}
	
	public String fetchToken(Account account){
		logger.debug("fetchToken for " + account.getUsername()+ " ... ");
		return (String)getForMap(loginUrl(account)).get("token");
	}
	
	private List<Map<String,Object>> postForList(String url, MultiValueMap<String, Object> data){
		ResponseEntity<List> response = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(data, headers);
		try{
			response = rest.postForEntity(url, request, List.class);
		}catch (RestClientException e) {
			logger.info("failed to get "+url + " because " + e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		if(response != null && response.getStatusCode().is2xxSuccessful()){
			 return response.getBody();
		}
		return Collections.emptyList();
	}
	
	private Map<String,Object> getForMap(String url){
		ResponseEntity<Map> response = null;
		try{
			response = rest.getForEntity(url, Map.class);
		}catch (RestClientException e) {
			logger.info("failed to get "+url + " because " + e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		if(response != null && response.getStatusCode().is2xxSuccessful()){
			return response.getBody();
		}else{
			return Collections.emptyMap();
		}
	}
	
	private String loginUrl(Account account){
		return API + "auth/login?userName=" + account.getUsername()+"&password="+account.getPassword();
	}
	
	private String projectsUrl(){
		return API + "project/list";
	}
}
