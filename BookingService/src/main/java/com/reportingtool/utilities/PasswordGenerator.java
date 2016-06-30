package com.reportingtool.utilities;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PasswordGenerator {
	private SecureRandom random = new SecureRandom();

	public String nextPassword() {
		return new BigInteger(130, random).toString(32);
	}
}
