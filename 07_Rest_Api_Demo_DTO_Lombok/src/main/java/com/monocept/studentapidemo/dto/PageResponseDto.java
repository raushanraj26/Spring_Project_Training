package com.monocept.studentapidemo.dto;

import java.util.List;

public class PageResponseDto<T> {
	private List<T> content;
	private int pageNumber;
	private int pageSize;
	private int totalPage;
	private boolean lastpage;
	private int totalCount;

	public PageResponseDto() {
	}

	public PageResponseDto(List<T> content, int pageNumber, int pageSize, int totalPage, boolean lastpage,
			int totalCount) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.lastpage = lastpage;
		this.totalCount = totalCount;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isLastpage() {
		return lastpage;
	}

	public void setLastpage(boolean lastpage) {
		this.lastpage = lastpage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long l) {
		this.totalCount = (int) l;
	}

	@Override
	public String toString() {
		return "PageResponseDto [content=" + content + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalPage=" + totalPage + ", lastpage=" + lastpage + ", totalCount=" + totalCount + "]";
	}
	

}
