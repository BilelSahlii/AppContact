package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.idao.PhysiqueDao;

@Service("PhysiqueDao")
@Transactional
public class PhysiqueDaoImpl extends GenericDaoImpl implements PhysiqueDao {


}
