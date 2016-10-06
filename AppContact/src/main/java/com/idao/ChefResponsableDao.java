package com.idao;

import java.io.File;
import java.io.InputStream;

public interface ChefResponsableDao extends GenericIDao {
	public void saveFile(InputStream inputstream,File file);

}
