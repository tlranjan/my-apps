package com.dev.portal.services.catalogadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.AwsCatalogDaoClient;
import com.dev.portal.models.AwsCatalogModel;

@RestController
@RequestMapping(AppURN.CATALOG_ADMIN_USER)
public class AwsCatalog {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getallawscatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsCatalogModel> getAllAwsCatalog() {
        return new AwsCatalogDaoClient(mongoTemplate).getAllAwsCatalogModel();
    }

    @RequestMapping(value = "/addawscatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AwsCatalogModel addAwsCatalog(@RequestBody AwsCatalogModel awsCatalogModel) {
        return new AwsCatalogDaoClient(mongoTemplate).addOrUpdateAwsCatalogModel(awsCatalogModel);
    }

    @RequestMapping(value = "/updateawscatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AwsCatalogModel updateAwsCatalog(@RequestBody AwsCatalogModel awsCatalogModel) {
        return new AwsCatalogDaoClient(mongoTemplate).addOrUpdateAwsCatalogModel(awsCatalogModel);
    }

    @RequestMapping(value = "/deleteawscatalog", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteAwscatalog(@RequestParam String id) {
        return new AwsCatalogDaoClient(mongoTemplate).deleteAwsCatalogModel(id);
    }

}
