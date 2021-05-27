package com.tron.jsonrpc.service;

import com.tron.jsonrpc.model.dto.trongrid.GetAccountResp;
import com.tron.jsonrpc.model.dto.trongrid.GetTransactionsResp;

/**
 * @Classname TronGridService
 * @Description
 * @Date 2021/5/26 18:23
 * @Created by zhiyuan
 */
public interface TronGridService {
    /**
     * 查询账号信息
     *
     * @param address
     */
    GetAccountResp getAccount(String address);

    /**
     * 查询交易信息
     *
     * @param address
     */
    GetTransactionsResp getTransactions(String address);
}
