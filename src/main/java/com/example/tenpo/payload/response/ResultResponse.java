package com.example.tenpo.payload.response;

public class ResultResponse {

	private Integer response;

	public Integer getResponse() {
		return response;
	}

	public void setResponse(Integer response) {
		this.response = response;
	}

	public ResultResponse(Integer response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResultResponse [response=" + response + "]";
	}
}
