package pizza.api.dto;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pizza.api.core.MenuRow;
import pizza.api.core.PizzaInfo;

public class PizzaInfoFromJson {

	public static PizzaInfo fromJson(String jsonString) throws JsonProcessingException {
		return new ObjectMapper().readValue(jsonString, PizzaInfo.class);
	}

	public static String toJson(List<MenuRow> menu) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(menu);
	}
}