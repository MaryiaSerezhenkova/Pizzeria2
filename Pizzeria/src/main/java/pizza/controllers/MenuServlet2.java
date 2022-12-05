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


import pizza.api.IMenu;
import pizza.api.dto.MenuDTO;
import pizza.service.MenuServiceSingleton;
import pizza.service.api.IMenuService;

//CRUD controller
//IMenu
@RestController
@RequestMapping("/menu2")
public class MenuServlet2 {

	private final IMenuService menuService = MenuServiceSingleton.getInstance();

	// Read POSITION
	// 1) Read list
	// 2) Read item (card) need id param
	@GetMapping
	@RequestMapping("/{id}")
	protected ResponseEntity<IMenu> get(@PathVariable long id) {
		return ResponseEntity.ok(menuService.read(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ResponseEntity<List<IMenu>> getList(@PathVariable long id) {
		return ResponseEntity.ok(menuService.get());
	}

	// CREATE POSITION
	// body json
	@PostMapping
	public ResponseEntity<IMenu> doPost(@RequestBody MenuDTO data) {
		IMenu created = this.menuService.create(data);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	// UPDATE POSITION
	// need param id
	// need param version/date_update - optimistic lock
	// body json
	@PutMapping
	@RequestMapping("/{id}/dt_update/{dt_update}")
	protected ResponseEntity<IMenu> doPut(@PathVariable long id, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody MenuDTO data) {
		LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.of("UTC"));
		return ResponseEntity.ok(this.menuService.update(id, dtUpdate, data));
	}

	// DELETE POSITION
	// need param id
	// need param version/date_update - optimistic lock
	@DeleteMapping
	@RequestMapping("/{id}/dt_update/{dt_update}")
	protected ResponseEntity<?> doDelete(@PathVariable long id, @PathVariable("dt_update") long dtUpdateRow,
			@RequestBody MenuDTO data) {
		LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdateRow), ZoneId.of("UTC"));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}