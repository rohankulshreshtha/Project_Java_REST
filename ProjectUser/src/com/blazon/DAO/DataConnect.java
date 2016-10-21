package com.blazon.DAO;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
 
public class DataConnect {
	
	 private static DBCollection table;  
     
     //JDBCSingleton prevents the instantiation from any other class.  
       private  DataConnect() {}
		
         public static DBCollection getCollection() {    
          if (table==null)  
          {  
        	  try { 		
        		  MongoClient mongo = new MongoClient("localhost", 27017);
        		//  MongoClient mongo = new MongoClient("213.136.88.136", 27017);
                        DB db = mongo.getDB("test");  
                        table = db.getCollection("userdata");
                        return table;
                   }  
              catch (Exception ex) {
                                   System.out.println("Database.getConnection() Error -->"
                                   + ex.getMessage());
                                   return null;
                                   }
           }
           else
               return table;
                         
             
         }
         /*
    public  DBCollection getConnection() {
        try {
        	MongoClient mongo = new MongoClient("localhost", 27017);
        	DB db = mongo.getDB("test");
        	DBCollection table = db.getCollection("userdata");
        	return table;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }
    */
    }
