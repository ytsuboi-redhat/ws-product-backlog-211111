package com.redhat.jp.labs.sample.backend.service;

import java.util.List;
import java.util.Optional;

import com.redhat.jp.labs.sample.backend.domain.ProductBacklogItem;
import com.redhat.jp.labs.sample.backend.domain.ProductBacklogOrder;
import com.redhat.jp.labs.sample.backend.repository.ProductBacklogItemRepository;

import com.redhat.jp.labs.sample.backend.repository.ProductBacklogOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBacklogService {

    @Autowired
    ProductBacklogItemRepository productBacklogItemRepository;

    @Autowired
    ProductBacklogOrderRepository productBacklogOrderRepository;

    public List<ProductBacklogItem> getBacklog(long limit, long offset) {
        return productBacklogItemRepository.find(limit, offset);
    }

    public ProductBacklogItem getBacklogItem(long itemId) {
        Optional<ProductBacklogItem> item = productBacklogItemRepository.findById(itemId);
        if (item.isPresent()) {
            return item.get();
        }
        return null;
    }

    public ProductBacklogItem saveBacklogItem(ProductBacklogItem productBacklogItem) {
        return productBacklogItemRepository.save(productBacklogItem);
    }

    public ProductBacklogOrder saveBacklogOrder(ProductBacklogOrder productBacklogOrder) {
        return productBacklogOrderRepository.save(productBacklogOrder);
    }

    public void deleteBacklogItem(long itemId) {
        productBacklogItemRepository.deleteById(itemId);
    }
}