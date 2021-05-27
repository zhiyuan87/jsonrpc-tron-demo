package com.tron.jsonrpc.model.dto.trongrid;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @Classname GetTransactionsReq
 * @Description
 * @Date 2021/5/27 12:19
 * @Created by zhiyuan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetTransactionsReq extends BaseReq {
    @Serial
    private static final long serialVersionUID = 1306515395463031962L;

    private Boolean only_to;
    private Integer limit;
    private String fingerprint;
    private Long min_timestamp;
    private Long max_timestamp;


}
