package org.appsugar.concurrency.index;

import java.util.Collection;

import org.appsugar.commons.index.Index;
import org.appsugar.commons.index.IndexableMap;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * set索引数据结构测试
 * @author NewYoung
 * 2016年9月8日下午12:49:18
 */
public class IndexableMapTest extends TestCase {

	@Test
	public void testIndex() {
		IndexableMap<Integer, String> map = IndexableMap.create();
		Index<Integer, String> lengthIndex = map.uniqueIndex(String::length);
		Index<Character, Collection<String>> firstCharIndex = map.multipleIndex(e -> e.charAt(0));
		map.put(1, "abc");
		map.put(2, "acbd");
		Assert.assertTrue(lengthIndex.ifPresent(3));
		Assert.assertTrue(lengthIndex.ifPresent(4));
		Assert.assertTrue(firstCharIndex.get('a').size() == 2);
		map.remove(2);
		Assert.assertFalse(lengthIndex.ifPresent(4));
	}
}
