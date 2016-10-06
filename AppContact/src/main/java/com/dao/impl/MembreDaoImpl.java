package com.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.idao.MembreDao;;

@Service("MembreDao")
@Transactional
public class MembreDaoImpl extends GenericDaoImpl implements MembreDao {

	
	public void saveFile(InputStream inputstream,File file)
	{

		
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

try {
	IOUtils.copyLarge(inputstream, output);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
	}


}
