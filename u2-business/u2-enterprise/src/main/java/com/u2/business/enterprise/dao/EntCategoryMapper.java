package com.u2.business.enterprise.dao;

import com.u2.api.enterprise.domain.EntCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类Mapper接口
 *
 * @author ego
 * @date 2022-04-12
 */
public interface EntCategoryMapper {
    /**
     * 查询商品分类管理数据
     *
     * @param category 商品分类信息
     * @return 商品分类信息集合
     */
    List<EntCategory> selectCategoryList(EntCategory category);

    /**
     * 根据商品分类ID查询信息
     *
     * @param id 商品分类ID
     * @return 商品分类信息
     */
    EntCategory selectCategoryById(Long id);

    /**
     * 根据ID查询所有子商品分类
     *
     * @param id 商品分类ID
     * @return 商品分类列表
     */
    List<EntCategory> selectChildrenCategoryById(Long id);

    /**
     * 根据ID查询所有子商品分类数（正常状态）
     *
     * @param id 商品分类ID
     * @return 子商品分类数
     */
    int selectNormalChildrenCategoryById(Long id);

    /**
     * 是否存在子节点
     *
     * @param id 商品分类ID
     * @return 结果
     */
    int hasChildByCategoryId(Long id);

    /**
     * 查询商品分类下是否存在商品
     *
     * @param id 商品分类ID
     * @return 结果
     */
    int checkCategoryExistProduct(Long id);

    /**
     * 校验商品分类名称是否唯一
     *
     * @param name 商品分类名称
     * @param parentId 父商品分类ID
     * @return 结果
     */
    EntCategory checkCategoryNameUnique(@Param("name") String name, @Param("parentId") Long parentId);

    /**
     * 新增商品分类信息
     *
     * @param category 商品分类信息
     * @return 结果
     */
    int insertCategory(EntCategory category);

    /**
     * 修改商品分类信息
     *
     * @param category 商品分类信息
     * @return 结果
     */
    int updateCategory(EntCategory category);

    /**
     * 修改商品分类为正常状态
     *
     * @param ids 商品分类ID组
     */
    void updateCategoryStatusNormal(Long[] ids);

    /**
     * 修改子元素关系
     *
     * @param categorys 子元素
     * @return 结果
     */
    int updateCategoryChildren(@Param("categorys") List<EntCategory> categorys);

    /**
     * 删除商品分类管理信息
     *
     * @param id 商品分类ID
     * @return 结果
     */
    int deleteCategoryById(Long id);
}
