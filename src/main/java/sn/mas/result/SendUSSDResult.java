package sn.mas.result;

import java.util.List;

public class SendUSSDResult extends BaseResult {
	
	private int errorCode;
	private List<SendUSSDResultItem> result;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public List<SendUSSDResultItem> getResult() {
		return result;
	}
	public void setResult(List<SendUSSDResultItem> result) {
		this.result = result;
	}
	
}
