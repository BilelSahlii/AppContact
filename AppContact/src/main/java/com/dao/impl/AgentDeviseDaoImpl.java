package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idao.AgentDeviseDao;

@Service("agentDeviseDao")
@Transactional
public class AgentDeviseDaoImpl extends GenericDaoImpl implements AgentDeviseDao {


}
