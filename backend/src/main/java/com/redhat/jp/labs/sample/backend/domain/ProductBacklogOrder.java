package com.redhat.jp.labs.sample.backend.domain;

import org.springframework.data.annotation.Id;

public class ProductBacklogOrder extends GenericDomain {

    @Id
    private Long orderId;

    private Long index;

    private Long itemId;

    public ProductBacklogOrder(Long orderId, Long index, Long itemId) {
        this.orderId = orderId;
        this.index = index;
        this.itemId = itemId;
    }

    @Override
    protected Object keyObject() {
        return this.orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
