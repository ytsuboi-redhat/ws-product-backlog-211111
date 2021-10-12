package com.redhat.jp.labs.sample.backend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
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
        ProductBacklogItem productBacklogItem1 = new ProductBacklogItem(1L, "name1", "label1", "desc1", "1", "memo1", new byte[1]);
        ProductBacklogItem productBacklogItem2 = new ProductBacklogItem(2L, "name2", "label2", "desc2", "2", "memo2", new byte[2]);
        List<ProductBacklogItem> list = Lists.newArrayList(productBacklogItem1, productBacklogItem2);

        when(productBacklogItemRepository.find(100, 200)).thenReturn(list);

        assertThat(productBacklogService.getBacklog(100, 200)).contains(productBacklogItem1, productBacklogItem2);
    }

    @Test
    public void getBacklogItem() {
        long id = 1L;
        ProductBacklogItem productBacklogItem = new ProductBacklogItem(id, "name1", "label1", "desc1", "1", "memo1", new byte[1]);

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
    public void validateStoryPoint() {
        String[] validPoints = new String[] { "1", "2", "3", "5", "8", "13", "21", "?" };
        for (String point : validPoints) {
            ProductBacklogItem productBacklogItem = new ProductBacklogItem(1L, "name1", "label1", "desc1", point, "memo1", new byte[1]);
            productBacklogService.validateStoryPoint(productBacklogItem);
        }
    }

    @Test
    public void validateStoryPointNotValid() {
        String[] invalidPoints = new String[] { "-1", "0", "4", "6", "7", "9", "10", "11", "12", "14", "15", "16", "17", "18", "19", "20", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34" };
        for (String point : invalidPoints) {
            ProductBacklogItem productBacklogItem = new ProductBacklogItem(1L, "name1", "label1", "desc1", point, "memo1", new byte[1]);
            try {
                productBacklogService.validateStoryPoint(productBacklogItem);
                fail(String.format("story point: %s is invalid but exception is not thrown.", point));
            } catch (ProductBacklogItemValidationException e) {
            }
        }
    }

    @Test
    public void saveBacklogItem() {
        ProductBacklogItem productBacklogItem = new ProductBacklogItem(1L, "name1", "label1", "desc1", "1", "memo1", new byte[1]);

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