package pizza.api.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import pizza.api.IPizzaInfo;
import pizza.api.dto.MenuDTO;
import pizza.api.dto.PizzaInfoDto;

public class JsonConverter {
	
	public static String fromMenuToJson(Menu menu) throws Exception {
		try {
			return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(menu);
		} catch (JsonProcessingException e) {
			throw new Exception("Failed to write MenuDtoOutput:" + menu.toString() + "as json", e);
		}
	}

	public static MenuDTO fromJsonToMenu(String jsonString) throws JsonProcessingException {
		return new ObjectMapper().readValue(jsonString, MenuDTO.class);
	}
	
	 public static String fromPizzaInfoToJson(IPizzaInfo iPizzaInfo) throws Exception {
	        try {
	            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(iPizzaInfo);
	        } catch (JsonProcessingException e) {
	            throw new Exception("failed to write IPizzaInfo as json", e);
	        }
	    }
	 public static PizzaInfoDto fromJsonToPizzaInfo(String jsonString) throws JsonProcessingException {
			return new ObjectMapper().readValue(jsonString, PizzaInfoDto.class);
		}
}
