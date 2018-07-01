package com.falcon.demo.consumer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Message, Long> {
}
