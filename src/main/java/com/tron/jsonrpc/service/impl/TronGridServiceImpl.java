package com.tron.jsonrpc.service.impl;

import com.tron.jsonrpc.model.dto.trongrid.GetAccountResp;
import com.tron.jsonrpc.model.dto.trongrid.GetTransactionsResp;
import com.tron.jsonrpc.service.TronGridService;
import com.tron.jsonrpc.utils.gson.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Classname TronGridServiceImpl
 * @Description
 * @Date 2021/5/26 18:24
 * @Created by zhiyuan
 */
@Service
public class TronGridServiceImpl implements TronGridService {
    private static final Logger logger = LoggerFactory.getLogger(TronGridServiceImpl.class);

    @Value("${trongrid.host}")
    private String host;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public GetAccountResp getAccount(String address) {
        synchronized (this) {
            try {
                var builder = UriComponentsBuilder.fromHttpUrl(host + "v1/accounts/" + address);
                logger.debug("getAccountReq = {}", builder.encode().build().toUri());

                Thread.sleep(100);
                var getAccountResp = restTemplate.getForObject(builder.encode().build().toUri(), GetAccountResp.class);
                logger.debug("getAccountResp = {}", GsonUtils.toJsonString(getAccountResp));
                return getAccountResp;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public GetTransactionsResp getTransactions(String address) {
        synchronized (this) {
            try {
                var builder = UriComponentsBuilder.fromHttpUrl(host + "v1/accounts/" + address + "/transactions")
                        .queryParam("only_to", true)
                        .queryParam("limit", 200)
                        .queryParam("min_timestamp", LocalDateTime.now().minus(Duration.ofDays(14)).toInstant(ZoneOffset.UTC).toEpochMilli())
                        .queryParam("max_timestamp", LocalDateTime.now().minus(Duration.ofHours(2)).toInstant(ZoneOffset.UTC).toEpochMilli());
                logger.debug("getTransactionsReq = {}", builder.encode().build().toUri());

                Thread.sleep(100);
                var getTransactionsResp = restTemplate.getForObject(builder.encode().build().toUri(), GetTransactionsResp.class);
                logger.debug("getTransactionsResp ={}", GsonUtils.toJsonString(getTransactionsResp));
                return getTransactionsResp;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}