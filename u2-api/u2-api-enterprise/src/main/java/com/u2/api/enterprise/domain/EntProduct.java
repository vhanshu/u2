package com.u2.api.enterprise.domain;

import com.u2.common.core.annotation.Excel;
import com.u2.common.core.annotation.Excels;
import com.u2.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品信息对象 ent_product
 *
 * @author ego
 * @date 2022-04-11
 */
public class EntProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品分类ID
     */
    private Long categoryId;

    /**
     * 所属品牌ID
     */
    private Long brandId;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String productName;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 商品图片
     */
    @Excel(name = "商品图片url")
    private String imageUrl;

    /**
     * 商品价格
     */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /**
     * 商品状态（0正常 1缺货 2打折 3活动 4进货）
     */
    @Excel(name = "商品状态", readConverterExp = "0=正常,1=缺货,2=打折,3=活动,4=进货")
    private String nature;

    /**
     * 商品库存
     */
    @Excel(name = "商品库存")
    private Integer stock;

    /**
     * 商品销量
     */
    @Excel(name = "商品销量")
    private Integer sellNum;

    /**
     * 商品浏览量
     */
    @Excel(name = "商品浏览量")
    private Integer viewNum;

    /**
     * 商品评论数
     */
    @Excel(name = "商品评论数")
    private Integer commentNum;

    /**
     * 商品详情页
     */
    private String content;

    /**
     * 商品分类对象
     */
    @Excels({
            @Excel(name = "分类名称", targetAttr = "name", type = Excel.Type.EXPORT),
            @Excel(name = "分类状态", targetAttr = "status", type = Excel.Type.EXPORT)
    })
    private EntCategory category;

    /**
     * 品牌对象
     */
    @Excels({
            @Excel(name = "品牌名称", targetAttr = "name", type = Excel.Type.EXPORT),
            @Excel(name = "品牌LOGO", targetAttr = "logo_url", type = Excel.Type.EXPORT)
    })
    private EntBrand brand;

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setCategoryId(Long productCategoryId) {
        this.categoryId = productCategoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNature() {
        return nature;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public EntCategory getCategory() {
        return category;
    }

    public void setCategory(EntCategory category) {
        this.category = category;
    }

    public EntBrand getBrand() {
        return brand;
    }

    public void setBrand(EntBrand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("productId", getProductId())
                .append("categoryId", getCategoryId())
                .append("name", getProductName())
                .append("ancestors", getAncestors())
                .append("imageUrl", getImageUrl())
                .append("price", getPrice())
                .append("nature", getNature())
                .append("stock", getStock())
                .append("sellNum", getSellNum())
                .append("viewNum", getViewNum())
                .append("commentNum", getCommentNum())
                .append("content", getContent())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("category", getCategory())
                .append("brand", getBrand())
                .toString();
    }
}
