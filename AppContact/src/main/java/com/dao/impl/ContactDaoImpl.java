package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.idao.ContactDao;


@Service("ContactDao")
@Transactional
public class ContactDaoImpl extends GenericDaoImpl implements ContactDao {


}
