package sn.mas.result;

import java.util.List;

public class QueryUSSDResult extends BaseResult {
	
	private int errorCode;
	private List<QueryUSSDResultItem> reply;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public List<QueryUSSDResultItem> getReply() {
		return reply;
	}
	public void setReply(List<QueryUSSDResultItem> reply) {
		this.reply = reply;
	}

}
