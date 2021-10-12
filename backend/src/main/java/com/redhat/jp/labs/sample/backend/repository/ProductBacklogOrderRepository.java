package com.redhat.jp.labs.sample.backend.repository;

import com.redhat.jp.labs.sample.backend.domain.ProductBacklogOrder;
import org.springframework.data.repository.CrudRepository;

public interface ProductBacklogOrderRepository extends CrudRepository<ProductBacklogOrder, Long> {
}
