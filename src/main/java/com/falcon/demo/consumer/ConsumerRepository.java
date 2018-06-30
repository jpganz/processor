package com.falcon.demo.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Message, Long>{
}
