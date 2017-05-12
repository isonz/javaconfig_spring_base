package cn.ptp.test_message;

import cn.ptp.entity.Message;
import cn.ptp.repository.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.*;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessageRepositoryTests
{
    @Autowired
    private MessageRepository messageRepository;

    private Message message = new Message();

    @Test
    @Rollback
    public void testCurd() throws Exception
    {
        // insert一条数据，并select出来验证
        message.setName("AAA");
        message.setMsg("BBBBBBB");
        messageRepository.save(message);
        Message m = messageRepository.findName("AAA");
        Assert.assertEquals("BBBBBBB", m.getMsg());

        // update一条数据，并select出来验证
        m.setMsg("CCCCCCCCCC");
        messageRepository.save(m);
        m = messageRepository.findName("AAA");
        Assert.assertEquals("CCCCCCCCCC", m.getMsg());

        // 删除这条数据，并select验证
        messageRepository.delete(m.getId());
        m = messageRepository.findName("AAA");
        Assert.assertEquals(null, m);

        // 分页查询
        Pageable pageable = new PageRequest(0, 20);
        Page<Message> page = this.messageRepository.findAll(pageable);
        List<Message> items = page.getContent();
        for(Message item : items){
            Assert.assertNotEquals(null, item.getId());
            Assert.assertNotEquals(null, item.getName());
        }
        Assert.assertTrue(page.getTotalElements() > 0);
    }


    @Test
    public void dynamicUpdate()
    {
        message.setName("aaaa");
        message.setMsg("bbbbb");
        message.setId(125l);
        messageRepository.save(message);

        message = messageRepository.findOne(125l);
        System.out.println(message.getCreateAt());
        System.out.println(message.getUpdateAt());
    }


    /**
     * Demo1: 通过Java反射机制得到类的包名和类名
     */
    @Test
    public void Demo1()
    {
        System.out.println("Demo1: 包名: " + message.getClass().getPackage().getName() + "，" + "完整类名: " + message.getClass().getName());
    }

    /**
     * Demo2: 验证所有的类都是Class类的实例对象
     * @throws ClassNotFoundException
     */
    @Test
    public void Demo2() throws ClassNotFoundException
    {
        //定义两个类型都未知的Class , 设置初值为null, 看看如何给它们赋值成Person类
        Class<?> class1 = null;
        Class<?> class2 = null;

        //写法1, 可能抛出 ClassNotFoundException [多用这个写法]
        class1 = Class.forName("cn.ptp.entity.Message");
        System.out.println("Demo2:(写法1) 包名: " + class1.getPackage().getName() + "，"
                + "完整类名: " + class1.getName());

        //写法2
        class2 = Message.class;
        System.out.println("Demo2:(写法2) 包名: " + class2.getPackage().getName() + "，"
                + "完整类名: " + class2.getName());
    }

    /**
     * Demo3: 通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void Demo3() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Class<?> class1 = null;
        class1 = Class.forName("cn.ptp.entity.Message");
        //由于这里不能带参数，所以你要实例化的这个类Person，一定要有无参构造函数哈～
        Message message = (Message) class1.newInstance();
        message.setCreateAt(20000);
        message.setName("LeeFeng");
        System.out.println("Demo3: " + message.getName() + " : " + message.getCreateAt());
    }


    /**
     * Demo4: 通过Java反射机制得到一个类的构造函数，并实现创建带参实例对象
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IllegalArgumentException
     */
    @Test
    public void Demo4() throws ClassNotFoundException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Class<?> class1 = null;
        Message message1 = null;
        Message message2 = null;

        class1 = Class.forName("cn.ptp.entity.Message");
        //得到一系列构造函数集合
        Constructor<?>[] constructors = class1.getConstructors();

        message1 = (Message) constructors[0].newInstance();
        message1.setCreateAt(20000);
        message1.setName("LeeFeng");

        message2 = (Message) constructors[1].newInstance(20,"leeFeng");

        System.out.println("Demo4: " + message1.getName() + " : " + message1.getCreateAt()
                + "  ,   " + message2.getName() + " : " + message2.getCreateAt()
        );

    }


    /**
     * Demo5: 通过Java反射机制操作成员变量, set 和 get
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    @Test
    public void Demo5() throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException, InstantiationException, ClassNotFoundException
    {
        Class<?> class1 = null;
        class1 = Class.forName("cn.ptp.entity.Message");
        Object obj = class1.newInstance();

        Field personNameField = class1.getDeclaredField("name");
        personNameField.setAccessible(true);
        personNameField.set(obj, "胖虎先森");

        Field messageMsgField = class1.getDeclaredField("msg");
        messageMsgField.setAccessible(true);
        messageMsgField.set(obj, "胖虎先森hahaha");

        System.out.println("Demo5: 修改属性之后得到属性变量的值：" + personNameField.get(obj));
        System.out.println("Demo5: 修改属性之后得到属性变量的值：" + messageMsgField.get(obj));

    }


    /**
     * Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
     * @throws ClassNotFoundException
     */
    @Test
    public void Demo6() throws ClassNotFoundException
    {
        Class<?> class1 = null;
        class1 = Class.forName("cn.ptp.entity.Message");

        //取得父类名称
        Class<?>  superClass = class1.getSuperclass();
        System.out.println("Demo6:  SuperMan类的父类名: " + superClass.getName());

        System.out.println("===============================================");


        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("类中的成员: " + fields[i]);
        }
        System.out.println("===============================================");


        //取得类方法
        Method[] methods = class1.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("Demo6,取得SuperMan类的方法：");
            System.out.println("函数名：" + methods[i].getName());
            System.out.println("函数返回类型：" + methods[i].getReturnType());
            System.out.println("函数访问修饰符：" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("函数代码写法： " + methods[i]);
        }

        System.out.println("===============================================");

        //取得类实现的接口,因为接口类也属于Class,所以得到接口中的方法也是一样的方法得到哈
        Class<?> interfaces[] = class1.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("实现的接口类名: " + interfaces[i].getName() );
        }

    }


    /**
     * Demo7: 通过Java反射机制调用类方法
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     */
    @Test
    public void Demo7() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        Class<?> class1 = null;
        class1 = Class.forName("cn.ptp.entity.Message");

        System.out.println("Demo7: \n调用无参方法hashCode()：");
        Method method = class1.getMethod("hashCode");
        method.invoke(class1.newInstance());

        System.out.println("调用有参方法setCreateAt(int m)：");
        method = class1.getMethod("setCreateAt",int.class);
        method.invoke(class1.newInstance(),100);
    }


    /**
     * Demo8: 通过Java反射机制得到类加载器信息
     *
     * 在java中有三种类类加载器。[这段资料网上截取]

     1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。

     2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类

     3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void Demo8() throws ClassNotFoundException
    {
        Class<?> class1 = null;
        class1 = Class.forName("cn.ptp.entity.Message");
        String nameString = class1.getClassLoader().getClass().getName();

        System.out.println("Demo8: 类加载器类名: " + nameString);
    }


}
