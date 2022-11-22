package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntBrand;
import com.u2.business.enterprise.service.EntBrandService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.poi.ExcelUtil;
import com.u2.common.core.web.controller.BaseController;
import com.u2.common.core.web.domain.model.AjaxResult;
import com.u2.common.core.web.page.TableDataInfo;
import com.u2.common.log.annotation.Log;
import com.u2.common.log.enums.BusinessType;
import com.u2.common.security.annotation.RequiresPermissions;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 品牌信息Controller
 *
 * @author vhans
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/brand")
public class EntBrandController extends BaseController {
    @Resource
    private EntBrandService entBrandService;

    /**
     * 查询品牌信息列表
     */
//    @RequiresPermissions("enterprise:partner:list")
    @GetMapping("/list")
    public TableDataInfo list(EntBrand entBrand) {
        startPage();
        List<EntBrand> list = entBrandService.selectBrandList(entBrand);
        return getDataTable(list);
    }

    /**
     * 导出品牌信息列表
     */
//    @RequiresPermissions("enterprise:partner:export")
    @Log(title = "品牌信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntBrand entBrand) {
        List<EntBrand> list = entBrandService.selectBrandList(entBrand);
        ExcelUtil<EntBrand> util = new ExcelUtil<>(EntBrand.class);
        util.exportExcel(response, list, "品牌信息数据");
    }

    /**
     * 根据品牌ID获取详细信息
     */
//    @RequiresPermissions("enterprise:partner:query")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId) {
        return AjaxResult.success(entBrandService.selectBrandById(brandId));
    }

    /**
     * 新增品牌信息
     */
//    @RequiresPermissions("enterprise:partner:add")
    @Log(title = "品牌信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody EntBrand brand) {
        if (UserConstants.NOT_UNIQUE.equals(entBrandService.checkBrandNameUnique(brand))) {
            return AjaxResult.error("新增品牌'" + brand.getBrandName() + "'失败，品牌名称已存在");
        }
        brand.setCreateBy(SecurityUtils.getUsername());
        return toAjax(entBrandService.insertBrand(brand));
    }

    /**
     * 修改品牌信息
     */
//    @RequiresPermissions("enterprise:partner:edit")
    @Log(title = "品牌信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EntBrand brand) {
        if (UserConstants.NOT_UNIQUE.equals(entBrandService.checkBrandNameUnique(brand))) {
            return AjaxResult.error("修改品牌'" + brand.getBrandName() + "'失败，品牌名称已存在");
        }
        brand.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entBrandService.updateBrand(brand));
    }

    /**
     * 删除品牌信息
     */
//    @RequiresPermissions("enterprise:partner:remove")
    @Log(title = "品牌信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds) {
        List<Long> temp = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        // 排除绑定商品的品牌
        for(Long brandId: brandIds){
            int res = entBrandService.checkBrandExistProduct(brandId);
            if( res == 0){
                temp.add(brandId);
            }else {
                EntBrand entBrand = entBrandService.selectBrandById(brandId);
                str.append(entBrand.getBrandName()).append(" ");
            }
        }
        Long[] newBrandIds = temp.toArray(new Long[0]);
        toAjax(entBrandService.deleteBrandByIds(newBrandIds));
        return AjaxResult.error("删除品牌[ " + str + "]失败，品牌已绑定商品");
    }
}
