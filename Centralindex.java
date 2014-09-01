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
  public String  postActivity_stream(String entity_id,String entity_name,String type,String country,String longitude,String latitude) throws Exception { 
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
  public String  getActivity_stream(String type,String country,String latitude_1,String longitude_1,String latitude_2,String longitude_2,String number_results,String unique_action) throws Exception { 
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
   * Get all advertisers that have been updated from a give date for a given reseller
   *
   *  @param from_date
   *  @param country
   *  @return - the data from the api
  */
  public String  getAdvertiserUpdated(String from_date,String country) throws Exception { 
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
  public String  getAdvertiserUpdatedBy_publisher(String publisher_id,String from_date,String country) throws Exception { 
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
   *  @param language - An ISO compatible language code, E.g. en e.g. en
   *  @return - the data from the api
  */
  public String  getAutocompleteLocation(String str,String country,String language) throws Exception { 
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
  public String  getAutocompleteLocationBy_resolution(String str,String country,String resolution) throws Exception { 
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
   *  @param destructive
   *  @param delete_mode - The type of object contribution deletion
   *  @param master_entity_id - The entity you want this data to go to
   *  @return - the data from the api
  */
  public String  putBusiness(String name,String building_number,String branch_name,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String country,String latitude,String longitude,String timezone,String telephone_number,String additional_telephone_number,String email,String website,String category_id,String category_type,String do_not_display,String referrer_url,String referrer_name,String destructive,String delete_mode,String master_entity_id,String _user_id) throws Exception { 
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
    	params.put("destructive", destructive);
    	params.put("delete_mode", delete_mode);
    	params.put("master_entity_id", master_entity_id);
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
   *  @return - the data from the api
  */
  public String  putBusinessJson(String json,String country,String timezone,String master_entity_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("json", json);
    	params.put("country", country);
    	params.put("timezone", timezone);
    	params.put("master_entity_id", master_entity_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("PUT","/business/json",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create entity via JSON
   *
   *  @param queue_id - Queue ID
   *  @return - the data from the api
  */
  public String  postBusinessJsonProcess(String queue_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("queue_id", queue_id);
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
  public String  deleteBusiness_tool(String tool_id) throws Exception { 
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
  public String  getBusiness_tool(String tool_id) throws Exception { 
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
  public String  postBusiness_tool(String tool_id,String country,String headline,String description,String link_url,String active) throws Exception { 
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
   *  @return - the data from the api
  */
  public String  getBusiness_toolBy_masheryid(String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country", country);
    	retval = this.doCurl("GET","/business_tool/by_masheryid",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Assigns a Business Tool image
   *
   *  @param tool_id
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postBusiness_toolImage(String tool_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("tool_id", tool_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/business_tool/image",params);
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
  public String  getCategory(String category_id) throws Exception { 
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
   * Returns all Central Index categories and associated data
   *
   *  @return - the data from the api
  */
  public String  getCategoryAll() throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
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
   * With a known category id, a mapping object can be deleted.
   *
   *  @param category_id
   *  @param category_type
   *  @param mapped_id
   *  @return - the data from the api
  */
  public String  deleteCategoryMappings(String category_id,String category_type,String mapped_id) throws Exception { 
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
   * Get the contract from the ID supplied
   *
   *  @param contract_id
   *  @return - the data from the api
  */
  public String  getContract(String contract_id) throws Exception { 
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
   * Get a contract from the payment provider id supplied
   *
   *  @param payment_provider
   *  @param payment_provider_id
   *  @return - the data from the api
  */
  public String  getContractBy_payment_provider_id(String payment_provider,String payment_provider_id) throws Exception { 
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
  public String  getContractBy_user_id(String user_id) throws Exception { 
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
  public String  postContractCancel(String contract_id) throws Exception { 
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
   *  @param billing_period
   *  @param source
   *  @param channel
   *  @param campaign
   *  @param referrer_domain
   *  @param referrer_name
   *  @param flatpack_id
   *  @return - the data from the api
  */
  public String  postContractCreate(String entity_id,String user_id,String payment_provider,String basket,String billing_period,String source,String channel,String campaign,String referrer_domain,String referrer_name,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("user_id", user_id);
    	params.put("payment_provider", payment_provider);
    	params.put("basket", basket);
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
  public String  postContractFreeactivate(String contract_id,String user_name,String user_surname,String user_email_address) throws Exception { 
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
  public String  postContractPaymentFailure(String contract_id,String failure_reason,String payment_date,String amount,String currency,String response) throws Exception { 
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
  public String  postContractPaymentSetup(String contract_id,String payment_provider_id,String payment_provider_profile,String user_name,String user_surname,String user_billing_address,String user_email_address) throws Exception { 
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
  public String  postContractPaymentSuccess(String contract_id,String payment_date,String amount,String currency,String response) throws Exception { 
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
  public String  postContractProvision(String contract_id) throws Exception { 
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
  public String  postContract_log(String contract_id,String date,String payment_provider,String response,String success,String amount,String currency) throws Exception { 
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
   * Get the contract log from the ID supplied
   *
   *  @param contract_log_id
   *  @return - the data from the api
  */
  public String  getContract_log(String contract_log_id) throws Exception { 
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
   * Get the contract logs from the ID supplied
   *
   *  @param contract_id
   *  @param page
   *  @param per_page
   *  @return - the data from the api
  */
  public String  getContract_logBy_contract_id(String contract_id,String page,String per_page) throws Exception { 
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
  public String  getContract_logBy_payment_provider(String payment_provider,String page,String per_page) throws Exception { 
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
  public String  postCountry(String country_id,String name,String synonyms,String continentName,String continent,String geonameId,String dbpediaURL,String freebaseURL,String population,String currencyCode,String languages,String areaInSqKm,String capital,String east,String west,String north,String south,String claimProductId,String claimMethods,String twilio_sms,String twilio_phone,String twilio_voice,String currency_symbol,String currency_symbol_html,String postcodeLookupActive,String addressFields,String addressMatching,String dateFormat,String iso_3166_alpha_3,String iso_3166_numeric) throws Exception { 
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
  public String  getCountry(String country_id) throws Exception { 
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
   * For a given country add/update a background image to show in the add/edit path
   *
   *  @param country_id
   *  @param filedata
   *  @param backgroundImageAttr
   *  @return - the data from the api
  */
  public String  postCountryBackgroundImage(String country_id,String filedata,String backgroundImageAttr) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country_id", country_id);
    	params.put("filedata", filedata);
    	params.put("backgroundImageAttr", backgroundImageAttr);
    	retval = this.doCurl("POST","/country/backgroundImage",params);
    } finally { 
    }
    return retval;
  }


  /**
   * For a given country add/update a social login background image to show in the add/edit path
   *
   *  @param country_id
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postCountrySocialLoginImage(String country_id,String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("country_id", country_id);
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/country/socialLoginImage",params);
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
  public String  postEmail(String to_email_address,String reply_email_address,String source_account,String subject,String body,String html_body) throws Exception { 
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
   *  @param trust
   *  @param our_data
   *  @return - the data from the api
  */
  public String  putEntity(String type,String scope,String country,String trust,String our_data,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("type", type);
    	params.put("scope", scope);
    	params.put("country", country);
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
   *  @return - the data from the api
  */
  public String  getEntity(String entity_id,String domain,String path,String data_filter) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("domain", domain);
    	params.put("path", path);
    	params.put("data_filter", data_filter);
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
  public String  postEntityAdd(String entity_id,String add_referrer_url,String add_referrer_name,String _user_id) throws Exception { 
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
  public String  deleteEntityAdvertiser(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityAdvertiserCancel(String entity_id,String publisher_id,String reseller_ref,String reseller_agent_id,String _user_id) throws Exception { 
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
   *  @param max_tags
   *  @param max_locations
   *  @param expiry_date
   *  @param is_national
   *  @param language
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  postEntityAdvertiserCreate(String entity_id,String tags,String locations,String max_tags,String max_locations,String expiry_date,String is_national,String language,String reseller_ref,String reseller_agent_id,String publisher_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("tags", tags);
    	params.put("locations", locations);
    	params.put("max_tags", max_tags);
    	params.put("max_locations", max_locations);
    	params.put("expiry_date", expiry_date);
    	params.put("is_national", is_national);
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
  public String  postEntityAdvertiserLocation(String entity_id,String gen_id,String locations_to_add,String locations_to_remove,String _user_id) throws Exception { 
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
   * Renews an advertiser from an entity
   *
   *  @param entity_id
   *  @param expiry_date
   *  @param publisher_id
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @return - the data from the api
  */
  public String  postEntityAdvertiserRenew(String entity_id,String expiry_date,String publisher_id,String reseller_ref,String reseller_agent_id,String _user_id) throws Exception { 
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
  public String  postEntityAdvertiserTag(String gen_id,String entity_id,String language,String tags_to_add,String tags_to_remove,String _user_id) throws Exception { 
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
   *  @param extra_tags
   *  @param extra_locations
   *  @param is_national
   *  @param language
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @param publisher_id
   *  @return - the data from the api
  */
  public String  postEntityAdvertiserUpsell(String entity_id,String tags,String locations,String extra_tags,String extra_locations,String is_national,String language,String reseller_ref,String reseller_agent_id,String publisher_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("tags", tags);
    	params.put("locations", locations);
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
   * Deleteing an affiliate adblock from a known entity
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityAffiliate_adblock(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityAffiliate_adblock(String entity_id,String adblock,String _user_id) throws Exception { 
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
  public String  postEntityAffiliate_link(String entity_id,String affiliate_name,String affiliate_link,String affiliate_message,String affiliate_logo,String affiliate_action,String _user_id) throws Exception { 
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
  public String  deleteEntityAffiliate_link(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityBackground(String entity_id,String number_of_employees,String turnover,String net_profit,String vat_number,String duns_number,String registered_company_number,String _user_id) throws Exception { 
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
   * Uploads a CSV file of known format and bulk inserts into DB
   *
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postEntityBulkCsv(String filedata,String _user_id) throws Exception { 
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
   * Uploads a JSON file of known format and bulk inserts into DB
   *
   *  @param data
   *  @return - the data from the api
  */
  public String  postEntityBulkJson(String data,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("data", data);
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
  public String  getEntityBulkJsonStatus(String upload_id) throws Exception { 
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
   * Get all entities within a specified group
   *
   *  @param group_id
   *  @return - the data from the api
  */
  public String  getEntityBy_groupid(String group_id) throws Exception { 
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
   * uncontributes a given entities supplier content and makes the entity inactive if the entity is un-usable
   *
   *  @param entity_id - The entity to pull
   *  @param supplier_masheryid - The suppliers masheryid to match
   *  @param supplier_id - The supplier id to match
   *  @param supplier_user_id - The user id to match
   *  @return - the data from the api
  */
  public String  deleteEntityBy_supplier(String entity_id,String supplier_masheryid,String supplier_id,String supplier_user_id,String _user_id) throws Exception { 
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
   * With a known entity id, an category object can be added.
   *
   *  @param entity_id
   *  @param category_id
   *  @param category_type
   *  @return - the data from the api
  */
  public String  postEntityCategory(String entity_id,String category_id,String category_type,String _user_id) throws Exception { 
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
  public String  deleteEntityCategory(String entity_id,String gen_id,String _user_id) throws Exception { 
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
   * Allow an entity to be claimed by a valid user
   *
   *  @param entity_id
   *  @param claimed_user_id
   *  @param claimed_reseller_id
   *  @param expiry_date
   *  @param claimed_date
   *  @param claim_method
   *  @param phone_number
   *  @param referrer_url
   *  @param referrer_name
   *  @return - the data from the api
  */
  public String  postEntityClaim(String entity_id,String claimed_user_id,String claimed_reseller_id,String expiry_date,String claimed_date,String claim_method,String phone_number,String referrer_url,String referrer_name,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("claimed_user_id", claimed_user_id);
    	params.put("claimed_reseller_id", claimed_reseller_id);
    	params.put("expiry_date", expiry_date);
    	params.put("claimed_date", claimed_date);
    	params.put("claim_method", claim_method);
    	params.put("phone_number", phone_number);
    	params.put("referrer_url", referrer_url);
    	params.put("referrer_name", referrer_name);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/claim",params);
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
  public String  deleteEntityDescription(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityDescription(String entity_id,String headline,String body,String gen_id,String _user_id) throws Exception { 
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
   * Allows a phone object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityDocument(String entity_id,String gen_id,String _user_id) throws Exception { 
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
   * With a known entity id, an document object can be added.
   *
   *  @param entity_id
   *  @param name
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postEntityDocument(String entity_id,String name,String filedata,String _user_id) throws Exception { 
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
   * With a known entity id, an email address object can be added.
   *
   *  @param entity_id
   *  @param email_address
   *  @param email_description
   *  @return - the data from the api
  */
  public String  postEntityEmail(String entity_id,String email_address,String email_description,String _user_id) throws Exception { 
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
   * Allows a email object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityEmail(String entity_id,String gen_id,String _user_id) throws Exception { 
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
   * Allows an employee object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityEmployee(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityEmployee(String entity_id,String title,String forename,String surname,String job_title,String description,String email,String phone_number,String _user_id) throws Exception { 
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
   * Allows a fax object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityFax(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityFax(String entity_id,String number,String description,String _user_id) throws Exception { 
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
  public String  postEntityFeatured_message(String entity_id,String featured_text,String featured_url,String _user_id) throws Exception { 
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
  public String  deleteEntityFeatured_message(String entity_id,String _user_id) throws Exception { 
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
  public String  postEntityGeopoint(String entity_id,String longitude,String latitude,String accuracy,String _user_id) throws Exception { 
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
   * Allows a group object to be removed from an entities group members
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityGroup(String entity_id,String gen_id,String _user_id) throws Exception { 
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
   * With a known entity id, a group  can be added to group members.
   *
   *  @param entity_id
   *  @param group_id
   *  @return - the data from the api
  */
  public String  postEntityGroup(String entity_id,String group_id,String _user_id) throws Exception { 
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
   * With a known entity id, a image object can be added.
   *
   *  @param entity_id
   *  @param filedata
   *  @param image_name
   *  @return - the data from the api
  */
  public String  postEntityImage(String entity_id,String filedata,String image_name,String _user_id) throws Exception { 
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
  public String  deleteEntityImage(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityImageBy_url(String entity_id,String image_url,String image_name,String _user_id) throws Exception { 
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
   * With a known entity id and a known invoice_address ID, we can delete a specific invoice_address object from an enitity.
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  deleteEntityInvoice_address(String entity_id,String _user_id) throws Exception { 
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
  public String  postEntityInvoice_address(String entity_id,String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String address_type,String _user_id) throws Exception { 
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
   * With a known entity id, a list description object can be added.
   *
   *  @param entity_id
   *  @param headline
   *  @param body
   *  @return - the data from the api
  */
  public String  postEntityList(String entity_id,String headline,String body,String _user_id) throws Exception { 
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
   * Allows a list description object to be reduced in confidence
   *
   *  @param gen_id
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  deleteEntityList(String gen_id,String entity_id,String _user_id) throws Exception { 
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
   * Find all entities in a group
   *
   *  @param group_id - A valid group_id
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @return - the data from the api
  */
  public String  getEntityList_by_group_id(String group_id,String per_page,String page) throws Exception { 
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
   * With a known entity id, a logo object can be added.
   *
   *  @param entity_id
   *  @param filedata
   *  @param logo_name
   *  @return - the data from the api
  */
  public String  postEntityLogo(String entity_id,String filedata,String logo_name,String _user_id) throws Exception { 
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
   * Allows a phone object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityLogo(String entity_id,String gen_id,String _user_id) throws Exception { 
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
   * With a known entity id, a logo can be retrieved from a url and added.
   *
   *  @param entity_id
   *  @param logo_url
   *  @param logo_name
   *  @return - the data from the api
  */
  public String  postEntityLogoBy_url(String entity_id,String logo_url,String logo_name,String _user_id) throws Exception { 
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
  public String  postEntityMerge(String from,String to,String override_trust,String uncontribute_masheryid,String uncontribute_userid,String uncontribute_supplierid,String delete_mode,String _user_id) throws Exception { 
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
   * With a known entity id, a name can be updated.
   *
   *  @param entity_id
   *  @param name
   *  @param formal_name
   *  @param branch_name
   *  @return - the data from the api
  */
  public String  postEntityName(String entity_id,String name,String formal_name,String branch_name,String _user_id) throws Exception { 
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
   * With a known entity id, a opening times object can be removed.
   *
   *  @param entity_id - The id of the entity to edit
   *  @return - the data from the api
  */
  public String  deleteEntityOpening_times(String entity_id,String _user_id) throws Exception { 
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
  public String  postEntityOpening_times(String entity_id,String monday,String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday,String closed,String closed_public_holidays,String _user_id) throws Exception { 
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
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/entity/opening_times",params);
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
  public String  postEntityPayment_type(String entity_id,String payment_type,String _user_id) throws Exception { 
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
   * Allows a payment_type object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityPayment_type(String entity_id,String gen_id,String _user_id) throws Exception { 
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
   * Allows a new phone object to be added to a specified entity. A new object id will be calculated and returned to you if successful.
   *
   *  @param entity_id
   *  @param number
   *  @param trackable
   *  @return - the data from the api
  */
  public String  postEntityPhone(String entity_id,String number,String trackable,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("number", number);
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
  public String  deleteEntityPhone(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityPostal_address(String entity_id,String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String address_type,String do_not_display,String _user_id) throws Exception { 
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
  public String  getEntityProvisionalBy_supplier_id(String supplier_id) throws Exception { 
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
   * removes  a given entities supplier/masheryid/user_id content and makes the entity inactive if the entity is un-usable
   *
   *  @param entity_id - The entity to pull
   *  @param purge_masheryid - The purge masheryid to match
   *  @param purge_supplier_id - The purge supplier id to match
   *  @param purge_user_id - The purge user id to match
   *  @param destructive
   *  @return - the data from the api
  */
  public String  postEntityPurge(String entity_id,String purge_masheryid,String purge_supplier_id,String purge_user_id,String _user_id,String destructive) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("purge_masheryid", purge_masheryid);
    	params.put("purge_supplier_id", purge_supplier_id);
    	params.put("purge_user_id", purge_user_id);
    	params.put("_user_id", _user_id);
    	params.put("destructive", destructive);
    	retval = this.doCurl("POST","/entity/purge",params);
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
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchByboundingbox(String latitude_1,String longitude_1,String latitude_2,String longitude_2,String per_page,String page,String country,String language,String domain,String path) throws Exception { 
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
    	params.put("domain", domain);
    	params.put("path", path);
    	retval = this.doCurl("GET","/entity/search/byboundingbox",params);
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
   *  @param latitude - The decimal latitude of the search context (optional)
   *  @param longitude - The decimal longitude of the search context (optional)
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchBylocation(String where,String per_page,String page,String country,String language,String latitude,String longitude,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("where", where);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	retval = this.doCurl("GET","/entity/search/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for entities matching the supplied group_id, ordered by nearness
   *
   *  @param group_id - the group_id to search for
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
   *  @return - the data from the api
  */
  public String  getEntitySearchGroupBynearest(String group_id,String country,String per_page,String page,String language,String latitude,String longitude,String where,String domain,String path,String unitOfDistance) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
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
    	retval = this.doCurl("GET","/entity/search/group/bynearest",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for entities matching the supplied 'who', ordered by nearness
   *
   *  @param keyword - What to get results for. E.g. cafe e.g. cafe
   *  @param country - The country to fetch results for e.g. gb
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the centre point of the search
   *  @param longitude - The decimal longitude of the centre point of the search
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchKeywordBynearest(String keyword,String country,String per_page,String page,String language,String latitude,String longitude,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("keyword", keyword);
    	params.put("country", country);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	retval = this.doCurl("GET","/entity/search/keyword/bynearest",params);
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
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchWhat(String what,String per_page,String page,String country,String language,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
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
   *  @param per_page
   *  @param page
   *  @param country - A valid ISO 3166 country code e.g. ie
   *  @param language
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchWhatByboundingbox(String what,String latitude_1,String longitude_1,String latitude_2,String longitude_2,String per_page,String page,String country,String language,String domain,String path) throws Exception { 
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
    	params.put("domain", domain);
    	params.put("path", path);
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
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the search context (optional)
   *  @param longitude - The decimal longitude of the search context (optional)
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchWhatBylocation(String what,String where,String per_page,String page,String country,String language,String latitude,String longitude,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("where", where);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	retval = this.doCurl("GET","/entity/search/what/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for matching entities, ordered by nearness
   *
   *  @param what - What to get results for. E.g. Plumber e.g. plumber
   *  @param country - The country to fetch results for e.g. gb
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the centre point of the search
   *  @param longitude - The decimal longitude of the centre point of the search
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchWhatBynearest(String what,String country,String per_page,String page,String language,String latitude,String longitude,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("what", what);
    	params.put("country", country);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
    	retval = this.doCurl("GET","/entity/search/what/bynearest",params);
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
   *  @param language - An ISO compatible language code, E.g. en
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchWho(String who,String per_page,String page,String country,String language,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
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
   *  @param per_page
   *  @param page
   *  @param country
   *  @param language - An ISO compatible language code, E.g. en
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchWhoByboundingbox(String who,String latitude_1,String longitude_1,String latitude_2,String longitude_2,String per_page,String page,String country,String language,String domain,String path) throws Exception { 
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
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
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
   *  @param latitude - The decimal latitude of the search context (optional)
   *  @param longitude - The decimal longitude of the search context (optional)
   *  @param language - An ISO compatible language code, E.g. en
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchWhoBylocation(String who,String where,String per_page,String page,String country,String latitude,String longitude,String language,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("where", where);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("country", country);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("language", language);
    	params.put("domain", domain);
    	params.put("path", path);
    	retval = this.doCurl("GET","/entity/search/who/bylocation",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Search for entities matching the supplied 'who', ordered by nearness
   *
   *  @param who - What to get results for. E.g. Plumber e.g. plumber
   *  @param country - The country to fetch results for e.g. gb
   *  @param per_page - Number of results returned per page
   *  @param page - Which page number to retrieve
   *  @param language - An ISO compatible language code, E.g. en
   *  @param latitude - The decimal latitude of the centre point of the search
   *  @param longitude - The decimal longitude of the centre point of the search
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  getEntitySearchWhoBynearest(String who,String country,String per_page,String page,String language,String latitude,String longitude,String domain,String path) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("who", who);
    	params.put("country", country);
    	params.put("per_page", per_page);
    	params.put("page", page);
    	params.put("language", language);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("domain", domain);
    	params.put("path", path);
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
  public String  postEntitySend_email(String entity_id,String gen_id,String from_email,String subject,String content,String _user_id) throws Exception { 
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
   * Allows a social media object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntitySocialmedia(String entity_id,String gen_id,String _user_id) throws Exception { 
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
   * With a known entity id, a social media object can be added.
   *
   *  @param entity_id
   *  @param type
   *  @param website_url
   *  @return - the data from the api
  */
  public String  postEntitySocialmedia(String entity_id,String type,String website_url,String _user_id) throws Exception { 
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
   * Allows a special offer object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntitySpecial_offer(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntitySpecial_offer(String entity_id,String title,String description,String terms,String start_date,String expiry_date,String url,String _user_id) throws Exception { 
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
  public String  postEntityStatus(String entity_id,String status,String inactive_reason,String inactive_description,String _user_id) throws Exception { 
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
   * Allows a tag object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityTag(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityTag(String entity_id,String tag,String language,String _user_id) throws Exception { 
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
  public String  deleteEntityTestimonial(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityTestimonial(String entity_id,String title,String text,String date,String testifier_name,String _user_id) throws Exception { 
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
  public String  getEntityUncontribute(String entity_id,String object_name,String supplier_id,String user_id) throws Exception { 
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
   *  @param supplier_masheryid
   *  @param supplier_id
   *  @return - the data from the api
  */
  public String  postEntityUnmerge(String entity_id,String supplier_masheryid,String supplier_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("supplier_masheryid", supplier_masheryid);
    	params.put("supplier_id", supplier_id);
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
  public String  postEntityUser_trust(String entity_id,String user_id,String trust,String _user_id) throws Exception { 
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
   * Allows a video object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityVideo(String entity_id,String gen_id,String _user_id) throws Exception { 
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
  public String  postEntityVideoYoutube(String entity_id,String embed_code,String _user_id) throws Exception { 
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
   * With a known entity id, a website object can be added.
   *
   *  @param entity_id
   *  @param website_url
   *  @param display_url
   *  @param website_description
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  postEntityWebsite(String entity_id,String website_url,String display_url,String website_description,String gen_id,String _user_id) throws Exception { 
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
   * Allows a website object to be reduced in confidence
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityWebsite(String entity_id,String gen_id,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("DELETE","/entity/website",params);
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
  public String  postEntityYext_list(String entity_id,String yext_list_id,String description,String name,String type,String _user_id) throws Exception { 
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
  public String  deleteEntityYext_list(String entity_id,String gen_id,String _user_id) throws Exception { 
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
   *  @param entity_id - The id of the entity to create the entityserve event for
   *  @param country - the ISO code of the country
   *  @param event_type - The event type being recorded
   *  @param domain
   *  @param path
   *  @return - the data from the api
  */
  public String  putEntityserve(String entity_id,String country,String event_type,String domain,String path) throws Exception { 
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
   *  @param domainName - the domain name to serve this flatpack site on (no leading http:// or anything please)
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
   *  @param linkToRoot - the root domain name to serve this flatpack site on (no leading http:// or anything please)
   *  @return - the data from the api
  */
  public String  postFlatpack(String flatpack_id,String domainName,String stub,String flatpackName,String less,String language,String country,String mapsType,String mapKey,String searchFormShowOn,String searchFormShowKeywordsBox,String searchFormShowLocationBox,String searchFormKeywordsAutoComplete,String searchFormLocationsAutoComplete,String searchFormDefaultLocation,String searchFormPlaceholderKeywords,String searchFormPlaceholderLocation,String searchFormKeywordsLabel,String searchFormLocationLabel,String cannedLinksHeader,String homepageTitle,String homepageDescription,String homepageIntroTitle,String homepageIntroText,String head,String adblock,String bodyTop,String bodyBottom,String header_menu,String header_menu_bottom,String footer_menu,String bdpTitle,String bdpDescription,String bdpAds,String serpTitle,String serpDescription,String serpNumberResults,String serpNumberAdverts,String serpAds,String serpTitleNoWhat,String serpDescriptionNoWhat,String cookiePolicyUrl,String cookiePolicyNotice,String addBusinessButtonText,String twitterUrl,String facebookUrl,String copyright,String phoneReveal,String loginLinkText,String contextLocationId,String addBusinessButtonPosition,String denyIndexing,String contextRadius,String activityStream,String activityStreamSize,String products,String linkToRoot) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("flatpack_id", flatpack_id);
    	params.put("domainName", domainName);
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
  public String  getFlatpack(String flatpack_id) throws Exception { 
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
  public String  deleteFlatpack(String flatpack_id) throws Exception { 
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
  public String  postFlatpackAdminCSS(String flatpack_id,String filedata) throws Exception { 
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
  public String  postFlatpackAdminHDLogo(String flatpack_id,String filedata) throws Exception { 
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
  public String  postFlatpackAdminLargeLogo(String flatpack_id,String filedata) throws Exception { 
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
  public String  postFlatpackAdminSmallLogo(String flatpack_id,String filedata) throws Exception { 
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
   * Get flatpacks by country and location
   *
   *  @param country
   *  @param latitude
   *  @param longitude
   *  @return - the data from the api
  */
  public String  getFlatpackBy_country(String country,String latitude,String longitude) throws Exception { 
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
  public String  getFlatpackBy_countryKml(String country) throws Exception { 
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
   *  @return - the data from the api
  */
  public String  getFlatpackBy_domain_name(String domainName) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("domainName", domainName);
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
  public String  getFlatpackBy_masheryid() throws Exception { 
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
  public String  getFlatpackClone(String flatpack_id,String domainName) throws Exception { 
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
   * Upload an icon to serve out with this flatpack
   *
   *  @param flatpack_id - the id of the flatpack to update
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postFlatpackIcon(String flatpack_id,String filedata) throws Exception { 
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
   * returns the LESS theme from a flatpack
   *
   *  @param flatpack_id - the unique id to search for
   *  @return - the data from the api
  */
  public String  getFlatpackLess(String flatpack_id) throws Exception { 
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
   * Add a canned link to an existing flatpack site.
   *
   *  @param flatpack_id - the id of the flatpack to delete
   *  @param keywords - the keywords to use in the canned search
   *  @param location - the location to use in the canned search
   *  @param linkText - the link text to be used to in the canned search link
   *  @return - the data from the api
  */
  public String  postFlatpackLink(String flatpack_id,String keywords,String location,String linkText) throws Exception { 
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
   * Remove a canned link to an existing flatpack site.
   *
   *  @param flatpack_id - the id of the flatpack to delete
   *  @param gen_id - the id of the canned link to remove
   *  @return - the data from the api
  */
  public String  deleteFlatpackLink(String flatpack_id,String gen_id) throws Exception { 
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
   * Remove all canned links from an existing flatpack.
   *
   *  @param flatpack_id - the id of the flatpack to remove links from
   *  @return - the data from the api
  */
  public String  deleteFlatpackLinkAll(String flatpack_id) throws Exception { 
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
  public String  postFlatpackLogo(String flatpack_id,String filedata) throws Exception { 
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
  public String  postFlatpackLogoHd(String flatpack_id,String filedata) throws Exception { 
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
   * Upload a TXT file to act as the sitemap for this flatpack
   *
   *  @param flatpack_id - the id of the flatpack to update
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postFlatpackSitemap(String flatpack_id,String filedata) throws Exception { 
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
  public String  postGroup(String group_id,String name,String description,String url,String stamp_user_id,String stamp_sql) throws Exception { 
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
   * Delete a group with a specified group_id
   *
   *  @param group_id
   *  @return - the data from the api
  */
  public String  deleteGroup(String group_id) throws Exception { 
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
   * Returns group that matches a given group id
   *
   *  @param group_id
   *  @return - the data from the api
  */
  public String  getGroup(String group_id) throws Exception { 
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
  public String  getGroupAll() throws Exception { 
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
  public String  postGroupBulk_delete(String group_id,String filedata,String _user_id) throws Exception { 
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
  public String  postGroupBulk_ingest(String group_id,String filedata,String category_type,String _user_id) throws Exception { 
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
  public String  postGroupBulk_update(String group_id,String _user_id,String data) throws Exception { 
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
  public String  getHeartbeatBy_date(String from_date,String to_date,String country_id) throws Exception { 
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
  public String  getHeartbeatTodayClaims(String country,String claim_type) throws Exception { 
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
  public String  postIngest_file(String job_id,String filedata) throws Exception { 
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
   * Get an ingest job from the collection
   *
   *  @param job_id
   *  @return - the data from the api
  */
  public String  getIngest_job(String job_id) throws Exception { 
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
   * Add a ingest job to the collection
   *
   *  @param description
   *  @param category_type
   *  @return - the data from the api
  */
  public String  postIngest_job(String description,String category_type,String _user_id) throws Exception { 
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
   * Get an ingest log from the collection
   *
   *  @param job_id
   *  @param success
   *  @param errors
   *  @param limit
   *  @param skip
   *  @return - the data from the api
  */
  public String  getIngest_logBy_job_id(String job_id,String success,String errors,String limit,String skip) throws Exception { 
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
  public String  getIngest_queue(String flush) throws Exception { 
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
  public String  postLocation(String location_id,String type,String country,String language,String name,String formal_name,String resolution,String population,String description,String timezone,String latitude,String longitude,String parent_town,String parent_county,String parent_province,String parent_region,String parent_neighbourhood,String parent_district,String postalcode,String searchable_id,String searchable_ids) throws Exception { 
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
   * Given a location_id or a lat/lon, find other locations within the radius
   *
   *  @param location_id
   *  @param latitude
   *  @param longitude
   *  @param radius - Radius in km
   *  @param resolution
   *  @param country
   *  @return - the data from the api
  */
  public String  getLocationContext(String location_id,String latitude,String longitude,String radius,String resolution,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("radius", radius);
    	params.put("resolution", resolution);
    	params.put("country", country);
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
  public String  getLocationMultiple(String location_ids) throws Exception { 
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
   * Find a location from cache or cloudant depending if it is in the cache (locz)
   *
   *  @param string
   *  @param language
   *  @param country
   *  @param latitude
   *  @param longitude
   *  @return - the data from the api
  */
  public String  getLookupLocation(String string,String language,String country,String latitude,String longitude) throws Exception { 
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
   * Find all matches by phone number, returning up to 10 matches
   *
   *  @param phone
   *  @param country
   *  @param exclude - Entity ID to exclude from the results
   *  @return - the data from the api
  */
  public String  getMatchByphone(String phone,String country,String exclude) throws Exception { 
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
  public String  getMatchOftheday(String primary_entity_id,String secondary_entity_id,String return_entities) throws Exception { 
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
  public String  putMatching_log(String primary_entity_id,String secondary_entity_id,String primary_name,String secondary_name,String address_score,String address_match,String name_score,String name_match,String distance,String phone_match,String category_match,String email_match,String website_match,String match) throws Exception { 
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
  public String  postMessage(String message_id,String ses_id,String from_user_id,String from_email,String to_entity_id,String to_email,String subject,String body,String bounced) throws Exception { 
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
   * Fetching a message
   *
   *  @param message_id - The message id to pull the message for
   *  @return - the data from the api
  */
  public String  getMessage(String message_id) throws Exception { 
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
   * Fetching messages by ses_id
   *
   *  @param ses_id - The amazon id to pull the message for
   *  @return - the data from the api
  */
  public String  getMessageBy_ses_id(String ses_id) throws Exception { 
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
  public String  postMultipack(String multipack_id,String group_id,String domainName,String multipackName,String less,String country,String menuTop,String menuBottom,String language,String menuFooter,String searchNumberResults,String searchTitle,String searchDescription,String searchTitleNoWhere,String searchDescriptionNoWhere,String searchIntroHeader,String searchIntroText,String searchShowAll,String searchUnitOfDistance,String cookiePolicyShow,String cookiePolicyUrl,String twitterUrl,String facebookUrl) throws Exception { 
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
  public String  getMultipack(String multipack_id) throws Exception { 
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
   * Get a multipack using a domain name
   *
   *  @param domainName - the domain name to search for
   *  @return - the data from the api
  */
  public String  getMultipackBy_domain_name(String domainName) throws Exception { 
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
  public String  getMultipackClone(String multipack_id,String domainName,String group_id) throws Exception { 
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
  public String  getMultipackLess(String multipack_id) throws Exception { 
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
  public String  postMultipackLogo(String multipack_id,String filedata) throws Exception { 
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
  public String  postMultipackMap_pin(String multipack_id,String filedata,String mapPinOffsetX,String mapPinOffsetY) throws Exception { 
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
   * Allows a private object to be removed
   *
   *  @param private_object_id - The id of the private object to remove
   *  @return - the data from the api
  */
  public String  deletePrivate_object(String private_object_id) throws Exception { 
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
  public String  putPrivate_object(String entity_id,String data,String _user_id) throws Exception { 
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
  public String  getPrivate_objectAll(String entity_id) throws Exception { 
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
   *  @param name - The name of the product
   *  @param strapline - The description of the product
   *  @param price - The price of the product
   *  @param tax_rate - The tax markup for this product
   *  @param currency - The currency in which the price is in
   *  @param active - is this an active product
   *  @param billing_period
   *  @param title - Title of the product
   *  @param intro_paragraph - introduction paragraph
   *  @param bullets - pipe separated product features
   *  @param outro_paragraph - closing paragraph
   *  @param thanks_paragraph - thank you paragraph
   *  @return - the data from the api
  */
  public String  postProduct(String product_id,String name,String strapline,String price,String tax_rate,String currency,String active,String billing_period,String title,String intro_paragraph,String bullets,String outro_paragraph,String thanks_paragraph) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	params.put("name", name);
    	params.put("strapline", strapline);
    	params.put("price", price);
    	params.put("tax_rate", tax_rate);
    	params.put("currency", currency);
    	params.put("active", active);
    	params.put("billing_period", billing_period);
    	params.put("title", title);
    	params.put("intro_paragraph", intro_paragraph);
    	params.put("bullets", bullets);
    	params.put("outro_paragraph", outro_paragraph);
    	params.put("thanks_paragraph", thanks_paragraph);
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
  public String  getProduct(String product_id) throws Exception { 
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
   * Removes a provisioning object from product
   *
   *  @param product_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteProductProvisioning(String product_id,String gen_id) throws Exception { 
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
  public String  postProductProvisioningAdvert(String product_id,String publisher_id,String max_tags,String max_locations) throws Exception { 
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
   *  @return - the data from the api
  */
  public String  postProductProvisioningClaim(String product_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("product_id", product_id);
    	retval = this.doCurl("POST","/product/provisioning/claim",params);
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
  public String  postProductProvisioningSchedulesms(String product_id,String package) throws Exception { 
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
  public String  postProductProvisioningSyndication(String product_id,String publisher_id) throws Exception { 
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
  public String  getPtbAll(String entity_id,String destructive) throws Exception { 
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
  public String  getPtbLog(String year,String month,String entity_id) throws Exception { 
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
  public String  getPtbModule(String entity_id,String module,String destructive) throws Exception { 
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
  public String  getPtbRunrate(String country,String year,String month,String day) throws Exception { 
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
  public String  getPtbValueadded(String country,String year,String month,String day) throws Exception { 
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
   * Update/Add a publisher
   *
   *  @param publisher_id
   *  @param country
   *  @param name
   *  @param description
   *  @param active
   *  @param url_patterns
   *  @return - the data from the api
  */
  public String  postPublisher(String publisher_id,String country,String name,String description,String active,String url_patterns) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("publisher_id", publisher_id);
    	params.put("country", country);
    	params.put("name", name);
    	params.put("description", description);
    	params.put("active", active);
    	params.put("url_patterns", url_patterns);
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
   * Find a queue item by its cloudant id
   *
   *  @param queue_id
   *  @return - the data from the api
  */
  public String  getQueueBy_id(String queue_id) throws Exception { 
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
   * Update/Add a reseller
   *
   *  @param reseller_id
   *  @param country
   *  @param name
   *  @param description
   *  @param active
   *  @param products
   *  @param master_user_id
   *  @return - the data from the api
  */
  public String  postReseller(String reseller_id,String country,String name,String description,String active,String products,String master_user_id) throws Exception { 
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
    	retval = this.doCurl("POST","/reseller",params);
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
  public String  getReseller(String reseller_id) throws Exception { 
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
   * Return a sales log by id
   *
   *  @param sales_log_id - The sales log id to pull
   *  @return - the data from the api
  */
  public String  getSales_log(String sales_log_id) throws Exception { 
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
  public String  getSales_logBy_countryBy_date(String from_date,String country,String action_type) throws Exception { 
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
   * Return a sales log by id
   *
   *  @param from_date
   *  @param to_date
   *  @return - the data from the api
  */
  public String  getSales_logBy_date(String from_date,String to_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("from_date", from_date);
    	params.put("to_date", to_date);
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
  public String  postSales_logEntity(String entity_id,String country,String action_type,String publisher_id,String mashery_id,String reseller_ref,String reseller_agent_id,String max_tags,String max_locations,String extra_tags,String extra_locations,String expiry_date) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("country", country);
    	params.put("action_type", action_type);
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
  public String  postSales_logSyndication(String action_type,String syndication_type,String publisher_id,String expiry_date,String entity_id,String group_id,String seed_masheryid,String supplier_masheryid,String country,String reseller_masheryid,String _user_id) throws Exception { 
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
   * Make a url shorter
   *
   *  @param url - the url to shorten
   *  @return - the data from the api
  */
  public String  putShortenurl(String url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("url", url);
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
  public String  getShortenurl(String id) throws Exception { 
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
   *  @return - the data from the api
  */
  public String  postSignal(String entity_id,String country,String gen_id,String signal_type,String data_type,String inactive_reason,String inactive_description) throws Exception { 
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
    	retval = this.doCurl("POST","/signal",params);
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
  public String  getStatsEntityBy_date(String entity_id,String year,String month) throws Exception { 
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
  public String  getStatsEntityBy_year(String entity_id,String year) throws Exception { 
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
   * get a Syndication
   *
   *  @param syndication_id
   *  @return - the data from the api
  */
  public String  getSyndication(String syndication_id) throws Exception { 
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
  public String  getSyndicationBy_entity_id(String entity_id) throws Exception { 
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
   * Cancel a syndication
   *
   *  @param syndication_id
   *  @return - the data from the api
  */
  public String  postSyndicationCancel(String syndication_id) throws Exception { 
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
  public String  postSyndicationCreate(String syndication_type,String publisher_id,String expiry_date,String entity_id,String group_id,String seed_masheryid,String supplier_masheryid,String country,String data_filter,String _user_id) throws Exception { 
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
  public String  postSyndicationRenew(String syndication_type,String publisher_id,String entity_id,String group_id,String seed_masheryid,String supplier_masheryid,String country,String _user_id,String expiry_date) throws Exception { 
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
   *  @return - the data from the api
  */
  public String  postSyndication_log(String entity_id,String publisher_id,String action,String success,String message,String syndicated_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	params.put("action", action);
    	params.put("success", success);
    	params.put("message", message);
    	params.put("syndicated_id", syndicated_id);
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
  public String  getSyndication_logBy_entity_id(String entity_id,String page,String per_page) throws Exception { 
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
  public String  getSyndication_logLast_syndicated_id(String entity_id,String publisher_id) throws Exception { 
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
  public String  putSyndication_submission(String syndication_type,String entity_id,String publisher_id,String submission_id) throws Exception { 
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
  public String  getSyndication_submission(String syndication_submission_id) throws Exception { 
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
  public String  postSyndication_submissionDeactivate(String syndication_submission_id) throws Exception { 
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
  public String  postSyndication_submissionProcessed(String syndication_submission_id) throws Exception { 
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
   *  @param portal_name - The name of the website that data is to be added on e.g. YourLocal
   *  @param country - The country of the entity to be added e.g. gb
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @return - the data from the api
  */
  public String  getTokenAdd(String language,String portal_name,String country,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("language", language);
    	params.put("portal_name", portal_name);
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
   *  @param language - The language to use to render the claim path e.g. en
   *  @param portal_name - The name of the website that entity is being claimed on e.g. YourLocal
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @return - the data from the api
  */
  public String  getTokenClaim(String entity_id,String language,String portal_name,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("language", language);
    	params.put("portal_name", portal_name);
    	params.put("flatpack_id", flatpack_id);
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
  public String  getTokenContact_us(String portal_name,String flatpack_id,String language,String referring_url) throws Exception { 
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
  public String  getTokenDecode(String token) throws Exception { 
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
  public String  getTokenEdit(String entity_id,String language,String flatpack_id,String edit_page) throws Exception { 
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
   * Fetch token for login path
   *
   *  @param portal_name - The name of the application that has initiated the login process, example: 'Your Local'
   *  @param language - The language for the app
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @return - the data from the api
  */
  public String  getTokenLogin(String portal_name,String language,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("portal_name", portal_name);
    	params.put("language", language);
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/token/login",params);
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
  public String  getTokenMessage(String entity_id,String portal_name,String language,String flatpack_id) throws Exception { 
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
  public String  getTokenProduct(String entity_id,String product_id,String language,String portal_name,String flatpack_id,String source,String channel,String campaign) throws Exception { 
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
  public String  getTokenProduct_selector(String entity_id,String portal_name,String flatpack_id,String language) throws Exception { 
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
  public String  getTokenReport(String entity_id,String portal_name,String language,String flatpack_id) throws Exception { 
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
   * Get a tokenised url for the testimonial path
   *
   *  @param portal_name - The portal name
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @param language - en, es, etc...
   *  @param entity_id
   *  @param shorten_url
   *  @return - the data from the api
  */
  public String  getTokenTestimonial(String portal_name,String flatpack_id,String language,String entity_id,String shorten_url) throws Exception { 
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
  public String  getToolsAddressdiff(String first_entity_id,String second_entity_id) throws Exception { 
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
  public String  getToolsCrash() throws Exception { 
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
  public String  postToolsCurl(String method,String path,String filedata,String email) throws Exception { 
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
   *  @param geocoder
   *  @return - the data from the api
  */
  public String  getToolsGeocode(String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String country,String geocoder) throws Exception { 
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
    	params.put("geocoder", geocoder);
    	retval = this.doCurl("GET","/tools/geocode",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Given a spreadsheet id add a row
   *
   *  @param spreadsheet_key - The key of the spreadsheet to edit
   *  @param data - A comma separated list to add as cells
   *  @return - the data from the api
  */
  public String  postToolsGooglesheetAdd_row(String spreadsheet_key,String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("spreadsheet_key", spreadsheet_key);
    	params.put("data", data);
    	retval = this.doCurl("POST","/tools/googlesheet/add_row",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Given some image data we can resize and upload the images
   *
   *  @param filedata - The image data to upload and resize
   *  @param type - The type of image to upload and resize
   *  @return - the data from the api
  */
  public String  postToolsImage(String filedata,String type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("filedata", filedata);
    	params.put("type", type);
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
   * compile the supplied less with the standard Bootstrap less into a CSS file
   *
   *  @param less - The LESS code to compile
   *  @return - the data from the api
  */
  public String  getToolsLess(String less) throws Exception { 
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
  public String  getToolsParseAddress(String address,String postcode,String country,String normalise) throws Exception { 
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
   *  @return - the data from the api
  */
  public String  getToolsPhonecallVerify(String to,String from,String pin,String twilio_voice) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("to", to);
    	params.put("from", from);
    	params.put("pin", pin);
    	params.put("twilio_voice", twilio_voice);
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
   * replace some text parameters with some entity details
   *
   *  @param entity_id - The entity to pull for replacements
   *  @param string - The string full of parameters
   *  @return - the data from the api
  */
  public String  getToolsReplace(String entity_id,String string) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("string", string);
    	retval = this.doCurl("GET","/tools/replace",params);
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
  public String  getToolsSendsms(String from,String to,String message) throws Exception { 
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
   *  @return - the data from the api
  */
  public String  getToolsSpider(String url,String pages,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("url", url);
    	params.put("pages", pages);
    	params.put("country", country);
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
   * Fetch the result of submitted data we sent to InfoGroup
   *
   *  @param syndication_submission_id - The syndication_submission_id to fetch info for
   *  @return - the data from the api
  */
  public String  getToolsSubmissionInfogroup(String syndication_submission_id) throws Exception { 
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
   * Fetch the entity and convert it to Bing Ads CSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  getToolsSyndicateBingads(String entity_id) throws Exception { 
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
  public String  getToolsSyndicateBingplaces(String entity_id) throws Exception { 
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
  public String  getToolsSyndicateDnb(String entity_id) throws Exception { 
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
  public String  getToolsSyndicateEnablemedia(String entity_id,String reseller_masheryid,String destructive,String data_filter) throws Exception { 
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
  public String  getToolsSyndicateFactual(String entity_id,String destructive) throws Exception { 
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
  public String  getToolsSyndicateFactualcsv(String entity_id) throws Exception { 
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
  public String  getToolsSyndicateFoursquare(String entity_id,String destructive) throws Exception { 
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
  public String  getToolsSyndicateGoogle(String entity_id) throws Exception { 
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
   * Fetch the entity and convert add it to InfoGroup
   *
   *  @param entity_id - The entity_id to fetch
   *  @param destructive - Add the entity or simulate adding the entity
   *  @return - the data from the api
  */
  public String  getToolsSyndicateInfogroup(String entity_id,String destructive) throws Exception { 
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
   * Fetch the entity and convert it to Google KML format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  getToolsSyndicateKml(String entity_id) throws Exception { 
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
  public String  getToolsSyndicateLocaldatabase(String entity_id,String destructive) throws Exception { 
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
   * Fetch the entity and convert it to Nokia CSV format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  getToolsSyndicateNokia(String entity_id) throws Exception { 
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
  public String  getToolsSyndicateOsm(String entity_id,String destructive) throws Exception { 
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
   * Fetch the entity and convert it to TomTom XML format
   *
   *  @param entity_id - The entity_id to fetch
   *  @return - the data from the api
  */
  public String  getToolsSyndicateTomtom(String entity_id) throws Exception { 
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
  public String  getToolsSyndicateYalwa(String entity_id) throws Exception { 
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
  public String  getToolsSyndicateYasabe(String entity_id,String destructive) throws Exception { 
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
  public String  getToolsTestmatch(String name,String building_number,String branch_name,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String country,String latitude,String longitude,String timezone,String telephone_number,String additional_telephone_number,String email,String website,String category_id,String category_type,String do_not_display,String referrer_url,String referrer_name) throws Exception { 
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
  public String  getToolsTransactional_email(String email_id,String destination_email,String email_supplier) throws Exception { 
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
  public String  postToolsUpload(String filedata) throws Exception { 
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
  public String  getToolsUrl_details(String url,String max_redirects) throws Exception { 
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
  public String  getToolsValidate_email(String email_address) throws Exception { 
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
  public String  getToolsValidate_phone(String phone_number,String country) throws Exception { 
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
  public String  deleteTraction(String traction_id) throws Exception { 
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
  public String  postTraction(String traction_id,String trigger_type,String action_type,String country,String email_addresses,String title,String body,String api_method,String api_url,String api_params,String active,String reseller_masheryid,String publisher_masheryid,String description) throws Exception { 
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
   * Fetching a traction
   *
   *  @param traction_id
   *  @return - the data from the api
  */
  public String  getTraction(String traction_id) throws Exception { 
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
   * Fetching active tractions
   *
   *  @return - the data from the api
  */
  public String  getTractionActive() throws Exception { 
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
   * Update user based on email address or social_network/social_network_id
   *
   *  @param email
   *  @param user_id
   *  @param first_name
   *  @param last_name
   *  @param active
   *  @param trust
   *  @param creation_date
   *  @param user_type
   *  @param social_network
   *  @param social_network_id
   *  @param reseller_admin_masheryid
   *  @param group_id
   *  @param admin_upgrader
   *  @return - the data from the api
  */
  public String  postUser(String email,String user_id,String first_name,String last_name,String active,String trust,String creation_date,String user_type,String social_network,String social_network_id,String reseller_admin_masheryid,String group_id,String admin_upgrader,String _user_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("email", email);
    	params.put("user_id", user_id);
    	params.put("first_name", first_name);
    	params.put("last_name", last_name);
    	params.put("active", active);
    	params.put("trust", trust);
    	params.put("creation_date", creation_date);
    	params.put("user_type", user_type);
    	params.put("social_network", social_network);
    	params.put("social_network_id", social_network_id);
    	params.put("reseller_admin_masheryid", reseller_admin_masheryid);
    	params.put("group_id", group_id);
    	params.put("admin_upgrader", admin_upgrader);
    	params.put("_user_id", _user_id);
    	retval = this.doCurl("POST","/user",params);
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
  public String  getUserAllowed_to_edit(String entity_id,String user_id) throws Exception { 
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
   * Returns all the users that match the supplied group_id
   *
   *  @param group_id
   *  @return - the data from the api
  */
  public String  getUserBy_groupid(String group_id) throws Exception { 
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
  public String  getUserBy_reseller_admin_masheryid(String reseller_admin_masheryid) throws Exception { 
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
   * Downgrade an existing user
   *
   *  @param user_id
   *  @param user_type
   *  @return - the data from the api
  */
  public String  postUserDowngrade(String user_id,String user_type) throws Exception { 
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
  public String  postUserGroup_admin_remove(String user_id) throws Exception { 
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
   * Removes reseller privileges from a specified user
   *
   *  @param user_id
   *  @return - the data from the api
  */
  public String  postUserReseller_remove(String user_id) throws Exception { 
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
  public String  deleteUserSocial_network(String user_id,String social_network,String _user_id) throws Exception { 
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
  public String  getViewhelper(String database,String designdoc,String view,String doc) throws Exception { 
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



}


