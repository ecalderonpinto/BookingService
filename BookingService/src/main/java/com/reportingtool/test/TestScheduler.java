package com.reportingtool.test;

import org.springframework.stereotype.Component;

@Component("testBean")
public class TestScheduler {

	public void printMessage() {
		System.out.println("Se hace...");
	}
}
