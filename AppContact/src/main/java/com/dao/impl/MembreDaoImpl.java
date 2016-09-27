package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.idao.MembreDao;;

@Service("MembreDao")
@Transactional
public class MembreDaoImpl extends GenericDaoImpl implements MembreDao {


}
