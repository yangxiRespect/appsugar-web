package org.appsugar.data.mongo;

import org.apache.commons.lang3.StringUtils;

/**
 * 路径类
 * @author NewYoung
 * 2016年11月4日下午9:22:18
 */
public class Paths {
	/**
	 * 组合路径
	 * <pre>
	 * paths("1","2","3") == "1.2.3"
	 * </pre> 
	 */
	public static final String join(String... pathName) {
		return StringUtils.join(pathName, ".");
	}
}
