package com.quantum.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author BC Holmes
 */
public class InputStreamHelper {

	public static String readIntoString(InputStream inputStream) throws IOException {
		return readIntoString(inputStream, null);
	}

	public static String readIntoString(InputStream input, String charset)
			throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		for (int b = input.read(); b >= 0; b = input.read()) {
			output.write((byte) b);
		}
		return charset == null ? new String(output.toByteArray()) : new String(output
				.toByteArray(), charset);
	}
}
