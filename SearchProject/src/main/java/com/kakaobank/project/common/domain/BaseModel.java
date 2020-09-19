package com.kakaobank.project.common.domain;

public class BaseModel {
	private String resultCode;
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		return "BaseModel [resultCode=" + resultCode + "]";
	}
}
