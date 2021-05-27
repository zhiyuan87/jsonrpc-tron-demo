package com.tron.jsonrpc.model.dto.trongrid;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname GetTransactionsResp
 * @Description
 * @Date 2021/5/27 12:19
 * @Created by zhiyuan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetTransactionsResp extends BaseResp {
    @Serial
    private static final long serialVersionUID = 2154664055053523534L;

    private List<Transaction> data;
    private Meta meta;

    @Data
    public static class Transaction implements Serializable {
        @Serial
        private static final long serialVersionUID = 3981271100593594626L;

        private String txID;
        private BigDecimal net_fee;
        private BigDecimal energy_fee;
        private Integer blockNumber;
        private RawData raw_data;

        @Data
        public static class RawData implements Serializable {
            @Serial
            private static final long serialVersionUID = 3286451837556134467L;

            private List<Contract> contract;

            @Data
            public static class Contract implements Serializable {
                @Serial
                private static final long serialVersionUID = 3286451837556134467L;

                private String type;
                private Parameter parameter;

                @Data
                public static class Parameter implements Serializable {
                    @Serial
                    private static final long serialVersionUID = 3695250397442090524L;

                    private Value value;

                    @Data
                    public static class Value implements Serializable {
                        @Serial
                        private static final long serialVersionUID = -5721335440302243030L;

                        private BigDecimal amount;
                        private String to_address;
                        private String owner_address;
                    }
                }
            }
        }
    }

    @Data
    public static class Meta implements Serializable {
        @Serial
        private static final long serialVersionUID = 965220401006084202L;

        private Long at;
        private Integer page_size;
        private String fingerprint;
    }
}