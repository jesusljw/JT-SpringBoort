package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUI_Tree;

@Controller
@RequestMapping("/item")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**商品树形结构展现
	 * 当页面中没有传递id时,默认值为0,即查询一级商品分类信息,传了则动态获取参数
	 * @param parentId 表示父级id
	 * @param id 表示页面传的id
	 * @return
	 */
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUI_Tree> findTree(@RequestParam(value="id",defaultValue="0")Long parentId){
		//Long parentId = id;
		return itemCatService.findTreeCache(parentId);
	}
}
