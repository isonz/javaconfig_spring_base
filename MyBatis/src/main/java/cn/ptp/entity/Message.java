package cn.ptp.entity;

import java.util.Date;
import java.util.List;

public class Message {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.id
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.create_at
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    private Integer create_at;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.date
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.days
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    private Float days;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.name
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.update_at
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    private Date update_at;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.ip
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.msg
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    private String msg;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.id
     *
     * @return the value of message.id
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.id
     *
     * @param id the value for message.id
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.create_at
     *
     * @return the value of message.create_at
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public Integer getCreate_at() {
        return create_at;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.create_at
     *
     * @param create_at the value for message.create_at
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public void setCreate_at(Integer create_at) {
        this.create_at = create_at;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.date
     *
     * @return the value of message.date
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.date
     *
     * @param date the value for message.date
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.days
     *
     * @return the value of message.days
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public Float getDays() {
        return days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.days
     *
     * @param days the value for message.days
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public void setDays(Float days) {
        this.days = days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.name
     *
     * @return the value of message.name
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.name
     *
     * @param name the value for message.name
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.update_at
     *
     * @return the value of message.update_at
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public Date getUpdate_at() {
        return update_at;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.update_at
     *
     * @param update_at the value for message.update_at
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.ip
     *
     * @return the value of message.ip
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.ip
     *
     * @param ip the value for message.ip
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.msg
     *
     * @return the value of message.msg
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public String getMsg() {
        return msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.msg
     *
     * @param msg the value for message.msg
     *
     * @mbggenerated Thu Oct 27 15:00:23 CST 2016
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }


}