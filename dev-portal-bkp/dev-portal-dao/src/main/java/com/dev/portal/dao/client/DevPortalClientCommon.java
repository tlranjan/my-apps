package com.dev.portal.dao.client;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class DevPortalClientCommon {

    public <T> T getModelById(MongoTemplate mongoTemplate, Class<T> type, String id) {
        return mongoTemplate.find(new Query(Criteria.where("id").is(id)), type).get(0);
    }

    public <T> Boolean deleteModelById(MongoTemplate mongoTemplate, Class<T> type, String id) {
        return mongoTemplate.remove(getModelById(mongoTemplate, type, id)).getDeletedCount() > 0 ? true : false;
    }

}
