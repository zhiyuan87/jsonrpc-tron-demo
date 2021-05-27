package com.tron.jsonrpc.model.dto.trongrid;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Classname BaseResp
 * @Description
 * @Date 2020/5/28 17:33
 * @Created by zhiyuan
 */
@Data
public class BaseResp implements Serializable {
    @Serial
    private static final long serialVersionUID = 8486318895292589180L;

    private Boolean success;
    private String error;
    private Integer statusCode;
}