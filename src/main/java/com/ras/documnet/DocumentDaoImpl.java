/**
 * 
 */
package com.ras.documnet;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author algz
 *
 */
public class DocumentDaoImpl implements DocumentDao {
	
	@Autowired
	private SessionFactory sf;
	
	public void saveModel(){
		
	}
}
