package cz.kojotak.msaa.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cz.kojotak.msaa.service.Remote;
import cz.kojotak.msaa.service.Storage;
import cz.kojotak.msaa.to.Account;
import cz.kojotak.msaa.to.Project;

@Controller
public class WebController {
	
	Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired Storage storage;
	@Autowired Remote remote;
	
	/** homepage where user can setup his account */
	final String HOME = "home";
	
	/** mainpage where user can list projects */
	final String PROJECTS = "projects";
	
	/** view prefix for HTTP redirect */
	final String REDIRECT = "redirect:";
	
	final String SESSION = "session";
	
	@GetMapping(value={"/","/home"})
	public String home(Model model, HttpServletRequest request) {
		logger.info("home");
		model.addAttribute("account", new Account());
		request.getSession().setAttribute(SESSION, null);
		return HOME;
	}
	
	@PostMapping("/store")
	public String store(@ModelAttribute Account account, HttpServletRequest request) {
		logger.info("store " + account);
		String token = remote.fetchToken(account);
		if(token!=null && account!=null){
			Integer id = storage.store(account);
			if(id!=null){
				request.getSession().setAttribute(SESSION, account);
				//successful account setup
				return REDIRECT + PROJECTS;
			}
		}
		return REDIRECT + HOME;
	}
	
	@GetMapping(value = "projects")
	public String projects(Model model, HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute(SESSION);
		if(account==null){
			return REDIRECT + HOME;
		}
		model.addAttribute("account", account);
		return PROJECTS;
	}
	
	@GetMapping(value = "projects", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Project>> rest(Model model, HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute(SESSION);
		if(account==null){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<Project> projects = remote.fetchProjects(account);
		logger.info("fetched " + projects.size() + " projects");
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}

}