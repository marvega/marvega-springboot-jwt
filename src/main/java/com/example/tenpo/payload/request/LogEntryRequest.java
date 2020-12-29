package com.example.tenpo.payload.request;

public class LogEntryRequest {


	private Integer page;
	
	private Integer size;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "LogEntryRequest [page=" + page + ", size=" + size + "]";
	}

}
