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
import com.dev.portal.dao.client.VsphereCatalogDaoClient;
import com.dev.portal.models.VsphereCatalogModel;

@RestController
@RequestMapping(AppURN.CATALOG_ADMIN_USER)
public class VsphereCatalog {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getallvspherecatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereCatalogModel> getAllVsphereCatalog() {
        return new VsphereCatalogDaoClient(mongoTemplate).getAllVsphereCatalogModel();
    }

    @RequestMapping(value = "/addvspherecatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public VsphereCatalogModel addVsphereCatalog(@RequestBody VsphereCatalogModel vsphereCatalogModel) {
        return new VsphereCatalogDaoClient(mongoTemplate).addOrUpdateVsphereCatalogModel(vsphereCatalogModel);
    }

    @RequestMapping(value = "/updatevspherecatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public VsphereCatalogModel updateVsphereCatalog(@RequestBody VsphereCatalogModel vsphereCatalogModel) {
        return new VsphereCatalogDaoClient(mongoTemplate).addOrUpdateVsphereCatalogModel(vsphereCatalogModel);
    }

    @RequestMapping(value = "/deletevspherecatalog", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteVsphereCatalog(@RequestParam String id) {
        return new VsphereCatalogDaoClient(mongoTemplate).deleteVsphereCatalogModel(id);
    }

}
