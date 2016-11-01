package cn.ptp.service;

import cn.ptp.Page;
import cn.ptp.entity.Message;
import cn.ptp.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


@Service
@Transactional(readOnly = true)
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

    public Page<Message> paging(int start, int count)
    {
        if(start < 1) start = 0;
        if(count < 1) count = 1;
        double total = messageMapper.pageTotal();
        if(count > total) count = (int)total;
        double pages = Math.ceil(total/count);
        if(start+count > total) start = ((int)pages-1)+count;
        if(start+count <1 ) start = 0;
        List<Message> list = messageMapper.paging(start, count);
        Page<Message> page = new Page<Message>(list);
        page.setTotal(total);
        page.setPages(pages);
        return page;
    }

    public Message findByName(String name)
    {
        Assert.hasText(name, "Name must not be empty!");
        return messageMapper.findByName(name);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Message save(Message message)
    {
        Assert.hasText(message.getMsg(), "Mst must not be empty!");
        Message mesg = findByName(message.getName());
        if(null != mesg && mesg.getId() != message.getId()){
            throw new IllegalArgumentException("Name 重复!");
        }

        int id;
        try {
            id = message.getId();
            messageMapper.updateByPrimaryKeySelective(message);
        }catch (NullPointerException e){
            id = messageMapper.insert(message);       //插入的条数
        }

        System.out.println(message.getId());      //获取插入的ID
        //messageMapper.deleteByPrimaryKey(id); //测试Transactional

        if(id > 0) return message;
        return null;
    }

    @Transactional(readOnly = false)
    public boolean delete(int id)
    {
        Message mess = messageMapper.selectByPrimaryKey(id);
        if(null == mess){
            //throw new IllegalArgumentException("ID: "+id+" is not exists!");
            return false;
        }
        int status = messageMapper.deleteByPrimaryKey(id);
        if(status>0) return true;
        return false;
    }

}
