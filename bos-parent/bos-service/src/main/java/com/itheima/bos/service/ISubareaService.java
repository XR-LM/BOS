package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Subarea;
import com.itheima.bos.utils.PageBean;

public interface ISubareaService {

	void save(Subarea model);

	void pageQery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> findListNotAssiontion();

	List<Subarea> findListByDecidedzoneId(String decidedzoneId);

	List<Object> findSubByPro();

}
