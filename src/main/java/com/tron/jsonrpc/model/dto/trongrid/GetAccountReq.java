package com.tron.jsonrpc.model.dto.trongrid;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @Classname GetAccountReq
 * @Description
 * @Date 2021/5/26 16:48
 * @Created by zhiyuan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetAccountReq extends BaseReq {
    @Serial
    private static final long serialVersionUID = -6931501913097596251L;
}