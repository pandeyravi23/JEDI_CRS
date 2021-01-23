package com.flipkart.dao;

import com.flipkart.bean.Professor;

public interface ProfessorDAOInterface {
	public Professor getProfessorByEmail(String email);
}
