package sn.mas.sms;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import sn.mas.http.ArrayUtil;
import sn.mas.http.HttpUtil;
import sn.mas.result.QueryUSSDResult;
import sn.mas.result.QueryUSSDResultItem;
import sn.mas.result.SendUSSDResult;
import sn.mas.result.SendUSSDResultItem;

public class Gateway {

	private String host;
	private int port;
	private String authUser;
	private String authPassword;

	public Gateway(String host, int port, String authUser, String authPassword) {
		this.host = host;
		this.port = port;
		this.authUser = authUser;
		this.authPassword = authPassword;
	}

	
	/**
	 * Send USSD
	 * when exception happens ,return null
	 * @param text 
	 * 			text to send USSD
	 * @param ports
	 * 			ports to send
	 * @param command
	 * 				(send/cancel) if you not set, default is send
	 * @return
	 */
	public SendUSSDResult sendUSSD(String text, int[] ports, String command) {
		try {
			String e = "http://gateway_ip/api/send_ussd".replaceFirst(
					"gateway_ip", this.host);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("text", text);

			if (ports != null) {
				jsonObj.put("port", new JSONArray(ports));
			}
			if (command != null) {
				jsonObj.put("command", command);
			}

			String result = HttpUtil.sendPostRequest(e, jsonObj.toString());
			System.out.println("result = " + result);
			if (result == null) {
				return null;
			} else {
				JSONObject resultObj = new JSONObject(result);
				SendUSSDResult sendUSSDResult = new SendUSSDResult();
				List<SendUSSDResultItem> resultList = new ArrayList<SendUSSDResultItem>();

				sendUSSDResult.setJsonString(result);
				sendUSSDResult.setErrorCode(resultObj.getInt("result"));
				sendUSSDResult.setResult(resultList);

				if (resultObj.has("result")) {
					JSONArray smsarray = (JSONArray) resultObj.get("result");
					for (int i = 0; i < smsarray.length(); i++) {
						JSONObject resultItem = (JSONObject) smsarray.get(i);
						SendUSSDResultItem sms = new SendUSSDResultItem();
						if (resultItem.has("status")) {
							sms.setStatus(resultItem.getInt("status"));
						}
						if (resultItem.has("port")) {
							sms.setPort(resultItem.getInt("port"));
						}
						resultList.add(sms);
					}
				}
				return sendUSSDResult;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * if exception happens ,return null
	 * Query USSD RESULT
	 * @param ports
	 * 			ports to query
	 * @return
	 */
	public QueryUSSDResult queryUSSD(int[] ports) {
		try {
			String url = "http://gateway_ip/api/query_ussd_reply".replaceFirst("gateway_ip", this.host);
			String param = "";
			
			// add port parameter
			if (ports != null) {
				param = param + "port=" +ArrayUtil.intArrayJoin(ports, ",") + "&";
			}
			
			// delete tail &
			if (param.length() > 1) {
				param = param.substring(0, param.length() - 1);
			}
			
			String result = HttpUtil.sendGetRequest(url, param);
			if (result == null) {
				return null;
			} else {
				JSONObject resultObj = new JSONObject(result);
				QueryUSSDResult queryUssdResult = new QueryUSSDResult();
				List<QueryUSSDResultItem> replyList = new ArrayList<QueryUSSDResultItem>();

				queryUssdResult.setJsonString(result);
				queryUssdResult.setErrorCode(resultObj.getInt("error_code"));
				queryUssdResult.setReply(replyList);

				if (resultObj.has("reply")) {
					JSONArray smsarray = (JSONArray) resultObj.get("reply");
					for (int i = 0; i < smsarray.length(); i++) {
						JSONObject resultItem = (JSONObject) smsarray.get(i);
						QueryUSSDResultItem sms = new QueryUSSDResultItem();
						if (resultItem.has("text")) {
							sms.setText(resultItem.getString("text"));
						}
						if (resultItem.has("port")) {
							sms.setPort(resultItem.getInt("port"));
						}
						replyList.add(sms);
					}
				}
				return queryUssdResult;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
