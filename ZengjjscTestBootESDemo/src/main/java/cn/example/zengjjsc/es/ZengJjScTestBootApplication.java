package cn.example.zengjjsc.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
@SpringBootApplication
@Slf4j
public class ZengJjScTestBootApplication {

    public static void main(String[] args) {
        log.info("启动服务开始");
        SpringApplication.run(ZengJjScTestBootApplication.class, args);
        log.info("启动服务结束");
    }

}
