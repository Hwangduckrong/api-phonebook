package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Service
public class PhonebookService {

	@Autowired
	private PhonebookDao phonebookDao;

	// 리스트 가져오기
	public List<PersonVo> exeGetPersonList() {

		List<PersonVo> personList = phonebookDao.getPersonList();

		return personList;
	}

	// 삭제
	public int exePersonDelete(int no) {

		int count = phonebookDao.deletePerson(no);

		return count;
	}

	// 수정
	public int exeEditPerson(PersonVo personVo) {

		int count = phonebookDao.updatePerson(personVo);

		return count;
	}

	// 수정폼
	public PersonVo exeEditForm(int no) {

		PersonVo personVo = phonebookDao.getPersonOne(no);

		return personVo;
	}

	// 등록
	public int exeWritePerson(PersonVo personVo) {

		int count = phonebookDao.insertPerson(personVo);

		return count;
	}
}