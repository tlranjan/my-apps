package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class DevPortalClientCommon {

	public <T> List<T> getModels(MongoTemplate mongoTemplate, Class<T> type) {
		return mongoTemplate.findAll(type);
	}

	public <T> List<T> getModelsByKeyValue(MongoTemplate mongoTemplate, Class<T> type, String key, String value) {
		return mongoTemplate.find(new Query(Criteria.where(key).is(value)), type);
	}

	public <T> Boolean deleteModel(MongoTemplate mongoTemplate, Class<T> type, T model) {
		return mongoTemplate.remove(model).getDeletedCount() > 0 ? true : false;
	}

	public <T> Boolean deleteModelById(MongoTemplate mongoTemplate, Class<T> type, String id) {
		return mongoTemplate.remove(new Query(Criteria.where("id").is(id)), type).getDeletedCount() > 0 ? true : false;
	}

	public <T> Boolean deleteModelByName(MongoTemplate mongoTemplate, Class<T> type, String name) {
		return mongoTemplate.remove(new Query(Criteria.where("name").is(name)), type).getDeletedCount() > 0 ? true : false;
	}

}
