package org.appsugar.data.mongo.util;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * TODO  move this to appsugar-test
 * @author shenliuyang
 * @email  shenliuyang@gmail.com
 *
 * 2015年8月6日下午5:40:03
 */
public class MongoDbUtils {

	private static final Logger logger = LoggerFactory.getLogger(MongoDbUtils.class);

	/**
	 * 往mongodb中导入数据
	 * @param template
	 * @param in
	 * @param dropBeforeInsert 插入文档前,是否删除对于collection
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static void importCollections(MongoTemplate template, InputStream in, boolean dropBeforeInsert)
			throws JsonProcessingException, IOException {
		logger.debug("import  json data start");
		ObjectMapper m = new ObjectMapper();
		JsonNode root = m.readTree(in);
		Assert.isTrue(root.isArray(), "Root node must be an array");
		ArrayNode arrayNode = (ArrayNode) root;
		//循环导入表
		arrayNode.forEach(n -> importCollection(template, n, dropBeforeInsert));
		logger.debug("import  json data end");
	}

	private static void importCollection(MongoTemplate template, JsonNode jsonNode, boolean dropBeforeInsert) {
		Assert.isTrue(jsonNode.has("className"), "Field not found by name : className " + jsonNode.toString());
		Assert.isTrue(jsonNode.has("items"), "Field not found by name : items " + jsonNode.toString());
		String className = jsonNode.get("className").textValue();
		try {
			Class<?> clazz = Class.forName(className);
			JavaType type = TypeFactory.defaultInstance().constructType(clazz);
			JsonNode items = jsonNode.get("items");
			Assert.isTrue(items.isArray(), "items must be array " + items.toString());
			String collectionName = template.getCollectionName(clazz);
			if (dropBeforeInsert) {
				logger.debug("drop collection {} ", collectionName);
				template.dropCollection(clazz);
			}
			logger.debug("import collection {} start", collectionName);
			//循环导入每条文档
			((ArrayNode) items).forEach(n -> importDocument(template, type, n));
			logger.debug("import collection {} end", collectionName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static final ObjectMapper mapper = new ObjectMapper();

	private static void importDocument(MongoTemplate template, JavaType type, JsonNode jsonNode) {
		try {
			Object item = mapper.readValue(jsonNode.toString(), type);
			template.insert(item);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
