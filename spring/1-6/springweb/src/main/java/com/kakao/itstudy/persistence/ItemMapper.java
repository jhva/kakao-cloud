package com.kakao.itstudy.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kakao.itstudy.domain.ItemEntity;

@Repository
public interface ItemMapper {
	@Select("select * from item")
	public List<ItemEntity> allItem();
	
	@Select("select * from item where itemid=#{itemid}")
	public ItemEntity getItem(int itemid);
}






