/**
 * 
 */
package com.solutions.andriod.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author faroukElabady
 * 
 */
public class Utilities {

	public static Map<String, Boolean> parseSyncData(String data) {

		Map<String, Boolean> query_pairs = new LinkedHashMap<String, Boolean>();
		String[] pairs = data.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			try {
				query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
						Boolean.valueOf(URLDecoder.decode(pair.substring(idx + 1), "UTF-8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return query_pairs;
	}

}
