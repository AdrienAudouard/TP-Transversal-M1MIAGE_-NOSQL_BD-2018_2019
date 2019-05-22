package com.miage.bigdata.daos.clientsDao;

public class KeyValueClientDao extends ClientDao {

    public KeyValueClientDao() {
        this.host = "https://db-keyvalue.documents.azure.com:443/";
        this.masterKey = "cowNe6kZJssatZ7a41CJE8CTRAKyGpMsdUjcq5xXyxGSmD9Pn6yproGIZ02gd93udHLSvqTav94K0VNsEvcdGQ==";
    }

}
