package sn.mas.result;

public class BaseResult {
	protected String jsonValueString;
	
	public BaseResult() {
		
	}

	public String getJsonString() {
		return this.jsonValueString;
	}
	
	public void setJsonString(String jsonString) {
		this.jsonValueString = jsonString;
	}
	
}
