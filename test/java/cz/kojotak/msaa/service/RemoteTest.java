package cz.kojotak.msaa.service;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

import cz.kojotak.msaa.to.Account;
import cz.kojotak.msaa.to.Project;

public class RemoteTest {

	private Remote remote = new Remote();

	private final Account real = new Account("CHANGE", "THIS");
	
	@Test
	public void fetchTokenBadCredentials(){
		Account account = new Account("should", "not-pass");
		String token = remote.fetchToken(account);
		assertNull(token);
	}
	
	@Test
	@Ignore("replace TODO with real username and password")
	public void fetchTokenGoodCredentials(){
		String token = remote.fetchToken(real);
		assertNotNull(token);
	}
	
	@Test
	public void fetchProjects(){
		List<Project> projects = remote.fetchProjects(real);
		assertNotNull(projects);
	}
}
