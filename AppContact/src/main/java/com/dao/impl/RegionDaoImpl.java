package com.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;







import com.idao.RegionDao;


@Service("RegionDao")
@Transactional
public class RegionDaoImpl extends GenericDaoImpl implements RegionDao {


}
