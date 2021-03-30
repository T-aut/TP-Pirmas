package com.example.TP_Pirmas.mybatis.dao;

import com.example.TP_Pirmas.mybatis.model.Project;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROJECT
     *
     * @mbg.generated Tue Mar 30 19:53:55 EEST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROJECT
     *
     * @mbg.generated Tue Mar 30 19:53:55 EEST 2021
     */
    int insert(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROJECT
     *
     * @mbg.generated Tue Mar 30 19:53:55 EEST 2021
     */
    Project selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROJECT
     *
     * @mbg.generated Tue Mar 30 19:53:55 EEST 2021
     */
    List<Project> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROJECT
     *
     * @mbg.generated Tue Mar 30 19:53:55 EEST 2021
     */
    int updateByPrimaryKey(Project record);
}