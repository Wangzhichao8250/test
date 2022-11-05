package com.mooc.meetingfilm.user.dao.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author jiangzh
 * @since 2021-07-19
 */
@Data
public class MoocUserT extends Model<MoocUserT> implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户手机号
     */
    private String userPhone;


    @Override
    protected Serializable pkVal() {
        return null;
    }



}
