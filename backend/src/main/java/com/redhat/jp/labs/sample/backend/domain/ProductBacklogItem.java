package com.redhat.jp.labs.sample.backend.domain;

import java.util.Arrays;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class ProductBacklogItem extends GenericDomain {

    @Id
    private Long itemId;

    @NotNull
    @Size(min = 1)
    private String name;

    private String label;

    private String description;

    private String storyPoint;

    private String memo;

    private byte[] attachment;

    public ProductBacklogItem() {
    }

    public ProductBacklogItem(String name, String label, String description, String storyPoint, String memo, byte[] attachment) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.storyPoint = storyPoint;
        this.memo = memo;
        this.attachment = attachment;
    }

    public ProductBacklogItem(long itemId, String name, String label, String description, String storyPoint, String memo, byte[] attachment) {
        this.itemId = itemId;
        this.name = name;
        this.label = label;
        this.description = description;
        this.storyPoint = storyPoint;
        this.memo = memo;
        this.attachment = attachment;
    }

    @Override
    protected Object keyObject() {
        return getItemId();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(String storyPoint) {
        this.storyPoint = storyPoint;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductBacklogItem that = (ProductBacklogItem) o;
        return itemId.equals(that.itemId) && name.equals(that.name) && Objects.equals(label, that.label) && Objects.equals(description, that.description) && Objects.equals(storyPoint, that.storyPoint) && Objects.equals(memo, that.memo) && Arrays.equals(attachment, that.attachment);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), itemId, name, label, description, storyPoint, memo);
        result = 31 * result + Arrays.hashCode(attachment);
        return result;
    }
}