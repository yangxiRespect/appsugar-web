package org.appsugar.bean.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 * @author NewYoung
 * 2016年5月6日下午3:41:54
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1741187794637422865L;

	private List<T> content;

	private Pageable pageable;

	private int total;

	public Page() {
		super();
	}

	public Page(List<T> content, Pageable pageable, int total) {
		super();
		this.content = content;
		this.pageable = pageable;
		this.total = total;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public long getTotal() {
		return total;
	}

	/**
	 * 获取当前页
	 */
	public int getPageNumber() {
		return pageable.getPageNumber();
	}

	/**
	 * 获取每页条数
	 */
	public int getPageSize() {
		return pageable.getPageSize();
	}

	/**
	 * 获取总页数
	 */
	public int getTotalPage() {
		return (int) Math.ceil((double) total / (double) getPageSize());
	}

	/**
	 * 是否最后一页
	 */
	public boolean isLast() {
		return !hasNext();
	}

	/**
	 * 是否有下一页
	 */
	public boolean hasNext() {
		return getPageNumber() + 1 < getTotalPage();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [content=").append(content).append(", pageable=").append(pageable).append(", total=")
				.append(total).append("]");
		return builder.toString();
	}

}
