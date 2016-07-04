package org.appsugar.bean.domain;

import java.io.Serializable;

/**
 * key value
 * @author NewYoung
 * 2016年2月25日下午3:39:49
 */
public class KeyValue<K, V> implements Serializable {

	private static final long serialVersionUID = 161516760618857689L;

	protected K key;

	protected V value;

	public KeyValue() {
		super();
	}

	public KeyValue(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyValue [key=").append(key).append(", value=").append(value).append("]");
		return builder.toString();
	}

}
