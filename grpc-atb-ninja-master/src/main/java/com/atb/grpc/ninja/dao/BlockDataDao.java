package com.atb.grpc.ninja.dao;

import org.bson.Document;
import org.springframework.stereotype.Component;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class BlockDataDao {

	private MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private MongoDatabase database = mongoClient.getDatabase("dbatb");
    private MongoCollection<Document> collection = database.getCollection("grpcblockchain");
    
    
    public void save(Document document)
    {
    collection.insertOne(document);
    }
    
    public String getLinkHash()
    {
    return collection
    				.find()
						.sort(new BasicDBObject("_id", -1))
							.first()
								.getString("hash");
    
   
    }
    
}
