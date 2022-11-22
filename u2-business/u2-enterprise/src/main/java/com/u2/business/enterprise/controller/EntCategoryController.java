package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntCategory;
import com.u2.business.enterprise.service.EntCategoryService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.poi.ExcelUtil;
import com.u2.common.core.web.controller.BaseController;
import com.u2.common.core.web.domain.model.AjaxResult;
import com.u2.common.log.annotation.Log;
import com.u2.common.log.enums.BusinessType;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品分类Controller
 *
 * @author ego
 * @date 2022-04-12
 */
@RestController
@RequestMapping("/category")
public class EntCategoryController extends BaseController {
    @Resource
    private EntCategoryService entCategoryService;

    /**
     * 查询商品分类列表
     */
//    @RequiresPermissions("enterprise:category:list")
    @GetMapping("/list")
    public AjaxResult list(EntCategory entCategory) {
        List<EntCategory> list = entCategoryService.selectCategoryList(entCategory);
        return AjaxResult.success(list);
    }

    /**
     * 导出商品分类列表
     */
//    @RequiresPermissions("enterprise:category:export")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntCategory entCategory) {
        List<EntCategory> list = entCategoryService.selectCategoryList(entCategory);
        ExcelUtil<EntCategory> util = new ExcelUtil<>(EntCategory.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 根据ID获取商品分类详细信息
     */
//    @RequiresPermissions("enterprise:category:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(entCategoryService.selectCategoryById(id));
    }

    /**
     * 获取商品分类下拉树列表
     */
    @GetMapping("/treeSelect")
    public AjaxResult treeSelect(EntCategory category) {
        List<EntCategory> categorys = entCategoryService.selectCategoryList(category);
        return AjaxResult.success(entCategoryService.buildCategoryTreeSelect(categorys));
    }


    /**
     * 新增商品分类
     */
//    @RequiresPermissions("enterprise:category:add")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody EntCategory entCategory) {
        if (UserConstants.NOT_UNIQUE.equals(entCategoryService.checkCategoryNameUnique(entCategory))) {
            return AjaxResult.error("新增类别'" + entCategory.getName() + "'失败，类别名称已存在");
        }
        entCategory.setCreateBy(SecurityUtils.getUsername());
        return toAjax(entCategoryService.insertCategory(entCategory));
    }

    /**
     * 修改商品分类
     */
//    @RequiresPermissions("enterprise:category:edit")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EntCategory entCategory) {
        Long categoryId = entCategory.getId();
        if (UserConstants.NOT_UNIQUE.equals(entCategoryService.checkCategoryNameUnique(entCategory))) {
            return AjaxResult.error("修改类别'" + entCategory.getName() + "'失败，类别名称已存在");
        } else if (entCategory.getParentId().equals(categoryId)) {
            return AjaxResult.error("修改类别'" + entCategory.getName() + "'失败，上级类别不能是自己");
        } else if (entCategoryService.selectNormalChildrenCategoryById(categoryId) > 0) {
            return AjaxResult.error("该类别包含未停用的子类别！");
        }
        entCategory.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entCategoryService.updateCategory(entCategory));
    }

    /**
     * 删除商品分类
     */
//    @RequiresPermissions("enterprise:category:remove")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        if (entCategoryService.hasChildByCategoryId(id)) {
            return AjaxResult.error("存在下级类别,不允许删除");
        }
        if (entCategoryService.checkCategoryExistProduct(id)) {
            return AjaxResult.error("类别存在商品,不允许删除");
        }
        return toAjax(entCategoryService.deleteCategoryById(id));
    }
}
