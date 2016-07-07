package servidor;

import java.util.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class KVKeyValuehasp {
	/**
	 * @param args
	 */
		    public String key;       public String valor;   
		    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String,String>(); 
		 
		    public KVKeyValuehasp(){
		    }

		    public KVKeyValuehasp(String key, String valor)

		         { this.key = key; this.valor = valor;}
		    
		    public void putKeyValue(String key, String valor){
		    	map.putIfAbsent(key, valor);
		    }

		    public String getKeyValue(String key){
		    	return map.get(key);
		    }
		    
		    public void delKeyValue(String key){
		    	map.remove(key);
		    }
		    
		    public Set<String> getListKeyValue(){
		    	return map.keySet();
		    }
		    
		}
