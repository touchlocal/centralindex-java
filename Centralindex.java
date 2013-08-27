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
   *  @param from_date
   *  @param country
   *  @return - the data from the api
  */
  public String  getAdvertiserUpdatedBy_publisher(String from_date,String country) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
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
   * The search matches a locz given string and language.
   *
   *  @param str - A string to search against, E.g. Dub e.g. dub
   *  @param country - Which country to return results for. An ISO compatible country code, E.g. ie e.g. ie
   *  @param language - An ISO compatible language code, E.g. en e.g. en
   *  @return - the data from the api
  */
  public String  getAutocompleteLocz(String str,String country,String language) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("country", country);
    	params.put("language", language);
    	retval = this.doCurl("GET","/autocomplete/locz",params);
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
   *  @param all_data
   *  @return - the data from the api
  */
  public String  getAutocompleteLoczBy_resolution(String str,String country,String resolution,String all_data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("str", str);
    	params.put("country", country);
    	params.put("resolution", resolution);
    	params.put("all_data", all_data);
    	retval = this.doCurl("GET","/autocomplete/locz/by_resolution",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Create a new business entity with all it's objects
   *
   *  @param name
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
  public String  putBusiness(String name,String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String country,String latitude,String longitude,String timezone,String telephone_number,String additional_telephone_number,String email,String website,String category_id,String category_type,String do_not_display,String referrer_url,String referrer_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("name", name);
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
    	retval = this.doCurl("PUT","/business",params);
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
   *  @param claimPrice
   *  @param claimMethods
   *  @param nokia_country_code
   *  @param twilio_sms
   *  @param twilio_phone
   *  @param twilio_voice
   *  @param currency_symbol - the symbol of this country's currency
   *  @param currency_symbol_html - the html version of the symbol of this country's currency
   *  @param postcodeLookupActive - Whether the lookup is activated for this country
   *  @param addressFields - Whether fields are activated for this country
   *  @return - the data from the api
  */
  public String  postCountry(String country_id,String name,String synonyms,String continentName,String continent,String geonameId,String dbpediaURL,String freebaseURL,String population,String currencyCode,String languages,String areaInSqKm,String capital,String east,String west,String north,String south,String claimPrice,String claimMethods,String nokia_country_code,String twilio_sms,String twilio_phone,String twilio_voice,String currency_symbol,String currency_symbol_html,String postcodeLookupActive,String addressFields) throws Exception { 
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
    	params.put("claimPrice", claimPrice);
    	params.put("claimMethods", claimMethods);
    	params.put("nokia_country_code", nokia_country_code);
    	params.put("twilio_sms", twilio_sms);
    	params.put("twilio_phone", twilio_phone);
    	params.put("twilio_voice", twilio_voice);
    	params.put("currency_symbol", currency_symbol);
    	params.put("currency_symbol_html", currency_symbol_html);
    	params.put("postcodeLookupActive", postcodeLookupActive);
    	params.put("addressFields", addressFields);
    	retval = this.doCurl("POST","/country",params);
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
   * With a known entity id, an add can be updated.
   *
   *  @param entity_id
   *  @param add_referrer_url
   *  @param add_referrer_name
   *  @return - the data from the api
  */
  public String  postEntityAdd(String entity_id,String add_referrer_url,String add_referrer_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("add_referrer_url", add_referrer_url);
    	params.put("add_referrer_name", add_referrer_name);
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
   * Expires an advertiser from and entity
   *
   *  @param entity_id
   *  @param publisher_id
   *  @param reseller_ref
   *  @param reseller_agent_id
   *  @return - the data from the api
  */
  public String  postEntityAdvertiserCancel(String entity_id,String publisher_id,String reseller_ref,String reseller_agent_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("publisher_id", publisher_id);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
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
  public String  postEntityAdvertiserCreate(String entity_id,String tags,String locations,String max_tags,String max_locations,String expiry_date,String is_national,String language,String reseller_ref,String reseller_agent_id,String publisher_id) throws Exception { 
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
  public String  postEntityAdvertiserLocation(String entity_id,String gen_id,String locations_to_add,String locations_to_remove) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("locations_to_add", locations_to_add);
    	params.put("locations_to_remove", locations_to_remove);
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
  public String  postEntityAdvertiserRenew(String entity_id,String expiry_date,String publisher_id,String reseller_ref,String reseller_agent_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("expiry_date", expiry_date);
    	params.put("publisher_id", publisher_id);
    	params.put("reseller_ref", reseller_ref);
    	params.put("reseller_agent_id", reseller_agent_id);
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
  public String  postEntityAdvertiserUpsell(String entity_id,String tags,String locations,String extra_tags,String extra_locations,String is_national,String language,String reseller_ref,String reseller_agent_id,String publisher_id) throws Exception { 
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
   * Uploads a JSON file of known format and bulk inserts into DB
   *
   *  @param data
   *  @return - the data from the api
  */
  public String  postEntityBulkJson(String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("data", data);
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
  public String  postEntityCategory(String entity_id,String category_id,String category_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("category_id", category_id);
    	params.put("category_type", category_type);
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
   *  @param claimed_date
   *  @param claim_method
   *  @param phone_number
   *  @param referrer_url
   *  @param referrer_name
   *  @return - the data from the api
  */
  public String  postEntityClaim(String entity_id,String claimed_user_id,String claimed_date,String claim_method,String phone_number,String referrer_url,String referrer_name) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("claimed_user_id", claimed_user_id);
    	params.put("claimed_date", claimed_date);
    	params.put("claim_method", claim_method);
    	params.put("phone_number", phone_number);
    	params.put("referrer_url", referrer_url);
    	params.put("referrer_name", referrer_name);
    	retval = this.doCurl("POST","/entity/claim",params);
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
   * Allows a group object to be removed from an entities group members
   *
   *  @param entity_id
   *  @param gen_id
   *  @return - the data from the api
  */
  public String  deleteEntityGroup(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
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
  public String  postEntityGroup(String entity_id,String group_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("group_id", group_id);
    	retval = this.doCurl("POST","/entity/group",params);
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
  public String  postEntityInvoice_address(String entity_id,String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String address_type) throws Exception { 
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
   * With a known entity id, a opening times object can be removed.
   *
   *  @param entity_id - The id of the entity to edit
   *  @return - the data from the api
  */
  public String  deleteEntityOpening_times(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
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
  public String  postEntityPostal_address(String entity_id,String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String address_type,String do_not_display) throws Exception { 
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
   * Send an email to an email address specified in an entity
   *
   *  @param entity_id - The entity id of the entity you wish to contact
   *  @param gen_id - The gen_id of the email address you wish to send the message to
   *  @param from_email - The email of the person sending the message 
   *  @param subject - The subject for the email
   *  @param content - The content of the email
   *  @return - the data from the api
  */
  public String  postEntitySend_email(String entity_id,String gen_id,String from_email,String subject,String content) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
    	params.put("from_email", from_email);
    	params.put("subject", subject);
    	params.put("content", content);
    	retval = this.doCurl("POST","/entity/send_email",params);
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
  public String  postEntitySocialmedia(String entity_id,String type,String website_url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("type", type);
    	params.put("website_url", website_url);
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
  public String  deleteEntitySocialmedia(String entity_id,String gen_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("gen_id", gen_id);
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
   * With a known entity id, a YouTube video object can be added.
   *
   *  @param entity_id
   *  @param embed_code
   *  @return - the data from the api
  */
  public String  postEntityVideoYoutube(String entity_id,String embed_code) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("embed_code", embed_code);
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
   * Add an entityserve document
   *
   *  @param entity_id - The id of the entity to create the entityserve event for
   *  @param country - the ISO code of the country
   *  @param event_type - The event type being recorded
   *  @return - the data from the api
  */
  public String  putEntityserve(String entity_id,String country,String event_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("country", country);
    	params.put("event_type", event_type);
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
   *  @param cookiePolicyUrl - The cookie policy url of the flatpack
   *  @param cookiePolicyNotice - Whether to show the cookie policy on this flatpack
   *  @param addBusinessButtonText - The text used in the 'Add your business' button
   *  @param twitterUrl - Twitter link
   *  @param facebookUrl - Facebook link
   *  @param copyright - Copyright message
   *  @param advertUpgradeActive - whether upgrade message is displayed on this Flatpack
   *  @param advertUpgradePrice - the cost of upgrading
   *  @param advertUpgradeMaxTags - the number of tags upgrading gives you
   *  @param advertUpgradeMaxLocations - the number of locations upgrading gives you
   *  @param advertUpgradeContractLength - the length of the contract (days)
   *  @param advertUpgradeRefId - a unique reference for the upgrade
   *  @param phoneReveal - record phone number reveal
   *  @param loginLinkText - the link text for the Login link
   *  @param contextLocationId - The location ID to use as the context for searches on this flatpack
   *  @param addBusinessButtonPosition - The location ID to use as the context for searches on this flatpack
   *  @return - the data from the api
  */
  public String  postFlatpack(String flatpack_id,String domainName,String stub,String flatpackName,String less,String language,String country,String mapsType,String mapKey,String searchFormShowOn,String searchFormShowKeywordsBox,String searchFormShowLocationBox,String searchFormKeywordsAutoComplete,String searchFormLocationsAutoComplete,String searchFormDefaultLocation,String searchFormPlaceholderKeywords,String searchFormPlaceholderLocation,String searchFormKeywordsLabel,String searchFormLocationLabel,String cannedLinksHeader,String homepageTitle,String homepageDescription,String homepageIntroTitle,String homepageIntroText,String head,String adblock,String bodyTop,String bodyBottom,String header_menu,String header_menu_bottom,String footer_menu,String bdpTitle,String bdpDescription,String bdpAds,String serpTitle,String serpDescription,String serpNumberResults,String serpNumberAdverts,String serpAds,String cookiePolicyUrl,String cookiePolicyNotice,String addBusinessButtonText,String twitterUrl,String facebookUrl,String copyright,String advertUpgradeActive,String advertUpgradePrice,String advertUpgradeMaxTags,String advertUpgradeMaxLocations,String advertUpgradeContractLength,String advertUpgradeRefId,String phoneReveal,String loginLinkText,String contextLocationId,String addBusinessButtonPosition) throws Exception { 
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
    	params.put("cookiePolicyUrl", cookiePolicyUrl);
    	params.put("cookiePolicyNotice", cookiePolicyNotice);
    	params.put("addBusinessButtonText", addBusinessButtonText);
    	params.put("twitterUrl", twitterUrl);
    	params.put("facebookUrl", facebookUrl);
    	params.put("copyright", copyright);
    	params.put("advertUpgradeActive", advertUpgradeActive);
    	params.put("advertUpgradePrice", advertUpgradePrice);
    	params.put("advertUpgradeMaxTags", advertUpgradeMaxTags);
    	params.put("advertUpgradeMaxLocations", advertUpgradeMaxLocations);
    	params.put("advertUpgradeContractLength", advertUpgradeContractLength);
    	params.put("advertUpgradeRefId", advertUpgradeRefId);
    	params.put("phoneReveal", phoneReveal);
    	params.put("loginLinkText", loginLinkText);
    	params.put("contextLocationId", contextLocationId);
    	params.put("addBusinessButtonPosition", addBusinessButtonPosition);
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
   * Upload a file to our asset server and return the url
   *
   *  @param filedata
   *  @return - the data from the api
  */
  public String  postFlatpackUpload(String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/flatpack/upload",params);
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
   * Update/Add a Group
   *
   *  @param group_id
   *  @param name
   *  @param description
   *  @param url
   *  @return - the data from the api
  */
  public String  postGroup(String group_id,String name,String description,String url) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	params.put("name", name);
    	params.put("description", description);
    	params.put("url", url);
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
   * Bulk update entities with a specified group
   *
   *  @param group_id
   *  @param data
   *  @return - the data from the api
  */
  public String  postGroupBulk_update(String group_id,String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("group_id", group_id);
    	params.put("data", data);
    	retval = this.doCurl("POST","/group/bulk_update",params);
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
   * Add a ingest job to the collection
   *
   *  @param description
   *  @param category_type
   *  @return - the data from the api
  */
  public String  postIngest_job(String description,String category_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("description", description);
    	params.put("category_type", category_type);
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
   *  @param parent_town
   *  @param parent_county
   *  @param parent_province
   *  @param parent_region
   *  @param parent_neighbourhood
   *  @param parent_district
   *  @param postalcode
   *  @return - the data from the api
  */
  public String  postLocation(String location_id,String name,String formal_name,String latitude,String longitude,String resolution,String country,String population,String description,String timezone,String is_duplicate,String is_default,String parent_town,String parent_county,String parent_province,String parent_region,String parent_neighbourhood,String parent_district,String postalcode) throws Exception { 
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
    	params.put("parent_town", parent_town);
    	params.put("parent_county", parent_county);
    	params.put("parent_province", parent_province);
    	params.put("parent_region", parent_region);
    	params.put("parent_neighbourhood", parent_neighbourhood);
    	params.put("parent_district", parent_district);
    	params.put("postalcode", postalcode);
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
   * Given a csv of location synonyms add them
   *
   *  @param filedata - A tab separated CSV file
   *  @return - the data from the api
  */
  public String  postLocationSynonymBulk(String filedata) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("filedata", filedata);
    	retval = this.doCurl("POST","/location/synonym/bulk",params);
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
  public String  postLocz(String location_id,String type,String country,String language,String name,String formal_name,String resolution,String population,String description,String timezone,String latitude,String longitude,String parent_town,String parent_county,String parent_province,String parent_region,String parent_neighbourhood,String parent_district,String postalcode,String searchable_id,String searchable_ids) throws Exception { 
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
    	retval = this.doCurl("POST","/locz",params);
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
  public String  getLocz(String location_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	retval = this.doCurl("GET","/locz",params);
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
  public String  getLoczMultiple(String location_ids) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_ids", location_ids);
    	retval = this.doCurl("GET","/locz/multiple",params);
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
   * Find all the child locations of the selected location
   *
   *  @param location_id
   *  @param resolution
   *  @return - the data from the api
  */
  public String  getLookupLocationChildren(String location_id,String resolution) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	params.put("resolution", resolution);
    	retval = this.doCurl("GET","/lookup/location/children",params);
    } finally { 
    }
    return retval;
  }


  /**
   * Find all the parents locations of the selected location
   *
   *  @param location_id
   *  @return - the data from the api
  */
  public String  getLookupLocationParents(String location_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("location_id", location_id);
    	retval = this.doCurl("GET","/lookup/location/parents",params);
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
  public String  getLookupLocz(String string,String language,String country,String latitude,String longitude) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("string", string);
    	params.put("language", language);
    	params.put("country", country);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	retval = this.doCurl("GET","/lookup/locz",params);
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
   * Find all matches by phone number and then return all matches that also match company name and location. Default location_strictness is defined in Km and the default is set to 0.2 (200m)
   *
   *  @param phone
   *  @param company_name
   *  @param latitude
   *  @param longitude
   *  @param country
   *  @param name_strictness
   *  @param location_strictness
   *  @return - the data from the api
  */
  public String  getMatchByphone(String phone,String company_name,String latitude,String longitude,String country,String name_strictness,String location_strictness) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("phone", phone);
    	params.put("company_name", company_name);
    	params.put("latitude", latitude);
    	params.put("longitude", longitude);
    	params.put("country", country);
    	params.put("name_strictness", name_strictness);
    	params.put("location_strictness", location_strictness);
    	retval = this.doCurl("GET","/match/byphone",params);
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
  public String  putPrivate_object(String entity_id,String data) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("data", data);
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
   * Perform the whole PTB process on the supplied entity
   *
   *  @param entity_id
   *  @return - the data from the api
  */
  public String  getPtbAll(String entity_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
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
   *  @return - the data from the api
  */
  public String  getPtbModule(String entity_id,String module) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("module", module);
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
  public String  postSales_log(String entity_id,String country,String action_type,String publisher_id,String mashery_id,String reseller_ref,String reseller_agent_id,String max_tags,String max_locations,String extra_tags,String extra_locations,String expiry_date) throws Exception { 
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
    	retval = this.doCurl("POST","/sales_log",params);
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
   *  @return - the data from the api
  */
  public String  postSignal(String entity_id,String country,String gen_id,String signal_type,String data_type) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("country", country);
    	params.put("gen_id", gen_id);
    	params.put("signal_type", signal_type);
    	params.put("data_type", data_type);
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
   * Fetch token for update path
   *
   *  @param entity_id - The id of the entity being upgraded
   *  @param portal_name - The name of the application that has initiated the login process, example: 'Your Local'
   *  @param language - The language for the app
   *  @param price - The price of the advert in the entities native currency
   *  @param max_tags - The number of tags attached to the advert
   *  @param max_locations - The number of locations attached to the advert
   *  @param contract_length - The number of days from the initial sale date that the contract is valid for
   *  @param ref_id - The campaign or reference id
   *  @param flatpack_id - The id of the flatpack site where the request originated
   *  @return - the data from the api
  */
  public String  getTokenUpgrade(String entity_id,String portal_name,String language,String price,String max_tags,String max_locations,String contract_length,String ref_id,String flatpack_id) throws Exception { 
     Hashtable params = new Hashtable();
     String retval = "" ;
     try { 
    	params.put("entity_id", entity_id);
    	params.put("portal_name", portal_name);
    	params.put("language", language);
    	params.put("price", price);
    	params.put("max_tags", max_tags);
    	params.put("max_locations", max_locations);
    	params.put("contract_length", contract_length);
    	params.put("ref_id", ref_id);
    	params.put("flatpack_id", flatpack_id);
    	retval = this.doCurl("GET","/token/upgrade",params);
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
   *  @return - the data from the api
  */
  public String  getToolsGeocode(String building_number,String address1,String address2,String address3,String district,String town,String county,String province,String postcode,String country) throws Exception { 
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
   *  @param reseller_admin_masheryid
   *  @param group_id
   *  @param admin_upgrader
   *  @return - the data from the api
  */
  public String  postUser(String email,String first_name,String last_name,String active,String trust,String creation_date,String user_type,String social_network,String social_network_id,String reseller_admin_masheryid,String group_id,String admin_upgrader) throws Exception { 
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
    	params.put("reseller_admin_masheryid", reseller_admin_masheryid);
    	params.put("group_id", group_id);
    	params.put("admin_upgrader", admin_upgrader);
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



}

