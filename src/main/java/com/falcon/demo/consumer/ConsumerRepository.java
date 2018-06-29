package com.falcon.demo.consumer;

import org.springframework.data.jpa.repository.JpaRepository;
import sun.plugin2.message.Message;

public interface ConsumerRepository extends JpaRepository<Message, Long>{
}
