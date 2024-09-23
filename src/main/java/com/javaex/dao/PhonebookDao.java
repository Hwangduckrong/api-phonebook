package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhonebookDao {

	@Autowired

	private SqlSession sqlSession;

	// 리스트
	public List<PersonVo> getPersonList() {

		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");

		return personList;
	}

	// 삭제
	public int deletePerson(int no) {

		int count = sqlSession.delete("phonebook.delete", no);
		return count;
	}

	// 수정
	public int updatePerson(PersonVo personVo) {

		int count = sqlSession.update("phonebook.update", personVo);
		return count;
	}

	// 사람 1명 정보 가져오기
	public PersonVo getPersonOne(int no) {

		PersonVo personVo = sqlSession.selectOne("phonebook.selectOne", no);

		return personVo;
	}

	// 등록
	public int insertPerson(PersonVo personVo) {

		int count = sqlSession.insert("phonebook.insert", personVo);

		return count;

	}

}