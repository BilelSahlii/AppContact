package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.idao.GroupeContactDao;

@Service("GroupeContactDao")
@Transactional
public class GroupeContactDaoImpl extends GenericDaoImpl implements GroupeContactDao {


}
