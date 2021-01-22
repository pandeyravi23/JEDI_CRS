/**
 * 
 */
package com.flipkart.dao;

import com.flipkart.bean.Professor;

/**
 * @author jedi04
 *
 */
public interface AdminDAOInterface {
	public boolean verifyEmail(String email);
	public int addAdmin(String email, String password);
	public int addProfessor(String password, Professor prof);
}
