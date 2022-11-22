package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntOrderItem;
import com.u2.business.enterprise.service.EntOrderItemService;
import com.u2.common.core.utils.poi.ExcelUtil;
import com.u2.common.core.web.controller.BaseController;
import com.u2.common.core.web.domain.model.AjaxResult;
import com.u2.common.core.web.page.TableDataInfo;
import com.u2.common.log.annotation.Log;
import com.u2.common.log.enums.BusinessType;
import com.u2.common.security.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单子项信息Controller
 *
 * @author vhans
 * @date 2022-05-26
 */
@RestController
@RequestMapping("/order/item")
public class EntOrderItemController extends BaseController {
    @Resource
    private EntOrderItemService entOrderItemService;

    /**
     * 查询订单子项信息列表
     */
//    @RequiresPermissions("enterprise:order:list")
    @GetMapping("/list")
    public TableDataInfo list(EntOrderItem entOrderItem) {
        startPage();
        List<EntOrderItem> list = entOrderItemService.selectOrderItemList(entOrderItem);
        return getDataTable(list);
    }

    /**
     * 导出订单子项信息列表
     */
//    @RequiresPermissions("enterprise:order:export")
    @Log(title = "订单子项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntOrderItem entOrderItem) {
        List<EntOrderItem> list = entOrderItemService.selectOrderItemList(entOrderItem);
        ExcelUtil<EntOrderItem> util = new ExcelUtil<>(EntOrderItem.class);
        util.exportExcel(response, list, "订单子项数据");
    }

    /**
     * 根据ID获取订单子项详细信息
     */
//    @RequiresPermissions("enterprise:order:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(entOrderItemService.selectOrderItemById(id));
    }

    /**
     * 获取订单下的订单子项列表
     */
    @GetMapping("/{orderId}")
    public AjaxResult optionSelect(@PathVariable("orderId") Long orderId) {
        List<EntOrderItem> orders = entOrderItemService.selectOrderItemListByOrderId(orderId);
        return AjaxResult.success(orders);
    }

    /**
     * 新增订单子项信息
     */
//    @RequiresPermissions("enterprise:order:add")
    @Log(title = "订单子项信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EntOrderItem entOrderItem) {
        return toAjax(entOrderItemService.insertOrderItem(entOrderItem));
    }

    /**
     * 修改订单子项信息
     */
//    @RequiresPermissions("enterprise:order:edit")
    @Log(title = "订单子项信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EntOrderItem entOrderItem) {
        return toAjax(entOrderItemService.updateOrderItem(entOrderItem));
    }

    /**
     * 删除订单子项信息
     */
//    @RequiresPermissions("enterprise:order:remove")
    @Log(title = "订单子项信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(entOrderItemService.deleteOrderItemByIds(ids));
    }
}
