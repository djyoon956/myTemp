package com.dao;

import org.springframework.stereotype.Service;

@Service
public class TestDao {

	public boolean insertSomething() {
		boolean result = false;
		try {
			System.out.println("Start insert something");
			System.out.println("End insert something");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean exceptionSomething() throws Exception {
		try {
			throw new Exception("ERROR 발생");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
}
