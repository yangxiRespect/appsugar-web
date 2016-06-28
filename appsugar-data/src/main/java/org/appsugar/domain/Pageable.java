package org.appsugar.domain;

import java.io.Serializable;

/**
 * 分页查询条件
 * @author NewYoung
 * 2016年5月6日下午3:41:42
 */
public class Pageable implements Serializable {

	private static final long serialVersionUID = 5544898701574744188L;

	/**
	 * 每页条数
	 */
	private int pageSize;
	/**
	 * 当前页
	 * 起始页0
	 */
	private int pageNumber;

	/**
	 * 排序
	 */
	private Sort sort;

	public Pageable() {
		this(25, 0, null);
	}

	public Pageable(int pageSize, int pageNumber) {
		this(pageSize, pageNumber, null);
	}

	public Pageable(int pageSize, int pageNumber, Sort sort) {
		setPageSize(pageSize);
		setPageNumber(pageNumber);
		this.sort = sort;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			throw new IllegalArgumentException("pageSize must > 0  pageSize is " + pageSize);
		}
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("pageNumber must >= 0  pageNumber is " + pageNumber);
		}
		this.pageNumber = pageNumber;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pageable [pageSize=").append(pageSize).append(", pageNumber=").append(pageNumber)
				.append(", sort=").append(sort).append("]");
		return builder.toString();
	}

}
