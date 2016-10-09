package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






import com.idao.ActiviteDao;



@Service("ActiviteDao")
@Transactional
public class ActiviteDaoImpl extends GenericDaoImpl implements ActiviteDao {


}
