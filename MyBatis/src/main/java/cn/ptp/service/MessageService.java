package cn.ptp.service;

import cn.ptp.entity.Message;
import cn.ptp.mapper.MessageMapper;
import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService
{
    private final MessageMapper messageMapper;

    public Message findOne(int id)
    {
        Message message = messageMapper.selectByPrimaryKey(id);
        return message;
    }

    public List<Message> findAll()
    {
        return messageMapper.findAll();
    }

    public List<Message> findAllOrderByIdDesc()
    {
        return messageMapper.findAllOrderByIdDesc();
    }

}
