package com.redhat.jp.labs.sample.backend.service;

import com.redhat.jp.labs.sample.backend.repository.ProductBacklogOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductBacklogOrderService {

    @Autowired
    ProductBacklogOrderRepository repository;


}
