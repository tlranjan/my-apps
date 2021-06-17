//mongeez formatted javascript
//changeset jyoti:v1_mongo_schema
db.userAuthModel.save({ "_id" : "admin_user", "_class" : "com.dev.portal.models.UserAuthModel", "username" : "admin", "password" : "admin" });
db.userDetailsModel.save({ "_id" : "admin_details", "_class" : "com.dev.portal.models.UserDetailsModel", "username" : "admin", "organizationName" : "default", "roles" : [ "ROLE_ADMIN" ] });
db.organizationDetailsModel.save({ "_id" : "default_organization", "_class" : "com.dev.portal.models.OrganizationDetailsModel", "organizationName" : "default", "organizationOwner" : "admin" });