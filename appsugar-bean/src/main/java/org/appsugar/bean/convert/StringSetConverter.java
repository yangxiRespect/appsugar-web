package org.appsugar.bean.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 把Set<String> 当做column存储提高性能
 * @author NewYoung
 *
 */
@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

	@Override
	public String convertToDatabaseColumn(Set<String> set) {
		if (set == null) {
			return null;
		}
		return String.join(",", set);
	}

	@Override
	public Set<String> convertToEntityAttribute(String joined) {
		if (joined == null || joined.isEmpty()) {
			return new HashSet<>();
		}
		return new HashSet<>(Arrays.asList(joined.split(",")));
	}

}
