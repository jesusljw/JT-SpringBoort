package com.jt.sso.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName(value="tb_user")
public class User extends BasePojo implements Serializable{
	@TableId(type=IdType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private String phone;
	private String email;
	
}
