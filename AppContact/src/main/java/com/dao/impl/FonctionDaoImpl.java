package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.idao.FonctionDao;

@Service("FonctionDao")
@Transactional
public class FonctionDaoImpl extends GenericDaoImpl implements FonctionDao {


}
