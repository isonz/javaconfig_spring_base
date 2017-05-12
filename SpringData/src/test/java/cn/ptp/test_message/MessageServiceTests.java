package cn.ptp.test_message;

import cn.ptp.entity.Message;
import cn.ptp.repository.MessageRepository;
import cn.ptp.service.MessageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessageServiceTests
{
    @Autowired
    private MessageService service;

    @MockBean
    private MessageRepository repository;


    @Test
    public void paged() throws Exception{
        Page<Message> page = service.paged(new PageRequest(0, 20));
        given(page.getTotalElements()).willReturn(anyLong());
    }

    @Test
    public void findAll() throws Exception{
        Iterable<Message> it = repository.findAll();
        for(Message i : it){
            Assert.assertNotEquals(null, i.getId());
            Assert.assertNotEquals(null, i.getName());
            Assert.assertTrue(i.getId()> 0);
        }
    }


}
