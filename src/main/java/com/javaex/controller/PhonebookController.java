package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.PhonebookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.PersonVo;

@RestController // 이러면 ResponsBody를 생략하고 사용할 수 있다.
				// 왜 이전에는 안 썻지?:자바 스프링 부트에서는 json으로 보내야 할 경우와 그렇지 않은 경우가 나뉘었다.
				// 리액트에서는 모두 json으로 보내고 받으니 사용함.
public class PhonebookController {

	@Autowired
	private PhonebookService phonebookService;

	@GetMapping("/api/persons") // 리스트할때는 이렇게
	public List<PersonVo> getList() {

		List<PersonVo> personList = phonebookService.exeGetPersonList();

		return personList;
	}

	@PostMapping("/api/persons")
	public int addPerson(@RequestBody PersonVo personVo) {

		int count = phonebookService.exeWritePerson(personVo);

		return count;
	}

	@DeleteMapping(value = "/api/persons/{no}")
	public JsonResult delPerson(@PathVariable(value = "no") int no) {
		System.out.println("PhonebookController.delPerson");
		System.out.println(no);
		int count = phonebookService.exePersonDelete(no);
		if (count != 1) {
			return JsonResult.fail("해당번호가 없습니다");
		} else {
			return JsonResult.success(count);
		}
	}

	// 수정 폼
	@GetMapping("/api/persons/{no}") // no는 자바에서 pathvariable이라는 용법으로 쓰일수 있다.
	public JsonResult getPerson(@PathVariable(value = "no") int personId) {
		System.out.println("PhonebookController.getPerson()");

		PersonVo personVo = phonebookService.exeEditForm(personId);
		System.out.println(personVo);// 없는 번호는 null값이 뜰 것

		if (personVo == null) {
			return JsonResult.fail("해당번호는 없습니다");
		} else {
			return JsonResult.success(personVo);
		}

	}

	// 수정
	@PutMapping("/api/persons/{no}")
	public JsonResult updatePerson(@PathVariable(value = "no") int personId, @RequestBody PersonVo personVo) {
		System.out.println("PhonebookController.updatePerson()");
		personVo.setPersonId(personId);
		System.out.println(personVo);

		int count = phonebookService.exeEditPerson(personVo);

		if (count == 0) {
			return JsonResult.fail("수정할 데이터가 없습니다");
		} else {
			return JsonResult.success(count);
		}

	}

}
