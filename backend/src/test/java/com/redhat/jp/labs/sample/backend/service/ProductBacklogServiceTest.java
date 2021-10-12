package com.redhat.jp.labs.sample.backend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.redhat.jp.labs.sample.backend.domain.ProductBacklogItem;
import com.redhat.jp.labs.sample.backend.domain.ProductBacklogOrder;
import com.redhat.jp.labs.sample.backend.repository.ProductBacklogItemRepository;

import com.redhat.jp.labs.sample.backend.repository.ProductBacklogOrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductBacklogServiceTest {

    @Mock
    private ProductBacklogItemRepository productBacklogItemRepository;
    @Mock
    private ProductBacklogOrderRepository productBacklogOrderRepository;

    @InjectMocks
    private ProductBacklogService productBacklogService;

    @Test
    public void getBacklog() {
        ProductBacklogItem productBacklogItem1 = new ProductBacklogItem(1L, "title1", "status1", "desc1");
        ProductBacklogItem productBacklogItem2 = new ProductBacklogItem(2L, "title2", "status2", "desc2");
        List<ProductBacklogItem> list = Lists.newArrayList(productBacklogItem1, productBacklogItem2);

        when(productBacklogItemRepository.find(100, 200)).thenReturn(list);

        assertThat(productBacklogService.getBacklog(100, 200)).contains(productBacklogItem1, productBacklogItem2);
    }

    @Test
    public void getTodo() {
        long id = 1L;
        ProductBacklogItem productBacklogItem = new ProductBacklogItem(id, "title1", "status1", "desc1");

        when(productBacklogItemRepository.findById(id)).thenReturn(Optional.of(productBacklogItem));

        assertThat(productBacklogService.getBacklogItem(id)).isEqualTo(productBacklogItem);
    }

    @Test
    public void getBacklogItemIfNotPresent() {
        long id = 1L;
        Optional<ProductBacklogItem> backlogItem = Optional.empty();

        when(productBacklogItemRepository.findById(id)).thenReturn(backlogItem);

        assertThat(productBacklogService.getBacklogItem(id)).isNull();
    }

    @Test
    public void saveBacklogItem() {
        ProductBacklogItem productBacklogItem = new ProductBacklogItem(null, null, null);

        ArgumentCaptor<ProductBacklogItem> captor = ArgumentCaptor.forClass(ProductBacklogItem.class);
        when(productBacklogItemRepository.save(captor.capture())).thenReturn(productBacklogItem);

        ProductBacklogItem saved = productBacklogService.saveBacklogItem(productBacklogItem);

        assertThat(captor.getValue()).isEqualTo(productBacklogItem);
        assertThat(saved).isEqualTo(productBacklogItem);
    }

    @Test
    public void saveBacklogOrder() {
        ProductBacklogOrder productBacklogOrder = new ProductBacklogOrder(1L, 2L, 3L);

        ArgumentCaptor<ProductBacklogOrder> captor = ArgumentCaptor.forClass(ProductBacklogOrder.class);
        when(productBacklogOrderRepository.save(captor.capture())).thenReturn(productBacklogOrder);

        ProductBacklogOrder saved = productBacklogService.saveBacklogOrder(productBacklogOrder);

        assertThat(captor.getValue()).isEqualTo(productBacklogOrder);
        assertThat(saved).isEqualTo(productBacklogOrder);
    }

    @Test
    public void deleteBacklogItem() {
        long id = 1L;

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        doNothing().when(productBacklogItemRepository).deleteById(captor.capture());

        productBacklogService.deleteBacklogItem(id);

        assertThat(captor.getValue()).isEqualTo(id);
    }

}