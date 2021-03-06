package cn.ptp.mapper;

import cn.ptp.Page;
import cn.ptp.entity.Message;
import cn.ptp.entity.MessageExample;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int countByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int deleteByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int insert(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int insertSelective(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    List<Message> selectByExampleWithBLOBs(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    List<Message> selectByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    Message selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    int updateByPrimaryKey(Message record);

    @Select("select * from message where name = #{name}")
    Message findByName(@Param("name") String name);

    List<Message> findAll();

    List<Message> findAllOrderByIdDesc();

    //--------- Paging
    @Select("select * from message LIMIT #{start},#{count}")
    List<Message> paging(@Param("start") int start, @Param("count") int count);
    @Select("select COUNT(id) count from message")
    double pageTotal();
    //--------
}