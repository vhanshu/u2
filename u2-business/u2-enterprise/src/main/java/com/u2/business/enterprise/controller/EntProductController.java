package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntCategory;
import com.u2.api.enterprise.domain.EntProduct;
import com.u2.business.enterprise.service.EntCategoryService;
import com.u2.business.enterprise.service.EntProductService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.poi.ExcelUtil;
import com.u2.common.core.web.controller.BaseController;
import com.u2.common.core.web.domain.model.AjaxResult;
import com.u2.common.core.web.page.TableDataInfo;
import com.u2.common.log.annotation.Log;
import com.u2.common.log.enums.BusinessType;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品信息Controller
 *
 * @author ego
 * @date 2022-04-11
 */
@RestController
@RequestMapping("/product")
public class EntProductController extends BaseController {
    @Resource
    private EntProductService entProductService;

    @Resource
    private EntCategoryService entCategoryService;

    /**
     * 查询商品信息列表
     */
//    @RequiresPermissions("enterprise:product:list")
    @GetMapping("/list")
    public TableDataInfo list(EntProduct entProduct) {
        startPage();
        List<EntProduct> list = entProductService.selectProductList(entProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
//    @RequiresPermissions("enterprise:product:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntProduct entProduct) {
        List<EntProduct> list = entProductService.selectProductList(entProduct);
        ExcelUtil<EntProduct> util = new ExcelUtil<>(EntProduct.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品信息详细信息
     */
//    @RequiresPermissions("enterprise:product:query")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId) {
        return AjaxResult.success(entProductService.selectProductById(productId));
    }

    /**
     * 新增商品信息
     */
//    @RequiresPermissions("enterprise:product:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EntProduct entProduct) {
        if (UserConstants.NOT_UNIQUE.equals(entProductService.checkProductNameUnique(entProduct))) {
            return AjaxResult.error("新增商品'" + entProduct.getProductName() + "'失败，商品名称已存在");
        }
        entProduct.setCreateBy(SecurityUtils.getUsername());
        return toAjax(entProductService.insertProduct(entProduct));
    }

    /**
     * 修改商品信息
     */
//    @RequiresPermissions("enterprise:product:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EntProduct entProduct) {
        if (UserConstants.NOT_UNIQUE.equals(entProductService.checkProductNameUnique(entProduct))) {
            return AjaxResult.error("修改商品'" + entProduct.getProductName() + "'失败，商品名称已存在");
        }
        entProduct.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entProductService.updateProduct(entProduct));
    }

    /**
     * 删除商品信息
     */
//    @RequiresPermissions("enterprise:product:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds) {
        return toAjax(entProductService.deleteProductByIds(productIds));
    }

    /**
     * 获取类别树列表
     */
//    @RequiresPermissions("enterprise:product:list")
    @GetMapping("/categoryTree")
    public AjaxResult categoryTree(EntCategory category) {
        return AjaxResult.success(entCategoryService.selectCategoryTreeList(category));
    }
}
