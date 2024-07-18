package cn.example.zengjjsc.es.dto;

import lombok.Data;

import java.util.Date;

@Data
/***
 * 知识点主表
 */
public class KbsPointEsModel {


    /**
     * 主键
     */
    private String pointId;


    /**
     * 标题
     */
    private String title;

    /**
     * 机构id
     */
    private String organId;

    /**
     * 机构名称
     */
    private String organName;

    /**
     * 创建人代码
     */
    private String createCode;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 所属目录
     */
    private String directoryId;

    /**
     * 内容,纯文本
     */
    private String content;

    /**
     * 内容，包含格式
     */
    private String contentHtml;

    /**
     * 点击量
     */
    private Integer pageView;

    /**
     * 重要度
     */
    private String priority;

    /**
     * 关键字
     */
    private String keyWords;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0-暂存，1-待审核，2-审核通过，3-审核不通过
     */
    private String status;

    /**
     * 有效期起
     */
    private Date validStartDate;

    /**
     * 有效期止
     */
    private Date validEndDate;

    /**
     * 审核意见
     */
    private String reviewReason;


}
