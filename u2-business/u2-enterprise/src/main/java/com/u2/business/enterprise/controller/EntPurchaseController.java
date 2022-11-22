package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntOrderItem;
import com.u2.api.enterprise.domain.EntPurchase;
import com.u2.api.enterprise.domain.EntPurchaseItem;
import com.u2.business.enterprise.service.EntPurchaseService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.StringUtils;
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
import java.util.List;


/**
 * 采购单信息Controller
 *
 * @author vhans
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/purchase")
public class EntPurchaseController extends BaseController {
    @Resource
    private EntPurchaseService entPurchaseService;

    /**
     * 查询采购单信息列表
     */
//    @RequiresPermissions("enterprise:purchase:list")
    @GetMapping("/list")
    public TableDataInfo list(EntPurchase entPurchase) {
        startPage();
        List<EntPurchase> list = entPurchaseService.selectPurchaseList(entPurchase);
        return getDataTable(list);
    }

    /**
     * 导出采购单信息列表
     */
//    @RequiresPermissions("enterprise:purchase:export")
    @Log(title = "采购单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntPurchase entPurchase) {
        List<EntPurchase> list = entPurchaseService.selectPurchaseList(entPurchase);
        ExcelUtil<EntPurchase> util = new ExcelUtil<>(EntPurchase.class);
        util.exportExcel(response, list, "采购单数据");
    }

    /**
     * 获取采购单信息详细信息
     */
//    @RequiresPermissions("enterprise:purchase:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(entPurchaseService.selectPurchaseById(id));
    }

    /**
     * 获取采购单下的订单子项列表
     */
    @GetMapping("/items/{purchaseId}")
    public AjaxResult getItems(@PathVariable("purchaseId") Long purchaseId) {
        List<EntPurchaseItem> purchaseItems = entPurchaseService.selectPurchaseItemList(purchaseId);
        return AjaxResult.success(purchaseItems);
    }

    /**
     * 新增采购单信息
     */
//    @RequiresPermissions("enterprise:purchase:add")
    @Log(title = "采购单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody EntPurchase entPurchase) {
        if (UserConstants.NOT_UNIQUE.equals(entPurchaseService.checkSnUnique(entPurchase))) {
            return AjaxResult.error("新增采购单'" + entPurchase.getSn() + "'失败，采购单号已存在");
        }
        entPurchase.setCreateBy(SecurityUtils.getUsername());
        return toAjax(entPurchaseService.insertPurchase(entPurchase));
    }

    /**
     * 修改采购单信息
     */
//    @RequiresPermissions("enterprise:purchase:edit")
    @Log(title = "采购单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EntPurchase entPurchase) {
        if (StringUtils.isNotNull(entPurchase.getMoney()) || StringUtils.isNotNull(entPurchase.getNum())) {
            return AjaxResult.error("修改采购单'" + entPurchase.getSn() + "'失败，采购单数据属性不允许修改");
        }
        if (UserConstants.NOT_UNIQUE.equals(entPurchaseService.checkSnUnique(entPurchase))) {
            return AjaxResult.error("修改采购单'" + entPurchase.getSn() + "'失败，采购单号已存在");
        }
        entPurchase.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entPurchaseService.updatePurchase(entPurchase));
    }

    /**
     * 删除采购单信息
     */
//    @RequiresPermissions("enterprise:purchase:remove")
    @Log(title = "采购单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{purchaseId}")
    public AjaxResult remove(@PathVariable Long purchaseId) {
        return toAjax(entPurchaseService.deletePurchaseById(purchaseId));
    }
}
