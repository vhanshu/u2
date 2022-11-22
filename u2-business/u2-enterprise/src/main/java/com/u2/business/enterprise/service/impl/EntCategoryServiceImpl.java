package com.u2.business.enterprise.service.impl;

import com.u2.api.enterprise.domain.EntCategory;
import com.u2.api.enterprise.domain.EntProduct;
import com.u2.business.enterprise.dao.EntCategoryMapper;
import com.u2.business.enterprise.dao.EntProductMapper;
import com.u2.business.enterprise.service.EntCategoryService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.DateUtils;
import com.u2.common.core.utils.SpringUtils;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.core.web.domain.TreeSelect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品分类Service业务层处理
 *
 * @author ego
 * @date 2022-04-12
 */
@Service
public class EntCategoryServiceImpl implements EntCategoryService {
    @Resource
    private EntCategoryMapper entCategoryMapper;

    private EntProductMapper entProductMapper;

    @Override
    public EntCategory selectCategoryById(Long id) {
        return entCategoryMapper.selectCategoryById(id);
    }

    @Override
    public List<EntCategory> selectCategoryList(EntCategory entCategory) {
        return entCategoryMapper.selectCategoryList(entCategory);
    }

    @Override
    public List<EntCategory> selectChildrenCategoryById(Long id){
        return entCategoryMapper.selectChildrenCategoryById(id);
    }

    @Override
    public int selectNormalChildrenCategoryById(Long id) {
        return entCategoryMapper.selectNormalChildrenCategoryById(id);
    }

    @Override
    public boolean hasChildByCategoryId(Long id) {
        int res = entCategoryMapper.hasChildByCategoryId(id);
        return res > 0;
    }

    @Override
    public boolean checkCategoryExistProduct(Long id) {
        int res = entCategoryMapper.checkCategoryExistProduct(id);
        return res > 0;
    }

    @Override
    public String checkCategoryNameUnique(EntCategory category) {
        long categoryId = StringUtils.isNull(category.getId()) ? -1L : category.getId();
        EntCategory info = entCategoryMapper.checkCategoryNameUnique(category.getName(), category.getParentId());
        if (StringUtils.isNotNull(info) && info.getId() != categoryId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertCategory(EntCategory entCategory) {
        entCategory.setCreateTime(DateUtils.getNowDate());
        return entCategoryMapper.insertCategory(entCategory);
    }

    @Override
    public int updateCategory(EntCategory entCategory) {
        entCategory.setUpdateTime(DateUtils.getNowDate());
        return entCategoryMapper.updateCategory(entCategory);
    }

    @Override
    public void updateCategoryStatusNormal(Long[] ids) {
        entCategoryMapper.updateCategoryStatusNormal(ids);
    }

    @Override
    public int updateCategoryChildren(List<EntCategory> categorys) {
        return entCategoryMapper.updateCategoryChildren(categorys);
    }

    @Override
    public int deleteCategoryById(Long id) {
        return entCategoryMapper.deleteCategoryById(id);
    }

    @Override
    public List<EntProduct> selectProductList(Long categoryId) {
        return entProductMapper.selectProductListByCategoryId(categoryId);
    }

    @Override
    public List<TreeSelect<EntCategory>> selectCategoryTreeList(EntCategory category) {
        List<EntCategory> categories = SpringUtils.getAopProxy(this).selectCategoryList(category);
        return buildCategoryTreeSelect(categories);
    }

    @Override
    public List<EntCategory> buildCategoryTree(List<EntCategory> categorys) {
        List<EntCategory> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<>();
        for (EntCategory category : categorys) {
            tempList.add(category.getId());
        }
        for (EntCategory category : categorys) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(category.getParentId())) {
                recursionFn(categorys, category);
                returnList.add(category);
            }
        }
        if (returnList.isEmpty()) {
            returnList = categorys;
        }
        return returnList;
    }

    @Override
    public List<TreeSelect<EntCategory>> buildCategoryTreeSelect(List<EntCategory> categorys) {
        List<EntCategory> categoryTrees = buildCategoryTree(categorys);
        return categoryTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<EntCategory> list, EntCategory t) {
        // 得到子节点列表
        List<EntCategory> childList = getChildList(list, t);
        t.setChildren(childList);
        for (EntCategory tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<EntCategory> getChildList(List<EntCategory> list, EntCategory t) {
        List<EntCategory> tlist = new ArrayList<>();
        for (EntCategory n : list) {
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<EntCategory> list, EntCategory t) {
        return getChildList(list, t).size() > 0;
    }

}
