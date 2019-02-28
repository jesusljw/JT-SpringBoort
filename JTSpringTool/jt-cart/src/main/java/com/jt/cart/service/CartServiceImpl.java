package com.jt.cart.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;


@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;
	
	
	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
		queryWrapper.eq("user_id",userId);
		return cartMapper.selectList(queryWrapper);
	}

	@Transactional
	@Override
	public void updateCartNum(Cart cart) {
		Cart cartTemp = new Cart();
		cartTemp.setNum(cart.getNum()).setCreated(new Date());;
		UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<Cart>();
		updateWrapper.eq("user_Id", cart.getUserId()).eq("item_Id", cart.getItemId());
		cartMapper.update(cartTemp, updateWrapper);
		
	}

	/**
	 * 新增业务逻辑:item_id,user_id
	 * 如果根据item_id,user_id查询时数据库有记录:则只做数据的更新操作,否则做数据新增操作
	 */
	@Transactional
	@Override
	public void saveCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("item_id", cart.getItemId()).eq("user_id", cart.getUserId());
		Cart cartDB = cartMapper.selectOne(queryWrapper);
		if(cartDB == null) {
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			int num = cart.getNum()+cartDB.getNum();
			//只更新数量而不是全部数据
			Cart cartTemp = new Cart();
			cartTemp.setNum(num);
			UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq("item_id", cart.getItemId()).eq("user_id", cart.getUserId());
			cartMapper.update(cartTemp, updateWrapper);
		}
		
	}

	@Override
	public void deleteCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>(cart);
//		queryWrapper.eq("item_id", cart.getItemId()).eq("user_id", cart.getUserId());
		cartMapper.delete(queryWrapper);
		
	}


	

	
	
	
	
}
