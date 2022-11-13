package pizza.api.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {
	
    private static final ObjectMapper mapper  = new ObjectMapper();
    

//    public static ObjectMapper getObjectMapper() {
//        return mapper.get();
//    }
}