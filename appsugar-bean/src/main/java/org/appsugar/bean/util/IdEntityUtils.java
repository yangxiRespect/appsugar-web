package org.appsugar.bean.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.appsugar.bean.entity.GenericIdEntity;

/** 
 * 基础实体辅助
 * @author NewYoung
 * 2016年2月25日下午6:53:14
 */
public class IdEntityUtils {

	/**
	 * 获取每一个实体的id
	 */
	public static final <ID extends Serializable> List<ID> getIdList(Iterable<GenericIdEntity<ID>> entities) {
		List<ID> idList = new ArrayList<>();
		for (GenericIdEntity<ID> entity : entities) {
			idList.add(entity.identification());
		}
		return idList;
	}
}
