package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idao.AgentDeviseDao;
import com.idao.ChefResponsableDao;

@Service("ChefResponsableDao")
@Transactional
public class ChefResponsableDaoImpl extends GenericDaoImpl implements ChefResponsableDao {


}
