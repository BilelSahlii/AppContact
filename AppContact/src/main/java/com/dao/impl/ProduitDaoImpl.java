package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





import com.idao.ProduitDao;


@Service("ProduitDao")
@Transactional
public class ProduitDaoImpl extends GenericDaoImpl implements ProduitDao {


}
