2022/12/14 12:36:15 STARTUP Redis server: tcp://127.0.0.1:6379
2022/12/14 12:36:15 STARTUP ElasticSearch server: http://172.22.12.49:9200
2022/12/14 12:36:15 STARTUP ES view server: http://172.22.114.129:63300/view/view
2022/12/14 12:36:15 STARTUP CouchDB server: http://wolf_staging:******@172.22.10.201:5984
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
              String val = new String((String)params.get(key));
              if (val.length() > 0) {
                nvps.add(new BasicNameValuePair(key, (String)params.get(key)));
              }
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
   * With a 192 id get remote entity data
   *
   *  @param oneninetwo_id
   *  @return - the data from the api
  */
  public String  GET192Get(String oneninetwo_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("oneninetwo_id", oneninetwo_id);
    	retval = this.doCurl("GET","/192/get",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the activity from the collection
   *
   *  @param type - The activity type: add, claim, special offer, image, video, description, testimonial
   *  @param country - The country to filter by
   *  @param latitude_1 - The latitude_1 to filter by
   *  @param longitude_1 - The longitude_1 to filter by
   *  @param latitude_2 - The latitude_2 to filter by
   *  @param longitude_2 - The longitude_2 to filter by
   *  @param number_results - The number_results to filter by
   *  @param unique_action - Return only the most recent instance of this action?
   *  @return - the data from the api
  */
  public String  GETActivity_stream(String type,String country,String latitude_1,String longitude_1,String latitude_2,String longitude_2,String number_results,String unique_action) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("type", type);
    	params.put("country", country);
    	params.put("latitude_1", latitude_1);
    	params.put("longitude_1", longitude_1);
    	params.put("latitude_2", latitude_2);
    	params.put("longitude_2", longitude_2);
    	params.put("number_results", number_results);
    	params.put("unique_action", unique_action);
    	retval = this.doCurl("GET","/activity_stream",params);
    } finally { 
    }
    return retval;
  }


  /**
   * When we get some activity make a record of it
   *
   *  @param entity_id - The entity to pull
   *  @param entity_name - The entity name this entry refers to
   *  @param type - The activity type.
   *  @param country - The country for the activity
   *  @param longitude - The longitude for teh activity
   *  @param latitude - The latitude for teh activity
   *  @return - the data from the api
  */
  public String  POSTActivity_stream(String entity_id,String entity_name,String type,String country,String longitude,String latitude) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("entity_name", entity_name);
    	params.put("type", type);
    	params.put("country", country);
    	params.put("longitude", longitude);
    	params.put("latitude", latitude);
    	retval = this.doCurl("POST","/activity_stream",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get all entities in which live ads have the matched reseller_masheryid.
   *
   *  @param country
   *  @param reseller_masheryid
   *  @param name_only - If true the query result contains entity name only; otherwise, the entity object.
   *  @param name_match - Filter the result in which the name contains the given text.
   *  @param skip
   *  @param take - Set 0 to get all result. However, if name_only=false, only 100 objects at most will be returned to prevent oversized response body.
   *  @return - the data from the api
  */
  public String  GETAdvertiserBy_reseller_masheryid(String country,String reseller_masheryid,String name_only,String name_match,String skip,String take) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	params.put("reseller_masheryid", reseller_masheryid);
    	params.put("name_only", name_only);
    	params.put("name_match", name_match);
    	params.put("skip", skip);
    	params.put("take", take);
    	retval = this.doCurl("GET","/advertiser/by_reseller_masheryid",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get all advertisers that have been updated from a give date for a given reseller
   *
   *  @param from_date
   *  @param country
   *  @return - the data from the api
  */
  public String  GETAdvertiserUpdated(String from_date,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from_date", from_date);
    	params.put("country", country);
    	retval = this.doCurl("GET","/advertiser/updated",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get all advertisers that have been updated from a give date for a given publisher
   *
   *  @param publisher_id
   *  @param from_date
   *  @param country
   *  @return - the data from the api
  */
  public String  GETAdvertiserUpdatedBy_publisher(String publisher_id,String from_date,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("publisher_id", publisher_id);
    	params.put("from_date", from_date);
    	params.put("country", country);
    	retval = this.doCurl("GET","/advertiser/updated/by_publisher",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Check that the advertiser has a premium inventory
   *
   *  @param type
   *  @param category_id - The category of the advertiser
   *  @param location_id - The location of the advertiser
   *  @param publisher_id - The publisher of the advertiser
   *  @return - the data from the api
  */
  public String  GETAdvertisersPremiumInventorycheck(String type,String category_id,String location_id,String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("type", type);
    	params.put("category_id", category_id);
    	params.put("location_id", location_id);
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("GET","/advertisers/premium/inventorycheck",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete an association
   *
   *  @param association_id
   *  @return - the data from the api
  */
  public String  DELETEAssociation(String association_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("association_id", association_id);
    	retval = this.doCurl("DELETE","/association",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch an association
   *
   *  @param association_id
   *  @return - the data from the api
  */
  public String  GETAssociation(String association_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("association_id", association_id);
    	retval = this.doCurl("GET","/association",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Will create a new association or update an existing one
   *
   *  @param association_id
   *  @param association_name
   *  @param association_url
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTAssociation(String association_id,String association_name,String association_url,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("association_id", association_id);
    	params.put("association_name", association_name);
    	params.put("association_url", association_url);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/association",params);
    } finally { 
    }
    return retval;
  }


  /**
   * The search matches a category name on a given string and language.
   *
   *  @param str - A string to search against, E.g. Plumbers e.g. but
   *  @param language - An ISO compatible language code, E.g. en e.g. en
   *  @param mapped_to_partner - Only return CI categories that have a partner mapping
   *  @return - the data from the api
  */
  public String  GETAutocompleteCategory(String str,String language,String mapped_to_partner) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("language", language);
    	params.put("mapped_to_partner", mapped_to_partner);
    	retval = this.doCurl("GET","/autocomplete/category",params);
    } finally { 
    }
    return retval;
  }


  /**
   * The search matches a category name and ID on a given string and language.
   *
   *  @param str - A string to search against, E.g. Plumbers e.g. but
   *  @param language - An ISO compatible language code, E.g. en e.g. en
   *  @param mapped_to_partner - Only return CI categories that have a partner mapping
   *  @return - the data from the api
  */
  public String  GETAutocompleteCategoryId(String str,String language,String mapped_to_partner) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("language", language);
    	params.put("mapped_to_partner", mapped_to_partner);
    	retval = this.doCurl("GET","/autocomplete/category/id",params);
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
  public String  GETAutocompleteKeyword(String str,String language) throws Exception { 
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
   *  @param language - An ISO compatible language code, E.g. en e.g. en
   *  @return - the data from the api
  */
  public String  GETAutocompleteLocation(String str,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/autocomplete/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * The search matches a location name or synonym on a given string and language.
   *
   *  @param str - A string to search against, E.g. Middle e.g. dub
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param resolution
   *  @return - the data from the api
  */
  public String  GETAutocompleteLocationBy_resolution(String str,String country,String resolution) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("country", country);
    	params.put("resolution", resolution);
    	retval = this.doCurl("GET","/autocomplete/location/by_resolution",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create a new business entity with all it's objects
   *
   *  @param name
   *  @param status
   *  @param building_number
   *  @param branch_name
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param province
   *  @param postcode
   *  @param country
   *  @param latitude
   *  @param longitude
   *  @param timezone
   *  @param telephone_number
   *  @param allow_no_address
   *  @param allow_no_phone
   *  @param additional_telephone_number
   *  @param email
   *  @param website
   *  @param payment_types - Payment types separated by comma
   *  @param tags - Tags separated by comma
   *  @param category_id
   *  @param category_type
   *  @param featured_message_text - Featured message content
   *  @param featured_message_url - Featured message URL
   *  @param do_not_display
   *  @param orderonline
   *  @param delivers
   *  @param referrer_url
   *  @param referrer_name
   *  @param destructive
   *  @param delete_mode - The type of object contribution deletion
   *  @param master_entity_id - The entity you want this data to go to
   *  @param no_merge_on_error - If true, data duplication error will be returned when a matched entity is found. If false, such error is suppressed and data is merged into the matched entity.
   *  @return - the data from the api
  */
  public String  PUTBusiness(String name,String status,String building_number,String branch_name,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String country,String latitude,String longitude,String timezone,String telephone_number,String allow_no_address,String allow_no_phone,String additional_telephone_number,String email,String website,String payment_types,String tags,String category_id,String category_type,String featured_message_text,String featured_message_url,String do_not_display,String orderonline,String delivers,String referrer_url,String referrer_name,String destructive,String delete_mode,String master_entity_id,String no_merge_on_error,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("name", name);
    	params.put("status", status);
    	params.put("building_number", building_number);
    	params.put("branch_name", branch_name);
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("province", province);
    	params.put("postcode", postcode);
    	params.put("country", country);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("timezone", timezone);
    	params.put("telephone_number", telephone_number);
    	params.put("allow_no_address", allow_no_address);
    	params.put("allow_no_phone", allow_no_phone);
    	params.put("additional_telephone_number", additional_telephone_number);
    	params.put("email", email);
    	params.put("website", website);
    	params.put("payment_types", payment_types);
    	params.put("tags", tags);
    	params.put("category_id", category_id);
    	params.put("category_type", category_type);
    	params.put("featured_message_text", featured_message_text);
    	params.put("featured_message_url", featured_message_url);
    	params.put("do_not_display", do_not_display);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("referrer_url", referrer_url);
    	params.put("referrer_name", referrer_name);
    	params.put("destructive", destructive);
    	params.put("delete_mode", delete_mode);
    	params.put("master_entity_id", master_entity_id);
    	params.put("no_merge_on_error", no_merge_on_error);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("PUT","/business",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create entity via JSON
   *
   *  @param json - Business JSON
   *  @param country - The country to fetch results for e.g. gb
   *  @param timezone
   *  @param master_entity_id - The entity you want this data to go to
   *  @param allow_no_address
   *  @param allow_no_phone
   *  @param queue_priority
   *  @param skip_dedup_check - If true, skip checking on existing supplier ID, phone numbers, etc.
   *  @return - the data from the api
  */
  public String  PUTBusinessJson(String json,String country,String timezone,String master_entity_id,String allow_no_address,String allow_no_phone,String _user_id,String queue_priority,String skip_dedup_check) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("json", json);
    	params.put("country", country);
    	params.put("timezone", timezone);
    	params.put("master_entity_id", master_entity_id);
    	params.put("allow_no_address", allow_no_address);
    	params.put("allow_no_phone", allow_no_phone);
    	params.put("_user_id", _user_id);
    	params.put("queue_priority", queue_priority);
    	params.put("skip_dedup_check", skip_dedup_check);
    	retval = this.doCurl("PUT","/business/json",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create entity via JSON
   *
   *  @param entity_id - The entity to add rich data too
   *  @param json - The rich data to add to the entity
   *  @return - the data from the api
  */
  public String  POSTBusinessJsonProcess(String entity_id,String json,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("json", json);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/business/json/process",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete a business tool with a specified tool_id
   *
   *  @param tool_id
   *  @return - the data from the api
  */
  public String  DELETEBusiness_tool(String tool_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tool_id", tool_id);
    	retval = this.doCurl("DELETE","/business_tool",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns business tool that matches a given tool id
   *
   *  @param tool_id
   *  @return - the data from the api
  */
  public String  GETBusiness_tool(String tool_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tool_id", tool_id);
    	retval = this.doCurl("GET","/business_tool",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a Business Tool
   *
   *  @param tool_id
   *  @param country
   *  @param headline
   *  @param description
   *  @param link_url
   *  @param active
   *  @return - the data from the api
  */
  public String  POSTBusiness_tool(String tool_id,String country,String headline,String description,String link_url,String active) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tool_id", tool_id);
    	params.put("country", country);
    	params.put("headline", headline);
    	params.put("description", description);
    	params.put("link_url", link_url);
    	params.put("active", active);
    	retval = this.doCurl("POST","/business_tool",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns active business tools for a specific masheryid in a given country
   *
   *  @param country
   *  @param activeonly
   *  @return - the data from the api
  */
  public String  GETBusiness_toolBy_masheryid(String country,String activeonly) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	params.put("activeonly", activeonly);
    	retval = this.doCurl("GET","/business_tool/by_masheryid",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Assigns a Call To Action to a Business Tool
   *
   *  @param tool_id
   *  @param enablecta
   *  @param cta_id
   *  @param slug
   *  @param nomodal
   *  @param type
   *  @param headline
   *  @param textshort
   *  @param link
   *  @param linklabel
   *  @param textlong
   *  @param textoutro
   *  @param bullets
   *  @param masheryids
   *  @param imgurl
   *  @param custombranding
   *  @param customcol
   *  @param custombkg
   *  @param customctacol
   *  @param customctabkg
   *  @param custominfocol
   *  @param custominfobkg
   *  @return - the data from the api
  */
  public String  POSTBusiness_toolCta(String tool_id,String enablecta,String cta_id,String slug,String nomodal,String type,String headline,String textshort,String link,String linklabel,String textlong,String textoutro,String bullets,String masheryids,String imgurl,String custombranding,String customcol,String custombkg,String customctacol,String customctabkg,String custominfocol,String custominfobkg) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tool_id", tool_id);
    	params.put("enablecta", enablecta);
    	params.put("cta_id", cta_id);
    	params.put("slug", slug);
    	params.put("nomodal", nomodal);
    	params.put("type", type);
    	params.put("headline", headline);
    	params.put("textshort", textshort);
    	params.put("link", link);
    	params.put("linklabel", linklabel);
    	params.put("textlong", textlong);
    	params.put("textoutro", textoutro);
    	params.put("bullets", bullets);
    	params.put("masheryids", masheryids);
    	params.put("imgurl", imgurl);
    	params.put("custombranding", custombranding);
    	params.put("customcol", customcol);
    	params.put("custombkg", custombkg);
    	params.put("customctacol", customctacol);
    	params.put("customctabkg", customctabkg);
    	params.put("custominfocol", custominfocol);
    	params.put("custominfobkg", custominfobkg);
    	retval = this.doCurl("POST","/business_tool/cta",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Assigns a Business Tool image
   *
   *  @param tool_id
   *  @param assignimage
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTBusiness_toolImage(String tool_id,String assignimage,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tool_id", tool_id);
    	params.put("assignimage", assignimage);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/business_tool/image",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Assigns a Business Tool image
   *
   *  @param tool_id
   *  @param image_url
   *  @return - the data from the api
  */
  public String  POSTBusiness_toolImageBy_url(String tool_id,String image_url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tool_id", tool_id);
    	params.put("image_url", image_url);
    	retval = this.doCurl("POST","/business_tool/image/by_url",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known cache key get the data from cache
   *
   *  @param cache_key
   *  @param use_compression
   *  @return - the data from the api
  */
  public String  GETCache(String cache_key,String use_compression) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("cache_key", cache_key);
    	params.put("use_compression", use_compression);
    	retval = this.doCurl("GET","/cache",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add some data to the cache with a given expiry
   *
   *  @param cache_key
   *  @param expiry - The cache expiry in seconds
   *  @param data - The data to cache
   *  @param use_compression
   *  @return - the data from the api
  */
  public String  POSTCache(String cache_key,String expiry,String data,String use_compression) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("cache_key", cache_key);
    	params.put("expiry", expiry);
    	params.put("data", data);
    	params.put("use_compression", use_compression);
    	retval = this.doCurl("POST","/cache",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns the supplied wolf category object by fetching the supplied category_id from our categories object.
   *
   *  @param category_id
   *  @return - the data from the api
  */
  public String  GETCategory(String category_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("category_id", category_id);
    	retval = this.doCurl("GET","/category",params);
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
  public String  PUTCategory(String category_id,String language,String name) throws Exception { 
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
   * Returns all Central Index categories and associated data
   *
   *  @param partner
   *  @return - the data from the api
  */
  public String  GETCategoryAll(String partner) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("partner", partner);
    	retval = this.doCurl("GET","/category/all",params);
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
  public String  POSTCategoryMappings(String category_id,String type,String id,String name) throws Exception { 
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
   * With a known category id, a mapping object can be deleted.
   *
   *  @param category_id
   *  @param category_type
   *  @param mapped_id
   *  @return - the data from the api
  */
  public String  DELETECategoryMappings(String category_id,String category_type,String mapped_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("category_id", category_id);
    	params.put("category_type", category_type);
    	params.put("mapped_id", mapped_id);
    	retval = this.doCurl("DELETE","/category/mappings",params);
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
  public String  POSTCategoryMerge(String from,String to) throws Exception { 
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
   * With a known category id, an synonym object can be added.
   *
   *  @param category_id
   *  @param synonym
   *  @param language
   *  @return - the data from the api
  */
  public String  POSTCategorySynonym(String category_id,String synonym,String language) throws Exception { 
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
  public String  DELETECategorySynonym(String category_id,String synonym,String language) throws Exception { 
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
   * Get the contract from the ID supplied
   *
   *  @param contract_id
   *  @return - the data from the api
  */
  public String  GETContract(String contract_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	retval = this.doCurl("GET","/contract",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the active contracts from the ID supplied
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  GETContractBy_entity_id(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/contract/by_entity_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a contract from the payment provider id supplied
   *
   *  @param payment_provider
   *  @param payment_provider_id
   *  @return - the data from the api
  */
  public String  GETContractBy_payment_provider_id(String payment_provider,String payment_provider_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("payment_provider", payment_provider);
    	params.put("payment_provider_id", payment_provider_id);
    	retval = this.doCurl("GET","/contract/by_payment_provider_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the active contracts from the ID supplied
   *
   *  @param user_id
   *  @return - the data from the api
  */
  public String  GETContractBy_user_id(String user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	retval = this.doCurl("GET","/contract/by_user_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Cancels an existing contract for a given id
   *
   *  @param contract_id
   *  @return - the data from the api
  */
  public String  POSTContractCancel(String contract_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	retval = this.doCurl("POST","/contract/cancel",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Creates a new contract for a given entity
   *
   *  @param entity_id
   *  @param user_id
   *  @param payment_provider
   *  @param basket
   *  @param taxrate
   *  @param billing_period
   *  @param source
   *  @param channel
   *  @param campaign
   *  @param referrer_domain
   *  @param referrer_name
   *  @param flatpack_id
   *  @return - the data from the api
  */
  public String  POSTContractCreate(String entity_id,String user_id,String payment_provider,String basket,String taxrate,String billing_period,String source,String channel,String campaign,String referrer_domain,String referrer_name,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("user_id", user_id);
    	params.put("payment_provider", payment_provider);
    	params.put("basket", basket);
    	params.put("taxrate", taxrate);
    	params.put("billing_period", billing_period);
    	params.put("source", source);
    	params.put("channel", channel);
    	params.put("campaign", campaign);
    	params.put("referrer_domain", referrer_domain);
    	params.put("referrer_name", referrer_name);
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("POST","/contract/create",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Activate a contract that is free
   *
   *  @param contract_id
   *  @param user_name
   *  @param user_surname
   *  @param user_email_address
   *  @return - the data from the api
  */
  public String  POSTContractFreeactivate(String contract_id,String user_name,String user_surname,String user_email_address) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	params.put("user_name", user_name);
    	params.put("user_surname", user_surname);
    	params.put("user_email_address", user_email_address);
    	retval = this.doCurl("POST","/contract/freeactivate",params);
    } finally { 
    }
    return retval;
  }


  /**
   * When we failed to receive money add the dates etc to the contract
   *
   *  @param contract_id
   *  @param failure_reason
   *  @param payment_date
   *  @param amount
   *  @param currency
   *  @param response
   *  @return - the data from the api
  */
  public String  POSTContractPaymentFailure(String contract_id,String failure_reason,String payment_date,String amount,String currency,String response) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	params.put("failure_reason", failure_reason);
    	params.put("payment_date", payment_date);
    	params.put("amount", amount);
    	params.put("currency", currency);
    	params.put("response", response);
    	retval = this.doCurl("POST","/contract/payment/failure",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds payment details to a given contract_id
   *
   *  @param contract_id
   *  @param payment_provider_id
   *  @param payment_provider_profile
   *  @param user_name
   *  @param user_surname
   *  @param user_billing_address
   *  @param user_email_address
   *  @return - the data from the api
  */
  public String  POSTContractPaymentSetup(String contract_id,String payment_provider_id,String payment_provider_profile,String user_name,String user_surname,String user_billing_address,String user_email_address) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	params.put("payment_provider_id", payment_provider_id);
    	params.put("payment_provider_profile", payment_provider_profile);
    	params.put("user_name", user_name);
    	params.put("user_surname", user_surname);
    	params.put("user_billing_address", user_billing_address);
    	params.put("user_email_address", user_email_address);
    	retval = this.doCurl("POST","/contract/payment/setup",params);
    } finally { 
    }
    return retval;
  }


  /**
   * When we receive money add the dates etc to the contract
   *
   *  @param contract_id
   *  @param payment_date
   *  @param amount
   *  @param currency
   *  @param response
   *  @return - the data from the api
  */
  public String  POSTContractPaymentSuccess(String contract_id,String payment_date,String amount,String currency,String response) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	params.put("payment_date", payment_date);
    	params.put("amount", amount);
    	params.put("currency", currency);
    	params.put("response", response);
    	retval = this.doCurl("POST","/contract/payment/success",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Go through all the products in a contract and provision them
   *
   *  @param contract_id
   *  @return - the data from the api
  */
  public String  POSTContractProvision(String contract_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	retval = this.doCurl("POST","/contract/provision",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Ensures contract has been cancelled for a given id, expected to be called from stripe on deletion of subscription
   *
   *  @param contract_id
   *  @return - the data from the api
  */
  public String  POSTContractSubscriptionended(String contract_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	retval = this.doCurl("POST","/contract/subscriptionended",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the contract log from the ID supplied
   *
   *  @param contract_log_id
   *  @return - the data from the api
  */
  public String  GETContract_log(String contract_log_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_log_id", contract_log_id);
    	retval = this.doCurl("GET","/contract_log",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Creates a new contract log for a given contract
   *
   *  @param contract_id
   *  @param date
   *  @param payment_provider
   *  @param response
   *  @param success
   *  @param amount
   *  @param currency
   *  @return - the data from the api
  */
  public String  POSTContract_log(String contract_id,String date,String payment_provider,String response,String success,String amount,String currency) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	params.put("date", date);
    	params.put("payment_provider", payment_provider);
    	params.put("response", response);
    	params.put("success", success);
    	params.put("amount", amount);
    	params.put("currency", currency);
    	retval = this.doCurl("POST","/contract_log",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the contract logs from the ID supplied
   *
   *  @param contract_id
   *  @param page
   *  @param per_page
   *  @return - the data from the api
  */
  public String  GETContract_logBy_contract_id(String contract_id,String page,String per_page) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("contract_id", contract_id);
    	params.put("page", page);
    	params.put("per_page", per_page);
    	retval = this.doCurl("GET","/contract_log/by_contract_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the contract logs from the payment_provider supplied
   *
   *  @param payment_provider
   *  @param page
   *  @param per_page
   *  @return - the data from the api
  */
  public String  GETContract_logBy_payment_provider(String payment_provider,String page,String per_page) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("payment_provider", payment_provider);
    	params.put("page", page);
    	params.put("per_page", per_page);
    	retval = this.doCurl("GET","/contract_log/by_payment_provider",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a country
   *
   *  @param country_id
   *  @param name
   *  @param synonyms
   *  @param continentName
   *  @param continent
   *  @param geonameId
   *  @param dbpediaURL
   *  @param freebaseURL
   *  @param population
   *  @param currencyCode
   *  @param languages
   *  @param areaInSqKm
   *  @param capital
   *  @param east
   *  @param west
   *  @param north
   *  @param south
   *  @param claimProductId
   *  @param claimMethods
   *  @param twilio_sms
   *  @param twilio_phone
   *  @param twilio_voice
   *  @param currency_symbol - the symbol of this country's currency
   *  @param currency_symbol_html - the html version of the symbol of this country's currency
   *  @param postcodeLookupActive - Whether the lookup is activated for this country
   *  @param addressFields - Whether fields are activated for this country
   *  @param addressMatching - The configurable matching algorithm
   *  @param dateFormat - The format of the date for this country
   *  @param iso_3166_alpha_3
   *  @param iso_3166_numeric
   *  @return - the data from the api
  */
  public String  POSTCountry(String country_id,String name,String synonyms,String continentName,String continent,String geonameId,String dbpediaURL,String freebaseURL,String population,String currencyCode,String languages,String areaInSqKm,String capital,String east,String west,String north,String south,String claimProductId,String claimMethods,String twilio_sms,String twilio_phone,String twilio_voice,String currency_symbol,String currency_symbol_html,String postcodeLookupActive,String addressFields,String addressMatching,String dateFormat,String iso_3166_alpha_3,String iso_3166_numeric) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country_id", country_id);
    	params.put("name", name);
    	params.put("synonyms", synonyms);
    	params.put("continentName", continentName);
    	params.put("continent", continent);
    	params.put("geonameId", geonameId);
    	params.put("dbpediaURL", dbpediaURL);
    	params.put("freebaseURL", freebaseURL);
    	params.put("population", population);
    	params.put("currencyCode", currencyCode);
    	params.put("languages", languages);
    	params.put("areaInSqKm", areaInSqKm);
    	params.put("capital", capital);
    	params.put("east", east);
    	params.put("west", west);
    	params.put("north", north);
    	params.put("south", south);
    	params.put("claimProductId", claimProductId);
    	params.put("claimMethods", claimMethods);
    	params.put("twilio_sms", twilio_sms);
    	params.put("twilio_phone", twilio_phone);
    	params.put("twilio_voice", twilio_voice);
    	params.put("currency_symbol", currency_symbol);
    	params.put("currency_symbol_html", currency_symbol_html);
    	params.put("postcodeLookupActive", postcodeLookupActive);
    	params.put("addressFields", addressFields);
    	params.put("addressMatching", addressMatching);
    	params.put("dateFormat", dateFormat);
    	params.put("iso_3166_alpha_3", iso_3166_alpha_3);
    	params.put("iso_3166_numeric", iso_3166_numeric);
    	retval = this.doCurl("POST","/country",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetching a country
   *
   *  @param country_id
   *  @return - the data from the api
  */
  public String  GETCountry(String country_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country_id", country_id);
    	retval = this.doCurl("GET","/country",params);
    } finally { 
    }
    return retval;
  }


  /**
   * An API call to fetch a crash report by its ID
   *
   *  @param crash_report_id - The crash report to pull
   *  @return - the data from the api
  */
  public String  GETCrash_report(String crash_report_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("crash_report_id", crash_report_id);
    	retval = this.doCurl("GET","/crash_report",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Send an email via amazon
   *
   *  @param to_email_address - The email address to send the email too
   *  @param reply_email_address - The email address to add in the reply to field
   *  @param source_account - The source account to send the email from
   *  @param subject - The subject for the email
   *  @param body - The body for the email
   *  @param html_body - If the body of the email is html
   *  @return - the data from the api
  */
  public String  POSTEmail(String to_email_address,String reply_email_address,String source_account,String subject,String body,String html_body) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("to_email_address", to_email_address);
    	params.put("reply_email_address", reply_email_address);
    	params.put("source_account", source_account);
    	params.put("subject", subject);
    	params.put("body", body);
    	params.put("html_body", html_body);
    	retval = this.doCurl("POST","/email",params);
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
   *  @param timezone
   *  @param trust
   *  @param our_data
   *  @return - the data from the api
  */
  public String  PUTEntity(String type,String scope,String country,String timezone,String trust,String our_data,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("type", type);
    	params.put("scope", scope);
    	params.put("country", country);
    	params.put("timezone", timezone);
    	params.put("trust", trust);
    	params.put("our_data", our_data);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("PUT","/entity",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a whole entity to be pulled from the datastore by its unique id
   *
   *  @param entity_id - The unique entity ID e.g. 379236608286720
   *  @param domain
   *  @param path
   *  @param data_filter
   *  @param filter_by_confidence
   *  @return - the data from the api
  */
  public String  GETEntity(String entity_id,String domain,String path,String data_filter,String filter_by_confidence) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("data_filter", data_filter);
    	params.put("filter_by_confidence", filter_by_confidence);
    	retval = this.doCurl("GET","/entity",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an add can be updated.
   *
   *  @param entity_id
   *  @param add_referrer_url
   *  @param add_referrer_name
   *  @return - the data from the api
  */
  public String  POSTEntityAdd(String entity_id,String add_referrer_url,String add_referrer_name,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("add_referrer_url", add_referrer_url);
    	params.put("add_referrer_name", add_referrer_name);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/add",params);
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
  public String  DELETEEntityAdvertiser(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/advertiser",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Expires an advertiser from and entity
   *
   *  @param entity_id
   *  @param publisher_id
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @return - the data from the api
  */
  public String  POSTEntityAdvertiserCancel(String entity_id,String publisher_id,String reseller_ref,String reseller_agent_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/cancel",params);
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
   *  @param loc_tags
   *  @param region_tags
   *  @param max_tags
   *  @param max_locations
   *  @param expiry_date
   *  @param is_national
   *  @param is_regional
   *  @param language
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  POSTEntityAdvertiserCreate(String entity_id,String tags,String locations,String loc_tags,String region_tags,String max_tags,String max_locations,String expiry_date,String is_national,String is_regional,String language,String reseller_ref,String reseller_agent_id,String publisher_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("tags", tags);
    	params.put("locations", locations);
    	params.put("loc_tags", loc_tags);
    	params.put("region_tags", region_tags);
    	params.put("max_tags", max_tags);
    	params.put("max_locations", max_locations);
    	params.put("expiry_date", expiry_date);
    	params.put("is_national", is_national);
    	params.put("is_regional", is_regional);
    	params.put("language", language);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
    	params.put("publisher_id", publisher_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/create",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds/removes locations
   *
   *  @param entity_id
   *  @param gen_id
   *  @param locations_to_add
   *  @param locations_to_remove
   *  @return - the data from the api
  */
  public String  POSTEntityAdvertiserLocation(String entity_id,String gen_id,String locations_to_add,String locations_to_remove,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("locations_to_add", locations_to_add);
    	params.put("locations_to_remove", locations_to_remove);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a premium advertiser is cancelled
   *
   *  @param entity_id
   *  @param publisher_id
   *  @param type
   *  @param category_id - The category of the advertiser
   *  @param location_id - The location of the advertiser
   *  @return - the data from the api
  */
  public String  POSTEntityAdvertiserPremiumCancel(String entity_id,String publisher_id,String type,String category_id,String location_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	params.put("type", type);
    	params.put("category_id", category_id);
    	params.put("location_id", location_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/premium/cancel",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a premium advertiser is added
   *
   *  @param entity_id
   *  @param type
   *  @param category_id - The category of the advertiser
   *  @param location_id - The location of the advertiser
   *  @param expiry_date
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  POSTEntityAdvertiserPremiumCreate(String entity_id,String type,String category_id,String location_id,String expiry_date,String reseller_ref,String reseller_agent_id,String publisher_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("type", type);
    	params.put("category_id", category_id);
    	params.put("location_id", location_id);
    	params.put("expiry_date", expiry_date);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
    	params.put("publisher_id", publisher_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/premium/create",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Renews an existing premium advertiser in an entity
   *
   *  @param entity_id
   *  @param type
   *  @param category_id - The category of the advertiser
   *  @param location_id - The location of the advertiser
   *  @param expiry_date
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  POSTEntityAdvertiserPremiumRenew(String entity_id,String type,String category_id,String location_id,String expiry_date,String reseller_ref,String reseller_agent_id,String publisher_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("type", type);
    	params.put("category_id", category_id);
    	params.put("location_id", location_id);
    	params.put("expiry_date", expiry_date);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
    	params.put("publisher_id", publisher_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/premium/renew",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Renews an advertiser from an entity
   *
   *  @param entity_id
   *  @param expiry_date
   *  @param publisher_id
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @return - the data from the api
  */
  public String  POSTEntityAdvertiserRenew(String entity_id,String expiry_date,String publisher_id,String reseller_ref,String reseller_agent_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("expiry_date", expiry_date);
    	params.put("publisher_id", publisher_id);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/renew",params);
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
  public String  POSTEntityAdvertiserTag(String gen_id,String entity_id,String language,String tags_to_add,String tags_to_remove,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("gen_id", gen_id);
    	params.put("entity_id", entity_id);
    	params.put("language", language);
    	params.put("tags_to_add", tags_to_add);
    	params.put("tags_to_remove", tags_to_remove);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/tag",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an advertiser is updated
   *
   *  @param entity_id
   *  @param tags
   *  @param locations
   *  @param loc_tags
   *  @param is_regional
   *  @param region_tags
   *  @param extra_tags
   *  @param extra_locations
   *  @param is_national
   *  @param language
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  POSTEntityAdvertiserUpsell(String entity_id,String tags,String locations,String loc_tags,String is_regional,String region_tags,String extra_tags,String extra_locations,String is_national,String language,String reseller_ref,String reseller_agent_id,String publisher_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("tags", tags);
    	params.put("locations", locations);
    	params.put("loc_tags", loc_tags);
    	params.put("is_regional", is_regional);
    	params.put("region_tags", region_tags);
    	params.put("extra_tags", extra_tags);
    	params.put("extra_locations", extra_locations);
    	params.put("is_national", is_national);
    	params.put("language", language);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
    	params.put("publisher_id", publisher_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/advertiser/upsell",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities that are advertisers and return a random selection upto the limit requested
   *
   *  @param tag - The word or words the advertiser is to appear for in searches
   *  @param where - The location to get results for. E.g. Dublin
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param is_national
   *  @param limit - The number of advertisers that are to be returned
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @param benchmark
   *  @return - the data from the api
  */
  public String  GETEntityAdvertisers(String tag,String where,String orderonline,String delivers,String isClaimed,String is_national,String limit,String country,String language,String benchmark) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tag", tag);
    	params.put("where", where);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("is_national", is_national);
    	params.put("limit", limit);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("benchmark", benchmark);
    	retval = this.doCurl("GET","/entity/advertisers",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities in a specified location that are advertisers and return a random selection upto the limit requested
   *
   *  @param location - The location to get results for. E.g. Dublin
   *  @param is_national
   *  @param limit - The number of advertisers that are to be returned
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @return - the data from the api
  */
  public String  GETEntityAdvertisersBy_location(String location,String is_national,String limit,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location", location);
    	params.put("is_national", is_national);
    	params.put("limit", limit);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/advertisers/by_location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Check if an entity has an advert from a specified publisher
   *
   *  @param entity_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  GETEntityAdvertisersInventorycheck(String entity_id,String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("GET","/entity/advertisers/inventorycheck",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get advertisers premium
   *
   *  @param what
   *  @param where
   *  @param type
   *  @param country
   *  @param language
   *  @return - the data from the api
  */
  public String  GETEntityAdvertisersPremium(String what,String where,String type,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("where", where);
    	params.put("type", type);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/entity/advertisers/premium",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Deleteing an affiliate adblock from a known entity
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityAffiliate_adblock(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/affiliate_adblock",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adding an affiliate adblock to a known entity
   *
   *  @param entity_id
   *  @param adblock - Number of results returned per page
   *  @return - the data from the api
  */
  public String  POSTEntityAffiliate_adblock(String entity_id,String adblock,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("adblock", adblock);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/affiliate_adblock",params);
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
   *  @param affiliate_action
   *  @return - the data from the api
  */
  public String  POSTEntityAffiliate_link(String entity_id,String affiliate_name,String affiliate_link,String affiliate_message,String affiliate_logo,String affiliate_action,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("affiliate_name", affiliate_name);
    	params.put("affiliate_link", affiliate_link);
    	params.put("affiliate_message", affiliate_message);
    	params.put("affiliate_logo", affiliate_logo);
    	params.put("affiliate_action", affiliate_action);
    	params.put("_user_id", _user_id);
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
  public String  DELETEEntityAffiliate_link(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/affiliate_link",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add/edit an annoucement object to an existing entity.
   *
   *  @param entity_id
   *  @param announcement_id
   *  @param headline
   *  @param body
   *  @param link_label
   *  @param link
   *  @param terms_link
   *  @param publish_date
   *  @param expiry_date
   *  @param media_type
   *  @param image_url
   *  @param video_url
   *  @param type - Type of announcement, which affects how it is displayed.
   *  @return - the data from the api
  */
  public String  POSTEntityAnnouncement(String entity_id,String announcement_id,String headline,String body,String link_label,String link,String terms_link,String publish_date,String expiry_date,String media_type,String image_url,String video_url,String type,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("announcement_id", announcement_id);
    	params.put("headline", headline);
    	params.put("body", body);
    	params.put("link_label", link_label);
    	params.put("link", link);
    	params.put("terms_link", terms_link);
    	params.put("publish_date", publish_date);
    	params.put("expiry_date", expiry_date);
    	params.put("media_type", media_type);
    	params.put("image_url", image_url);
    	params.put("video_url", video_url);
    	params.put("type", type);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/announcement",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch an announcement object from an existing entity.
   *
   *  @param entity_id
   *  @param announcement_id
   *  @return - the data from the api
  */
  public String  GETEntityAnnouncement(String entity_id,String announcement_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("announcement_id", announcement_id);
    	retval = this.doCurl("GET","/entity/announcement",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Remove an announcement object to an existing entity.
   *
   *  @param entity_id
   *  @param announcement_id
   *  @return - the data from the api
  */
  public String  DELETEEntityAnnouncement(String entity_id,String announcement_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("announcement_id", announcement_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/announcement",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Will create a new association_membership or update an existing one
   *
   *  @param entity_id
   *  @param association_id
   *  @param association_member_url
   *  @param association_member_id
   *  @return - the data from the api
  */
  public String  POSTEntityAssociation_membership(String entity_id,String association_id,String association_member_url,String association_member_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("association_id", association_id);
    	params.put("association_member_url", association_member_url);
    	params.put("association_member_id", association_member_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/association_membership",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a association_membership object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityAssociation_membership(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/association_membership",params);
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
  public String  POSTEntityBackground(String entity_id,String number_of_employees,String turnover,String net_profit,String vat_number,String duns_number,String registered_company_number,String _user_id) throws Exception { 
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
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/background",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a brand object can be added.
   *
   *  @param entity_id
   *  @param value
   *  @return - the data from the api
  */
  public String  POSTEntityBrand(String entity_id,String value,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("value", value);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/brand",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a brand object can be deleted.
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityBrand(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/brand",params);
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
  public String  POSTEntityBulkCsv(String filedata,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("filedata", filedata);
    	params.put("_user_id", _user_id);
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
  public String  GETEntityBulkCsvStatus(String upload_id) throws Exception { 
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
   * Uploads a JSON file of known format and bulk inserts into DB
   *
   *  @param data
   *  @param new_entities
   *  @return - the data from the api
  */
  public String  POSTEntityBulkJson(String data,String new_entities,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("data", data);
    	params.put("new_entities", new_entities);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/bulk/json",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Shows the current status of a bulk JSON upload
   *
   *  @param upload_id
   *  @return - the data from the api
  */
  public String  GETEntityBulkJsonStatus(String upload_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("upload_id", upload_id);
    	retval = this.doCurl("GET","/entity/bulk/json/status",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetches the document that matches the given data_source_type and external_id.
   *
   *  @param data_source_type - The data source type of the entity
   *  @param external_id - The external ID of the entity
   *  @return - the data from the api
  */
  public String  GETEntityBy_external_id(String data_source_type,String external_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("data_source_type", data_source_type);
    	params.put("external_id", external_id);
    	retval = this.doCurl("GET","/entity/by_external_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get all entities within a specified group
   *
   *  @param group_id
   *  @return - the data from the api
  */
  public String  GETEntityBy_groupid(String group_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	retval = this.doCurl("GET","/entity/by_groupid",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetches the document that matches the given legacy_url
   *
   *  @param legacy_url - The URL of the entity in the directory it was imported from.
   *  @return - the data from the api
  */
  public String  GETEntityBy_legacy_url(String legacy_url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("legacy_url", legacy_url);
    	retval = this.doCurl("GET","/entity/by_legacy_url",params);
    } finally { 
    }
    return retval;
  }


  /**
   * uncontributes a given entities supplier content and makes the entity inactive if the entity is un-usable
   *
   *  @param entity_id - The entity to pull
   *  @param supplier_masheryid - The suppliers masheryid to match
   *  @param supplier_id - The supplier id to match
   *  @param supplier_user_id - The user id to match
   *  @return - the data from the api
  */
  public String  DELETEEntityBy_supplier(String entity_id,String supplier_masheryid,String supplier_id,String supplier_user_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("supplier_masheryid", supplier_masheryid);
    	params.put("supplier_id", supplier_id);
    	params.put("supplier_user_id", supplier_user_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/by_supplier",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetches the documents that match the given masheryid and supplier_id
   *
   *  @param supplier_id - The Supplier ID, or a list of supplier IDs separated by comma
   *  @return - the data from the api
  */
  public String  GETEntityBy_supplier_id(String supplier_id) throws Exception { 
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
   * Get all entities added or claimed by a specific user
   *
   *  @param user_id - The unique user ID of the user with claimed entities e.g. 379236608286720
   *  @param filter
   *  @param skip
   *  @param limit
   *  @return - the data from the api
  */
  public String  GETEntityBy_user_id(String user_id,String filter,String skip,String limit) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	params.put("filter", filter);
    	params.put("skip", skip);
    	params.put("limit", limit);
    	retval = this.doCurl("GET","/entity/by_user_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an category object can be added.
   *
   *  @param entity_id
   *  @param category_id
   *  @param category_type
   *  @return - the data from the api
  */
  public String  POSTEntityCategory(String entity_id,String category_id,String category_type,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("category_id", category_id);
    	params.put("category_type", category_type);
    	params.put("_user_id", _user_id);
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
  public String  DELETEEntityCategory(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/category",params);
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
  public String  GETEntityChangelog(String entity_id) throws Exception { 
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
   * Unlike cancel, this operation remove the claim data from the entity
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  DELETEEntityClaim(String entity_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/claim",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allow an entity to be claimed by a valid user
   *
   *  @param entity_id
   *  @param claimed_user_id
   *  @param claimed_reseller_id
   *  @param expiry_date
   *  @param claimed_date
   *  @param verified_status - If set to a value, this field will promote the claim to pro mode (expiry aligned with claim expiry)
   *  @param claim_method
   *  @param phone_number
   *  @param referrer_url
   *  @param referrer_name
   *  @param reseller_ref
   *  @param reseller_description
   *  @return - the data from the api
  */
  public String  POSTEntityClaim(String entity_id,String claimed_user_id,String claimed_reseller_id,String expiry_date,String claimed_date,String verified_status,String claim_method,String phone_number,String referrer_url,String referrer_name,String reseller_ref,String reseller_description,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("claimed_user_id", claimed_user_id);
    	params.put("claimed_reseller_id", claimed_reseller_id);
    	params.put("expiry_date", expiry_date);
    	params.put("claimed_date", claimed_date);
    	params.put("verified_status", verified_status);
    	params.put("claim_method", claim_method);
    	params.put("phone_number", phone_number);
    	params.put("referrer_url", referrer_url);
    	params.put("referrer_name", referrer_name);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_description", reseller_description);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/claim",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Cancel a claim that is on the entity
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  POSTEntityClaimCancel(String entity_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/claim/cancel",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allow an entity to be claimed by a valid user
   *
   *  @param entity_id
   *  @param claimed_user_id
   *  @param reseller_ref
   *  @param reseller_description
   *  @param expiry_date
   *  @param renew_verify - Update the verified_status (where present) as well. Paid claims should do this -- free claims generally will not.
   *  @return - the data from the api
  */
  public String  POSTEntityClaimRenew(String entity_id,String claimed_user_id,String reseller_ref,String reseller_description,String expiry_date,String renew_verify,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("claimed_user_id", claimed_user_id);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_description", reseller_description);
    	params.put("expiry_date", expiry_date);
    	params.put("renew_verify", renew_verify);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/claim/renew",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allow an entity to be claimed by a valid reseller
   *
   *  @param entity_id
   *  @param reseller_ref
   *  @param reseller_description
   *  @return - the data from the api
  */
  public String  POSTEntityClaimReseller(String entity_id,String _user_id,String reseller_ref,String reseller_description) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("_user_id", _user_id);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_description", reseller_description);
    	retval = this.doCurl("POST","/entity/claim/reseller",params);
    } finally { 
    }
    return retval;
  }


  /**
   * If an entity is currently claimed then set or remove the verified_entity block (Expiry will match the claim expiry)
   *
   *  @param entity_id
   *  @param verified_status - If set to a value, this field will promote the claim to pro mode. If blank, verified status will be wiped
   *  @return - the data from the api
  */
  public String  POSTEntityClaimVerfied_status(String entity_id,String verified_status,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("verified_status", verified_status);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/claim/verfied_status",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add/change delivers flag for an existing entity - to indicate whether business offers delivery
   *
   *  @param entity_id
   *  @param delivers
   *  @return - the data from the api
  */
  public String  POSTEntityDelivers(String entity_id,String delivers,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("delivers", delivers);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/delivers",params);
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
  public String  DELETEEntityDescription(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/description",params);
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
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  POSTEntityDescription(String entity_id,String headline,String body,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("headline", headline);
    	params.put("body", body);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/description",params);
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
  public String  POSTEntityDocument(String entity_id,String name,String filedata,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("name", name);
    	params.put("filedata", filedata);
    	params.put("_user_id", _user_id);
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
  public String  DELETEEntityDocument(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/document",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Upload a document to an entity
   *
   *  @param entity_id
   *  @param document
   *  @return - the data from the api
  */
  public String  POSTEntityDocumentBy_url(String entity_id,String document,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("document", document);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/document/by_url",params);
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
  public String  DELETEEntityEmail(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/email",params);
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
  public String  POSTEntityEmail(String entity_id,String email_address,String email_description,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("email_address", email_address);
    	params.put("email_description", email_description);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/email",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch an emergency statement object from an existing entity.
   *
   *  @param entity_id
   *  @param emergencystatement_id
   *  @return - the data from the api
  */
  public String  GETEntityEmergencystatement(String entity_id,String emergencystatement_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("emergencystatement_id", emergencystatement_id);
    	retval = this.doCurl("GET","/entity/emergencystatement",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add or update an emergency statement object to an existing entity.
   *
   *  @param entity_id
   *  @param id
   *  @param headline
   *  @param body
   *  @param link_label
   *  @param link
   *  @param publish_date
   *  @return - the data from the api
  */
  public String  POSTEntityEmergencystatement(String entity_id,String id,String headline,String body,String link_label,String link,String publish_date,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("id", id);
    	params.put("headline", headline);
    	params.put("body", body);
    	params.put("link_label", link_label);
    	params.put("link", link);
    	params.put("publish_date", publish_date);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/emergencystatement",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Remove an emergencystatement object to an existing entity.
   *
   *  @param entity_id
   *  @param emergencystatement_id
   *  @return - the data from the api
  */
  public String  DELETEEntityEmergencystatement(String entity_id,String emergencystatement_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("emergencystatement_id", emergencystatement_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/emergencystatement",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch emergency statement objects from an existing entity.
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  GETEntityEmergencystatements(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/entity/emergencystatements",params);
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
  public String  POSTEntityEmployee(String entity_id,String title,String forename,String surname,String job_title,String description,String email,String phone_number,String _user_id) throws Exception { 
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
    	params.put("_user_id", _user_id);
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
  public String  DELETEEntityEmployee(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/employee",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an FAQ question and answer can be added.
   *
   *  @param entity_id
   *  @param question
   *  @param answer
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  POSTEntityFaq(String entity_id,String question,String answer,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("question", question);
    	params.put("answer", answer);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/faq",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an FAQ question and answer can be removed.
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityFaq(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/faq",params);
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
  public String  DELETEEntityFax(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/fax",params);
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
  public String  POSTEntityFax(String entity_id,String number,String description,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("number", number);
    	params.put("description", description);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/fax",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a featured message can be added
   *
   *  @param entity_id
   *  @param featured_text
   *  @param featured_url
   *  @return - the data from the api
  */
  public String  POSTEntityFeatured_message(String entity_id,String featured_text,String featured_url,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("featured_text", featured_text);
    	params.put("featured_url", featured_url);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/featured_message",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a featured message object to be removed
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  DELETEEntityFeatured_message(String entity_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/featured_message",params);
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
  public String  POSTEntityGeopoint(String entity_id,String longitude,String latitude,String accuracy,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("longitude", longitude);
    	params.put("latitude", latitude);
    	params.put("accuracy", accuracy);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/geopoint",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a group  can be added to group members.
   *
   *  @param entity_id
   *  @param group_id
   *  @return - the data from the api
  */
  public String  POSTEntityGroup(String entity_id,String group_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("group_id", group_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/group",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a group object to be removed from an entities group members
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityGroup(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/group",params);
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
  public String  POSTEntityImage(String entity_id,String filedata,String image_name,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("filedata", filedata);
    	params.put("image_name", image_name);
    	params.put("_user_id", _user_id);
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
  public String  DELETEEntityImage(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/image",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a image can be retrieved from a url and added.
   *
   *  @param entity_id
   *  @param image_url
   *  @param image_name
   *  @return - the data from the api
  */
  public String  POSTEntityImageBy_url(String entity_id,String image_url,String image_name,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("image_url", image_url);
    	params.put("image_name", image_name);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/image/by_url",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, an invoice_address object can be updated.
   *
   *  @param entity_id
   *  @param building_number
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param province
   *  @param postcode
   *  @param address_type
   *  @return - the data from the api
  */
  public String  POSTEntityInvoice_address(String entity_id,String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String address_type,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("building_number", building_number);
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("province", province);
    	params.put("postcode", postcode);
    	params.put("address_type", address_type);
    	params.put("_user_id", _user_id);
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
  public String  DELETEEntityInvoice_address(String entity_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/invoice_address",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a language object can be deleted.
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityLanguage(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/language",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a language object can be added.
   *
   *  @param entity_id
   *  @param value
   *  @return - the data from the api
  */
  public String  POSTEntityLanguage(String entity_id,String value,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("value", value);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/language",params);
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
  public String  DELETEEntityList(String gen_id,String entity_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("gen_id", gen_id);
    	params.put("entity_id", entity_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/list",params);
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
  public String  POSTEntityList(String entity_id,String headline,String body,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("headline", headline);
    	params.put("body", body);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/list",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find all entities in a group
   *
   *  @param group_id - A valid group_id
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @return - the data from the api
  */
  public String  GETEntityList_by_group_id(String group_id,String per_page,String page) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	retval = this.doCurl("GET","/entity/list_by_group_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds/removes loc_tags
   *
   *  @param entity_id
   *  @param gen_id
   *  @param loc_tags_to_add
   *  @param loc_tags_to_remove
   *  @return - the data from the api
  */
  public String  POSTEntityLoc_tag(String entity_id,String gen_id,String loc_tags_to_add,String loc_tags_to_remove,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("loc_tags_to_add", loc_tags_to_add);
    	params.put("loc_tags_to_remove", loc_tags_to_remove);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/loc_tag",params);
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
  public String  DELETEEntityLogo(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/logo",params);
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
  public String  POSTEntityLogo(String entity_id,String filedata,String logo_name,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("filedata", filedata);
    	params.put("logo_name", logo_name);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a logo can be retrieved from a url and added.
   *
   *  @param entity_id
   *  @param logo_url
   *  @param logo_name
   *  @return - the data from the api
  */
  public String  POSTEntityLogoBy_url(String entity_id,String logo_url,String logo_name,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("logo_url", logo_url);
    	params.put("logo_name", logo_name);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/logo/by_url",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Merge two entities into one
   *
   *  @param from
   *  @param to
   *  @param override_trust - Do you want to override the trust of the 'from' entity
   *  @param uncontribute_masheryid - Do we want to uncontribute any data for a masheryid?
   *  @param uncontribute_userid - Do we want to uncontribute any data for a user_id?
   *  @param uncontribute_supplierid - Do we want to uncontribute any data for a supplier_id?
   *  @param delete_mode - The type of object contribution deletion
   *  @return - the data from the api
  */
  public String  POSTEntityMerge(String from,String to,String override_trust,String uncontribute_masheryid,String uncontribute_userid,String uncontribute_supplierid,String delete_mode,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from", from);
    	params.put("to", to);
    	params.put("override_trust", override_trust);
    	params.put("uncontribute_masheryid", uncontribute_masheryid);
    	params.put("uncontribute_userid", uncontribute_userid);
    	params.put("uncontribute_supplierid", uncontribute_supplierid);
    	params.put("delete_mode", delete_mode);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/merge",params);
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
  public String  POSTEntityMigrate_category(String from,String to,String limit) throws Exception { 
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
   * With a known entity id, a name can be updated.
   *
   *  @param entity_id
   *  @param name
   *  @param formal_name
   *  @param branch_name
   *  @return - the data from the api
  */
  public String  POSTEntityName(String entity_id,String name,String formal_name,String branch_name,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("name", name);
    	params.put("formal_name", formal_name);
    	params.put("branch_name", branch_name);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/name",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a opening times object can be added. Each day can be either 'closed' to indicate that the entity is closed that day, '24hour' to indicate that the entity is open all day or single/split time ranges can be supplied in 4-digit 24-hour format, such as '09001730' or '09001200,13001700' to indicate hours of opening.
   *
   *  @param entity_id - The id of the entity to edit
   *  @param statement - Statement describing reasons for special opening/closing times
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
  public String  POSTEntityOpening_times(String entity_id,String statement,String monday,String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday,String closed,String closed_public_holidays,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("statement", statement);
    	params.put("monday", monday);
    	params.put("tuesday", tuesday);
    	params.put("wednesday", wednesday);
    	params.put("thursday", thursday);
    	params.put("friday", friday);
    	params.put("saturday", saturday);
    	params.put("sunday", sunday);
    	params.put("closed", closed);
    	params.put("closed_public_holidays", closed_public_holidays);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/opening_times",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a opening times object can be removed.
   *
   *  @param entity_id - The id of the entity to edit
   *  @return - the data from the api
  */
  public String  DELETEEntityOpening_times(String entity_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/opening_times",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add an order online to an existing entity - to indicate e-commerce capability.
   *
   *  @param entity_id
   *  @param orderonline
   *  @return - the data from the api
  */
  public String  POSTEntityOrderonline(String entity_id,String orderonline,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("orderonline", orderonline);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/orderonline",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a payment_type object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityPayment_type(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/payment_type",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a payment_type object can be added.
   *
   *  @param entity_id - the id of the entity to add the payment type to
   *  @param payment_type - the payment type to add to the entity
   *  @return - the data from the api
  */
  public String  POSTEntityPayment_type(String entity_id,String payment_type,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("payment_type", payment_type);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/payment_type",params);
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
   *  @param trackable
   *  @return - the data from the api
  */
  public String  POSTEntityPhone(String entity_id,String number,String description,String trackable,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("number", number);
    	params.put("description", description);
    	params.put("trackable", trackable);
    	params.put("_user_id", _user_id);
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
  public String  DELETEEntityPhone(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/phone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create/Update a postal address
   *
   *  @param entity_id
   *  @param building_number
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param province
   *  @param postcode
   *  @param address_type
   *  @param do_not_display
   *  @return - the data from the api
  */
  public String  POSTEntityPostal_address(String entity_id,String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String address_type,String do_not_display,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("building_number", building_number);
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("province", province);
    	params.put("postcode", postcode);
    	params.put("address_type", address_type);
    	params.put("do_not_display", do_not_display);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/postal_address",params);
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
  public String  GETEntityProvisionalBy_supplier_id(String supplier_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("supplier_id", supplier_id);
    	retval = this.doCurl("GET","/entity/provisional/by_supplier_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * removes a given entities supplier/masheryid/user_id content and makes the entity inactive if the entity is un-usable
   *
   *  @param entity_id - The entity to pull
   *  @param purge_masheryid - The purge masheryid to match
   *  @param purge_supplier_id - The purge supplier id to match
   *  @param purge_user_id - The purge user id to match
   *  @param exclude - List of entity fields that are excluded from the purge
   *  @param destructive
   *  @return - the data from the api
  */
  public String  POSTEntityPurge(String entity_id,String purge_masheryid,String purge_supplier_id,String purge_user_id,String _user_id,String exclude,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("purge_masheryid", purge_masheryid);
    	params.put("purge_supplier_id", purge_supplier_id);
    	params.put("purge_user_id", purge_user_id);
    	params.put("_user_id", _user_id);
    	params.put("exclude", exclude);
    	params.put("destructive", destructive);
    	retval = this.doCurl("POST","/entity/purge",params);
    } finally { 
    }
    return retval;
  }


  /**
   * removes a portion of a given entity and makes the entity inactive if the resulting leftover entity is un-usable
   *
   *  @param entity_id - The entity to pull
   *  @param object
   *  @param gen_id - The gen_id of any multi-object being purged
   *  @param purge_masheryid - The purge masheryid to match
   *  @param purge_supplier_id - The purge supplier id to match
   *  @param purge_user_id - The purge user id to match
   *  @param destructive
   *  @return - the data from the api
  */
  public String  POSTEntityPurgeBy_object(String entity_id,String object,String gen_id,String purge_masheryid,String purge_supplier_id,String purge_user_id,String _user_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("object", object);
    	params.put("gen_id", gen_id);
    	params.put("purge_masheryid", purge_masheryid);
    	params.put("purge_supplier_id", purge_supplier_id);
    	params.put("purge_user_id", purge_user_id);
    	params.put("_user_id", _user_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("POST","/entity/purge/by_object",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Deletes a specific review for an entity via Review API
   *
   *  @param entity_id - The entity with the review
   *  @param review_id - The review id
   *  @return - the data from the api
  */
  public String  DELETEEntityReview(String entity_id,String review_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("review_id", review_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/review",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Gets a specific review  for an entity
   *
   *  @param entity_id - The entity with the review
   *  @param review_id - The review id
   *  @return - the data from the api
  */
  public String  GETEntityReview(String entity_id,String review_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("review_id", review_id);
    	retval = this.doCurl("GET","/entity/review",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Appends a review to an entity
   *
   *  @param entity_id - the entity to append the review to
   *  @param reviewer_user_id - The user id
   *  @param review_id - The review id. If this is supplied will attempt to update an existing review
   *  @param title - The title of the review
   *  @param content - The full text content of the review
   *  @param star_rating - The rating of the review
   *  @param domain - The domain the review originates from
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTEntityReview(String entity_id,String reviewer_user_id,String review_id,String title,String content,String star_rating,String domain,String filedata,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("reviewer_user_id", reviewer_user_id);
    	params.put("review_id", review_id);
    	params.put("title", title);
    	params.put("content", content);
    	params.put("star_rating", star_rating);
    	params.put("domain", domain);
    	params.put("filedata", filedata);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/review",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Gets all reviews for an entity
   *
   *  @param entity_id - The entity with the review
   *  @param limit - Limit the number of results returned
   *  @param skip - Number of results skipped
   *  @return - the data from the api
  */
  public String  GETEntityReviewList(String entity_id,String limit,String skip) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("limit", limit);
    	params.put("skip", skip);
    	retval = this.doCurl("GET","/entity/review/list",params);
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
  public String  GETEntityRevisions(String entity_id) throws Exception { 
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
  public String  GETEntityRevisionsByRevisionID(String entity_id,String revision_id) throws Exception { 
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
   * Search for matching entities
   *
   *  @param latitude_1
   *  @param longitude_1
   *  @param latitude_2
   *  @param longitude_2
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param per_page
   *  @param page
   *  @param country
   *  @param language
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @return - the data from the api
  */
  public String  GETEntitySearchByboundingbox(String latitude_1,String longitude_1,String latitude_2,String longitude_2,String orderonline,String delivers,String isClaimed,String per_page,String page,String country,String language,String domain,String path,String restrict_category_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("latitude_1", latitude_1);
    	params.put("longitude_1", longitude_1);
    	params.put("latitude_2", latitude_2);
    	params.put("longitude_2", longitude_2);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	retval = this.doCurl("GET","/entity/search/byboundingbox",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param where - Location to search for results. E.g. Dublin e.g. Dublin
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param per_page - How many results per page
   *  @param page - What page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the search context (optional)
   *  @param longitude - The decimal longitude of the search context (optional)
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @param benchmark
   *  @return - the data from the api
  */
  public String  GETEntitySearchBylocation(String where,String orderonline,String delivers,String isClaimed,String per_page,String page,String country,String language,String latitude,String longitude,String domain,String path,String restrict_category_ids,String benchmark) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("where", where);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	params.put("benchmark", benchmark);
    	retval = this.doCurl("GET","/entity/search/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for entities matching the supplied group_id, ordered by nearness
   *
   *  @param group_id - the group_id to search for
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param country - The country to fetch results for e.g. gb
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the centre point of the search
   *  @param longitude - The decimal longitude of the centre point of the search
   *  @param where - The location to search for
   *  @param domain
   *  @param path
   *  @param unitOfDistance
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @return - the data from the api
  */
  public String  GETEntitySearchGroupBynearest(String group_id,String orderonline,String delivers,String isClaimed,String country,String per_page,String page,String language,String latitude,String longitude,String where,String domain,String path,String unitOfDistance,String restrict_category_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("country", country);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("where", where);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("unitOfDistance", unitOfDistance);
    	params.put("restrict_category_ids", restrict_category_ids);
    	retval = this.doCurl("GET","/entity/search/group/bynearest",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for entities matching the supplied 'who', ordered by nearness. NOTE if you want to see any advertisers then append MASHERYID (even if using API key) and include_ads=true to get your ads matching that keyword and the derived location.
   *
   *  @param keyword - What to get results for. E.g. cafe e.g. cafe
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param country - The country to fetch results for e.g. gb
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the centre point of the search
   *  @param longitude - The decimal longitude of the centre point of the search
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @param include_ads - Find nearby advertisers with tags that match the keyword
   *  @return - the data from the api
  */
  public String  GETEntitySearchKeywordBynearest(String keyword,String orderonline,String delivers,String isClaimed,String country,String per_page,String page,String language,String latitude,String longitude,String domain,String path,String restrict_category_ids,String include_ads) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("keyword", keyword);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("country", country);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	params.put("include_ads", include_ads);
    	retval = this.doCurl("GET","/entity/search/keyword/bynearest",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param what - What to get results for. E.g. Plumber e.g. plumber
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param per_page - Number of results returned per page
   *  @param page - The page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @param benchmark
   *  @return - the data from the api
  */
  public String  GETEntitySearchWhat(String what,String orderonline,String delivers,String isClaimed,String per_page,String page,String country,String language,String domain,String path,String restrict_category_ids,String benchmark) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	params.put("benchmark", benchmark);
    	retval = this.doCurl("GET","/entity/search/what",params);
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
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param per_page
   *  @param page
   *  @param country - A valid ISO 3166 country code e.g. ie
   *  @param language
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @return - the data from the api
  */
  public String  GETEntitySearchWhatByboundingbox(String what,String latitude_1,String longitude_1,String latitude_2,String longitude_2,String orderonline,String delivers,String isClaimed,String per_page,String page,String country,String language,String domain,String path,String restrict_category_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("latitude_1", latitude_1);
    	params.put("longitude_1", longitude_1);
    	params.put("latitude_2", latitude_2);
    	params.put("longitude_2", longitude_2);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	retval = this.doCurl("GET","/entity/search/what/byboundingbox",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param what - What to get results for. E.g. Plumber e.g. plumber
   *  @param where - The location to get results for. E.g. Dublin e.g. Dublin
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the search context (optional)
   *  @param longitude - The decimal longitude of the search context (optional)
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @param benchmark
   *  @return - the data from the api
  */
  public String  GETEntitySearchWhatBylocation(String what,String where,String orderonline,String delivers,String isClaimed,String per_page,String page,String country,String language,String latitude,String longitude,String domain,String path,String restrict_category_ids,String benchmark) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("where", where);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	params.put("benchmark", benchmark);
    	retval = this.doCurl("GET","/entity/search/what/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities, ordered by nearness
   *
   *  @param what - What to get results for. E.g. Plumber e.g. plumber
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param country - The country to fetch results for e.g. gb
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the centre point of the search
   *  @param longitude - The decimal longitude of the centre point of the search
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @return - the data from the api
  */
  public String  GETEntitySearchWhatBynearest(String what,String orderonline,String delivers,String isClaimed,String country,String per_page,String page,String language,String latitude,String longitude,String domain,String path,String restrict_category_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("country", country);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	retval = this.doCurl("GET","/entity/search/what/bynearest",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities
   *
   *  @param who - Company name e.g. Starbucks
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param per_page - How many results per page
   *  @param page - What page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @param benchmark
   *  @return - the data from the api
  */
  public String  GETEntitySearchWho(String who,String orderonline,String delivers,String isClaimed,String per_page,String page,String country,String language,String domain,String path,String restrict_category_ids,String benchmark) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	params.put("benchmark", benchmark);
    	retval = this.doCurl("GET","/entity/search/who",params);
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
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param per_page
   *  @param page
   *  @param country
   *  @param language - An ISO compatible language code, E.g. en
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @return - the data from the api
  */
  public String  GETEntitySearchWhoByboundingbox(String who,String latitude_1,String longitude_1,String latitude_2,String longitude_2,String orderonline,String delivers,String isClaimed,String per_page,String page,String country,String language,String domain,String path,String restrict_category_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("latitude_1", latitude_1);
    	params.put("longitude_1", longitude_1);
    	params.put("latitude_2", latitude_2);
    	params.put("longitude_2", longitude_2);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
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
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param latitude - The decimal latitude of the search context (optional)
   *  @param longitude - The decimal longitude of the search context (optional)
   *  @param language - An ISO compatible language code, E.g. en
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @param benchmark
   *  @return - the data from the api
  */
  public String  GETEntitySearchWhoBylocation(String who,String where,String orderonline,String delivers,String isClaimed,String per_page,String page,String country,String latitude,String longitude,String language,String domain,String path,String restrict_category_ids,String benchmark) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("where", where);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	params.put("benchmark", benchmark);
    	retval = this.doCurl("GET","/entity/search/who/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for entities matching the supplied 'who', ordered by nearness
   *
   *  @param who - What to get results for. E.g. Plumber e.g. plumber
   *  @param orderonline - Favours online ordering where set to true
   *  @param delivers - Favours delivery where set to true
   *  @param isClaimed - 1: claimed; 0: not claimed or claim expired; -1: ignore this filter.
   *  @param country - The country to fetch results for e.g. gb
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the centre point of the search
   *  @param longitude - The decimal longitude of the centre point of the search
   *  @param domain
   *  @param path
   *  @param restrict_category_ids - Pipe delimited optional IDs to restrict matches to (optional)
   *  @return - the data from the api
  */
  public String  GETEntitySearchWhoBynearest(String who,String orderonline,String delivers,String isClaimed,String country,String per_page,String page,String language,String latitude,String longitude,String domain,String path,String restrict_category_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("orderonline", orderonline);
    	params.put("delivers", delivers);
    	params.put("isClaimed", isClaimed);
    	params.put("country", country);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("restrict_category_ids", restrict_category_ids);
    	retval = this.doCurl("GET","/entity/search/who/bynearest",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Send an email to an email address specified in an entity
   *
   *  @param entity_id - The entity id of the entity you wish to contact
   *  @param gen_id - The gen_id of the email address you wish to send the message to
   *  @param from_email - The email of the person sending the message 
   *  @param subject - The subject for the email
   *  @param content - The content of the email
   *  @return - the data from the api
  */
  public String  POSTEntitySend_email(String entity_id,String gen_id,String from_email,String subject,String content,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("from_email", from_email);
    	params.put("subject", subject);
    	params.put("content", content);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/send_email",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a service object can be added.
   *
   *  @param entity_id
   *  @param value
   *  @return - the data from the api
  */
  public String  POSTEntityService(String entity_id,String value,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("value", value);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/service",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a service object can be deleted.
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityService(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/service",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a social media object can be added.
   *
   *  @param entity_id
   *  @param type
   *  @param website_url
   *  @return - the data from the api
  */
  public String  POSTEntitySocialmedia(String entity_id,String type,String website_url,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("type", type);
    	params.put("website_url", website_url);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/socialmedia",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a social media object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntitySocialmedia(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/socialmedia",params);
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
  public String  DELETEEntitySpecial_offer(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/special_offer",params);
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
   *  @return - the data from the api
  */
  public String  POSTEntitySpecial_offer(String entity_id,String title,String description,String terms,String start_date,String expiry_date,String url,String _user_id) throws Exception { 
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
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/special_offer",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a status object can be updated.
   *
   *  @param entity_id
   *  @param status
   *  @param inactive_reason
   *  @param inactive_description
   *  @return - the data from the api
  */
  public String  POSTEntityStatus(String entity_id,String status,String inactive_reason,String inactive_description,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("status", status);
    	params.put("inactive_reason", inactive_reason);
    	params.put("inactive_description", inactive_description);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/status",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Suspend all entiies added or claimed by a specific user
   *
   *  @param user_id - The unique user ID of the user with claimed entities e.g. 379236608286720
   *  @return - the data from the api
  */
  public String  POSTEntityStatusSuspend_by_user_id(String user_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/status/suspend_by_user_id",params);
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
  public String  DELETEEntityTag(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/tag",params);
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
  public String  POSTEntityTag(String entity_id,String tag,String language,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("tag", tag);
    	params.put("language", language);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/tag",params);
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
  public String  DELETEEntityTestimonial(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/testimonial",params);
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
  public String  POSTEntityTestimonial(String entity_id,String title,String text,String date,String testifier_name,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("title", title);
    	params.put("text", text);
    	params.put("date", date);
    	params.put("testifier_name", testifier_name);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/testimonial",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the updates a uncontribute would perform
   *
   *  @param entity_id - The entity to pull
   *  @param object_name - The entity object to update
   *  @param supplier_id - The supplier_id to remove
   *  @param user_id - The user_id to remove
   *  @return - the data from the api
  */
  public String  GETEntityUncontribute(String entity_id,String object_name,String supplier_id,String user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("object_name", object_name);
    	params.put("supplier_id", supplier_id);
    	params.put("user_id", user_id);
    	retval = this.doCurl("GET","/entity/uncontribute",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Separates an entity into two distinct entities 
   *
   *  @param entity_id
   *  @param unmerge_masheryid
   *  @param unmerge_supplier_id
   *  @param unmerge_user_id
   *  @param destructive
   *  @return - the data from the api
  */
  public String  POSTEntityUnmerge(String entity_id,String unmerge_masheryid,String unmerge_supplier_id,String unmerge_user_id,String destructive,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("unmerge_masheryid", unmerge_masheryid);
    	params.put("unmerge_supplier_id", unmerge_supplier_id);
    	params.put("unmerge_user_id", unmerge_user_id);
    	params.put("destructive", destructive);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/unmerge",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find the provided user in all the sub objects and update the trust
   *
   *  @param entity_id - the entity_id to update
   *  @param user_id - the user to search for
   *  @param trust - The new trust for the user
   *  @return - the data from the api
  */
  public String  POSTEntityUser_trust(String entity_id,String user_id,String trust,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("user_id", user_id);
    	params.put("trust", trust);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/user_trust",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a verified source object to an existing entity.
   *
   *  @param entity_id
   *  @param public_source - Corresponds to entity_obj.attribution.name
   *  @param source_name - Corresponds to entity_obj.data_sources.type
   *  @param source_id - Corresponds to entity_obj.data_sources.external_id
   *  @param source_url - Corresponds to entity_obj.attribution.url
   *  @param source_logo - Corresponds to entity_obj.attribution.logo
   *  @param verified_date - Corresponds to entity_obj.data_sources.created_at
   *  @return - the data from the api
  */
  public String  POSTEntityVerified(String entity_id,String public_source,String source_name,String source_id,String source_url,String source_logo,String verified_date,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("public_source", public_source);
    	params.put("source_name", source_name);
    	params.put("source_id", source_id);
    	params.put("source_url", source_url);
    	params.put("source_logo", source_logo);
    	params.put("verified_date", verified_date);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/verified",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Remove a verified source object to an existing entity.
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  DELETEEntityVerified(String entity_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/verified",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a video object can be added.
   *
   *  @param entity_id
   *  @param type
   *  @param link
   *  @return - the data from the api
  */
  public String  POSTEntityVideo(String entity_id,String type,String link,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("type", type);
    	params.put("link", link);
    	params.put("_user_id", _user_id);
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
  public String  DELETEEntityVideo(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/video",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a YouTube video object can be added.
   *
   *  @param entity_id
   *  @param embed_code
   *  @return - the data from the api
  */
  public String  POSTEntityVideoYoutube(String entity_id,String embed_code,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("embed_code", embed_code);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/video/youtube",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a website object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @param force
   *  @return - the data from the api
  */
  public String  DELETEEntityWebsite(String entity_id,String gen_id,String force,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("force", force);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/website",params);
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
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  POSTEntityWebsite(String entity_id,String website_url,String display_url,String website_description,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("website_url", website_url);
    	params.put("display_url", display_url);
    	params.put("website_description", website_description);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/website",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a yext list can be added
   *
   *  @param entity_id
   *  @param yext_list_id
   *  @param description
   *  @param name
   *  @param type
   *  @return - the data from the api
  */
  public String  POSTEntityYext_list(String entity_id,String yext_list_id,String description,String name,String type,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("yext_list_id", yext_list_id);
    	params.put("description", description);
    	params.put("name", name);
    	params.put("type", type);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/yext_list",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a yext list object to be removed
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEEntityYext_list(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/yext_list",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add an entityserve document
   *
   *  @param entity_id - The ids of the entity/entities to create the entityserve event(s) for
   *  @param country - the ISO code of the country
   *  @param event_type - The event type being recorded
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  PUTEntityserve(String entity_id,String country,String event_type,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("country", country);
    	params.put("event_type", event_type);
    	params.put("domain", domain);
    	params.put("path", path);
    	retval = this.doCurl("PUT","/entityserve",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a flatpack
   *
   *  @param flatpack_id - this record's unique, auto-generated id - if supplied, then this is an edit, otherwise it's an add
   *  @param status - The status of the flatpack, it is required on creation. Syndication link logic depends on this.
   *  @param nodefaults - create an flatpack that's empty apart from provided values (used for child flatpacks), IMPORTANT: if set, any parameters with default values will be ignored even if overridden. Edit the created flatpack to set those parameters on a nodefaults flatpack.
   *  @param domainName - the domain name to serve this flatpack site on (no leading http:// or anything please)
   *  @param inherits - inherit from domain
   *  @param stub - the stub that is appended to the flatpack's url e.g. http://dev.localhost/stub
   *  @param flatpackName - the name of the Flat pack instance
   *  @param less - the LESS configuration to use to overrides the Bootstrap CSS
   *  @param language - the language in which to render the flatpack site
   *  @param country - the country to use for searches etc
   *  @param mapsType - the type of maps to use
   *  @param mapKey - the nokia map key to use to render maps
   *  @param searchFormShowOn - list of pages to show the search form
   *  @param searchFormShowKeywordsBox - whether to display the keywords box on the search form
   *  @param searchFormShowLocationBox - whether to display the location box on search forms - not required
   *  @param searchFormKeywordsAutoComplete - whether to do auto-completion on the keywords box on the search form
   *  @param searchFormLocationsAutoComplete - whether to do auto-completion on the locations box on the search form
   *  @param searchFormDefaultLocation - the string to use as the default location for searches if no location is supplied
   *  @param searchFormPlaceholderKeywords - the string to show in the keyword box as placeholder text e.g. e.g. cafe
   *  @param searchFormPlaceholderLocation - the string to show in the location box as placeholder text e.g. e.g. Dublin
   *  @param searchFormKeywordsLabel - the string to show next to the keywords control e.g. I'm looking for
   *  @param searchFormLocationLabel - the string to show next to the location control e.g. Located in
   *  @param cannedLinksHeader - the string to show above canned searches
   *  @param homepageTitle - the page title of site's home page
   *  @param homepageDescription - the meta description of the home page
   *  @param homepageIntroTitle - the introductory title for the homepage
   *  @param homepageIntroText - the introductory text for the homepage
   *  @param head - payload to put in the head of the flatpack
   *  @param adblock - payload to put in the adblock of the flatpack
   *  @param bodyTop - the payload to put in the top of the body of a flatpack
   *  @param bodyBottom - the payload to put in the bottom of the body of a flatpack
   *  @param header_menu - the JSON that describes a navigation at the top of the page
   *  @param header_menu_bottom - the JSON that describes a navigation below the masthead
   *  @param footer_menu - the JSON that describes a navigation at the bottom of the page
   *  @param bdpTitle - The page title of the entity business profile pages
   *  @param bdpDescription - The meta description of entity business profile pages
   *  @param bdpAds - The block of HTML/JS that renders Ads on BDPs
   *  @param serpTitle - The page title of the serps
   *  @param serpDescription - The meta description of serps
   *  @param serpNumberResults - The number of results per search page
   *  @param serpNumberAdverts - The number of adverts to show on the first search page
   *  @param serpAds - The block of HTML/JS that renders Ads on Serps
   *  @param serpAdsBottom - The block of HTML/JS that renders Ads on Serps at the bottom
   *  @param serpTitleNoWhat - The text to display in the title for where only searches
   *  @param serpDescriptionNoWhat - The text to display in the description for where only searches
   *  @param cookiePolicyUrl - The cookie policy url of the flatpack
   *  @param cookiePolicyNotice - Whether to show the cookie policy on this flatpack
   *  @param addBusinessButtonText - The text used in the 'Add your business' button
   *  @param twitterUrl - Twitter link
   *  @param facebookUrl - Facebook link
   *  @param copyright - Copyright message
   *  @param phoneReveal - record phone number reveal
   *  @param loginLinkText - the link text for the Login link
   *  @param contextLocationId - The location ID to use as the context for searches on this flatpack
   *  @param addBusinessButtonPosition - The location ID to use as the context for searches on this flatpack
   *  @param denyIndexing - Whether to noindex a flatpack
   *  @param contextRadius - allows you to set a catchment area around the contextLocationId in miles for use when displaying the activity stream module
   *  @param activityStream - allows you to set the activity to be displayed in the activity stream
   *  @param activityStreamSize - Sets the number of items to show within the activity stream.
   *  @param products - A Collection of Central Index products the flatpack is allowed to sell
   *  @param linkToRoot - The root domain name to serve this flatpack site on (no leading http:// or anything please)
   *  @param termsLink - A URL for t's and c's specific to this partner
   *  @param serpNumberEmbedAdverts - The number of embed adverts per search
   *  @param serpEmbedTitle - Custom page title for emdedded searches
   *  @param adminLess - the LESS configuration to use to overrides the Bootstrap CSS for the admin on themed domains
   *  @param adminConfNoLocz - operate without recourse to verified location data (locz)
   *  @param adminConfNoSocialLogin - suppress social media login interface
   *  @param adminConfEasyClaim - captcha only for claim
   *  @param adminConfPaymentMode - payment gateway
   *  @param adminConfEnableProducts - show upgrade on claim
   *  @param adminConfSimpleadmin - render a template for the reduced functionality
   *  @return - the data from the api
  */
  public String  POSTFlatpack(String flatpack_id,String status,String nodefaults,String domainName,String inherits,String stub,String flatpackName,String less,String language,String country,String mapsType,String mapKey,String searchFormShowOn,String searchFormShowKeywordsBox,String searchFormShowLocationBox,String searchFormKeywordsAutoComplete,String searchFormLocationsAutoComplete,String searchFormDefaultLocation,String searchFormPlaceholderKeywords,String searchFormPlaceholderLocation,String searchFormKeywordsLabel,String searchFormLocationLabel,String cannedLinksHeader,String homepageTitle,String homepageDescription,String homepageIntroTitle,String homepageIntroText,String head,String adblock,String bodyTop,String bodyBottom,String header_menu,String header_menu_bottom,String footer_menu,String bdpTitle,String bdpDescription,String bdpAds,String serpTitle,String serpDescription,String serpNumberResults,String serpNumberAdverts,String serpAds,String serpAdsBottom,String serpTitleNoWhat,String serpDescriptionNoWhat,String cookiePolicyUrl,String cookiePolicyNotice,String addBusinessButtonText,String twitterUrl,String facebookUrl,String copyright,String phoneReveal,String loginLinkText,String contextLocationId,String addBusinessButtonPosition,String denyIndexing,String contextRadius,String activityStream,String activityStreamSize,String products,String linkToRoot,String termsLink,String serpNumberEmbedAdverts,String serpEmbedTitle,String adminLess,String adminConfNoLocz,String adminConfNoSocialLogin,String adminConfEasyClaim,String adminConfPaymentMode,String adminConfEnableProducts,String adminConfSimpleadmin) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("status", status);
    	params.put("nodefaults", nodefaults);
    	params.put("domainName", domainName);
    	params.put("inherits", inherits);
    	params.put("stub", stub);
    	params.put("flatpackName", flatpackName);
    	params.put("less", less);
    	params.put("language", language);
    	params.put("country", country);
    	params.put("mapsType", mapsType);
    	params.put("mapKey", mapKey);
    	params.put("searchFormShowOn", searchFormShowOn);
    	params.put("searchFormShowKeywordsBox", searchFormShowKeywordsBox);
    	params.put("searchFormShowLocationBox", searchFormShowLocationBox);
    	params.put("searchFormKeywordsAutoComplete", searchFormKeywordsAutoComplete);
    	params.put("searchFormLocationsAutoComplete", searchFormLocationsAutoComplete);
    	params.put("searchFormDefaultLocation", searchFormDefaultLocation);
    	params.put("searchFormPlaceholderKeywords", searchFormPlaceholderKeywords);
    	params.put("searchFormPlaceholderLocation", searchFormPlaceholderLocation);
    	params.put("searchFormKeywordsLabel", searchFormKeywordsLabel);
    	params.put("searchFormLocationLabel", searchFormLocationLabel);
    	params.put("cannedLinksHeader", cannedLinksHeader);
    	params.put("homepageTitle", homepageTitle);
    	params.put("homepageDescription", homepageDescription);
    	params.put("homepageIntroTitle", homepageIntroTitle);
    	params.put("homepageIntroText", homepageIntroText);
    	params.put("head", head);
    	params.put("adblock", adblock);
    	params.put("bodyTop", bodyTop);
    	params.put("bodyBottom", bodyBottom);
    	params.put("header_menu", header_menu);
    	params.put("header_menu_bottom", header_menu_bottom);
    	params.put("footer_menu", footer_menu);
    	params.put("bdpTitle", bdpTitle);
    	params.put("bdpDescription", bdpDescription);
    	params.put("bdpAds", bdpAds);
    	params.put("serpTitle", serpTitle);
    	params.put("serpDescription", serpDescription);
    	params.put("serpNumberResults", serpNumberResults);
    	params.put("serpNumberAdverts", serpNumberAdverts);
    	params.put("serpAds", serpAds);
    	params.put("serpAdsBottom", serpAdsBottom);
    	params.put("serpTitleNoWhat", serpTitleNoWhat);
    	params.put("serpDescriptionNoWhat", serpDescriptionNoWhat);
    	params.put("cookiePolicyUrl", cookiePolicyUrl);
    	params.put("cookiePolicyNotice", cookiePolicyNotice);
    	params.put("addBusinessButtonText", addBusinessButtonText);
    	params.put("twitterUrl", twitterUrl);
    	params.put("facebookUrl", facebookUrl);
    	params.put("copyright", copyright);
    	params.put("phoneReveal", phoneReveal);
    	params.put("loginLinkText", loginLinkText);
    	params.put("contextLocationId", contextLocationId);
    	params.put("addBusinessButtonPosition", addBusinessButtonPosition);
    	params.put("denyIndexing", denyIndexing);
    	params.put("contextRadius", contextRadius);
    	params.put("activityStream", activityStream);
    	params.put("activityStreamSize", activityStreamSize);
    	params.put("products", products);
    	params.put("linkToRoot", linkToRoot);
    	params.put("termsLink", termsLink);
    	params.put("serpNumberEmbedAdverts", serpNumberEmbedAdverts);
    	params.put("serpEmbedTitle", serpEmbedTitle);
    	params.put("adminLess", adminLess);
    	params.put("adminConfNoLocz", adminConfNoLocz);
    	params.put("adminConfNoSocialLogin", adminConfNoSocialLogin);
    	params.put("adminConfEasyClaim", adminConfEasyClaim);
    	params.put("adminConfPaymentMode", adminConfPaymentMode);
    	params.put("adminConfEnableProducts", adminConfEnableProducts);
    	params.put("adminConfSimpleadmin", adminConfSimpleadmin);
    	retval = this.doCurl("POST","/flatpack",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a flatpack
   *
   *  @param flatpack_id - the unique id to search for
   *  @return - the data from the api
  */
  public String  GETFlatpack(String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/flatpack",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Remove a flatpack using a supplied flatpack_id
   *
   *  @param flatpack_id - the id of the flatpack to delete
   *  @return - the data from the api
  */
  public String  DELETEFlatpack(String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("DELETE","/flatpack",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Upload a CSS file for the Admin for this flatpack
   *
   *  @param flatpack_id - the id of the flatpack to update
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTFlatpackAdminCSS(String flatpack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/adminCSS",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a HD Admin logo to a flatpack domain
   *
   *  @param flatpack_id - the unique id to search for
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTFlatpackAdminHDLogo(String flatpack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/adminHDLogo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Upload an image to serve out as the large logo in the Admin for this flatpack
   *
   *  @param flatpack_id - the id of the flatpack to update
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTFlatpackAdminLargeLogo(String flatpack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/adminLargeLogo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Upload an image to serve out as the small logo in the Admin for this flatpack
   *
   *  @param flatpack_id - the id of the flatpack to update
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTFlatpackAdminSmallLogo(String flatpack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/adminSmallLogo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Remove a flatpack using a supplied flatpack_id
   *
   *  @param flatpack_id - the unique id to search for
   *  @param adblock
   *  @param serpAds
   *  @param serpAdsBottom
   *  @param bdpAds
   *  @return - the data from the api
  */
  public String  DELETEFlatpackAds(String flatpack_id,String adblock,String serpAds,String serpAdsBottom,String bdpAds) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("adblock", adblock);
    	params.put("serpAds", serpAds);
    	params.put("serpAdsBottom", serpAdsBottom);
    	params.put("bdpAds", bdpAds);
    	retval = this.doCurl("DELETE","/flatpack/ads",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Generate flatpacks based on the files passed in
   *
   *  @param json - The flatpack JSON to make replacements on
   *  @param filedata - a file that contains the replacements in the JSON
   *  @param slack_user
   *  @return - the data from the api
  */
  public String  POSTFlatpackBulkJson(String json,String filedata,String slack_user) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("json", json);
    	params.put("filedata", filedata);
    	params.put("slack_user", slack_user);
    	retval = this.doCurl("POST","/flatpack/bulk/json",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get flatpacks by country and location
   *
   *  @param country
   *  @param latitude
   *  @param longitude
   *  @return - the data from the api
  */
  public String  GETFlatpackBy_country(String country,String latitude,String longitude) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	retval = this.doCurl("GET","/flatpack/by_country",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get flatpacks by country in KML format
   *
   *  @param country
   *  @return - the data from the api
  */
  public String  GETFlatpackBy_countryKml(String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	retval = this.doCurl("GET","/flatpack/by_country/kml",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a flatpack using a domain name
   *
   *  @param domainName - the domain name to search for
   *  @param matchAlias - Whether to match alias as well
   *  @return - the data from the api
  */
  public String  GETFlatpackBy_domain_name(String domainName,String matchAlias) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("domainName", domainName);
    	params.put("matchAlias", matchAlias);
    	retval = this.doCurl("GET","/flatpack/by_domain_name",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get flatpacks that match the supplied masheryid
   *
   *  @return - the data from the api
  */
  public String  GETFlatpackBy_masheryid() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/flatpack/by_masheryid",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Clone an existing flatpack
   *
   *  @param flatpack_id - the flatpack_id to clone
   *  @param domainName - the domain of the new flatpack site (no leading http:// or anything please)
   *  @return - the data from the api
  */
  public String  GETFlatpackClone(String flatpack_id,String domainName) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("domainName", domainName);
    	retval = this.doCurl("GET","/flatpack/clone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * undefined
   *
   *  @param flatpack_id - the unique id to search for
   *  @param domainName
   *  @return - the data from the api
  */
  public String  POSTFlatpackDomain_alias(String flatpack_id,String domainName) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("domainName", domainName);
    	retval = this.doCurl("POST","/flatpack/domain_alias",params);
    } finally { 
    }
    return retval;
  }


  /**
   * undefined
   *
   *  @param flatpack_id - the unique id to search for
   *  @param domainName
   *  @return - the data from the api
  */
  public String  DELETEFlatpackDomain_alias(String flatpack_id,String domainName) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("domainName", domainName);
    	retval = this.doCurl("DELETE","/flatpack/domain_alias",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns a list of domain names in which direct/inherited flatpack country match the specified one and status equals production.
   *
   *  @param country
   *  @return - the data from the api
  */
  public String  GETFlatpackDomain_nameBy_country(String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	retval = this.doCurl("GET","/flatpack/domain_name/by_country",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Upload an icon to serve out with this flatpack
   *
   *  @param flatpack_id - the id of the flatpack to update
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTFlatpackIcon(String flatpack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/icon",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a flatpack using a domain name
   *
   *  @param flatpack_id - the id to search for
   *  @return - the data from the api
  */
  public String  GETFlatpackInherit(String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/flatpack/inherit",params);
    } finally { 
    }
    return retval;
  }


  /**
   * returns the LESS theme from a flatpack
   *
   *  @param flatpack_id - the unique id to search for
   *  @return - the data from the api
  */
  public String  GETFlatpackLess(String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/flatpack/less",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Remove a canned link to an existing flatpack site.
   *
   *  @param flatpack_id - the id of the flatpack to delete
   *  @param gen_id - the id of the canned link to remove
   *  @return - the data from the api
  */
  public String  DELETEFlatpackLink(String flatpack_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/flatpack/link",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a canned link to an existing flatpack site.
   *
   *  @param flatpack_id - the id of the flatpack to delete
   *  @param keywords - the keywords to use in the canned search
   *  @param location - the location to use in the canned search
   *  @param linkText - the link text to be used to in the canned search link
   *  @return - the data from the api
  */
  public String  POSTFlatpackLink(String flatpack_id,String keywords,String location,String linkText) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("keywords", keywords);
    	params.put("location", location);
    	params.put("linkText", linkText);
    	retval = this.doCurl("POST","/flatpack/link",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Remove all canned links from an existing flatpack.
   *
   *  @param flatpack_id - the id of the flatpack to remove links from
   *  @return - the data from the api
  */
  public String  DELETEFlatpackLinkAll(String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("DELETE","/flatpack/link/all",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Upload a logo to serve out with this flatpack
   *
   *  @param flatpack_id - the id of the flatpack to update
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTFlatpackLogo(String flatpack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a hd logo to a flatpack domain
   *
   *  @param flatpack_id - the unique id to search for
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTFlatpackLogoHd(String flatpack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/logo/hd",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete a Redirect link from a flatpack
   *
   *  @param flatpack_id - the unique id to search for
   *  @return - the data from the api
  */
  public String  DELETEFlatpackRedirect(String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("DELETE","/flatpack/redirect",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a Redirect link to a flatpack
   *
   *  @param flatpack_id - the unique id to search for
   *  @param redirectTo
   *  @return - the data from the api
  */
  public String  POSTFlatpackRedirect(String flatpack_id,String redirectTo) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("redirectTo", redirectTo);
    	retval = this.doCurl("POST","/flatpack/redirect",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Upload a TXT file to act as the sitemap for this flatpack
   *
   *  @param flatpack_id - the id of the flatpack to update
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTFlatpackSitemap(String flatpack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/sitemap",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete a group with a specified group_id
   *
   *  @param group_id
   *  @return - the data from the api
  */
  public String  DELETEGroup(String group_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	retval = this.doCurl("DELETE","/group",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a Group
   *
   *  @param group_id
   *  @param name
   *  @param description
   *  @param url
   *  @param stamp_user_id
   *  @param stamp_sql
   *  @return - the data from the api
  */
  public String  POSTGroup(String group_id,String name,String description,String url,String stamp_user_id,String stamp_sql) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	params.put("name", name);
    	params.put("description", description);
    	params.put("url", url);
    	params.put("stamp_user_id", stamp_user_id);
    	params.put("stamp_sql", stamp_sql);
    	retval = this.doCurl("POST","/group",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns group that matches a given group id
   *
   *  @param group_id
   *  @return - the data from the api
  */
  public String  GETGroup(String group_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	retval = this.doCurl("GET","/group",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns all groups
   *
   *  @return - the data from the api
  */
  public String  GETGroupAll() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/group/all",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Bulk delete entities from a specified group
   *
   *  @param group_id
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTGroupBulk_delete(String group_id,String filedata,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	params.put("filedata", filedata);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/group/bulk_delete",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Bulk ingest entities into a specified group
   *
   *  @param group_id
   *  @param filedata
   *  @param category_type
   *  @return - the data from the api
  */
  public String  POSTGroupBulk_ingest(String group_id,String filedata,String category_type,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	params.put("filedata", filedata);
    	params.put("category_type", category_type);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/group/bulk_ingest",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Bulk update entities with a specified group
   *
   *  @param group_id
   *  @param data
   *  @return - the data from the api
  */
  public String  POSTGroupBulk_update(String group_id,String _user_id,String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	params.put("_user_id", _user_id);
    	params.put("data", data);
    	retval = this.doCurl("POST","/group/bulk_update",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get number of claims today
   *
   *  @param from_date
   *  @param to_date
   *  @param country_id
   *  @return - the data from the api
  */
  public String  GETHeartbeatBy_date(String from_date,String to_date,String country_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from_date", from_date);
    	params.put("to_date", to_date);
    	params.put("country_id", country_id);
    	retval = this.doCurl("GET","/heartbeat/by_date",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get number of claims today
   *
   *  @param country
   *  @param claim_type
   *  @return - the data from the api
  */
  public String  GETHeartbeatTodayClaims(String country,String claim_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	params.put("claim_type", claim_type);
    	retval = this.doCurl("GET","/heartbeat/today/claims",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Process a bulk file
   *
   *  @param job_id
   *  @param filedata - A tab separated file for ingest
   *  @return - the data from the api
  */
  public String  POSTIngest_file(String job_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("job_id", job_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/ingest_file",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a ingest job to the collection
   *
   *  @param description
   *  @param category_type
   *  @return - the data from the api
  */
  public String  POSTIngest_job(String description,String category_type,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("description", description);
    	params.put("category_type", category_type);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/ingest_job",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get an ingest job from the collection
   *
   *  @param job_id
   *  @return - the data from the api
  */
  public String  GETIngest_job(String job_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("job_id", job_id);
    	retval = this.doCurl("GET","/ingest_job",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get an ingest log from the collection
   *
   *  @param job_id
   *  @param success
   *  @param errors
   *  @param limit
   *  @param skip
   *  @return - the data from the api
  */
  public String  GETIngest_logBy_job_id(String job_id,String success,String errors,String limit,String skip) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("job_id", job_id);
    	params.put("success", success);
    	params.put("errors", errors);
    	params.put("limit", limit);
    	params.put("skip", skip);
    	retval = this.doCurl("GET","/ingest_log/by_job_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Check the status of the Ingest queue, and potentially flush it
   *
   *  @param flush
   *  @return - the data from the api
  */
  public String  GETIngest_queue(String flush) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flush", flush);
    	retval = this.doCurl("GET","/ingest_queue",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns entities that do not have claim or advertisers data
   *
   *  @param country_id - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param from_date
   *  @param to_date
   *  @param limit
   *  @param offset
   *  @param reduce - Set true to return the count value only.
   *  @return - the data from the api
  */
  public String  GETLeadsAdded(String country_id,String from_date,String to_date,String _user_id,String limit,String offset,String reduce) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country_id", country_id);
    	params.put("from_date", from_date);
    	params.put("to_date", to_date);
    	params.put("_user_id", _user_id);
    	params.put("limit", limit);
    	params.put("offset", offset);
    	params.put("reduce", reduce);
    	retval = this.doCurl("GET","/leads/added",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns entities that have advertisers data
   *
   *  @param country_id - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param from_date
   *  @param to_date
   *  @param limit
   *  @param offset
   *  @param reduce - Set true to return the count value only.
   *  @return - the data from the api
  */
  public String  GETLeadsAdvertisers(String country_id,String from_date,String to_date,String _user_id,String limit,String offset,String reduce) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country_id", country_id);
    	params.put("from_date", from_date);
    	params.put("to_date", to_date);
    	params.put("_user_id", _user_id);
    	params.put("limit", limit);
    	params.put("offset", offset);
    	params.put("reduce", reduce);
    	retval = this.doCurl("GET","/leads/advertisers",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns entities that have claim data
   *
   *  @param country_id - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param from_date
   *  @param to_date
   *  @param limit
   *  @param offset
   *  @param reduce - Set true to return the count value only.
   *  @return - the data from the api
  */
  public String  GETLeadsClaimed(String country_id,String from_date,String to_date,String _user_id,String limit,String offset,String reduce) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country_id", country_id);
    	params.put("from_date", from_date);
    	params.put("to_date", to_date);
    	params.put("_user_id", _user_id);
    	params.put("limit", limit);
    	params.put("offset", offset);
    	params.put("reduce", reduce);
    	retval = this.doCurl("GET","/leads/claimed",params);
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
  public String  GETLocation(String location_id) throws Exception { 
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
   * Create/update a new locz document with the supplied ID in the locations reference database.
   *
   *  @param location_id
   *  @param type
   *  @param country
   *  @param language
   *  @param name
   *  @param formal_name
   *  @param resolution
   *  @param population
   *  @param description
   *  @param timezone
   *  @param latitude
   *  @param longitude
   *  @param parent_town
   *  @param parent_county
   *  @param parent_province
   *  @param parent_region
   *  @param parent_neighbourhood
   *  @param parent_district
   *  @param postalcode
   *  @param searchable_id
   *  @param searchable_ids
   *  @return - the data from the api
  */
  public String  POSTLocation(String location_id,String type,String country,String language,String name,String formal_name,String resolution,String population,String description,String timezone,String latitude,String longitude,String parent_town,String parent_county,String parent_province,String parent_region,String parent_neighbourhood,String parent_district,String postalcode,String searchable_id,String searchable_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	params.put("type", type);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("name", name);
    	params.put("formal_name", formal_name);
    	params.put("resolution", resolution);
    	params.put("population", population);
    	params.put("description", description);
    	params.put("timezone", timezone);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("parent_town", parent_town);
    	params.put("parent_county", parent_county);
    	params.put("parent_province", parent_province);
    	params.put("parent_region", parent_region);
    	params.put("parent_neighbourhood", parent_neighbourhood);
    	params.put("parent_district", parent_district);
    	params.put("postalcode", postalcode);
    	params.put("searchable_id", searchable_id);
    	params.put("searchable_ids", searchable_ids);
    	retval = this.doCurl("POST","/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Given a location_id or a lat/lon, find other locations within the radius
   *
   *  @param location_id
   *  @param latitude
   *  @param longitude
   *  @param radius - Radius in km
   *  @param resolution
   *  @param country
   *  @param num_results
   *  @return - the data from the api
  */
  public String  GETLocationContext(String location_id,String latitude,String longitude,String radius,String resolution,String country,String num_results) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("radius", radius);
    	params.put("resolution", resolution);
    	params.put("country", country);
    	params.put("num_results", num_results);
    	retval = this.doCurl("GET","/location/context",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Read multiple locations with the supplied ID in the locations reference database.
   *
   *  @param location_ids
   *  @return - the data from the api
  */
  public String  GETLocationMultiple(String location_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_ids", location_ids);
    	retval = this.doCurl("GET","/location/multiple",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a unique login_id a login can be retrieved
   *
   *  @param login_id
   *  @return - the data from the api
  */
  public String  GETLogin(String login_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("login_id", login_id);
    	retval = this.doCurl("GET","/login",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create/Update login details
   *
   *  @param login_id
   *  @param email
   *  @param password
   *  @return - the data from the api
  */
  public String  POSTLogin(String login_id,String email,String password) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("login_id", login_id);
    	params.put("email", email);
    	params.put("password", password);
    	retval = this.doCurl("POST","/login",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a unique login_id a login can be deleted
   *
   *  @param login_id
   *  @return - the data from the api
  */
  public String  DELETELogin(String login_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("login_id", login_id);
    	retval = this.doCurl("DELETE","/login",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a unique email address a login can be retrieved
   *
   *  @param email
   *  @return - the data from the api
  */
  public String  GETLoginBy_email(String email) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email", email);
    	retval = this.doCurl("GET","/login/by_email",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Verify that a supplied email and password match a users saved login details
   *
   *  @param email
   *  @param password
   *  @return - the data from the api
  */
  public String  GETLoginVerify(String email,String password) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email", email);
    	params.put("password", password);
    	retval = this.doCurl("GET","/login/verify",params);
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
  public String  GETLogo(String a,String b,String c,String d) throws Exception { 
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
  public String  PUTLogo(String a) throws Exception { 
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
   * Find a category from cache or cloudant depending if it is in the cache
   *
   *  @param string - A string to search against, E.g. Plumbers
   *  @param language - An ISO compatible language code, E.g. en
   *  @return - the data from the api
  */
  public String  GETLookupCategory(String string,String language) throws Exception { 
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
  public String  GETLookupLegacyCategory(String id,String type) throws Exception { 
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
   * Find a location from cache or cloudant depending if it is in the cache (locz)
   *
   *  @param string
   *  @param language
   *  @param country
   *  @param latitude
   *  @param longitude
   *  @return - the data from the api
  */
  public String  GETLookupLocation(String string,String language,String country,String latitude,String longitude) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("string", string);
    	params.put("language", language);
    	params.put("country", country);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	retval = this.doCurl("GET","/lookup/location",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns a list of mashery IDs domain names in which direct/inherited flatpack country match the specified one and status equals production.
   *
   *  @return - the data from the api
  */
  public String  GETMasheryidAll() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/masheryid/all",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find all matches by phone number, returning up to 10 matches
   *
   *  @param phone
   *  @param country
   *  @param exclude - Entity ID to exclude from the results
   *  @return - the data from the api
  */
  public String  GETMatchByphone(String phone,String country,String exclude) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("phone", phone);
    	params.put("country", country);
    	params.put("exclude", exclude);
    	retval = this.doCurl("GET","/match/byphone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Perform a match on the two supplied entities, returning the outcome and showing our working
   *
   *  @param primary_entity_id
   *  @param secondary_entity_id
   *  @param return_entities - Should we return the entity documents
   *  @return - the data from the api
  */
  public String  GETMatchOftheday(String primary_entity_id,String secondary_entity_id,String return_entities) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("primary_entity_id", primary_entity_id);
    	params.put("secondary_entity_id", secondary_entity_id);
    	params.put("return_entities", return_entities);
    	retval = this.doCurl("GET","/match/oftheday",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Will create a new Matching Instruction or update an existing one
   *
   *  @param entity_id
   *  @param entity_name
   *  @return - the data from the api
  */
  public String  POSTMatching_instruction(String entity_id,String entity_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("entity_name", entity_name);
    	retval = this.doCurl("POST","/matching_instruction",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete Matching instruction
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  DELETEMatching_instruction(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("DELETE","/matching_instruction",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch all available Matching instructions
   *
   *  @param limit
   *  @return - the data from the api
  */
  public String  GETMatching_instructionAll(String limit) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("limit", limit);
    	retval = this.doCurl("GET","/matching_instruction/all",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create a matching log
   *
   *  @param primary_entity_id
   *  @param secondary_entity_id
   *  @param primary_name
   *  @param secondary_name
   *  @param address_score
   *  @param address_match
   *  @param name_score
   *  @param name_match
   *  @param distance
   *  @param phone_match
   *  @param category_match
   *  @param email_match
   *  @param website_match
   *  @param match
   *  @return - the data from the api
  */
  public String  PUTMatching_log(String primary_entity_id,String secondary_entity_id,String primary_name,String secondary_name,String address_score,String address_match,String name_score,String name_match,String distance,String phone_match,String category_match,String email_match,String website_match,String match) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("primary_entity_id", primary_entity_id);
    	params.put("secondary_entity_id", secondary_entity_id);
    	params.put("primary_name", primary_name);
    	params.put("secondary_name", secondary_name);
    	params.put("address_score", address_score);
    	params.put("address_match", address_match);
    	params.put("name_score", name_score);
    	params.put("name_match", name_match);
    	params.put("distance", distance);
    	params.put("phone_match", phone_match);
    	params.put("category_match", category_match);
    	params.put("email_match", email_match);
    	params.put("website_match", website_match);
    	params.put("match", match);
    	retval = this.doCurl("PUT","/matching_log",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known user ID add/create the maxclaims blcok
   *
   *  @param user_id
   *  @param contract_id
   *  @param country
   *  @param number
   *  @param expiry_date
   *  @return - the data from the api
  */
  public String  POSTMaxclaimsActivate(String user_id,String contract_id,String country,String number,String expiry_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	params.put("contract_id", contract_id);
    	params.put("country", country);
    	params.put("number", number);
    	params.put("expiry_date", expiry_date);
    	retval = this.doCurl("POST","/maxclaims/activate",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetching a message
   *
   *  @param message_id - The message id to pull the message for
   *  @return - the data from the api
  */
  public String  GETMessage(String message_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("message_id", message_id);
    	retval = this.doCurl("GET","/message",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a message
   *
   *  @param message_id - Message id to pull
   *  @param ses_id - Aamazon email id
   *  @param from_user_id - User sending the message
   *  @param from_email - Sent from email address
   *  @param to_entity_id - The id of the entity being sent the message
   *  @param to_email - Sent from email address
   *  @param subject - Subject for the message
   *  @param body - Body for the message
   *  @param bounced - If the message bounced
   *  @return - the data from the api
  */
  public String  POSTMessage(String message_id,String ses_id,String from_user_id,String from_email,String to_entity_id,String to_email,String subject,String body,String bounced) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("message_id", message_id);
    	params.put("ses_id", ses_id);
    	params.put("from_user_id", from_user_id);
    	params.put("from_email", from_email);
    	params.put("to_entity_id", to_entity_id);
    	params.put("to_email", to_email);
    	params.put("subject", subject);
    	params.put("body", body);
    	params.put("bounced", bounced);
    	retval = this.doCurl("POST","/message",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetching messages by ses_id
   *
   *  @param ses_id - The amazon id to pull the message for
   *  @return - the data from the api
  */
  public String  GETMessageBy_ses_id(String ses_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("ses_id", ses_id);
    	retval = this.doCurl("GET","/message/by_ses_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a multipack
   *
   *  @param multipack_id - this record's unique, auto-generated id - if supplied, then this is an edit, otherwise it's an add
   *  @param group_id - the id of the group that this site serves
   *  @param domainName - the domain name to serve this multipack site on (no leading http:// or anything please)
   *  @param multipackName - the name of the Flat pack instance
   *  @param less - the LESS configuration to use to overrides the Bootstrap CSS
   *  @param country - the country to use for searches etc
   *  @param menuTop - the JSON that describes a navigation at the top of the page
   *  @param menuBottom - the JSON that describes a navigation below the masthead
   *  @param language - An ISO compatible language code, E.g. en e.g. en
   *  @param menuFooter - the JSON that describes a navigation at the bottom of the page
   *  @param searchNumberResults - the number of search results per page
   *  @param searchTitle - Title of serps page
   *  @param searchDescription - Description of serps page
   *  @param searchTitleNoWhere - Title when no where is specified
   *  @param searchDescriptionNoWhere - Description of serps page when no where is specified
   *  @param searchIntroHeader - Introductory header
   *  @param searchIntroText - Introductory text
   *  @param searchShowAll - display all search results on one page
   *  @param searchUnitOfDistance - the unit of distance to use for search
   *  @param cookiePolicyShow - whether to show cookie policy
   *  @param cookiePolicyUrl - url of cookie policy
   *  @param twitterUrl - url of twitter feed
   *  @param facebookUrl - url of facebook feed
   *  @return - the data from the api
  */
  public String  POSTMultipack(String multipack_id,String group_id,String domainName,String multipackName,String less,String country,String menuTop,String menuBottom,String language,String menuFooter,String searchNumberResults,String searchTitle,String searchDescription,String searchTitleNoWhere,String searchDescriptionNoWhere,String searchIntroHeader,String searchIntroText,String searchShowAll,String searchUnitOfDistance,String cookiePolicyShow,String cookiePolicyUrl,String twitterUrl,String facebookUrl) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("multipack_id", multipack_id);
    	params.put("group_id", group_id);
    	params.put("domainName", domainName);
    	params.put("multipackName", multipackName);
    	params.put("less", less);
    	params.put("country", country);
    	params.put("menuTop", menuTop);
    	params.put("menuBottom", menuBottom);
    	params.put("language", language);
    	params.put("menuFooter", menuFooter);
    	params.put("searchNumberResults", searchNumberResults);
    	params.put("searchTitle", searchTitle);
    	params.put("searchDescription", searchDescription);
    	params.put("searchTitleNoWhere", searchTitleNoWhere);
    	params.put("searchDescriptionNoWhere", searchDescriptionNoWhere);
    	params.put("searchIntroHeader", searchIntroHeader);
    	params.put("searchIntroText", searchIntroText);
    	params.put("searchShowAll", searchShowAll);
    	params.put("searchUnitOfDistance", searchUnitOfDistance);
    	params.put("cookiePolicyShow", cookiePolicyShow);
    	params.put("cookiePolicyUrl", cookiePolicyUrl);
    	params.put("twitterUrl", twitterUrl);
    	params.put("facebookUrl", facebookUrl);
    	retval = this.doCurl("POST","/multipack",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a multipack
   *
   *  @param multipack_id - the unique id to search for
   *  @return - the data from the api
  */
  public String  GETMultipack(String multipack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("multipack_id", multipack_id);
    	retval = this.doCurl("GET","/multipack",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add an admin theme to a multipack
   *
   *  @param multipack_id - the unique id to search for
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTMultipackAdminCSS(String multipack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("multipack_id", multipack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/multipack/adminCSS",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a Admin logo to a Multipack domain
   *
   *  @param multipack_id - the unique id to search for
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTMultipackAdminLogo(String multipack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("multipack_id", multipack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/multipack/adminLogo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a multipack using a domain name
   *
   *  @param domainName - the domain name to search for
   *  @return - the data from the api
  */
  public String  GETMultipackBy_domain_name(String domainName) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("domainName", domainName);
    	retval = this.doCurl("GET","/multipack/by_domain_name",params);
    } finally { 
    }
    return retval;
  }


  /**
   * duplicates a given multipack
   *
   *  @param multipack_id - the unique id to search for
   *  @param domainName - the domain name to serve this multipack site on (no leading http:// or anything please)
   *  @param group_id - the group to use for search
   *  @return - the data from the api
  */
  public String  GETMultipackClone(String multipack_id,String domainName,String group_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("multipack_id", multipack_id);
    	params.put("domainName", domainName);
    	params.put("group_id", group_id);
    	retval = this.doCurl("GET","/multipack/clone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * returns the LESS theme from a multipack
   *
   *  @param multipack_id - the unique id to search for
   *  @return - the data from the api
  */
  public String  GETMultipackLess(String multipack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("multipack_id", multipack_id);
    	retval = this.doCurl("GET","/multipack/less",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a logo to a multipack domain
   *
   *  @param multipack_id - the unique id to search for
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTMultipackLogo(String multipack_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("multipack_id", multipack_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/multipack/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a map pin to a multipack domain
   *
   *  @param multipack_id - the unique id to search for
   *  @param filedata
   *  @param mapPinOffsetX
   *  @param mapPinOffsetY
   *  @return - the data from the api
  */
  public String  POSTMultipackMap_pin(String multipack_id,String filedata,String mapPinOffsetX,String mapPinOffsetY) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("multipack_id", multipack_id);
    	params.put("filedata", filedata);
    	params.put("mapPinOffsetX", mapPinOffsetX);
    	params.put("mapPinOffsetY", mapPinOffsetY);
    	retval = this.doCurl("POST","/multipack/map_pin",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch an ops_log
   *
   *  @param ops_log_id
   *  @return - the data from the api
  */
  public String  GETOps_log(String ops_log_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("ops_log_id", ops_log_id);
    	retval = this.doCurl("GET","/ops_log",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create an ops_log
   *
   *  @param success
   *  @param type
   *  @param action
   *  @param data
   *  @param slack_channel
   *  @return - the data from the api
  */
  public String  POSTOps_log(String success,String type,String action,String data,String slack_channel) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("success", success);
    	params.put("type", type);
    	params.put("action", action);
    	params.put("data", data);
    	params.put("slack_channel", slack_channel);
    	retval = this.doCurl("POST","/ops_log",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Run PTB for a given ingest job ID.
   *
   *  @param ingest_job_id - The ingest job ID
   *  @param email - When all entity IDs are pushed to the PTB queue, an email containing debug info will be sent.
   *  @return - the data from the api
  */
  public String  POSTPaintBy_ingest_job_id(String ingest_job_id,String email) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("ingest_job_id", ingest_job_id);
    	params.put("email", email);
    	retval = this.doCurl("POST","/paint/by_ingest_job_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id syndication of data back to a partner is enabled
   *
   *  @param entity_id
   *  @param publisher_id
   *  @param expiry_date
   *  @return - the data from the api
  */
  public String  POSTPartnersyndicateActivate(String entity_id,String publisher_id,String expiry_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	params.put("expiry_date", expiry_date);
    	retval = this.doCurl("POST","/partnersyndicate/activate",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Call CK syndication instruction and call cancel endpoint for partner/supplier_id
   *
   *  @param supplierid
   *  @param vendor
   *  @return - the data from the api
  */
  public String  POSTPartnersyndicateCancel(String supplierid,String vendor) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("supplierid", supplierid);
    	params.put("vendor", vendor);
    	retval = this.doCurl("POST","/partnersyndicate/cancel",params);
    } finally { 
    }
    return retval;
  }


  /**
   * This will call into CK in order to create the entity on the third party system.
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  POSTPartnersyndicateCreate(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("POST","/partnersyndicate/create",params);
    } finally { 
    }
    return retval;
  }


  /**
   * If this call fails CK is nudged for a human intervention for the future (so the call is NOT passive)
   *
   *  @param vendor_cat_id
   *  @param vendor_cat_string
   *  @param vendor
   *  @return - the data from the api
  */
  public String  GETPartnersyndicateRequestcat(String vendor_cat_id,String vendor_cat_string,String vendor) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("vendor_cat_id", vendor_cat_id);
    	params.put("vendor_cat_string", vendor_cat_string);
    	params.put("vendor", vendor);
    	retval = this.doCurl("GET","/partnersyndicate/requestcat",params);
    } finally { 
    }
    return retval;
  }


  /**
   * This will do nothing if the entity does not have a current partnersyndicate block. Syndication is invoked automatically when entities are saved, so this endpoint is designed for checking the status of syndication.
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  POSTPartnersyndicateUpdateToCk(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("POST","/partnersyndicate/updateToCk",params);
    } finally { 
    }
    return retval;
  }


  /**
   * When a plugin is added to the system it must be added to the service
   *
   *  @param id
   *  @param slug
   *  @param owner
   *  @param scope
   *  @param status
   *  @param params
   *  @return - the data from the api
  */
  public String  POSTPlugin(String id,String slug,String owner,String scope,String status,String params) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("id", id);
    	params.put("slug", slug);
    	params.put("owner", owner);
    	params.put("scope", scope);
    	params.put("status", status);
    	params.put("params", params);
    	retval = this.doCurl("POST","/plugin",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get plugin data
   *
   *  @param id
   *  @return - the data from the api
  */
  public String  GETPlugin(String id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("id", id);
    	retval = this.doCurl("GET","/plugin",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a plugin is enabled
   *
   *  @param entity_id
   *  @param plugin
   *  @param inapp_name
   *  @param expiry_date
   *  @return - the data from the api
  */
  public String  POSTPluginActivate(String entity_id,String plugin,String inapp_name,String expiry_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("plugin", plugin);
    	params.put("inapp_name", inapp_name);
    	params.put("expiry_date", expiry_date);
    	retval = this.doCurl("POST","/plugin/activate",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a plugin is cancelled
   *
   *  @param entity_id
   *  @param plugin
   *  @param inapp_name
   *  @param expiry_date
   *  @return - the data from the api
  */
  public String  POSTPluginCancel(String entity_id,String plugin,String inapp_name,String expiry_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("plugin", plugin);
    	params.put("inapp_name", inapp_name);
    	params.put("expiry_date", expiry_date);
    	retval = this.doCurl("POST","/plugin/cancel",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Arbitrary big data
   *
   *  @param pluginid
   *  @param name
   *  @param filter1
   *  @param filter2
   *  @param order
   *  @param fields - a json string with up to 20 fields indexed 'field1' thru 'field20'
   *  @return - the data from the api
  */
  public String  GETPluginDatarow(String pluginid,String name,String filter1,String filter2,String order,String fields) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("pluginid", pluginid);
    	params.put("name", name);
    	params.put("filter1", filter1);
    	params.put("filter2", filter2);
    	params.put("order", order);
    	params.put("fields", fields);
    	retval = this.doCurl("GET","/plugin/datarow",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Arbitrary big data
   *
   *  @param rowdataid
   *  @param pluginid
   *  @param name
   *  @param filter1
   *  @param filter2
   *  @param fields - a json string with up to 20 fields indexed 'field1' thru 'field20'
   *  @return - the data from the api
  */
  public String  POSTPluginDatarow(String rowdataid,String pluginid,String name,String filter1,String filter2,String fields) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("rowdataid", rowdataid);
    	params.put("pluginid", pluginid);
    	params.put("name", name);
    	params.put("filter1", filter1);
    	params.put("filter2", filter2);
    	params.put("fields", fields);
    	retval = this.doCurl("POST","/plugin/datarow",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a plugin is enabled
   *
   *  @param pluginid
   *  @param userid
   *  @param entity_id
   *  @param storekey
   *  @param storeval
   *  @return - the data from the api
  */
  public String  POSTPluginVar(String pluginid,String userid,String entity_id,String storekey,String storeval) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("pluginid", pluginid);
    	params.put("userid", userid);
    	params.put("entity_id", entity_id);
    	params.put("storekey", storekey);
    	params.put("storeval", storeval);
    	retval = this.doCurl("POST","/plugin/var",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get variables related to a particular entity
   *
   *  @param entityid
   *  @return - the data from the api
  */
  public String  GETPluginVarsByEntityId(String entityid) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entityid", entityid);
    	retval = this.doCurl("GET","/plugin/vars/byEntityId",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get info on all plugins
   *
   *  @return - the data from the api
  */
  public String  GETPlugins() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/plugins",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a private object to be removed
   *
   *  @param private_object_id - The id of the private object to remove
   *  @return - the data from the api
  */
  public String  DELETEPrivate_object(String private_object_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("private_object_id", private_object_id);
    	retval = this.doCurl("DELETE","/private_object",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a known entity id, a private object can be added.
   *
   *  @param entity_id - The entity to associate the private object with
   *  @param data - The data to store
   *  @return - the data from the api
  */
  public String  PUTPrivate_object(String entity_id,String data,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("data", data);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("PUT","/private_object",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Allows a private object to be returned based on the entity_id and masheryid
   *
   *  @param entity_id - The entity associated with the private object
   *  @return - the data from the api
  */
  public String  GETPrivate_objectAll(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/private_object/all",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a product
   *
   *  @param product_id - The ID of the product
   *  @param shortname - Desc
   *  @param name - The name of the product
   *  @param strapline - The description of the product
   *  @param alternate_title - The alternate title of the product
   *  @param fpzones - Hints for flatpack display (set a single hint 'void' to have this ignored)
   *  @param paygateid - The product id in the payment gateway (required for Stripe)
   *  @param price - The price of the product
   *  @param tax_rate - The tax markup for this product
   *  @param currency - The currency in which the price is in
   *  @param active - is this an active product
   *  @param billing_period
   *  @param title - Title of the product
   *  @param intro_paragraph - introduction paragraph
   *  @param bullets - pipe separated product features
   *  @param outro_paragraph - closing paragraph
   *  @param product_description_html - Overriding product description html blob
   *  @param thankyou_html - overriding thank you paragraph html
   *  @param thanks_paragraph - thank you paragraph
   *  @param video_url - video url
   *  @return - the data from the api
  */
  public String  POSTProduct(String product_id,String shortname,String name,String strapline,String alternate_title,String fpzones,String paygateid,String price,String tax_rate,String currency,String active,String billing_period,String title,String intro_paragraph,String bullets,String outro_paragraph,String product_description_html,String thankyou_html,String thanks_paragraph,String video_url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("shortname", shortname);
    	params.put("name", name);
    	params.put("strapline", strapline);
    	params.put("alternate_title", alternate_title);
    	params.put("fpzones", fpzones);
    	params.put("paygateid", paygateid);
    	params.put("price", price);
    	params.put("tax_rate", tax_rate);
    	params.put("currency", currency);
    	params.put("active", active);
    	params.put("billing_period", billing_period);
    	params.put("title", title);
    	params.put("intro_paragraph", intro_paragraph);
    	params.put("bullets", bullets);
    	params.put("outro_paragraph", outro_paragraph);
    	params.put("product_description_html", product_description_html);
    	params.put("thankyou_html", thankyou_html);
    	params.put("thanks_paragraph", thanks_paragraph);
    	params.put("video_url", video_url);
    	retval = this.doCurl("POST","/product",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns the product information given a valid product_id
   *
   *  @param product_id
   *  @return - the data from the api
  */
  public String  GETProduct(String product_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	retval = this.doCurl("GET","/product",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Uploads logo image to product
   *
   *  @param product_id
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTProductImageLogo(String product_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/product/image/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete the logo image within a specific product
   *
   *  @param product_id
   *  @return - the data from the api
  */
  public String  DELETEProductImageLogo(String product_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	retval = this.doCurl("DELETE","/product/image/logo",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete the main image within a specific product
   *
   *  @param product_id
   *  @return - the data from the api
  */
  public String  DELETEProductImageMain(String product_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	retval = this.doCurl("DELETE","/product/image/main",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds partblahnersyndicate provisioning object to a product
   *
   *  @param product_id
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTProductImageMain(String product_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/product/image/main",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Delete the small image within a specific product
   *
   *  @param product_id
   *  @return - the data from the api
  */
  public String  DELETEProductImageSmall(String product_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	retval = this.doCurl("DELETE","/product/image/small",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Uploads small image to product
   *
   *  @param product_id
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTProductImageSmall(String product_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/product/image/small",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Removes a provisioning object from product
   *
   *  @param product_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  DELETEProductProvisioning(String product_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("gen_id", gen_id);
    	retval = this.doCurl("DELETE","/product/provisioning",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds advertising provisioning object to a product
   *
   *  @param product_id
   *  @param publisher_id
   *  @param max_tags
   *  @param max_locations
   *  @return - the data from the api
  */
  public String  POSTProductProvisioningAdvert(String product_id,String publisher_id,String max_tags,String max_locations) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("publisher_id", publisher_id);
    	params.put("max_tags", max_tags);
    	params.put("max_locations", max_locations);
    	retval = this.doCurl("POST","/product/provisioning/advert",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds claim provisioning object to a product
   *
   *  @param product_id
   *  @param package
   *  @return - the data from the api
  */
  public String  POSTProductProvisioningClaim(String product_id,String package) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("package", package);
    	retval = this.doCurl("POST","/product/provisioning/claim",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds maxclaims provisioning object to a product
   *
   *  @param product_id
   *  @param country
   *  @param number
   *  @return - the data from the api
  */
  public String  POSTProductProvisioningMaxclaims(String product_id,String country,String number) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("country", country);
    	params.put("number", number);
    	retval = this.doCurl("POST","/product/provisioning/maxclaims",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds partnersyndicate provisioning object to a product
   *
   *  @param product_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  POSTProductProvisioningPartnersyndicate(String product_id,String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("POST","/product/provisioning/partnersyndicate",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds plugin provisioning object to a product
   *
   *  @param product_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  POSTProductProvisioningPlugin(String product_id,String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("POST","/product/provisioning/plugin",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds SCheduleSMS provisioning object to a product
   *
   *  @param product_id
   *  @param package
   *  @return - the data from the api
  */
  public String  POSTProductProvisioningSchedulesms(String product_id,String package) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("package", package);
    	retval = this.doCurl("POST","/product/provisioning/schedulesms",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Adds syndication provisioning object to a product
   *
   *  @param product_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  POSTProductProvisioningSyndication(String product_id,String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("POST","/product/provisioning/syndication",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Perform the whole PTB process on the supplied entity
   *
   *  @param entity_id
   *  @param destructive
   *  @return - the data from the api
  */
  public String  GETPtbAll(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/ptb/all",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Report on what happened to specific entity_id
   *
   *  @param year - the year to examine
   *  @param month - the month to examine
   *  @param entity_id - the entity to research
   *  @return - the data from the api
  */
  public String  GETPtbLog(String year,String month,String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("year", year);
    	params.put("month", month);
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/ptb/log",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Process an entity with a specific PTB module
   *
   *  @param entity_id
   *  @param module
   *  @param destructive
   *  @return - the data from the api
  */
  public String  GETPtbModule(String entity_id,String module,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("module", module);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/ptb/module",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Report on the run-rate of the Paint the Bridge System
   *
   *  @param country - the country to get the runrate for
   *  @param year - the year to examine
   *  @param month - the month to examine
   *  @param day - the day to examine
   *  @return - the data from the api
  */
  public String  GETPtbRunrate(String country,String year,String month,String day) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	params.put("year", year);
    	params.put("month", month);
    	params.put("day", day);
    	retval = this.doCurl("GET","/ptb/runrate",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Report on the value being added by Paint The Bridge
   *
   *  @param country - the country to get the runrate for
   *  @param year - the year to examine
   *  @param month - the month to examine
   *  @param day - the day to examine
   *  @return - the data from the api
  */
  public String  GETPtbValueadded(String country,String year,String month,String day) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	params.put("year", year);
    	params.put("month", month);
    	params.put("day", day);
    	retval = this.doCurl("GET","/ptb/valueadded",params);
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
  public String  GETPublisher(String publisher_id) throws Exception { 
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
   * Update/Add a publisher
   *
   *  @param publisher_id
   *  @param country
   *  @param name
   *  @param description
   *  @param active
   *  @param url_patterns
   *  @param premium_adverts_platinum
   *  @param premium_adverts_gold
   *  @return - the data from the api
  */
  public String  POSTPublisher(String publisher_id,String country,String name,String description,String active,String url_patterns,String premium_adverts_platinum,String premium_adverts_gold) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("publisher_id", publisher_id);
    	params.put("country", country);
    	params.put("name", name);
    	params.put("description", description);
    	params.put("active", active);
    	params.put("url_patterns", url_patterns);
    	params.put("premium_adverts_platinum", premium_adverts_platinum);
    	params.put("premium_adverts_gold", premium_adverts_gold);
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
  public String  DELETEPublisher(String publisher_id) throws Exception { 
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
   *  @param country
   *  @return - the data from the api
  */
  public String  GETPublisherByCountry(String country) throws Exception { 
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
  public String  GETPublisherByEntityId(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/publisher/byEntityId",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns a publisher that has the specified masheryid
   *
   *  @param publisher_masheryid
   *  @return - the data from the api
  */
  public String  GETPublisherBy_masheryid(String publisher_masheryid) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("publisher_masheryid", publisher_masheryid);
    	retval = this.doCurl("GET","/publisher/by_masheryid",params);
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
  public String  GETQueue(String limit,String queue_name) throws Exception { 
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
   * Create a queue item
   *
   *  @param queue_name
   *  @param data
   *  @return - the data from the api
  */
  public String  PUTQueue(String queue_name,String data) throws Exception { 
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
  public String  DELETEQueue(String queue_id) throws Exception { 
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
   * Find a queue item by its cloudant id
   *
   *  @param queue_id
   *  @return - the data from the api
  */
  public String  GETQueueBy_id(String queue_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("queue_id", queue_id);
    	retval = this.doCurl("GET","/queue/by_id",params);
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
  public String  POSTQueueError(String queue_id,String error) throws Exception { 
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
  public String  GETQueueSearch(String type,String id) throws Exception { 
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
   * Unlock queue items.
   *
   *  @param queue_name
   *  @param seconds
   *  @return - the data from the api
  */
  public String  POSTQueueUnlock(String queue_name,String seconds) throws Exception { 
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
   * Create an SQS queue item
   *
   *  @param queue_name
   *  @param data
   *  @return - the data from the api
  */
  public String  PUTQueue_sqs(String queue_name,String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("queue_name", queue_name);
    	params.put("data", data);
    	retval = this.doCurl("PUT","/queue_sqs",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the attributes of an SQS queue
   *
   *  @param queue_name
   *  @return - the data from the api
  */
  public String  GETQueue_sqsAttributes(String queue_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("queue_name", queue_name);
    	retval = this.doCurl("GET","/queue_sqs/attributes",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns reseller that matches a given reseller id
   *
   *  @param reseller_id
   *  @return - the data from the api
  */
  public String  GETReseller(String reseller_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("reseller_id", reseller_id);
    	retval = this.doCurl("GET","/reseller",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a reseller
   *
   *  @param reseller_id
   *  @param country
   *  @param name
   *  @param description
   *  @param active
   *  @param products
   *  @param master_user_id
   *  @param canViewEmployee
   *  @return - the data from the api
  */
  public String  POSTReseller(String reseller_id,String country,String name,String description,String active,String products,String master_user_id,String canViewEmployee) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("reseller_id", reseller_id);
    	params.put("country", country);
    	params.put("name", name);
    	params.put("description", description);
    	params.put("active", active);
    	params.put("products", products);
    	params.put("master_user_id", master_user_id);
    	params.put("canViewEmployee", canViewEmployee);
    	retval = this.doCurl("POST","/reseller",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Return a sales log by id
   *
   *  @param sales_log_id - The sales log id to pull
   *  @return - the data from the api
  */
  public String  GETSales_log(String sales_log_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("sales_log_id", sales_log_id);
    	retval = this.doCurl("GET","/sales_log",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Return a sales log by id
   *
   *  @param from_date
   *  @param country
   *  @param action_type
   *  @return - the data from the api
  */
  public String  GETSales_logBy_countryBy_date(String from_date,String country,String action_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from_date", from_date);
    	params.put("country", country);
    	params.put("action_type", action_type);
    	retval = this.doCurl("GET","/sales_log/by_country/by_date",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Return a sales log by date range, filtered by masheryid if it is given
   *
   *  @param from_date
   *  @param to_date
   *  @param group
   *  @param limit - Applicable only when group=false
   *  @param skip - Applicable only when group=false
   *  @return - the data from the api
  */
  public String  GETSales_logBy_date(String from_date,String to_date,String group,String limit,String skip) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from_date", from_date);
    	params.put("to_date", to_date);
    	params.put("group", group);
    	params.put("limit", limit);
    	params.put("skip", skip);
    	retval = this.doCurl("GET","/sales_log/by_date",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Log a sale
   *
   *  @param entity_id - The entity the sale was made against
   *  @param country - The country code the sales log orginated
   *  @param action_type - The type of action we are performing
   *  @param ad_type - The type of advertisements
   *  @param publisher_id - The publisher id that has made the sale
   *  @param mashery_id - The mashery id
   *  @param reseller_ref - The reference of the sale made by the seller
   *  @param reseller_agent_id - The id of the agent selling the product
   *  @param max_tags - The number of tags available to the entity
   *  @param max_locations - The number of locations available to the entity
   *  @param extra_tags - The extra number of tags
   *  @param extra_locations - The extra number of locations
   *  @param expiry_date - The date the product expires
   *  @return - the data from the api
  */
  public String  POSTSales_logEntity(String entity_id,String country,String action_type,String ad_type,String publisher_id,String mashery_id,String reseller_ref,String reseller_agent_id,String max_tags,String max_locations,String extra_tags,String extra_locations,String expiry_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("country", country);
    	params.put("action_type", action_type);
    	params.put("ad_type", ad_type);
    	params.put("publisher_id", publisher_id);
    	params.put("mashery_id", mashery_id);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
    	params.put("max_tags", max_tags);
    	params.put("max_locations", max_locations);
    	params.put("extra_tags", extra_tags);
    	params.put("extra_locations", extra_locations);
    	params.put("expiry_date", expiry_date);
    	retval = this.doCurl("POST","/sales_log/entity",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a Sales Log document for a syndication action
   *
   *  @param action_type
   *  @param syndication_type
   *  @param publisher_id
   *  @param expiry_date
   *  @param entity_id
   *  @param group_id
   *  @param seed_masheryid
   *  @param supplier_masheryid
   *  @param country
   *  @param reseller_masheryid
   *  @return - the data from the api
  */
  public String  POSTSales_logSyndication(String action_type,String syndication_type,String publisher_id,String expiry_date,String entity_id,String group_id,String seed_masheryid,String supplier_masheryid,String country,String reseller_masheryid,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("action_type", action_type);
    	params.put("syndication_type", syndication_type);
    	params.put("publisher_id", publisher_id);
    	params.put("expiry_date", expiry_date);
    	params.put("entity_id", entity_id);
    	params.put("group_id", group_id);
    	params.put("seed_masheryid", seed_masheryid);
    	params.put("supplier_masheryid", supplier_masheryid);
    	params.put("country", country);
    	params.put("reseller_masheryid", reseller_masheryid);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/sales_log/syndication",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Converts an Entity into a submission at the Scoot Partner API
   *
   *  @param entity_id - The entity to parse
   *  @param reseller - The reseller Mashery ID, it also determines which Scoot API key to use
   *  @param scoot_id - If specified, the related Scoot listing will be updated.
   *  @param autofill_scoot_id - If scoot_id is not given, look for past successful syndication logs to fill in the Scoot ID
   *  @return - the data from the api
  */
  public String  POSTScoot_priority(String entity_id,String reseller,String scoot_id,String autofill_scoot_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("reseller", reseller);
    	params.put("scoot_id", scoot_id);
    	params.put("autofill_scoot_id", autofill_scoot_id);
    	retval = this.doCurl("POST","/scoot_priority",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Make a url shorter
   *
   *  @param url - the url to shorten
   *  @param idOnly - Return just the Shortened URL ID
   *  @return - the data from the api
  */
  public String  PUTShortenurl(String url,String idOnly) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("url", url);
    	params.put("idOnly", idOnly);
    	retval = this.doCurl("PUT","/shortenurl",params);
    } finally { 
    }
    return retval;
  }


  /**
   * get the long url from the db
   *
   *  @param id - the id to fetch from the db
   *  @return - the data from the api
  */
  public String  GETShortenurl(String id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("id", id);
    	retval = this.doCurl("GET","/shortenurl",params);
    } finally { 
    }
    return retval;
  }


  /**
   * For insance, reporting a phone number as wrong
   *
   *  @param entity_id - A valid entity_id e.g. 379236608286720
   *  @param country - The country code from where the signal originated e.g. ie
   *  @param gen_id - The gen_id for the item being reported
   *  @param signal_type - The signal that is to be reported e.g. wrong
   *  @param data_type - The type of data being reported
   *  @param inactive_reason - The reason for making the entity inactive
   *  @param inactive_description - A description to accompany the inactive reasoning
   *  @param feedback - Some feedback from the person submitting the signal
   *  @return - the data from the api
  */
  public String  POSTSignal(String entity_id,String country,String gen_id,String signal_type,String data_type,String inactive_reason,String inactive_description,String feedback) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("country", country);
    	params.put("gen_id", gen_id);
    	params.put("signal_type", signal_type);
    	params.put("data_type", data_type);
    	params.put("inactive_reason", inactive_reason);
    	params.put("inactive_description", inactive_description);
    	params.put("feedback", feedback);
    	retval = this.doCurl("POST","/signal",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a given country and entity id suffix, this endpoint will return a list of entity IDs and their last modified dates for sitemap generation.
   *
   *  @param country - Target country code.
   *  @param id_suffix - Target entity Id suffix (4 digits).
   *  @param skip
   *  @param limit
   *  @return - the data from the api
  */
  public String  GETSitemapEntity(String country,String id_suffix,String skip,String limit) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	params.put("id_suffix", id_suffix);
    	params.put("skip", skip);
    	params.put("limit", limit);
    	retval = this.doCurl("GET","/sitemap/entity",params);
    } finally { 
    }
    return retval;
  }


  /**
   * With a given country, this endpoint will return a list of entity ID suffixes which have records.
   *
   *  @param country - Target country code.
   *  @return - the data from the api
  */
  public String  GETSitemapEntitySummary(String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	retval = this.doCurl("GET","/sitemap/entity/summary",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a spider document
   *
   *  @param spider_id
   *  @return - the data from the api
  */
  public String  GETSpider(String spider_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("spider_id", spider_id);
    	retval = this.doCurl("GET","/spider",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the number of times an entity has been served out as an advert or on serps/bdp pages
   *
   *  @param entity_id - A valid entity_id e.g. 379236608286720
   *  @param year - The year to report on
   *  @param month - The month to report on
   *  @return - the data from the api
  */
  public String  GETStatsEntityBy_date(String entity_id,String year,String month) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("year", year);
    	params.put("month", month);
    	retval = this.doCurl("GET","/stats/entity/by_date",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the stats on an entity in a given year
   *
   *  @param entity_id - A valid entity_id e.g. 379236608286720
   *  @param year - The year to report on
   *  @return - the data from the api
  */
  public String  GETStatsEntityBy_year(String entity_id,String year) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("year", year);
    	retval = this.doCurl("GET","/stats/entity/by_year",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Confirms that the API is active, and returns the current version number
   *
   *  @return - the data from the api
  */
  public String  GETStatus() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/status",params);
    } finally { 
    }
    return retval;
  }


  /**
   * get a Syndication
   *
   *  @param syndication_id
   *  @return - the data from the api
  */
  public String  GETSyndication(String syndication_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_id", syndication_id);
    	retval = this.doCurl("GET","/syndication",params);
    } finally { 
    }
    return retval;
  }


  /**
   * get a Syndication by entity_id
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  GETSyndicationBy_entity_id(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/syndication/by_entity_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a Syndication by Reseller (Mashery ID) and optional entity ID
   *
   *  @param reseller_masheryid
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  GETSyndicationBy_reseller(String reseller_masheryid,String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("reseller_masheryid", reseller_masheryid);
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/syndication/by_reseller",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Cancel a syndication
   *
   *  @param syndication_id
   *  @return - the data from the api
  */
  public String  POSTSyndicationCancel(String syndication_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_id", syndication_id);
    	retval = this.doCurl("POST","/syndication/cancel",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Add a Syndicate
   *
   *  @param syndication_type
   *  @param publisher_id
   *  @param expiry_date
   *  @param entity_id
   *  @param group_id
   *  @param seed_masheryid
   *  @param supplier_masheryid
   *  @param country
   *  @param data_filter
   *  @return - the data from the api
  */
  public String  POSTSyndicationCreate(String syndication_type,String publisher_id,String expiry_date,String entity_id,String group_id,String seed_masheryid,String supplier_masheryid,String country,String data_filter,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_type", syndication_type);
    	params.put("publisher_id", publisher_id);
    	params.put("expiry_date", expiry_date);
    	params.put("entity_id", entity_id);
    	params.put("group_id", group_id);
    	params.put("seed_masheryid", seed_masheryid);
    	params.put("supplier_masheryid", supplier_masheryid);
    	params.put("country", country);
    	params.put("data_filter", data_filter);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/syndication/create",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Renew a Syndicate
   *
   *  @param syndication_type
   *  @param publisher_id
   *  @param entity_id
   *  @param group_id
   *  @param seed_masheryid
   *  @param supplier_masheryid
   *  @param country
   *  @param expiry_date
   *  @return - the data from the api
  */
  public String  POSTSyndicationRenew(String syndication_type,String publisher_id,String entity_id,String group_id,String seed_masheryid,String supplier_masheryid,String country,String _user_id,String expiry_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_type", syndication_type);
    	params.put("publisher_id", publisher_id);
    	params.put("entity_id", entity_id);
    	params.put("group_id", group_id);
    	params.put("seed_masheryid", seed_masheryid);
    	params.put("supplier_masheryid", supplier_masheryid);
    	params.put("country", country);
    	params.put("_user_id", _user_id);
    	params.put("expiry_date", expiry_date);
    	retval = this.doCurl("POST","/syndication/renew",params);
    } finally { 
    }
    return retval;
  }


  /**
   * When we get a syndication update make a record of it
   *
   *  @param entity_id - The entity to pull
   *  @param publisher_id - The publisher this log entry refers to
   *  @param action - The log type
   *  @param success - If the syndication was successful
   *  @param message - An optional message e.g. submitted to the syndication partner
   *  @param syndicated_id - The entity as known to the publisher
   *  @param reseller_id - The optional reseller id used in the syndications
   *  @return - the data from the api
  */
  public String  POSTSyndication_log(String entity_id,String publisher_id,String action,String success,String message,String syndicated_id,String reseller_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	params.put("action", action);
    	params.put("success", success);
    	params.put("message", message);
    	params.put("syndicated_id", syndicated_id);
    	params.put("reseller_id", reseller_id);
    	retval = this.doCurl("POST","/syndication_log",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get all syndication log entries for a given entity id
   *
   *  @param entity_id
   *  @param page
   *  @param per_page
   *  @return - the data from the api
  */
  public String  GETSyndication_logBy_entity_id(String entity_id,String page,String per_page) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("page", page);
    	params.put("per_page", per_page);
    	retval = this.doCurl("GET","/syndication_log/by_entity_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get the latest syndication log feedback entry for a given entity id and publisher
   *
   *  @param entity_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  GETSyndication_logLast_syndicated_id(String entity_id,String publisher_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	retval = this.doCurl("GET","/syndication_log/last_syndicated_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Creates a new Syndication Submission
   *
   *  @param syndication_type
   *  @param entity_id
   *  @param publisher_id
   *  @param submission_id
   *  @return - the data from the api
  */
  public String  PUTSyndication_submission(String syndication_type,String entity_id,String publisher_id,String submission_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_type", syndication_type);
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	params.put("submission_id", submission_id);
    	retval = this.doCurl("PUT","/syndication_submission",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns a Syndication Submission
   *
   *  @param syndication_submission_id
   *  @return - the data from the api
  */
  public String  GETSyndication_submission(String syndication_submission_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_submission_id", syndication_submission_id);
    	retval = this.doCurl("GET","/syndication_submission",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Set active to false for a Syndication Submission
   *
   *  @param syndication_submission_id
   *  @return - the data from the api
  */
  public String  POSTSyndication_submissionDeactivate(String syndication_submission_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_submission_id", syndication_submission_id);
    	retval = this.doCurl("POST","/syndication_submission/deactivate",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Set the processed date to now for a Syndication Submission
   *
   *  @param syndication_submission_id
   *  @return - the data from the api
  */
  public String  POSTSyndication_submissionProcessed(String syndication_submission_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_submission_id", syndication_submission_id);
    	retval = this.doCurl("POST","/syndication_submission/processed",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Provides a tokenised URL to redirect a user so they can add an entity to Central Index
   *
   *  @param language - The language to use to render the add path e.g. en
   *  @param business_name - The name of the business (to be presented as a default) e.g. The Dog and Duck
   *  @param business_phone - The phone number of the business (to be presented as a default) e.g. 20 8480-2777
   *  @param business_postcode - The postcode of the business (to be presented as a default) e.g. EC1 1AA
   *  @param portal_name - The name of the website that data is to be added on e.g. YourLocal
   *  @param supplier_id - The supplier id e.g. 1234xxx889
   *  @param partner - syndication partner (eg 192)
   *  @param country - The country of the entity to be added e.g. gb
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @return - the data from the api
  */
  public String  GETTokenAdd(String language,String business_name,String business_phone,String business_postcode,String portal_name,String supplier_id,String partner,String country,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("language", language);
    	params.put("business_name", business_name);
    	params.put("business_phone", business_phone);
    	params.put("business_postcode", business_postcode);
    	params.put("portal_name", portal_name);
    	params.put("supplier_id", supplier_id);
    	params.put("partner", partner);
    	params.put("country", country);
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/token/add",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Provides a tokenised URL to redirect a user to claim an entity on Central Index
   *
   *  @param entity_id - Entity ID to be claimed e.g. 380348266819584
   *  @param supplier_id - Supplier ID to be added (along with masheryid) e.g. 380348266819584
   *  @param language - The language to use to render the claim path e.g. en
   *  @param portal_name - The name of the website that entity is being claimed on e.g. YourLocal
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param admin_host - The admin host to refer back to - will only be respected if whitelisted in configuration
   *  @return - the data from the api
  */
  public String  GETTokenClaim(String entity_id,String supplier_id,String language,String portal_name,String flatpack_id,String admin_host) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("supplier_id", supplier_id);
    	params.put("language", language);
    	params.put("portal_name", portal_name);
    	params.put("flatpack_id", flatpack_id);
    	params.put("admin_host", admin_host);
    	retval = this.doCurl("GET","/token/claim",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for the contact us method
   *
   *  @param portal_name - The portal name
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param language - en, es, etc...
   *  @param referring_url - the url where the request came from
   *  @return - the data from the api
  */
  public String  GETTokenContact_us(String portal_name,String flatpack_id,String language,String referring_url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("portal_name", portal_name);
    	params.put("flatpack_id", flatpack_id);
    	params.put("language", language);
    	params.put("referring_url", referring_url);
    	retval = this.doCurl("GET","/token/contact_us",params);
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
  public String  GETTokenDecode(String token) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("token", token);
    	retval = this.doCurl("GET","/token/decode",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for edit path
   *
   *  @param entity_id - The id of the entity being upgraded
   *  @param language - The language for the app
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param edit_page - the page in the edit path that the user should land on
   *  @return - the data from the api
  */
  public String  GETTokenEdit(String entity_id,String language,String flatpack_id,String edit_page) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	params.put("edit_page", edit_page);
    	retval = this.doCurl("GET","/token/edit",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for some admin page.
   *
   *  @param portal_name - The name of the application that has initiated the login process, example: 'Your Local'
   *  @param code - Secret string which will be validated by the target page.
   *  @param expireAt - When this token expires in UNIX timestamp. The target page should validate this.
   *  @param language - The language for the app
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param multipack_id - The id of the multipack site where the request originated
   *  @param data - Optional extra data to be passed to the targeted page.
   *  @return - the data from the api
  */
  public String  GETTokenEncode(String portal_name,String code,String expireAt,String language,String flatpack_id,String multipack_id,String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("portal_name", portal_name);
    	params.put("code", code);
    	params.put("expireAt", expireAt);
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	params.put("multipack_id", multipack_id);
    	params.put("data", data);
    	retval = this.doCurl("GET","/token/encode",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for login path
   *
   *  @param portal_name - The name of the application that has initiated the login process, example: 'Your Local'
   *  @param language - The language for the app
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param multipack_id - The id of the multipack site where the request originated
   *  @return - the data from the api
  */
  public String  GETTokenLogin(String portal_name,String language,String flatpack_id,String multipack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("portal_name", portal_name);
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	params.put("multipack_id", multipack_id);
    	retval = this.doCurl("GET","/token/login",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a tokenised url for an password reset
   *
   *  @param login_id - Login id
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param entity_id
   *  @param action
   *  @return - the data from the api
  */
  public String  GETTokenLoginReset_password(String login_id,String flatpack_id,String entity_id,String action) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("login_id", login_id);
    	params.put("flatpack_id", flatpack_id);
    	params.put("entity_id", entity_id);
    	params.put("action", action);
    	retval = this.doCurl("GET","/token/login/reset_password",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a tokenised url for an email verification
   *
   *  @param email - Email address
   *  @param first_name - First name of the new user
   *  @param last_name - Last name of the new user
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param entity_id
   *  @param action
   *  @return - the data from the api
  */
  public String  GETTokenLoginSet_password(String email,String first_name,String last_name,String flatpack_id,String entity_id,String action) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email", email);
    	params.put("first_name", first_name);
    	params.put("last_name", last_name);
    	params.put("flatpack_id", flatpack_id);
    	params.put("entity_id", entity_id);
    	params.put("action", action);
    	retval = this.doCurl("GET","/token/login/set_password",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for messaging path
   *
   *  @param entity_id - The id of the entity being messaged
   *  @param portal_name - The name of the application that has initiated the email process, example: 'Your Local'
   *  @param language - The language for the app
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @return - the data from the api
  */
  public String  GETTokenMessage(String entity_id,String portal_name,String language,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("portal_name", portal_name);
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/token/message",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for partnerclaim path
   *
   *  @param language - The language for the app
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param partner - The partner (eg 192)
   *  @param partnerid - the supplier id from the partner site
   *  @param preclaimed - is this already claimed on the partner site (used for messaging)
   *  @return - the data from the api
  */
  public String  GETTokenPartnerclaim(String language,String flatpack_id,String partner,String partnerid,String preclaimed) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	params.put("partner", partner);
    	params.put("partnerid", partnerid);
    	params.put("preclaimed", preclaimed);
    	retval = this.doCurl("GET","/token/partnerclaim",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for partnerclaim path (ie we start at a CI entity id rather than an external partner id)
   *
   *  @param language - The language for the app
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param partner - The partner (eg 192)
   *  @param entityid - the CI entity id
   *  @param preclaimed - is this already claimed on the partner site (used for messaging)
   *  @return - the data from the api
  */
  public String  GETTokenPartnerclaimInternal(String language,String flatpack_id,String partner,String entityid,String preclaimed) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	params.put("partner", partner);
    	params.put("entityid", entityid);
    	params.put("preclaimed", preclaimed);
    	retval = this.doCurl("GET","/token/partnerclaim/internal",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for product path
   *
   *  @param entity_id - The id of the entity to add a product to
   *  @param product_id - The product id of the product
   *  @param language - The language for the app
   *  @param portal_name - The portal name
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param source - email, social media etc
   *  @param channel - 
   *  @param campaign - the campaign identifier
   *  @return - the data from the api
  */
  public String  GETTokenProduct(String entity_id,String product_id,String language,String portal_name,String flatpack_id,String source,String channel,String campaign) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("product_id", product_id);
    	params.put("language", language);
    	params.put("portal_name", portal_name);
    	params.put("flatpack_id", flatpack_id);
    	params.put("source", source);
    	params.put("channel", channel);
    	params.put("campaign", campaign);
    	retval = this.doCurl("GET","/token/product",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch token for product path
   *
   *  @param entity_id - The id of the entity to add a product to
   *  @param portal_name - The portal name
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param language - en, es, etc...
   *  @return - the data from the api
  */
  public String  GETTokenProduct_selector(String entity_id,String portal_name,String flatpack_id,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("portal_name", portal_name);
    	params.put("flatpack_id", flatpack_id);
    	params.put("language", language);
    	retval = this.doCurl("GET","/token/product_selector",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Provides a tokenised URL that allows a user to report incorrect entity information
   *
   *  @param entity_id - The unique Entity ID e.g. 379236608286720
   *  @param portal_name - The name of the portal that the user is coming from e.g. YourLocal
   *  @param language - The language to use to render the report path
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @return - the data from the api
  */
  public String  GETTokenReport(String entity_id,String portal_name,String language,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("portal_name", portal_name);
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/token/report",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a tokenised url for the review path
   *
   *  @param portal_name - The portal name
   *  @param entity_id
   *  @param language - en, es, etc...
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @return - the data from the api
  */
  public String  GETTokenReview(String portal_name,String entity_id,String language,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("portal_name", portal_name);
    	params.put("entity_id", entity_id);
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/token/review",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Get a tokenised url for the testimonial path
   *
   *  @param portal_name - The portal name
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param language - en, es, etc...
   *  @param entity_id
   *  @param shorten_url
   *  @return - the data from the api
  */
  public String  GETTokenTestimonial(String portal_name,String flatpack_id,String language,String entity_id,String shorten_url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("portal_name", portal_name);
    	params.put("flatpack_id", flatpack_id);
    	params.put("language", language);
    	params.put("entity_id", entity_id);
    	params.put("shorten_url", shorten_url);
    	retval = this.doCurl("GET","/token/testimonial",params);
    } finally { 
    }
    return retval;
  }


  /**
   * The JaroWinklerDistance between two entities postal address objects
   *
   *  @param first_entity_id - The entity id of the first entity to be used in the postal address difference
   *  @param second_entity_id - The entity id of the second entity to be used in the postal address difference
   *  @return - the data from the api
  */
  public String  GETToolsAddressdiff(String first_entity_id,String second_entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("first_entity_id", first_entity_id);
    	params.put("second_entity_id", second_entity_id);
    	retval = this.doCurl("GET","/tools/addressdiff",params);
    } finally { 
    }
    return retval;
  }


  /**
   * An API call to test crashing the server
   *
   *  @return - the data from the api
  */
  public String  GETToolsCrash() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/tools/crash",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Provide a method, a path and some data to run a load of curl commands and get emailed when complete
   *
   *  @param method - The method e.g. POST
   *  @param path - The relative api call e.g. /entity/phone
   *  @param filedata - A tab separated file for ingest
   *  @param email - Response email address e.g. dave@fender.com
   *  @return - the data from the api
  */
  public String  POSTToolsCurl(String method,String path,String filedata,String email) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("method", method);
    	params.put("path", path);
    	params.put("filedata", filedata);
    	params.put("email", email);
    	retval = this.doCurl("POST","/tools/curl",params);
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
  public String  GETToolsDocs(String object,String format) throws Exception { 
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
   * Format an address according to the rules of the country supplied
   *
   *  @param address - The address to format
   *  @param country - The country where the address is based
   *  @return - the data from the api
  */
  public String  GETToolsFormatAddress(String address,String country) throws Exception { 
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
   * Format a phone number according to the rules of the country supplied
   *
   *  @param number - The telephone number to format
   *  @param country - The country where the telephone number is based
   *  @param ignoreRegionCheck - If ture, we only check if the phone number is valid, ignoring country context
   *  @return - the data from the api
  */
  public String  GETToolsFormatPhone(String number,String country,String ignoreRegionCheck) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("number", number);
    	params.put("country", country);
    	params.put("ignoreRegionCheck", ignoreRegionCheck);
    	retval = this.doCurl("GET","/tools/format/phone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Supply an address to geocode - returns lat/lon and accuracy
   *
   *  @param building_number
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param province
   *  @param postcode
   *  @param country
   *  @return - the data from the api
  */
  public String  GETToolsGeocode(String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("building_number", building_number);
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("province", province);
    	params.put("postcode", postcode);
    	params.put("country", country);
    	retval = this.doCurl("GET","/tools/geocode",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Given a spreadsheet ID, and a worksheet ID, add a row
   *
   *  @param spreadsheet_key - The key of the spreadsheet to edit
   *  @param worksheet_key - The key of the worksheet to edit - failure to provide this will assume worksheet with the label 'Sheet1'
   *  @param data - A comma separated list to add as cells
   *  @return - the data from the api
  */
  public String  POSTToolsGooglesheetAdd_row(String spreadsheet_key,String worksheet_key,String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("spreadsheet_key", spreadsheet_key);
    	params.put("worksheet_key", worksheet_key);
    	params.put("data", data);
    	retval = this.doCurl("POST","/tools/googlesheet/add_row",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Given a spreadsheet ID and the name of a worksheet identify the Google ID for the worksheet
   *
   *  @param spreadsheet_key - The key of the spreadsheet
   *  @param worksheet_name - The name/label of the worksheet to identify
   *  @return - the data from the api
  */
  public String  POSTToolsGooglesheetWorksheet_id(String spreadsheet_key,String worksheet_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("spreadsheet_key", spreadsheet_key);
    	params.put("worksheet_name", worksheet_name);
    	retval = this.doCurl("POST","/tools/googlesheet/worksheet_id",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Given some image data we can resize and upload the images
   *
   *  @param filedata - The image data to upload and resize
   *  @param type - The type of image to upload and resize
   *  @param image_dir - Set the destination directory of the generated images on S3. Only available when Image API is enabled.
   *  @return - the data from the api
  */
  public String  POSTToolsImage(String filedata,String type,String image_dir) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("filedata", filedata);
    	params.put("type", type);
    	params.put("image_dir", image_dir);
    	retval = this.doCurl("POST","/tools/image",params);
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
  public String  GETToolsIodocs(String mode,String path,String endpoint,String doctype) throws Exception { 
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
   * compile the supplied less with the standard Bootstrap less into a CSS file
   *
   *  @param less - The LESS code to compile
   *  @return - the data from the api
  */
  public String  GETToolsLess(String less) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("less", less);
    	retval = this.doCurl("GET","/tools/less",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Parse unstructured address data to fit our structured address objects
   *
   *  @param address
   *  @param postcode
   *  @param country
   *  @param normalise
   *  @return - the data from the api
  */
  public String  GETToolsParseAddress(String address,String postcode,String country,String normalise) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("address", address);
    	params.put("postcode", postcode);
    	params.put("country", country);
    	params.put("normalise", normalise);
    	retval = this.doCurl("GET","/tools/parse/address",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Ring the person and verify their account
   *
   *  @param to - The phone number to verify
   *  @param from - The phone number to call from
   *  @param pin - The pin to verify the phone number with
   *  @param twilio_voice - The language to read the verification in
   *  @param extension - The pin to verify the phone number with
   *  @return - the data from the api
  */
  public String  GETToolsPhonecallVerify(String to,String from,String pin,String twilio_voice,String extension) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("to", to);
    	params.put("from", from);
    	params.put("pin", pin);
    	params.put("twilio_voice", twilio_voice);
    	params.put("extension", extension);
    	retval = this.doCurl("GET","/tools/phonecall/verify",params);
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
  public String  GETToolsPhonetic(String text) throws Exception { 
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
   * Attempt to process a phone number, removing anything which is not a digit
   *
   *  @param number
   *  @return - the data from the api
  */
  public String  GETToolsProcess_phone(String number) throws Exception { 
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
   * Fully process a string. This includes removing punctuation, stops words and stemming a string. Also returns the phonetic representation of this string.
   *
   *  @param text
   *  @return - the data from the api
  */
  public String  GETToolsProcess_string(String text) throws Exception { 
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
   * Force refresh of search indexes
   *
   *  @return - the data from the api
  */
  public String  GETToolsReindex() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/tools/reindex",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Check to see if a supplied email address is valid
   *
   *  @param from - The phone number from which the SMS orginates
   *  @param to - The phone number to which the SMS is to be sent
   *  @param message - The message to be sent in the SMS
   *  @return - the data from the api
  */
  public String  GETToolsSendsms(String from,String to,String message) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from", from);
    	params.put("to", to);
    	params.put("message", message);
    	retval = this.doCurl("GET","/tools/sendsms",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Spider a single url looking for key facts
   *
   *  @param url
   *  @param pages
   *  @param country
   *  @param save
   *  @return - the data from the api
  */
  public String  GETToolsSpider(String url,String pages,String country,String save) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("url", url);
    	params.put("pages", pages);
    	params.put("country", country);
    	params.put("save", save);
    	retval = this.doCurl("GET","/tools/spider",params);
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
  public String  GETToolsStem(String text) throws Exception { 
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
   * Removes stopwords from a string
   *
   *  @param text
   *  @return - the data from the api
  */
  public String  GETToolsStopwords(String text) throws Exception { 
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
   * Fetch the result of submitted data we sent to InfoGroup
   *
   *  @param syndication_submission_id - The syndication_submission_id to fetch info for
   *  @return - the data from the api
  */
  public String  GETToolsSubmissionInfogroup(String syndication_submission_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("syndication_submission_id", syndication_submission_id);
    	retval = this.doCurl("GET","/tools/submission/infogroup",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to 118 Places CSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicate118(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/118",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to Bing Ads CSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateBingads(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/bingads",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to Bing Places CSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateBingplaces(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/bingplaces",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to DnB TSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateDnb(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/dnb",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert add it to arlington
   *
   *  @param entity_id - The entity_id to fetch
   *  @param reseller_masheryid - The reseller_masheryid
   *  @param destructive - Add the entity or simulate adding the entity
   *  @param data_filter - The level of filtering to apply to the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateEnablemedia(String entity_id,String reseller_masheryid,String destructive,String data_filter) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("reseller_masheryid", reseller_masheryid);
    	params.put("destructive", destructive);
    	params.put("data_filter", data_filter);
    	retval = this.doCurl("GET","/tools/syndicate/enablemedia",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert add it to Factual
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateFactual(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/tools/syndicate/factual",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to Factual CSV / TSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateFactualcsv(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/factualcsv",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Syndicate an entity to Foursquare
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateFoursquare(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/tools/syndicate/foursquare",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to TomTom XML format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateGoogle(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/google",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to Infobel CSV / TSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateInfobelcsv(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/infobelcsv",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert add it to InfoGroup
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateInfogroup(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/tools/syndicate/infogroup",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert add it to Judy's Book
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateJudysbook(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/tools/syndicate/judysbook",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to Google KML format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateKml(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/kml",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Syndicate database to localdatabase.com
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateLocaldatabase(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/tools/syndicate/localdatabase",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to Nokia NBS CSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateNokia(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/nokia",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Post an entity to OpenStreetMap
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateOsm(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/tools/syndicate/osm",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Syndicate an entity to ThomsonLocal
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateThomsonlocal(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/tools/syndicate/thomsonlocal",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to TomTom XML format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateTomtom(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/tomtom",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert it to YALWA csv
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateYalwa(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("GET","/tools/syndicate/yalwa",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetch the entity and convert add it to Yassaaaabeeee!!
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  GETToolsSyndicateYasabe(String entity_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("GET","/tools/syndicate/yasabe",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Test to see whether this supplied data would already match an entity
   *
   *  @param name
   *  @param building_number
   *  @param branch_name
   *  @param address1
   *  @param address2
   *  @param address3
   *  @param district
   *  @param town
   *  @param county
   *  @param province
   *  @param postcode
   *  @param country
   *  @param latitude
   *  @param longitude
   *  @param timezone
   *  @param telephone_number
   *  @param additional_telephone_number
   *  @param email
   *  @param website
   *  @param category_id
   *  @param category_type
   *  @param do_not_display
   *  @param referrer_url
   *  @param referrer_name
   *  @return - the data from the api
  */
  public String  GETToolsTestmatch(String name,String building_number,String branch_name,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String country,String latitude,String longitude,String timezone,String telephone_number,String additional_telephone_number,String email,String website,String category_id,String category_type,String do_not_display,String referrer_url,String referrer_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("name", name);
    	params.put("building_number", building_number);
    	params.put("branch_name", branch_name);
    	params.put("address1", address1);
    	params.put("address2", address2);
    	params.put("address3", address3);
    	params.put("district", district);
    	params.put("town", town);
    	params.put("county", county);
    	params.put("province", province);
    	params.put("postcode", postcode);
    	params.put("country", country);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("timezone", timezone);
    	params.put("telephone_number", telephone_number);
    	params.put("additional_telephone_number", additional_telephone_number);
    	params.put("email", email);
    	params.put("website", website);
    	params.put("category_id", category_id);
    	params.put("category_type", category_type);
    	params.put("do_not_display", do_not_display);
    	params.put("referrer_url", referrer_url);
    	params.put("referrer_name", referrer_name);
    	retval = this.doCurl("GET","/tools/testmatch",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Send a transactional email via Adestra or other email provider
   *
   *  @param email_id - The ID of the email to send
   *  @param destination_email - The email address to send to
   *  @param email_supplier - The email supplier
   *  @return - the data from the api
  */
  public String  GETToolsTransactional_email(String email_id,String destination_email,String email_supplier) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email_id", email_id);
    	params.put("destination_email", destination_email);
    	params.put("email_supplier", email_supplier);
    	retval = this.doCurl("GET","/tools/transactional_email",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Upload a file to our asset server and return the url
   *
   *  @param filedata
   *  @return - the data from the api
  */
  public String  POSTToolsUpload(String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/tools/upload",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find a canonical URL from a supplied URL
   *
   *  @param url - The url to process
   *  @param max_redirects - The maximum number of reirects
   *  @return - the data from the api
  */
  public String  GETToolsUrl_details(String url,String max_redirects) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("url", url);
    	params.put("max_redirects", max_redirects);
    	retval = this.doCurl("GET","/tools/url_details",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Check to see if a supplied email address is valid
   *
   *  @param email_address - The email address to validate
   *  @return - the data from the api
  */
  public String  GETToolsValidate_email(String email_address) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email_address", email_address);
    	retval = this.doCurl("GET","/tools/validate_email",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Calls a number to make sure it is active
   *
   *  @param phone_number - The phone number to validate
   *  @param country - The country code of the phone number to be validated
   *  @return - the data from the api
  */
  public String  GETToolsValidate_phone(String phone_number,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("phone_number", phone_number);
    	params.put("country", country);
    	retval = this.doCurl("GET","/tools/validate_phone",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Deleting a traction
   *
   *  @param traction_id
   *  @return - the data from the api
  */
  public String  DELETETraction(String traction_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("traction_id", traction_id);
    	retval = this.doCurl("DELETE","/traction",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetching a traction
   *
   *  @param traction_id
   *  @return - the data from the api
  */
  public String  GETTraction(String traction_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("traction_id", traction_id);
    	retval = this.doCurl("GET","/traction",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Update/Add a traction
   *
   *  @param traction_id
   *  @param trigger_type
   *  @param action_type
   *  @param country
   *  @param email_addresses
   *  @param title
   *  @param body
   *  @param api_method
   *  @param api_url
   *  @param api_params
   *  @param active
   *  @param reseller_masheryid
   *  @param publisher_masheryid
   *  @param description
   *  @return - the data from the api
  */
  public String  POSTTraction(String traction_id,String trigger_type,String action_type,String country,String email_addresses,String title,String body,String api_method,String api_url,String api_params,String active,String reseller_masheryid,String publisher_masheryid,String description) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("traction_id", traction_id);
    	params.put("trigger_type", trigger_type);
    	params.put("action_type", action_type);
    	params.put("country", country);
    	params.put("email_addresses", email_addresses);
    	params.put("title", title);
    	params.put("body", body);
    	params.put("api_method", api_method);
    	params.put("api_url", api_url);
    	params.put("api_params", api_params);
    	params.put("active", active);
    	params.put("reseller_masheryid", reseller_masheryid);
    	params.put("publisher_masheryid", publisher_masheryid);
    	params.put("description", description);
    	retval = this.doCurl("POST","/traction",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Fetching active tractions
   *
   *  @return - the data from the api
  */
  public String  GETTractionActive() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	retval = this.doCurl("GET","/traction/active",params);
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
  public String  PUTTransaction(String entity_id,String user_id,String basket_total,String basket,String currency,String notes) throws Exception { 
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
   * Given a transaction_id retrieve information on it
   *
   *  @param transaction_id
   *  @return - the data from the api
  */
  public String  GETTransaction(String transaction_id) throws Exception { 
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
   * Set a transactions status to authorised
   *
   *  @param transaction_id
   *  @param paypal_getexpresscheckoutdetails
   *  @return - the data from the api
  */
  public String  POSTTransactionAuthorised(String transaction_id,String paypal_getexpresscheckoutdetails) throws Exception { 
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
   * Given a transaction_id retrieve information on it
   *
   *  @param paypal_transaction_id
   *  @return - the data from the api
  */
  public String  GETTransactionBy_paypal_transaction_id(String paypal_transaction_id) throws Exception { 
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
   * Set a transactions status to cancelled
   *
   *  @param transaction_id
   *  @return - the data from the api
  */
  public String  POSTTransactionCancelled(String transaction_id) throws Exception { 
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
   * Set a transactions status to complete
   *
   *  @param transaction_id
   *  @param paypal_doexpresscheckoutpayment
   *  @param user_id
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  POSTTransactionComplete(String transaction_id,String paypal_doexpresscheckoutpayment,String user_id,String entity_id) throws Exception { 
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
   * Set a transactions status to inprogess
   *
   *  @param transaction_id
   *  @param paypal_setexpresscheckout
   *  @return - the data from the api
  */
  public String  POSTTransactionInprogress(String transaction_id,String paypal_setexpresscheckout) throws Exception { 
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
   * Update user based on email address or social_network/social_network_id
   *
   *  @param email
   *  @param user_id
   *  @param first_name
   *  @param last_name
   *  @param active
   *  @param last_flatpack - Last visited flatpack (used for admin deep linking)
   *  @param trust
   *  @param creation_date
   *  @param user_type
   *  @param social_network
   *  @param social_network_id
   *  @param reseller_admin_masheryid
   *  @param group_id
   *  @param admin_upgrader
   *  @param opt_in_marketing
   *  @return - the data from the api
  */
  public String  POSTUser(String email,String user_id,String first_name,String last_name,String active,String last_flatpack,String trust,String creation_date,String user_type,String social_network,String social_network_id,String reseller_admin_masheryid,String group_id,String admin_upgrader,String _user_id,String opt_in_marketing) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email", email);
    	params.put("user_id", user_id);
    	params.put("first_name", first_name);
    	params.put("last_name", last_name);
    	params.put("active", active);
    	params.put("last_flatpack", last_flatpack);
    	params.put("trust", trust);
    	params.put("creation_date", creation_date);
    	params.put("user_type", user_type);
    	params.put("social_network", social_network);
    	params.put("social_network_id", social_network_id);
    	params.put("reseller_admin_masheryid", reseller_admin_masheryid);
    	params.put("group_id", group_id);
    	params.put("admin_upgrader", admin_upgrader);
    	params.put("_user_id", _user_id);
    	params.put("opt_in_marketing", opt_in_marketing);
    	retval = this.doCurl("POST","/user",params);
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
  public String  GETUser(String user_id) throws Exception { 
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
   * Is this user allowed to edit this entity
   *
   *  @param entity_id
   *  @param user_id
   *  @return - the data from the api
  */
  public String  GETUserAllowed_to_edit(String entity_id,String user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("user_id", user_id);
    	retval = this.doCurl("GET","/user/allowed_to_edit",params);
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
  public String  GETUserBy_email(String email) throws Exception { 
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
   * Returns all the users that match the supplied group_id
   *
   *  @param group_id
   *  @return - the data from the api
  */
  public String  GETUserBy_groupid(String group_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	retval = this.doCurl("GET","/user/by_groupid",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Returns all the users that match the supplied reseller_admin_masheryid
   *
   *  @param reseller_admin_masheryid
   *  @return - the data from the api
  */
  public String  GETUserBy_reseller_admin_masheryid(String reseller_admin_masheryid) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("reseller_admin_masheryid", reseller_admin_masheryid);
    	retval = this.doCurl("GET","/user/by_reseller_admin_masheryid",params);
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
  public String  GETUserBy_social_media(String name,String id) throws Exception { 
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
   * Downgrade an existing user
   *
   *  @param user_id
   *  @param user_type
   *  @return - the data from the api
  */
  public String  POSTUserDowngrade(String user_id,String user_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	params.put("user_type", user_type);
    	retval = this.doCurl("POST","/user/downgrade",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Removes group_admin privileges from a specified user
   *
   *  @param user_id
   *  @return - the data from the api
  */
  public String  POSTUserGroup_admin_remove(String user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	retval = this.doCurl("POST","/user/group_admin_remove",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Log user activities into MariaDB
   *
   *  @param user_id
   *  @param action_type
   *  @param domain
   *  @param time
   *  @return - the data from the api
  */
  public String  POSTUserLog_activity(String user_id,String action_type,String domain,String time) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	params.put("action_type", action_type);
    	params.put("domain", domain);
    	params.put("time", time);
    	retval = this.doCurl("POST","/user/log_activity",params);
    } finally { 
    }
    return retval;
  }


  /**
   * List user activity times within given date range (between inclusive from and exclusive to)
   *
   *  @param user_id
   *  @param action_type
   *  @param from
   *  @param to
   *  @return - the data from the api
  */
  public String  GETUserLog_activityList_time(String user_id,String action_type,String from,String to) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	params.put("action_type", action_type);
    	params.put("from", from);
    	params.put("to", to);
    	retval = this.doCurl("GET","/user/log_activity/list_time",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Retrieve list of entities that the user manages
   *
   *  @param user_id
   *  @return - the data from the api
  */
  public String  GETUserManaged_entities(String user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	retval = this.doCurl("GET","/user/managed_entities",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Removes reseller privileges from a specified user
   *
   *  @param user_id
   *  @return - the data from the api
  */
  public String  POSTUserReseller_remove(String user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	retval = this.doCurl("POST","/user/reseller_remove",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Deletes a specific social network from a user
   *
   *  @param user_id
   *  @param social_network
   *  @return - the data from the api
  */
  public String  DELETEUserSocial_network(String user_id,String social_network,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("user_id", user_id);
    	params.put("social_network", social_network);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/user/social_network",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Shows what would be emitted by a view, given a document
   *
   *  @param database - the database being worked on e.g. entities
   *  @param designdoc - the design document containing the view e.g. _design/report
   *  @param view - the name of the view to be tested e.g. bydate
   *  @param doc - the JSON document to be analysed e.g. {}
   *  @return - the data from the api
  */
  public String  GETViewhelper(String database,String designdoc,String view,String doc) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("database", database);
    	params.put("designdoc", designdoc);
    	params.put("view", view);
    	params.put("doc", doc);
    	retval = this.doCurl("GET","/viewhelper",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Converts an Entity into webcard JSON and then doing a PUT /webcard/json
   *
   *  @param entity_id - The entity to create on webcard
   *  @return - the data from the api
  */
  public String  POSTWebcard(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	retval = this.doCurl("POST","/webcard",params);
    } finally { 
    }
    return retval;
  }



}


