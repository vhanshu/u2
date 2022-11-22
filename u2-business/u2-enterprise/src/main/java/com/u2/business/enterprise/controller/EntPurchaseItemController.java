package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntPurchaseItem;
import com.u2.business.enterprise.service.EntPurchaseItemService;
import com.u2.common.core.utils.poi.ExcelUtil;
import com.u2.common.core.web.controller.BaseController;
import com.u2.common.core.web.domain.model.AjaxResult;
import com.u2.common.core.web.page.TableDataInfo;
import com.u2.common.log.annotation.Log;
import com.u2.common.log.enums.BusinessType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 采购子项信息Controller
 *
 * @author vhans
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/purchase/item")
public class EntPurchaseItemController extends BaseController {
    @Resource
    private EntPurchaseItemService entPurchaseItemService;

    /**
     * 查询采购子项信息列表
     */
//    @RequiresPermissions("enterprise:purchase:list")
    @GetMapping("/list")
    public TableDataInfo list(EntPurchaseItem entPurchaseItem) {
        startPage();
        List<EntPurchaseItem> list = entPurchaseItemService.selectPurchaseItemList(entPurchaseItem);
        return getDataTable(list);
    }

    /**
     * 导出采购子项信息列表
     */
//    @RequiresPermissions("enterprise:purchase:export")
    @Log(title = "采购子项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntPurchaseItem entPurchaseItem) {
        List<EntPurchaseItem> list = entPurchaseItemService.selectPurchaseItemList(entPurchaseItem);
        ExcelUtil<EntPurchaseItem> util = new ExcelUtil<>(EntPurchaseItem.class);
        util.exportExcel(response, list, "采购子项数据");
    }

    /**
     * 根据ID获取采购子项信息详细信息
     */
//    @RequiresPermissions("enterprise:purchase:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(entPurchaseItemService.selectPurchaseItemById(id));
    }

    /**
     * 获取订单下的订单子项列表
     */
    @GetMapping("/{purchaseId}")
    public AjaxResult optionSelect(@PathVariable("purchaseId") Long purchaseId) {
        List<EntPurchaseItem> purchases = entPurchaseItemService.selectPurchaseItemListByPurchaseId(purchaseId);
        return AjaxResult.success(purchases);
    }

    /**
     * 新增采购子项信息
     */
//    @RequiresPermissions("enterprise:purchase:add")
    @Log(title = "采购子项信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EntPurchaseItem entPurchaseItem) {
        return toAjax(entPurchaseItemService.insertPurchaseItem(entPurchaseItem));
    }

    /**
     * 修改采购子项信息
     */
//    @RequiresPermissions("enterprise:purchase:edit")
    @Log(title = "采购子项信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EntPurchaseItem entPurchaseItem) {
        return toAjax(entPurchaseItemService.updatePurchaseItem(entPurchaseItem));
    }

    /**
     * 删除采购子项信息
     */
//    @RequiresPermissions("enterprise:purchase:remove")
    @Log(title = "采购子项信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(entPurchaseItemService.deletePurchaseItemByIds(ids));
    }
}
