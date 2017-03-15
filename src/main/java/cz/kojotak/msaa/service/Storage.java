package cz.kojotak.msaa.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cz.kojotak.msaa.to.Account;

@Service
public class Storage {

	Logger logger = LoggerFactory.getLogger(Storage.class);
	
	public Account load(int id){
		logger.debug("loading " + id);
		return null;
	}
	
	public int store(Account account){
		logger.debug("storing " + account);
		return -1;
	}
}
