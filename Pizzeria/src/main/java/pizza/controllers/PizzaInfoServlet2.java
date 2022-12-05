package pizza.controllers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pizza.api.IPizzaInfo;
import pizza.api.dto.PizzaInfoDto;
import pizza.service.PizzaInfoServiceSingleton;
import pizza.service.api.IPizzaInfoService;

@RestController
@RequestMapping("/pizzaInfo2")
public class PizzaInfoServlet2 {
	
	private final IPizzaInfoService pizzaInfoService = PizzaInfoServiceSingleton.getInstance();
	// Read POSITION
		// 1) Read list
		// 2) Read item (card) need id param
		@GetMapping
		@RequestMapping("/{id}")
		protected ResponseEntity<IPizzaInfo> get(@PathVariable long id) {
			return ResponseEntity.ok(pizzaInfoService.read(id));
		}

		@RequestMapping(method = RequestMethod.GET)
		protected ResponseEntity<List<IPizzaInfo>> getList(@PathVariable long id) {
			return ResponseEntity.ok(pizzaInfoService.get());
		}

		// CREATE POSITION
		// body json
		@PostMapping
		public ResponseEntity<IPizzaInfo> doPost(@RequestBody PizzaInfoDto data) {
			IPizzaInfo created = this.pizzaInfoService.create(data);
			return new ResponseEntity<>(created, HttpStatus.CREATED);
		}

		// UPDATE POSITION
		// need param id
		// need param version/date_update - optimistic lock
		// body json
		@PutMapping
		@RequestMapping("/{id}/dt_update/{dt_update}")
		protected ResponseEntity<IPizzaInfo> doPut(@PathVariable long id, @PathVariable("dt_update") long dtUpdateRow,
				@RequestBody PizzaInfoDto data) {
			LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.of("UTC"));
			return ResponseEntity.ok(this.pizzaInfoService.update(id, dtUpdate, data));
		}

		// DELETE POSITION
		// need param id
		// need param version/date_update - optimistic lock
		@DeleteMapping
		@RequestMapping("/{id}/dt_update/{dt_update}")
		protected ResponseEntity<?> doDelete(@PathVariable long id, @PathVariable("dt_update") long dtUpdateRow,
				@RequestBody PizzaInfoDto data) {
			LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.of("UTC"));
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}


