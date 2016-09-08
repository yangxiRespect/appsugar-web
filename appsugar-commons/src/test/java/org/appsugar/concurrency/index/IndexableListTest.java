package org.appsugar.concurrency.index;

import java.util.Collection;

import org.appsugar.commons.index.Index;
import org.appsugar.commons.index.IndexableList;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * 可索引list测试
 * @author NewYoung
 * 2016年9月8日上午11:20:43
 */
public class IndexableListTest extends TestCase {

	@Test
	public void testIndex() {
		IndexableList<String> list = IndexableList.create();
		Index<Integer, String> lengthIndex = list.uniqueIndex(String::length);
		Index<Character, Collection<String>> firstCharIndex = list.multipleIndex(e -> e.charAt(0));
		list.add("abc");
		list.add("acbd");
		Assert.assertTrue(lengthIndex.ifPresent(3));
		Assert.assertTrue(lengthIndex.ifPresent(4));
		Assert.assertTrue(firstCharIndex.get('a').size() == 2);
		list.remove(1);
		Assert.assertFalse(lengthIndex.ifPresent(4));
		Assert.assertTrue(firstCharIndex.get('a').size() == 1);
	}
}
