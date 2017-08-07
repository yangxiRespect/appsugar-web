package org.appsugar.bean.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 把List<String> 当做column存储提高性能
 * @author NewYoung
 *
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> list) {
		if (list == null) {
			return null;
		}
		return String.join(",", list);
	}

	@Override
	public List<String> convertToEntityAttribute(String joined) {
		if (joined == null || joined.isEmpty()) {
			return new ArrayList<>();
		}
		return new ArrayList<>(Arrays.asList(joined.split(",")));
	}

}
