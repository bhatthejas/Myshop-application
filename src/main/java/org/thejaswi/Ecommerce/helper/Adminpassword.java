package org.thejaswi.Ecommerce.helper;

import java.io.File;
import java.io.IOException;

public class Adminpassword {
	public static void main(String[] args) throws IOException {
		File file = new File("src/main/resources/static/images");
		if (!file.isDirectory())
			file.mkdir();
		
	}
}
