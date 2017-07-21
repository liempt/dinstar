package sn.mas.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class HttpUtil {

	public static final String credentials = "admin:admin";
//	public static final String basicAuth = "Basic " + new String(new Base64().encode(credentials.getBytes()));

	public static String sendPostRequest(String strUrl, String jsonParams) {
		try {
			byte[] authEncBytes = Base64.encodeBase64(credentials.getBytes());
			String basicAuth = new String(authEncBytes);
			URL e = new URL(strUrl);
			System.out.println("url => " + strUrl);
			System.out.println("jsonparams => " + jsonParams);
			
			HttpURLConnection httpConnection = (HttpURLConnection) e
					.openConnection();
			httpConnection.setDoInput(true);
			httpConnection.setDoOutput(true);
			httpConnection.setUseCaches(false);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Accept", "application/json");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			httpConnection.setRequestProperty("Authorization", "Basic " + basicAuth);
			httpConnection.connect();
			System.out.println("props " +httpConnection.getRequestProperty("Authorization"));

			OutputStreamWriter os = new OutputStreamWriter(
					httpConnection.getOutputStream(), "UTF-8");
			os.write(jsonParams);
			os.close();

			if (httpConnection.getResponseCode() != 200) {
				Map is1 = httpConnection.getHeaderFields();
				Iterator temp1 = is1.keySet().iterator();

				while (temp1.hasNext()) {
					String arrayos1 = (String) temp1.next();
					// System.out.println(arrayos1 + ":" + is1.get(arrayos1));
				}

				return null;
			} else {
				InputStream is = httpConnection.getInputStream();
				ByteArrayOutputStream arrayos = new ByteArrayOutputStream();
				byte[] temp = new byte[512];
				boolean readLen = false;

				int readLen1;
				while ((readLen1 = is.read(temp)) > 0) {
					arrayos.write(temp, 0, readLen1);
				}

				String result = new String(arrayos.toByteArray(), "UTF-8");
				arrayos.close();
				// System.out.println(result);
				return result;
			}
		} catch (MalformedURLException me) {
			// TODO: handle exception
			me.printStackTrace();
			return null;
		} catch (IOException ioe) {
			// TODO: handle exception
			ioe.printStackTrace();
			return null;
		}
	}

	public static String sendGetRequest(String strUrl, String param) {
		try {
			byte[] authEncBytes = Base64.encodeBase64(credentials.getBytes());
			String basicAuth = new String(authEncBytes);
			strUrl = strUrl + "?" + URLEncoder.encode(param, "UTF-8");
			URL e = new URL(strUrl);
			System.out.println("url => " + strUrl);
			System.out.println("param => " +param);
			HttpURLConnection httpConnection = (HttpURLConnection) e.openConnection();
			httpConnection.setDoInput(true);
			httpConnection.setUseCaches(false);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "application/json");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			httpConnection.setRequestProperty("Authorization", "Basic " + basicAuth);
			httpConnection.connect();
			if (httpConnection.getResponseCode() != 200) {
				Map is1 = httpConnection.getHeaderFields();
				Iterator temp1 = is1.keySet().iterator();

				while (temp1.hasNext()) {
					String os1 = (String) temp1.next();
					System.out.println(os1 + ":" + is1.get(os1));
				}

				return null;
			} else {
				InputStream is = httpConnection.getInputStream();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] temp = new byte[512];
				boolean readLen = false;

				int readLen1;
				while ((readLen1 = is.read(temp)) > 0) {
					os.write(temp, 0, readLen1);
				}

				String result = new String(os.toByteArray(), "UTF-8");
				os.close();
				is.close();
				System.out.println(result);
				return result;
			}
		} catch (MalformedURLException var10) {
			var10.printStackTrace();
			return null;
		} catch (IOException var11) {
			var11.printStackTrace();
			return null;
		}
	}

}
