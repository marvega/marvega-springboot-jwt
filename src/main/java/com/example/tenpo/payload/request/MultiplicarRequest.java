package com.example.tenpo.payload.request;

public class MultiplicarRequest {


	private Integer x;
	
	private Integer y;

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "MultiplicarRequest [x=" + x + ", y=" + y + "]";
	}
}
