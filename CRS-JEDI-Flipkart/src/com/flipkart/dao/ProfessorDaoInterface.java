package com.flipkart.dao;

import com.flipkart.bean.Professor;

public interface ProfessorDaoInterface {
	public Professor getProfessorByEmail(String email);
}
