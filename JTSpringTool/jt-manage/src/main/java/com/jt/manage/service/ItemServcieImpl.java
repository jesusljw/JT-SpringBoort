package com.jt.manage.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.vo.EasyUI_Data;


@Service
public class ItemServcieImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;

	
	@Override
	public EasyUI_Data findItemByPage(Integer page, Integer rows) {
//		int total = itemMapper.findCount();
		int total = itemMapper.selectCount(null);
		Integer start = (page-1)*rows;
		List<Item> itemList = itemMapper.findItemByPage(start,rows);
		return new EasyUI_Data(total,itemList);
	}

	@Override
	public String findItemCatNameById(Long itemId) {
		
		return itemMapper.findItemCatNameById(itemId);
	}

	@Override
	public void saveItem(Item item,String desc) {
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		//利用通用Mapper查询id
		//SELECT LAST_INSERT_ID();
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updateItem(Item item) {
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		
	}

	@Override
	public void updateStatus(Long[] ids, int status) {
		itemMapper.updateStatus(ids,status);
		
	}

	@Override
	public void deleteStatus(Long[] ids) {
		itemMapper.deleteByIDS(ids);
		
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public Item findItemById(Long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
		
	}
	
	
	
	
}
