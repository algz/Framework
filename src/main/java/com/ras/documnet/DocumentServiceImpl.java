/**
 * 
 */
package com.ras.documnet;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author algz
 *
 */
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentDao dao;
	
	public void saveModel(){
		dao.saveModel();
	}
}
