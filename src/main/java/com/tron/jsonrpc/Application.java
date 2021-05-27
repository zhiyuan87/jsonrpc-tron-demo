package com.tron.jsonrpc;

import com.tron.jsonrpc.model.dto.trongrid.GetTransactionsResp;
import com.tron.jsonrpc.service.TronGridService;
import com.tron.jsonrpc.utils.gson.GsonUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.tomcat.util.buf.HexUtils;
import org.bitcoinj.core.Base58;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.params.MainNetParams;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.crypto.Credentials;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.function.Consumer;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Value("${server.port}")
    private int port;

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

//        System.setProperty("http.proxyHost", "192.168.1.12");
//        System.setProperty("http.proxyPort", "1080");
//        System.setProperty("https.proxyHost", "192.168.1.12");
//        System.setProperty("https.proxyPort", "1080");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    static byte[] addCheckHash(byte[] data) {
        try {
            SHA256.Digest digest = new SHA256.Digest();
            digest.update(data);
            byte[] hash0 = digest.digest();
            digest.reset();
            digest.update(hash0);
            byte[] hash = Arrays.copyOf(digest.digest(), 4);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            buf.write(data);
            buf.write(hash);
            return buf.toByteArray();
        } catch (IOException var5) {
            throw new AssertionError(var5);
        }
    }

    @Resource
    private TronGridService tronGridService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Spring Boot done and listen on address {} and port {}.", InetAddress.getLocalHost().getHostAddress(), port);
        var deterministicKey = DeterministicKey.deserializeB58("", MainNetParams.get());

        for (int i = 0; i < 1; i++) {
            var childKey = HDKeyDerivation.deriveChildKey(deterministicKey, i);
            logger.debug("privateKey = {}", childKey.getPrivateKeyAsHex());
            var credentials = Credentials.create(childKey.getPrivKey().toString(16));

            var address = Base58.encode(addCheckHash(HexUtils.fromHexString("41" + credentials.getAddress().substring(2))));
            logger.debug("address = {}", address);

            // 查询余额
//            var getAccountResp = tronGridService.getAccount(address);
//            if (getAccountResp != null && getAccountResp.getSuccess()) {
//                if (getAccountResp.getData() != null && getAccountResp.getData().size() > 0) {
//                    var balance = getAccountResp.getData().get(0).getBalance().divide(new BigDecimal("10").pow(6), 8, RoundingMode.DOWN);
//                    logger.debug("balance = {}", balance.toPlainString());
//                }
//            }


            var getTransactionsResp = tronGridService.getTransactions(address);
            logger.debug("getTransactionsResp = {}", ReflectionToStringBuilder.toString(getTransactionsResp, ToStringStyle.MULTI_LINE_STYLE));
            getTransactionsResp.getData().forEach(new Consumer<GetTransactionsResp.Transaction>() {
                @Override
                public void accept(GetTransactionsResp.Transaction transaction) {
                    logger.debug("getTransactionsResp = {}", GsonUtils.toJsonString(transaction));
                }
            });
        }
        System.exit(0);
    }
}
