package com.redhat.jp.labs.sample.backend.service;

import java.util.Arrays;
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

    private static final String[] VALID_STORY_POINTS = new String[] { "1", "2", "3", "5", "8", "13", "21", "?" };

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

    public void validateStoryPoint(ProductBacklogItem productBacklogItem) {
        if (!Arrays.asList(VALID_STORY_POINTS).contains(productBacklogItem.getStoryPoint())) {
            throw new ProductBacklogItemValidationException();
        }
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