package org.appsugar.data.mongo;

import java.io.InputStream;

import org.appsugar.data.mongo.util.MongoDbUtils;
import org.appsugar.test.spring.SpringBaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class BaseRepositoryTest extends SpringBaseTest {
	public static final Logger log = LoggerFactory.getLogger(BaseRepositoryTest.class);

	private static volatile boolean loadedDB = false;
	private static String dataPath = "simple/data_mongo.json";
	private static final String propertyName = "refreshDb";

	public static void loadDBOnce(MongoTemplate operation) {
		if (loadedDB) {
			return;
		}
		synchronized (BaseRepositoryTest.class) {
			if (loadedDB) {
				return;
			}
			loadedDB = true;
		}
		if (!("true".equals(System.getProperty(propertyName)))) {
			log.debug("refresh db :  false");
			return;
		}
		log.debug("refresh db :  true");
		try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(dataPath)) {
			MongoDbUtils.importCollections(operation, in, true);
		} catch (Exception ex) {
			log.debug("import documents error ", ex);
		}
	}

	@Autowired
	public void setMongoOperation(MongoTemplate operation) {
		loadDBOnce(operation);
	}
}
