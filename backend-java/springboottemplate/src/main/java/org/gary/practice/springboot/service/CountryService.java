package org.gary.practice.springboot.service;

import java.util.List;

import org.gary.practice.springboot.mapper.CountryMapper;
import org.gary.practice.springboot.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;

@Service
public class CountryService {

	@Autowired
	private CountryMapper countryMapper;

	@Transactional(readOnly=true)
	public List<Country> search() {
		PageInfo<Country> pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				Condition condition = new Condition(Country.class);
				condition.orderBy("id");
				countryMapper.selectByCondition(condition);
			}
		});
		return pageInfo.getList();
	}
}
