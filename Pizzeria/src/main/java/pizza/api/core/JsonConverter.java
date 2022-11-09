package pizza.api.core;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletInputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import pizza.api.IMenu;
import pizza.api.IPizzaInfo;
import pizza.api.dto.MenuDTO;
import pizza.api.dto.PizzaInfoDto;

public class JsonConverter {
	
	public static String fromMenuToJson(IMenu iMenu) throws Exception {
		try {
			return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(iMenu);
		} catch (JsonProcessingException e) {
			throw new Exception("Failed to write MenuDtoOutput:" + iMenu.toString() + "as json", e);
		}
	}

	public static MenuDTO fromJsonToMenu(String jsonString) throws JsonProcessingException {
		return new ObjectMapper().readValue(jsonString, MenuDTO.class);
	}
	 public static String fromMenuListToJson(List<IMenu> list) throws Exception {
	        try { 
	            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(list);
	        } catch (JsonProcessingException e) {
	            throw new Exception("failed to write List PizzaInfo as json", e);
	        }
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
	 
	 public static String fromPizzaInfoListToJson(List<IPizzaInfo> list) throws Exception {
	        try { 
	            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(list);
	        } catch (JsonProcessingException e) {
	            throw new Exception("failed to write List PizzaInfo as json", e);
	        }
	    }

	  public static LocalDateTime convert(long unixTime) {
	        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), TimeZone.getDefault().toZoneId());
	    }

}
