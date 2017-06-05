package org.appsugar.data.redis;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.StringIdEntity;
import org.appsugar.data.elasticsearch.ElasticSearchIdEntityRepository;
import org.appsugar.test.data.SampleDataSet;
import org.appsugar.test.data.xml.XmlSampleDataSet;
import org.appsugar.test.spring.SpringBaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * redisTemplate测试
 * @author NewYoung
 * 2016年10月31日下午2:44:35
 */
@ContextConfiguration(classes = ApplicationConfiguration.class)
public abstract class BaseElasticSearchTest extends SpringBaseTest {
	public static final Logger log = LoggerFactory.getLogger(BaseElasticSearchTest.class);
	private static volatile boolean loadedDB = false;
	private static String dataPath = "data/sample-data.xml";
	private static final String propertyName = "refreshDb";
	@SuppressWarnings("hiding")
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private static void loadDBOnce(ApplicationContext ctx) {
		if (loadedDB) {
			return;
		}
		synchronized (BaseElasticSearchTest.class) {
			if (loadedDB) {
				return;
			}
			loadedDB = true;
		}
		if (!("true".equals(System.getProperty(propertyName)))) {
			log.debug("skip db refresh");
			return;
		}
		log.debug("Prepare to refresh db");
		try {
			loadDb(ctx);
		} catch (Exception ex) {
			log.debug("load db error ", ex);
		}
	}

	private static void loadDb(ApplicationContext ctx) throws Exception {
		SampleDataSet dataSet = new XmlSampleDataSet(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(dataPath));
		DateTimeConverter dtConverter = new DateConverter();
		dtConverter.setPattern("yyyy-MM-dd HH:mm:ss");
		ConvertUtils.register(dtConverter, Date.class);
		for (String className : dataSet.orderedDataSetNameList()) {
			Class<?> entityClass = Class.forName(className);
			try {
				ElasticSearchIdEntityRepository<StringIdEntity, String, StringIdEntityCondition> repository = findComponent(
						ctx, ElasticSearchIdEntityRepository.class, entityClass);
				log.debug("drop all {}", entityClass);
				repository.deleteAll();
				List entityList = dataSet.getDataListByDataSetName(className).stream().map(e -> {
					try {
						return copyBean(e, entityClass.newInstance());
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}).collect(Collectors.toList());
				repository.save(entityList);
			} catch (Exception ex) {
				log.debug("{} repository did not found", entityClass, ex);
			}
		}
	}

	public static <T> T copyBean(Object source, T target) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(target, source);
		return target;
	}

	@Autowired
	public void setApplication(ApplicationContext context) {
		loadDBOnce(context);
	}
}
