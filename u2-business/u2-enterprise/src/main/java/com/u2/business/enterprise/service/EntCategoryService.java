package com.u2.business.enterprise.service;

import com.u2.api.enterprise.domain.EntCategory;
import com.u2.api.enterprise.domain.EntProduct;
import com.u2.common.core.web.domain.TreeSelect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类Service接口
 *
 * @author ego
 * @date 2022-04-12
 */
public interface EntCategoryService {
    /**
     * 查询商品分类
     *
     * @param id 商品分类主键
     * @return 商品分类
     */
    EntCategory selectCategoryById(Long id);

    /**
     * 查询商品分类列表
     *
     * @param entCategory 商品分类
     * @return 商品分类集合
     */
    List<EntCategory> selectCategoryList(EntCategory entCategory);

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
    boolean hasChildByCategoryId(Long id);

    /**
     * 查询商品分类下是否存在商品
     *
     * @param id 商品分类ID
     * @return 结果
     */
    boolean checkCategoryExistProduct(Long id);

    /**
     * 校验商品分类名称是否唯一
     *
     * @param category 商品分类信息
     * @return 结果
     */
    String checkCategoryNameUnique(EntCategory category);

    /**
     * 新增商品分类
     *
     * @param entCategory 商品分类
     * @return 结果
     */
    int insertCategory(EntCategory entCategory);

    /**
     * 修改商品分类
     *
     * @param entCategory 商品分类
     * @return 结果
     */
    int updateCategory(EntCategory entCategory);

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
     * 删除商品分类信息
     *
     * @param id 商品分类主键
     * @return 结果
     */
    int deleteCategoryById(Long id);

    /**
     * 查询商品分类树结构信息
     *
     * @param category 商品分类信息
     * @return 部门树信息集合
     */
    List<TreeSelect<EntCategory>> selectCategoryTreeList(EntCategory category);

    /**
     * 构建前端所需要树结构
     *
     * @param category 菜单列表
     * @return 树结构列表
     */
    List<EntCategory> buildCategoryTree(List<EntCategory> category);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param categorys 菜单列表
     * @return 下拉树结构列表
     */
    List<TreeSelect<EntCategory>> buildCategoryTreeSelect(List<EntCategory> categorys);

    /**
     * 通过商品类别查询商品列表
     *
     * @param categoryId 类别ID
     * @return 商品对象列表
     */
    List<EntProduct> selectProductList(Long categoryId);
}
