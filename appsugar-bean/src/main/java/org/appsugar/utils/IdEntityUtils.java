package org.appsugar.utils;

import java.util.ArrayList;
import java.util.List;

import org.appsugar.entity.IdEntity;

/**
 * 基础实体辅助
 * @author NewYoung
 * 2016年2月25日下午6:53:14
 */
public class IdEntityUtils {

	/**
	 * 获取每一个实体的id
	 */
	public static final List<Long> getIdList(Iterable<? extends IdEntity> entities) {
		List<Long> idList = new ArrayList<>();
		for (IdEntity entity : entities) {
			idList.add(entity.getId());
		}
		return idList;
	}
}
