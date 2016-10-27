package cn.ptp.service;

import cn.ptp.entity.Message;
import cn.ptp.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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




}
