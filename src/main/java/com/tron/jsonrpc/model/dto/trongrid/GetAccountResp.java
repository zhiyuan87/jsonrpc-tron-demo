package com.tron.jsonrpc.model.dto.trongrid;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Classname GetAccountBalanceResp
 * @Description
 * @Date 2021/5/26 16:48
 * @Created by zhiyuan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetAccountResp extends BaseResp {
    @Serial
    private static final long serialVersionUID = -5086352575050822984L;

    private List<Account> data;

    @Data
    public static class Account implements Serializable {
        @Serial
        private static final long serialVersionUID = -7885118556139736061L;

        private String address;
        private BigDecimal balance;
        private List<Map<String, BigDecimal>> trc20;
    }
}