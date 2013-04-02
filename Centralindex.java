    /**
    * Central Index
    *
    * The Central Index JAVA Library
    *
    * @package 	CentralIndex
    * @author  	Paul Rutland
    * @link    	http://centralindex.com
    * @since   	Version 1.0
  */

  import org.apache.http.HttpEntity;
  import org.apache.http.HttpResponse;
	import org.apache.http.client.HttpClient;
	import org.apache.http.client.methods.*;
	import org.apache.http.entity.InputStreamEntity;
	import org.apache.http.impl.client.DefaultHttpClient;
	import org.apache.http.util.EntityUtils;
	import java.util.*;
	import org.apache.http.NameValuePair;
	import org.apache.http.message.BasicNameValuePair;
	import org.apache.http.client.entity.UrlEncodedFormEntity;
	import org.apache.http.client.ResponseHandler;
	import java.lang.String;
	import java.net.URLEncoder;
  
  
  public class Centralindex {
    
    // the endpoint of the central index API
    final String  API_URL = "http://api.centralindex.com/v1";
    
    // store the user's API key and whether debuggin is required
    protected String apikey;
    protected boolean debugMode;
    
    /**
     * Constructor - store the api key in the class
     *
     *  @param theapikey - the user's API key
     *  @param debug - whether to output debugging or not
     *  @return - the data from the api
    */ 
    
    Centralindex (String key, boolean debug) {
      apikey = key;
      debugMode = debug;
    
    }
    
    /**
     * Perform curl request
     *
     *  @param method - the HTTP method to do
     *  @param path - the relative path to visit
     *  @param data - an array of parameters to pass
     *  @return - the data from the api
    */
    
    
    private String doCurlPost (String path, Hashtable params) throws Exception {
     
       
       String retval = "";
       HttpClient httpclient = new DefaultHttpClient();

       try {
       
            String theUrl = API_URL+path;
                  
            System.out.println("URL " + theUrl);
            HttpPost httppost = new HttpPost(theUrl);
						
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();

            Set<String> keys = params.keySet();	
            for(String key: keys){
	            nvps.add(new BasicNameValuePair(key, (String)params.get(key)));
            }
            
            
            httppost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response = httpclient.execute(httppost);
         
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {
                retval = EntityUtils.toString(resEntity);
            }
            EntityUtils.consume(resEntity);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        
        
        return retval;
    
    
    }
    
    private String doCurlGet(String path, Hashtable params) throws Exception {
    
    
       HttpClient httpclient = new DefaultHttpClient();
       
       String retval = "";
       
       try {
       
            String theUrl = API_URL+path;
                  
            Set<String> keys = params.keySet();	
            int count = 0;
            for(String key: keys){

	            if (count==0) {
		            theUrl+="?"+key+"="+URLEncoder.encode((String)params.get(key));
	            } else {
		            theUrl+="&"+key+"="+URLEncoder.encode((String)params.get(key));
	            }
	
	            count++;
            }

            System.out.println("URL " + theUrl);

            HttpGet httpget = new HttpGet(theUrl);

            HttpResponse response = httpclient.execute(httpget);
         
            HttpEntity resEntity = response.getEntity();
               
            if (resEntity != null) {
                retval = EntityUtils.toString(resEntity);
            }
            EntityUtils.consume(resEntity);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        
        
        return retval;
    
    }
    
    private String doCurlPut (String path, Hashtable params) throws Exception {
    
    
       HttpClient httpclient = new DefaultHttpClient();
       
       String retval = "";
       
       try {
      	
					
         String theUrl = API_URL+path;
			
								
         HttpPut httpput = new HttpPut(theUrl);
												
         List <NameValuePair> nvps = new ArrayList <NameValuePair>();

         Set<String> keys = params.keySet();	
         for(String key: keys){

	        System.out.println("Response content " + params.get(key));
	        nvps.add(new BasicNameValuePair(key, (String)params.get(key)));
         }


          httpput.setEntity(new UrlEncodedFormEntity(nvps));

          HttpResponse response = httpclient.execute(httpput);

          HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {
                retval = EntityUtils.toString(resEntity);
            }
            EntityUtils.consume(resEntity);
            
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        
        
        return retval;
    
    
    }
    
     private String doCurlDelete(String path, Hashtable params) throws Exception {
    
    
       HttpClient httpclient = new DefaultHttpClient();
       
       String retval = "";
       
       try {
      	
					
         String theUrl = API_URL+path;
			
								
         Set<String> keys = params.keySet();	
         int count = 0;
         for(String key: keys){

	         if (count==0) {
		         theUrl+="?"+key+"="+URLEncoder.encode((String)params.get(key));
	         } else {
		         theUrl+="&"+key+"="+URLEncoder.encode((String)params.get(key));
	         }
	
	         count++;
         }


         HttpDelete httpdelete = new HttpDelete(theUrl);


         HttpResponse response = httpclient.execute(httpdelete);
         
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                retval = EntityUtils.toString(resEntity);    
            }
            EntityUtils.consume(resEntity);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        
        
        return retval;
    
    
    }
    
    
    public String doCurl(String method,String path,Hashtable params) throws Exception {
    
    
        String retval = "";
        
        params.put("api_key", this.apikey);

        try {
        
          if (method=="POST") {
            retval = this.doCurlPost(path,params);  
          } else if (method=="GET") {
            retval = this.doCurlGet(path,params); 
          } else if (method=="PUT") {
            retval = this.doCurlPut(path,params); 
          } else {
            retval = this.doCurlDelete(path,params); 
          }
          
  
        } finally {
          
        }
        
        return retval;
      }
      
      

      
  /**
   * Confirms that the API is active, and returns the current version number
   *
   *  @return - the data from the api
  */
  public String  getStatus() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/status",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the project logo, the symbol of the Wolf
   *
   *  @param a
   *  @param b
   *  @param c
   *  @param d
   *  @return - the data from the api
  */
  public String  getLogo(String a,String b,String c,String d) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("a", a);
    	params.put("b", b);
    	params.put("c", c);
    	params.put("d", d);
    	retval = this.doCurl("GET","/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the project logo, the symbol of the Wolf
   *
   *  @param a
   *  @return - the data from the api
  */
  public String  putLogo(String a) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("a", a);
    	retval = this.doCurl("PUT","/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Uploads a CSV file of known format and bulk inserts into DB
   *
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postEntityBulkCsv(String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/entity/bulk/csv",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Shows the current status of a bulk upload
   *
   *  @param upload_id
   *  @return - the data from the api
  */
  public String  getEntityBulkCsvStatus(String upload_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("upload_id", upload_id);
    	retval = this.doCurl("GET","/entity/bulk/csv/status",params);
    } finally { 
    }
    return retval;
  }


  /**
   * This entity isn't really supported anymore. You probably want PUT /business. Only to be used for testing.
   *
   *  @param type
   *  @param scope
   *  @param country
   *  @param trust
   *  @param our_data
   *  @return - the data from the api
  */
  public String  putEntity(String type,String scope,String country,String trust,String our_data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("type", type);
    	params.put("scope", scope);
    	params.put("country", country);
    	params.put("trust", trust);
    	params.put("our_data", our_data);
    	retval = this.doCurl("PUT","/entity",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetches the documents that match the given masheryid and supplier_id
   *
   *  @param supplier_id - The Supplier ID
   *  @return - the data from the api
  */
  public String  getEntityBy_supplier_id(String supplier_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("supplier_id", supplier_id);
    	retval = this.doCurl("GET","/entity/by_supplier_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param what
   *  @param entity_name
   *  @param where
   *  @param per_page
   *  @param page
   *  @param longitude
   *  @param latitude
   *  @param country
   *  @param language
   *  @return - the data from the api
  */
  public String  getEntitySearch(String what,String entity_name,String where,String per_page,String page,String longitude,String latitude,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("entity_name", entity_name);
    	params.put("where", where);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("longitude", longitude);
    	params.put("latitude", latitude);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/search",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param what - What to get results for. E.g. Plumber e.g. plumber
   *  @param where - The location to get results for. E.g. Dublin e.g. Dublin
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @return - the data from the api
  */
  public String  getEntitySearchWhatBylocation(String what,String where,String per_page,String page,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("where", where);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/search/what/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param what - What to get results for. E.g. Plumber e.g. plumber
   *  @param latitude_1 - Latitude of first point in bounding box e.g. 53.396842
   *  @param longitude_1 - Longitude of first point in bounding box e.g. -6.37619
   *  @param latitude_2 - Latitude of second point in bounding box e.g. 53.290463
   *  @param longitude_2 - Longitude of second point in bounding box e.g. -6.207275
   *  @param per_page
   *  @param page
   *  @param country - A valid ISO 3166 country code e.g. ie
   *  @param language
   *  @return - the data from the api
  */
  public String  getEntitySearchWhatByboundingbox(String what,String latitude_1,String longitude_1,String latitude_2,String longitude_2,String per_page,String page,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("latitude_1", latitude_1);
    	params.put("longitude_1", longitude_1);
    	params.put("latitude_2", latitude_2);
    	params.put("longitude_2", longitude_2);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/search/what/byboundingbox",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param who
   *  @param latitude_1
   *  @param longitude_1
   *  @param latitude_2
   *  @param longitude_2
   *  @param per_page
   *  @param page
   *  @param country
   *  @return - the data from the api
  */
  public String  getEntitySearchWhoByboundingbox(String who,String latitude_1,String longitude_1,String latitude_2,String longitude_2,String per_page,String page,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("latitude_1", latitude_1);
    	params.put("longitude_1", longitude_1);
    	params.put("latitude_2", latitude_2);
    	params.put("longitude_2", longitude_2);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	retval = this.doCurl("GET","/entity/search/who/byboundingbox",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param who - Company Name e.g. Starbucks
   *  @param where - The location to get results for. E.g. Dublin e.g. Dublin
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @return - the data from the api
  */
  public String  getEntitySearchWhoBylocation(String who,String where,String per_page,String page,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("where", where);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	retval = this.doCurl("GET","/entity/search/who/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param what - What to get results for. E.g. Plumber e.g. plumber
   *  @param per_page - Number of results returned per page
   *  @param page - The page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @return - the data from the api
  */
  public String  getEntitySearchWhat(String what,String per_page,String page,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/search/what",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param who - Company name e.g. Starbucks
   *  @param per_page - How many results per page
   *  @param page - What page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @return - the data from the api
  */
  public String  getEntitySearchWho(String who,String per_page,String page,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	retval = this.doCurl("GET","/entity/search/who",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param where - Location to search for results. E.g. Dublin e.g. Dublin
   *  @param per_page - How many results per page
   *  @param page - What page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @return - the data from the api
  */
  public String  getEntitySearchBylocation(String where,String per_page,String page,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("where", where);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/search/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param latitude_1
   *  @param longitude_1
   *  @param latitude_2
   *  @param longitude_2
   *  @param per_page
   *  @param page
   *  @param country
   *  @param language
   *  @return - the data from the api
  */
  public String  getEntitySearchByboundingbox(String latitude_1,String longitude_1,String latitude_2,String longitude_2,String per_page,String page,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("latitude_1", latitude_1);
    	params.put("longitude_1", longitude_1);
    	params.put("latitude_2", latitude_2);
    	params.put("longitude_2", longitude_2);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/search/byboundingbox",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities that are advertisers and return a random selection upto the limit requested
   *
   *  @param tag - The word or words the advertiser is to appear for in searches
   *  @param where - The location to get results for. E.g. Dublin
   *  @param limit - The number of advertisers that are to be returned
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @return - the data from the api
  */
  public String  getEntityAdvertisers(String tag,String where,String limit,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tag", tag);
    	params.put("where", where);
    	params.put("limit", limit);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/advertisers",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a whole entity to be pulled from the datastore by its unique id
   *
   *  @param entity_id - The unique entity ID e.g. 379236608286720
   *  @return - the data from the api
  */
  public String  getEntity(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/entity",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get all entiies claimed by a specific user
   *
   *  @param user_id - The unique user ID of the user with claimed entities e.g. 379236608286720
   *  @return - the data from the api
  */
  public String  getEntityBy_user_id(String user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	retval = this.doCurl("GET","/entity/by_user_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a list of available revisions to be returned by its entity id
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  getEntityRevisions(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/entity/revisions",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a specific revision of an entity to be returned by entity id and a revision number
   *
   *  @param entity_id
   *  @param revision_id
   *  @return - the data from the api
  */
  public String  getEntityRevisionsByRevisionID(String entity_id,String revision_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("revision_id", revision_id);
    	retval = this.doCurl("GET","/entity/revisions/byRevisionID",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Separates an entity into two distinct entities 
   *
   *  @param entity_id
   *  @param supplier_masheryid
   *  @param supplier_id
   *  @return - the data from the api
  */
  public String  postEntityUnmerge(String entity_id,String supplier_masheryid,String supplier_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("supplier_masheryid", supplier_masheryid);
    	params.put("supplier_id", supplier_id);
    	retval = this.doCurl("POST","/entity/unmerge",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetches the changelog documents that match the given entity_id
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  getEntityChangelog(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/entity/changelog",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Merge two entities into one
   *
   *  @param from
   *  @param to
   *  @return - the data from the api
  */
  public String  postEntityMerge(String from,String to) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from", from);
    	params.put("to", to);
    	retval = this.doCurl("POST","/entity/merge",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Force refresh of search indexes
   *
   *  @return - the data from the api
  */
  public String  getToolsReindex() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/tools/reindex",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Supply an entity and an object within it (e.g. a phone number), and retrieve a URL that allows the user to report an issue with that object
   *
   *  @param entity_id - The unique Entity ID e.g. 379236608286720
   *  @param gen_id - A Unique ID for the object you wish to report, E.g. Phone number e.g. 379236608299008
   *  @param language
   *  @return - the data from the api
  */
  public String  getEntityReport(String entity_id,String gen_id,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/report",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows us to identify the user, entity and element from an encoded endpoint URL's token
   *
   *  @param token
   *  @return - the data from the api
  */
  public String  getToolsDecodereport(String token) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("token", token);
    	retval = this.doCurl("GET","/tools/decodereport",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update entities that use an old category ID to a new one
   *
   *  @param from
   *  @param to
   *  @param limit
   *  @return - the data from the api
  */
  public String  postEntityMigrate_category(String from,String to,String limit) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from", from);
    	params.put("to", to);
    	params.put("limit", limit);
    	retval = this.doCurl("POST","/entity/migrate_category",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create a new business entity with all it's objects
   *
   *  @param name
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param postcode
   *  @param country
   *  @param latitude
   *  @param longitude
   *  @param timezone
   *  @param telephone_number
   *  @param email
   *  @param website
   *  @param category_id
   *  @param category_name
   *  @return - the data from the api
  */
  public String  putBusiness(String name,String address1,String address2,String address3,String district,String town,String county,String postcode,String country,String latitude,String longitude,String timezone,String telephone_number,String email,String website,String category_id,String category_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("name", name);
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("postcode", postcode);
    	params.put("country", country);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("timezone", timezone);
    	params.put("telephone_number", telephone_number);
    	params.put("email", email);
    	params.put("website", website);
    	params.put("category_id", category_id);
    	params.put("category_name", category_name);
    	retval = this.doCurl("PUT","/business",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Provides a personalised URL to redirect a user to add an entity to Central Index
   *
   *  @param language - The language to use to render the add path e.g. en
   *  @param portal_name - The name of the website that data is to be added on e.g. YourLocal
   *  @return - the data from the api
  */
  public String  getEntityAdd(String language,String portal_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("language", language);
    	params.put("portal_name", portal_name);
    	retval = this.doCurl("GET","/entity/add",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows the removal or insertion of tags into an advertiser object
   *
   *  @param gen_id - The gen_id of this advertiser
   *  @param entity_id - The entity_id of the advertiser
   *  @param language - The tag language to alter
   *  @param tags_to_add - The tags to add
   *  @param tags_to_remove - The tags to remove
   *  @return - the data from the api
  */
  public String  postEntityAdvertiserTag(String gen_id,String entity_id,String language,String tags_to_add,String tags_to_remove) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("gen_id", gen_id);
    	params.put("entity_id", entity_id);
    	params.put("language", language);
    	params.put("tags_to_add", tags_to_add);
    	params.put("tags_to_remove", tags_to_remove);
    	retval = this.doCurl("POST","/entity/advertiser/tag",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows the removal or insertion of locations into an advertiser object
   *
   *  @param gen_id - The gen_id of this advertiser
   *  @param entity_id - The entity_id of the advertiser
   *  @param locations_to_add - The locations to add
   *  @param locations_to_remove - The locations to remove
   *  @return - the data from the api
  */
  public String  postEntityAdvertiserLocation(String gen_id,String entity_id,String locations_to_add,String locations_to_remove) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("gen_id", gen_id);
    	params.put("entity_id", entity_id);
    	params.put("locations_to_add", locations_to_add);
    	params.put("locations_to_remove", locations_to_remove);
    	retval = this.doCurl("POST","/entity/advertiser/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find a location from cache or cloudant depending if it is in the cache
   *
   *  @param string
   *  @param country
   *  @return - the data from the api
  */
  public String  getLookupLocation(String string,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("string", string);
    	params.put("country", country);
    	retval = this.doCurl("GET","/lookup/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find a category from cache or cloudant depending if it is in the cache
   *
   *  @param string - A string to search against, E.g. Plumbers
   *  @param language - An ISO compatible language code, E.g. en
   *  @return - the data from the api
  */
  public String  getLookupCategory(String string,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("string", string);
    	params.put("language", language);
    	retval = this.doCurl("GET","/lookup/category",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find a category from a legacy ID or supplier (e.g. bill_moss)
   *
   *  @param id
   *  @param type
   *  @return - the data from the api
  */
  public String  getLookupLegacyCategory(String id,String type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("id", id);
    	params.put("type", type);
    	retval = this.doCurl("GET","/lookup/legacy/category",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a name can be updated.
   *
   *  @param entity_id
   *  @param name
   *  @param formal_name
   *  @return - the data from the api
  */
  public String  postEntityName(String entity_id,String name,String formal_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("name", name);
    	params.put("formal_name", formal_name);
    	retval = this.doCurl("POST","/entity/name",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an background object can be added. There can however only be one background object.
   *
   *  @param entity_id
   *  @param number_of_employees
   *  @param turnover
   *  @param net_profit
   *  @param vat_number
   *  @param duns_number
   *  @param registered_company_number
   *  @return - the data from the api
  */
  public String  postEntityBackground(String entity_id,String number_of_employees,String turnover,String net_profit,String vat_number,String duns_number,String registered_company_number) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("number_of_employees", number_of_employees);
    	params.put("turnover", turnover);
    	params.put("net_profit", net_profit);
    	params.put("vat_number", vat_number);
    	params.put("duns_number", duns_number);
    	params.put("registered_company_number", registered_company_number);
    	retval = this.doCurl("POST","/entity/background",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an employee object can be added.
   *
   *  @param entity_id
   *  @param title
   *  @param forename
   *  @param surname
   *  @param job_title
   *  @param description
   *  @param email
   *  @param phone_number
   *  @return - the data from the api
  */
  public String  postEntityEmployee(String entity_id,String title,String forename,String surname,String job_title,String description,String email,String phone_number) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("title", title);
    	params.put("forename", forename);
    	params.put("surname", surname);
    	params.put("job_title", job_title);
    	params.put("description", description);
    	params.put("email", email);
    	params.put("phone_number", phone_number);
    	retval = this.doCurl("POST","/entity/employee",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows an employee object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityEmployee(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/employee",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a new phone object to be added to a specified entity. A new object id will be calculated and returned to you if successful.
   *
   *  @param entity_id
   *  @param number
   *  @param description
   *  @return - the data from the api
  */
  public String  postEntityPhone(String entity_id,String number,String description) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("number", number);
    	params.put("description", description);
    	retval = this.doCurl("POST","/entity/phone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a phone object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityPhone(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/phone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an fax object can be added.
   *
   *  @param entity_id
   *  @param number
   *  @param description
   *  @return - the data from the api
  */
  public String  postEntityFax(String entity_id,String number,String description) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("number", number);
    	params.put("description", description);
    	retval = this.doCurl("POST","/entity/fax",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a fax object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityFax(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/fax",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known category id, an category object can be added.
   *
   *  @param category_id
   *  @param language
   *  @param name
   *  @return - the data from the api
  */
  public String  putCategory(String category_id,String language,String name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("category_id", category_id);
    	params.put("language", language);
    	params.put("name", name);
    	retval = this.doCurl("PUT","/category",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known category id, a mapping object can be added.
   *
   *  @param category_id
   *  @param type
   *  @param id
   *  @param name
   *  @return - the data from the api
  */
  public String  postCategoryMappings(String category_id,String type,String id,String name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("category_id", category_id);
    	params.put("type", type);
    	params.put("id", id);
    	params.put("name", name);
    	retval = this.doCurl("POST","/category/mappings",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known category id, an synonym object can be added.
   *
   *  @param category_id
   *  @param synonym
   *  @param language
   *  @return - the data from the api
  */
  public String  postCategorySynonym(String category_id,String synonym,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("category_id", category_id);
    	params.put("synonym", synonym);
    	params.put("language", language);
    	retval = this.doCurl("POST","/category/synonym",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known category id, a synonyms object can be removed.
   *
   *  @param category_id
   *  @param synonym
   *  @param language
   *  @return - the data from the api
  */
  public String  deleteCategorySynonym(String category_id,String synonym,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("category_id", category_id);
    	params.put("synonym", synonym);
    	params.put("language", language);
    	retval = this.doCurl("DELETE","/category/synonym",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a category object to merged with another
   *
   *  @param from
   *  @param to
   *  @return - the data from the api
  */
  public String  postCategoryMerge(String from,String to) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from", from);
    	params.put("to", to);
    	retval = this.doCurl("POST","/category/merge",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an category object can be added.
   *
   *  @param entity_id
   *  @param category_id
   *  @param category_name
   *  @return - the data from the api
  */
  public String  postEntityCategory(String entity_id,String category_id,String category_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("category_id", category_id);
    	params.put("category_name", category_name);
    	retval = this.doCurl("POST","/entity/category",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a category object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityCategory(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/category",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a geopoint can be updated.
   *
   *  @param entity_id
   *  @param longitude
   *  @param latitude
   *  @param accuracy
   *  @return - the data from the api
  */
  public String  postEntityGeopoint(String entity_id,String longitude,String latitude,String accuracy) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("longitude", longitude);
    	params.put("latitude", latitude);
    	params.put("accuracy", accuracy);
    	retval = this.doCurl("POST","/entity/geopoint",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find all matches by phone number and then return all matches that also match company name and location. Default location_strictness is defined in Km and the default is set to 0.2 (200m)
   *
   *  @param phone
   *  @param company_name
   *  @param latitude
   *  @param longitude
   *  @param name_strictness
   *  @param location_strictness
   *  @return - the data from the api
  */
  public String  getMatchByphone(String phone,String company_name,String latitude,String longitude,String name_strictness,String location_strictness) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("phone", phone);
    	params.put("company_name", company_name);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("name_strictness", name_strictness);
    	params.put("location_strictness", location_strictness);
    	retval = this.doCurl("GET","/match/byphone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find all matches by location and then return all matches that also match company name. Default location_strictness is set to 7, which equates to +/- 20m
   *
   *  @param company_name
   *  @param latitude
   *  @param longitude
   *  @param name_strictness
   *  @param location_strictness
   *  @return - the data from the api
  */
  public String  getMatchBylocation(String company_name,String latitude,String longitude,String name_strictness,String location_strictness) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("company_name", company_name);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("name_strictness", name_strictness);
    	params.put("location_strictness", location_strictness);
    	retval = this.doCurl("GET","/match/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Removes stopwords from a string
   *
   *  @param text
   *  @return - the data from the api
  */
  public String  getToolsStopwords(String text) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("text", text);
    	retval = this.doCurl("GET","/tools/stopwords",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns a stemmed version of a string
   *
   *  @param text
   *  @return - the data from the api
  */
  public String  getToolsStem(String text) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("text", text);
    	retval = this.doCurl("GET","/tools/stem",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Return the phonetic representation of a string
   *
   *  @param text
   *  @return - the data from the api
  */
  public String  getToolsPhonetic(String text) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("text", text);
    	retval = this.doCurl("GET","/tools/phonetic",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fully process a string. This includes removing punctuation, stops words and stemming a string. Also returns the phonetic representation of this string.
   *
   *  @param text
   *  @return - the data from the api
  */
  public String  getToolsProcess_string(String text) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("text", text);
    	retval = this.doCurl("GET","/tools/process_string",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Attempt to process a phone number, removing anything which is not a digit
   *
   *  @param number
   *  @return - the data from the api
  */
  public String  getToolsProcess_phone(String number) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("number", number);
    	retval = this.doCurl("GET","/tools/process_phone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Spider a single url looking for key facts
   *
   *  @param url
   *  @return - the data from the api
  */
  public String  getToolsSpider(String url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("url", url);
    	retval = this.doCurl("GET","/tools/spider",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Supply an address to geocode - returns lat/lon and accuracy
   *
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param postcode
   *  @param country
   *  @return - the data from the api
  */
  public String  getToolsGeocode(String address1,String address2,String address3,String district,String town,String county,String postcode,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("postcode", postcode);
    	params.put("country", country);
    	retval = this.doCurl("GET","/tools/geocode",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Generate JSON in the format to generate Mashery's IODocs
   *
   *  @param mode - The HTTP method of the API call to document. e.g. GET
   *  @param path - The path of the API call to document e.g, /entity
   *  @param endpoint - The Mashery 'endpoint' to prefix to our API paths e.g. v1
   *  @param doctype - Mashery has two forms of JSON to describe API methods; one on github, the other on its customer dashboard
   *  @return - the data from the api
  */
  public String  getToolsIodocs(String mode,String path,String endpoint,String doctype) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("mode", mode);
    	params.put("path", path);
    	params.put("endpoint", endpoint);
    	params.put("doctype", doctype);
    	retval = this.doCurl("GET","/tools/iodocs",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Use this call to get information (in HTML or JSON) about the data structure of given entity object (e.g. a phone number or an address)
   *
   *  @param object - The API call documentation is required for
   *  @param format - The format of the returned data eg. JSON or HTML
   *  @return - the data from the api
  */
  public String  getToolsDocs(String object,String format) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("object", object);
    	params.put("format", format);
    	retval = this.doCurl("GET","/tools/docs",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Format a phone number according to the rules of the country supplied
   *
   *  @param number - The telephone number to format
   *  @param country - The country where the telephone number is based
   *  @return - the data from the api
  */
  public String  getToolsFormatPhone(String number,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("number", number);
    	params.put("country", country);
    	retval = this.doCurl("GET","/tools/format/phone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Format an address according to the rules of the country supplied
   *
   *  @param address - The address to format
   *  @param country - The country where the address is based
   *  @return - the data from the api
  */
  public String  getToolsFormatAddress(String address,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("address", address);
    	params.put("country", country);
    	retval = this.doCurl("GET","/tools/format/address",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an invoice_address object can be updated.
   *
   *  @param entity_id
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param postcode
   *  @param address_type
   *  @return - the data from the api
  */
  public String  postEntityInvoice_address(String entity_id,String address1,String address2,String address3,String district,String town,String county,String postcode,String address_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("postcode", postcode);
    	params.put("address_type", address_type);
    	retval = this.doCurl("POST","/entity/invoice_address",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id and a known invoice_address ID, we can delete a specific invoice_address object from an enitity.
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  deleteEntityInvoice_address(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("DELETE","/entity/invoice_address",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an tag object can be added.
   *
   *  @param entity_id
   *  @param tag
   *  @param language
   *  @return - the data from the api
  */
  public String  postEntityTag(String entity_id,String tag,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("tag", tag);
    	params.put("language", language);
    	retval = this.doCurl("POST","/entity/tag",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a tag object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityTag(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/tag",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create/Update a postal address
   *
   *  @param entity_id
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param postcode
   *  @param address_type
   *  @return - the data from the api
  */
  public String  postEntityPostal_address(String entity_id,String address1,String address2,String address3,String district,String town,String county,String postcode,String address_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("postcode", postcode);
    	params.put("address_type", address_type);
    	retval = this.doCurl("POST","/entity/postal_address",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a advertiser is added
   *
   *  @param entity_id
   *  @param tags
   *  @param locations
   *  @param expiry
   *  @param is_national
   *  @param language
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  postEntityAdvertiser(String entity_id,String tags,String locations,String expiry,String is_national,String language,String reseller_ref,String reseller_agent_id,String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("tags", tags);
    	params.put("locations", locations);
    	params.put("expiry", expiry);
    	params.put("is_national", is_national);
    	params.put("language", language);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("POST","/entity/advertiser",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows an advertiser object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityAdvertiser(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/advertiser",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an email address object can be added.
   *
   *  @param entity_id
   *  @param email_address
   *  @param email_description
   *  @return - the data from the api
  */
  public String  postEntityEmail(String entity_id,String email_address,String email_description) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("email_address", email_address);
    	params.put("email_description", email_description);
    	retval = this.doCurl("POST","/entity/email",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a email object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityEmail(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/email",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a website object can be added.
   *
   *  @param entity_id
   *  @param website_url
   *  @param display_url
   *  @param website_description
   *  @return - the data from the api
  */
  public String  postEntityWebsite(String entity_id,String website_url,String display_url,String website_description) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("website_url", website_url);
    	params.put("display_url", display_url);
    	params.put("website_description", website_description);
    	retval = this.doCurl("POST","/entity/website",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a website object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityWebsite(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/website",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a image object can be added.
   *
   *  @param entity_id
   *  @param filedata
   *  @param image_name
   *  @return - the data from the api
  */
  public String  postEntityImage(String entity_id,String filedata,String image_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("filedata", filedata);
    	params.put("image_name", image_name);
    	retval = this.doCurl("POST","/entity/image",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a image object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityImage(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/image",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Read a location with the supplied ID in the locations reference database.
   *
   *  @param location_id
   *  @return - the data from the api
  */
  public String  getLocation(String location_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	retval = this.doCurl("GET","/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create/update a new location entity with the supplied ID in the locations reference database.
   *
   *  @param location_id
   *  @param name
   *  @param formal_name
   *  @param latitude
   *  @param longitude
   *  @param resolution
   *  @param country
   *  @param population
   *  @param description
   *  @param timezone
   *  @param is_duplicate
   *  @param is_default
   *  @return - the data from the api
  */
  public String  postLocation(String location_id,String name,String formal_name,String latitude,String longitude,String resolution,String country,String population,String description,String timezone,String is_duplicate,String is_default) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	params.put("name", name);
    	params.put("formal_name", formal_name);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("resolution", resolution);
    	params.put("country", country);
    	params.put("population", population);
    	params.put("description", description);
    	params.put("timezone", timezone);
    	params.put("is_duplicate", is_duplicate);
    	params.put("is_default", is_default);
    	retval = this.doCurl("POST","/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a new synonym to a known location
   *
   *  @param location_id
   *  @param synonym
   *  @param language
   *  @return - the data from the api
  */
  public String  postLocationSynonym(String location_id,String synonym,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	params.put("synonym", synonym);
    	params.put("language", language);
    	retval = this.doCurl("POST","/location/synonym",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Remove a new synonym from a known location
   *
   *  @param location_id
   *  @param synonym
   *  @param language
   *  @return - the data from the api
  */
  public String  deleteLocationSynonym(String location_id,String synonym,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	params.put("synonym", synonym);
    	params.put("language", language);
    	retval = this.doCurl("DELETE","/location/synonym",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a new source to a known location
   *
   *  @param location_id
   *  @param type
   *  @param url
   *  @param ref
   *  @return - the data from the api
  */
  public String  postLocationSource(String location_id,String type,String url,String ref) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	params.put("type", type);
    	params.put("url", url);
    	params.put("ref", ref);
    	retval = this.doCurl("POST","/location/source",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a status object can be updated.
   *
   *  @param entity_id
   *  @param status
   *  @return - the data from the api
  */
  public String  postEntityStatus(String entity_id,String status) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("status", status);
    	retval = this.doCurl("POST","/entity/status",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a logo object can be added.
   *
   *  @param entity_id
   *  @param filedata
   *  @param logo_name
   *  @return - the data from the api
  */
  public String  postEntityLogo(String entity_id,String filedata,String logo_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("filedata", filedata);
    	params.put("logo_name", logo_name);
    	retval = this.doCurl("POST","/entity/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a phone object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityLogo(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, avideo object can be added.
   *
   *  @param entity_id
   *  @param title
   *  @param description
   *  @param thumbnail
   *  @param embed_code
   *  @return - the data from the api
  */
  public String  postEntityVideo(String entity_id,String title,String description,String thumbnail,String embed_code) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("title", title);
    	params.put("description", description);
    	params.put("thumbnail", thumbnail);
    	params.put("embed_code", embed_code);
    	retval = this.doCurl("POST","/entity/video",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a video object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityVideo(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/video",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an affiliate link object can be added.
   *
   *  @param entity_id
   *  @param affiliate_name
   *  @param affiliate_link
   *  @param affiliate_message
   *  @param affiliate_logo
   *  @return - the data from the api
  */
  public String  postEntityAffiliate_link(String entity_id,String affiliate_name,String affiliate_link,String affiliate_message,String affiliate_logo) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("affiliate_name", affiliate_name);
    	params.put("affiliate_link", affiliate_link);
    	params.put("affiliate_message", affiliate_message);
    	params.put("affiliate_logo", affiliate_logo);
    	retval = this.doCurl("POST","/entity/affiliate_link",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows an affiliate link object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityAffiliate_link(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/affiliate_link",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a description object can be added.
   *
   *  @param entity_id
   *  @param headline
   *  @param body
   *  @return - the data from the api
  */
  public String  postEntityDescription(String entity_id,String headline,String body) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("headline", headline);
    	params.put("body", body);
    	retval = this.doCurl("POST","/entity/description",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a description object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityDescription(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/description",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a list description object can be added.
   *
   *  @param entity_id
   *  @param headline
   *  @param body
   *  @return - the data from the api
  */
  public String  postEntityList(String entity_id,String headline,String body) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("headline", headline);
    	params.put("body", body);
    	retval = this.doCurl("POST","/entity/list",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a list description object to be reduced in confidence
   *
   *  @param gen_id
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  deleteEntityList(String gen_id,String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("gen_id", gen_id);
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("DELETE","/entity/list",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an document object can be added.
   *
   *  @param entity_id
   *  @param name
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postEntityDocument(String entity_id,String name,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("name", name);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/entity/document",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a phone object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityDocument(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/document",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a testimonial object can be added.
   *
   *  @param entity_id
   *  @param title
   *  @param text
   *  @param date
   *  @param testifier_name
   *  @return - the data from the api
  */
  public String  postEntityTestimonial(String entity_id,String title,String text,String date,String testifier_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("title", title);
    	params.put("text", text);
    	params.put("date", date);
    	params.put("testifier_name", testifier_name);
    	retval = this.doCurl("POST","/entity/testimonial",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a testimonial object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityTestimonial(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/testimonial",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a opening times object can be added. Each day can be either 'closed' to indicate that the entity is closed that day, '24hour' to indicate that the entity is open all day or single/split time ranges can be supplied in 4-digit 24-hour format, such as '09001730' or '09001200,13001700' to indicate hours of opening.
   *
   *  @param entity_id - The id of the entity to edit
   *  @param monday - e.g. 'closed', '24hour' , '09001730' , '09001200,13001700'
   *  @param tuesday - e.g. 'closed', '24hour' , '09001730' , '09001200,13001700'
   *  @param wednesday - e.g. 'closed', '24hour' , '09001730' , '09001200,13001700'
   *  @param thursday - e.g. 'closed', '24hour' , '09001730' , '09001200,13001700'
   *  @param friday - e.g. 'closed', '24hour' , '09001730' , '09001200,13001700'
   *  @param saturday - e.g. 'closed', '24hour' , '09001730' , '09001200,13001700'
   *  @param sunday - e.g. 'closed', '24hour' , '09001730' , '09001200,13001700'
   *  @param closed - a comma-separated list of dates that the entity is closed e.g. '2013-04-29,2013-05-02'
   *  @param closed_public_holidays - whether the entity is closed on public holidays
   *  @return - the data from the api
  */
  public String  postEntityOpening_times(String entity_id,String monday,String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday,String closed,String closed_public_holidays) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("monday", monday);
    	params.put("tuesday", tuesday);
    	params.put("wednesday", wednesday);
    	params.put("thursday", thursday);
    	params.put("friday", friday);
    	params.put("saturday", saturday);
    	params.put("sunday", sunday);
    	params.put("closed", closed);
    	params.put("closed_public_holidays", closed_public_holidays);
    	retval = this.doCurl("POST","/entity/opening_times",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a website object can be added.
   *
   *  @param entity_id
   *  @param title
   *  @param description
   *  @param terms
   *  @param start_date
   *  @param expiry_date
   *  @param url
   *  @param image_url
   *  @return - the data from the api
  */
  public String  postEntitySpecial_offer(String entity_id,String title,String description,String terms,String start_date,String expiry_date,String url,String image_url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("title", title);
    	params.put("description", description);
    	params.put("terms", terms);
    	params.put("start_date", start_date);
    	params.put("expiry_date", expiry_date);
    	params.put("url", url);
    	params.put("image_url", image_url);
    	retval = this.doCurl("POST","/entity/special_offer",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a special offer object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntitySpecial_offer(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/entity/special_offer",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update user based on email address or social_network/social_network_id
   *
   *  @param email
   *  @param first_name
   *  @param last_name
   *  @param active
   *  @param trust
   *  @param creation_date
   *  @param user_type
   *  @param social_network
   *  @param social_network_id
   *  @return - the data from the api
  */
  public String  postUser(String email,String first_name,String last_name,String active,String trust,String creation_date,String user_type,String social_network,String social_network_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email", email);
    	params.put("first_name", first_name);
    	params.put("last_name", last_name);
    	params.put("active", active);
    	params.put("trust", trust);
    	params.put("creation_date", creation_date);
    	params.put("user_type", user_type);
    	params.put("social_network", social_network);
    	params.put("social_network_id", social_network_id);
    	retval = this.doCurl("POST","/user",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a unique email address an user can be retrieved
   *
   *  @param email
   *  @return - the data from the api
  */
  public String  getUserBy_email(String email) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email", email);
    	retval = this.doCurl("GET","/user/by_email",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a unique ID address an user can be retrieved
   *
   *  @param user_id
   *  @return - the data from the api
  */
  public String  getUser(String user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	retval = this.doCurl("GET","/user",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a unique ID address an user can be retrieved
   *
   *  @param name
   *  @param id
   *  @return - the data from the api
  */
  public String  getUserBy_social_media(String name,String id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("name", name);
    	params.put("id", id);
    	retval = this.doCurl("GET","/user/by_social_media",params);
    } finally { 
    }
    return retval;
  }


  /**
   * The search matches a category name on a given string and language.
   *
   *  @param str - A string to search against, E.g. Plumbers e.g. but
   *  @param language - An ISO compatible language code, E.g. en e.g. en
   *  @return - the data from the api
  */
  public String  getAutocompleteCategory(String str,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("language", language);
    	retval = this.doCurl("GET","/autocomplete/category",params);
    } finally { 
    }
    return retval;
  }


  /**
   * The search matches a category name or synonym on a given string and language.
   *
   *  @param str - A string to search against, E.g. Plumbers e.g. but
   *  @param language - An ISO compatible language code, E.g. en e.g. en
   *  @return - the data from the api
  */
  public String  getAutocompleteKeyword(String str,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("language", language);
    	retval = this.doCurl("GET","/autocomplete/keyword",params);
    } finally { 
    }
    return retval;
  }


  /**
   * The search matches a location name or synonym on a given string and language.
   *
   *  @param str - A string to search against, E.g. Dub e.g. dub
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @return - the data from the api
  */
  public String  getAutocompleteLocation(String str,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("country", country);
    	retval = this.doCurl("GET","/autocomplete/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create a queue item
   *
   *  @param queue_name
   *  @param data
   *  @return - the data from the api
  */
  public String  putQueue(String queue_name,String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("queue_name", queue_name);
    	params.put("data", data);
    	retval = this.doCurl("PUT","/queue",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known queue id, a queue item can be removed.
   *
   *  @param queue_id
   *  @return - the data from the api
  */
  public String  deleteQueue(String queue_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("queue_id", queue_id);
    	retval = this.doCurl("DELETE","/queue",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Retrieve queue items.
   *
   *  @param limit
   *  @param queue_name
   *  @return - the data from the api
  */
  public String  getQueue(String limit,String queue_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("limit", limit);
    	params.put("queue_name", queue_name);
    	retval = this.doCurl("GET","/queue",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Unlock queue items.
   *
   *  @param queue_name
   *  @param seconds
   *  @return - the data from the api
  */
  public String  postQueueUnlock(String queue_name,String seconds) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("queue_name", queue_name);
    	params.put("seconds", seconds);
    	retval = this.doCurl("POST","/queue/unlock",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add an error to a queue item
   *
   *  @param queue_id
   *  @param error
   *  @return - the data from the api
  */
  public String  postQueueError(String queue_id,String error) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("queue_id", queue_id);
    	params.put("error", error);
    	retval = this.doCurl("POST","/queue/error",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find a queue item by its type and id
   *
   *  @param type
   *  @param id
   *  @return - the data from the api
  */
  public String  getQueueSearch(String type,String id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("type", type);
    	params.put("id", id);
    	retval = this.doCurl("GET","/queue/search",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create a new transaction
   *
   *  @param entity_id
   *  @param user_id
   *  @param basket_total
   *  @param basket
   *  @param currency
   *  @param notes
   *  @return - the data from the api
  */
  public String  putTransaction(String entity_id,String user_id,String basket_total,String basket,String currency,String notes) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("user_id", user_id);
    	params.put("basket_total", basket_total);
    	params.put("basket", basket);
    	params.put("currency", currency);
    	params.put("notes", notes);
    	retval = this.doCurl("PUT","/transaction",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Set a transactions status to inprogess
   *
   *  @param transaction_id
   *  @param paypal_setexpresscheckout
   *  @return - the data from the api
  */
  public String  postTransactionInprogress(String transaction_id,String paypal_setexpresscheckout) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("transaction_id", transaction_id);
    	params.put("paypal_setexpresscheckout", paypal_setexpresscheckout);
    	retval = this.doCurl("POST","/transaction/inprogress",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Set a transactions status to authorised
   *
   *  @param transaction_id
   *  @param paypal_getexpresscheckoutdetails
   *  @return - the data from the api
  */
  public String  postTransactionAuthorised(String transaction_id,String paypal_getexpresscheckoutdetails) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("transaction_id", transaction_id);
    	params.put("paypal_getexpresscheckoutdetails", paypal_getexpresscheckoutdetails);
    	retval = this.doCurl("POST","/transaction/authorised",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Set a transactions status to complete
   *
   *  @param transaction_id
   *  @param paypal_doexpresscheckoutpayment
   *  @param user_id
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  postTransactionComplete(String transaction_id,String paypal_doexpresscheckoutpayment,String user_id,String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("transaction_id", transaction_id);
    	params.put("paypal_doexpresscheckoutpayment", paypal_doexpresscheckoutpayment);
    	params.put("user_id", user_id);
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("POST","/transaction/complete",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Set a transactions status to cancelled
   *
   *  @param transaction_id
   *  @return - the data from the api
  */
  public String  postTransactionCancelled(String transaction_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("transaction_id", transaction_id);
    	retval = this.doCurl("POST","/transaction/cancelled",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Given a transaction_id retrieve information on it
   *
   *  @param transaction_id
   *  @return - the data from the api
  */
  public String  getTransaction(String transaction_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("transaction_id", transaction_id);
    	retval = this.doCurl("GET","/transaction",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Given a transaction_id retrieve information on it
   *
   *  @param paypal_transaction_id
   *  @return - the data from the api
  */
  public String  getTransactionBy_paypal_transaction_id(String paypal_transaction_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("paypal_transaction_id", paypal_transaction_id);
    	retval = this.doCurl("GET","/transaction/by_paypal_transaction_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allow an entity to be claimed by a valid user
   *
   *  @param entity_id
   *  @param claimed_user_id
   *  @param claimed_date
   *  @return - the data from the api
  */
  public String  postEntityClaim(String entity_id,String claimed_user_id,String claimed_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("claimed_user_id", claimed_user_id);
    	params.put("claimed_date", claimed_date);
    	retval = this.doCurl("POST","/entity/claim",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a publisher
   *
   *  @param publisher_id
   *  @param country
   *  @param name
   *  @param description
   *  @param active
   *  @return - the data from the api
  */
  public String  postPublisher(String publisher_id,String country,String name,String description,String active) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("publisher_id", publisher_id);
    	params.put("country", country);
    	params.put("name", name);
    	params.put("description", description);
    	params.put("active", active);
    	retval = this.doCurl("POST","/publisher",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete a publisher with a specified publisher_id
   *
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  deletePublisher(String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("DELETE","/publisher",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns publisher that matches a given publisher id
   *
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  getPublisher(String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("GET","/publisher",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns publisher that matches a given publisher id
   *
   *  @param country
   *  @return - the data from the api
  */
  public String  getPublisherByCountry(String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	retval = this.doCurl("GET","/publisher/byCountry",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns publishers that are available for a given entity_id.
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  getPublisherByEntityId(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/publisher/byEntityId",params);
    } finally { 
    }
    return retval;
  }



}


