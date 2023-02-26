package com.github.wujie0628.app.search.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaInitialConfiguration {

    public static final String TOPPIC_NAME = "poetry_search_info";

    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
    // 如果要修改分区数，只需修改配置值重启项目即可
    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic(TOPPIC_NAME,1, (short) 1 );
    }

}