package com.redhat.jp.labs.sample.backend.repository;

import com.redhat.jp.labs.sample.backend.domain.ProductBacklogItem;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductBacklogItemRepository extends CrudRepository<ProductBacklogItem, Long> {

    @Query("select * from prodcut_backlog_item i " +
            "inner join product_backlog_order o on i.itemId = o.itemId " +
            "limit :count offset :start " +
            "order by o.index")
    public List<ProductBacklogItem> find(long limit, long offset);
}