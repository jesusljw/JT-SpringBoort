package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.Item;

public interface ItemMapper extends SysMapper<Item>{
	@Select("select count(*) from tb_item")
	int findCount();

	List<Item> findItemByPage(@Param("start")Integer start, @Param("rows")Integer rows);
	
	@Select("select name from tb_item_cat where id=#{itemId}")
	String findItemCatNameById(Long itemId);

	void updateStatus(@Param("ids")Long[] ids, @Param("status")int status);


}
