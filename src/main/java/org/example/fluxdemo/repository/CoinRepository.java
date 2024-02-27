package org.example.fluxdemo.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.example.fluxdemo.entity.CoinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CoinRepository {
    private final MongoCollection<Document> collection;

    public CoinRepository(@Autowired MongoClient mongoClient) {
        String dbName = "test";
        String collectionName = "coin";
        this.collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
    }

    public void insert(CoinVo coinVo) {
        Document document = new Document()
                .append("type", coinVo.getType())
                .append("name", coinVo.getName());
        collection.insertOne(document);
    }

    public void deleteByType(String type) {
        collection.deleteOne(Filters.eq("type", type));
    }

    public CoinVo updateByType(CoinVo coinVo) {
        collection.updateOne(Filters.eq("type", coinVo.getType()),
                Updates.combine(
                        Updates.set("name", coinVo.getName()),
                        Updates.set("type", coinVo.getType())
                ));
        return coinVo;
    }

    public CoinVo queryByType(String type) {
        Document document = collection.find(Filters.eq("type", type)).first();
        CoinVo vo = new CoinVo();
        if (document != null) {
            vo.setType(document.getString("type"));
            vo.setName(document.getString("name"));
        }
        return vo;
    }
}
