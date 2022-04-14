package com.redhat.jp.labs.sample.backend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import com.google.common.collect.Lists;
import com.redhat.jp.labs.sample.backend.domain.ProductBacklogItem;
import com.redhat.jp.labs.sample.backend.repository.ProductBacklogItemRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductBacklogServiceTest {

    @Mock
    private ProductBacklogItemRepository productBacklogItemRepository;

    @InjectMocks
    private ProductBacklogService productBacklogService;

    @Test
    public void getBacklog() {
        ProductBacklogItem productBacklogItem1 = new ProductBacklogItem(1L, "name1", "desc1", "1", "memo1");
        ProductBacklogItem productBacklogItem2 = new ProductBacklogItem(2L, "name2", "desc2", "2", "memo2");
        List<ProductBacklogItem> list = Lists.newArrayList(productBacklogItem1, productBacklogItem2);

        when(productBacklogItemRepository.find(100, 200)).thenReturn(list);

        assertThat(productBacklogService.getBacklog(100, 200)).contains(productBacklogItem1, productBacklogItem2);
    }

}