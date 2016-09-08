package org.appsugar.concurrency.index;

import java.util.Collection;

import org.appsugar.commons.index.Index;
import org.appsugar.commons.index.IndexableSet;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * set索引数据结构测试
 * @author NewYoung
 * 2016年9月8日下午12:49:18
 */
public class IndexableSetTest extends TestCase {

	@Test
	public void testIndex() {
		IndexableSet<String> set = IndexableSet.create();
		Index<Integer, String> lengthIndex = set.uniqueIndex(String::length);
		Index<Character, Collection<String>> firstCharIndex = set.multipleIndex(e -> e.charAt(0));
		set.add("abc");
		set.add("acbd");
		Assert.assertTrue(lengthIndex.ifPresent(3));
		Assert.assertTrue(lengthIndex.ifPresent(4));
		Assert.assertTrue(firstCharIndex.get('a').size() == 2);
		set.remove("acbd");
		Assert.assertFalse(lengthIndex.ifPresent(4));
	}
}
