package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntOrder;
import com.u2.api.enterprise.domain.EntOrderItem;
import com.u2.business.enterprise.service.EntOrderService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.core.utils.poi.ExcelUtil;
import com.u2.common.core.web.controller.BaseController;
import com.u2.common.core.web.domain.model.AjaxResult;
import com.u2.common.core.web.page.TableDataInfo;
import com.u2.common.log.annotation.Log;
import com.u2.common.log.enums.BusinessType;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单信息Controller
 *
 * @author vhans
 * @date 2022-05-26
 */
@RestController
@RequestMapping("/order")
public class EntOrderController extends BaseController {
    @Resource
    private EntOrderService entOrderService;


    /**
     * 查询订单信息列表
     */
//    @RequiresPermissions("enterprise:order:list")
    @GetMapping("/list")
    public TableDataInfo list(EntOrder entOrder) {
        startPage();
        List<EntOrder> list = entOrderService.selectOrderList(entOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单信息列表
     */
//    @RequiresPermissions("enterprise:order:export")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntOrder entOrder) {
        List<EntOrder> list = entOrderService.selectOrderList(entOrder);
        ExcelUtil<EntOrder> util = new ExcelUtil<>(EntOrder.class);
        util.exportExcel(response, list, "订单信息数据");
    }

    /**
     * 获取订单信息详细信息
     */
//    @RequiresPermissions("enterprise:order:query")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId) {
        return AjaxResult.success(entOrderService.selectOrderById(orderId));
    }

    /**
     * 获取订单下的订单子项列表
     */
    @GetMapping("/items/{orderId}")
    public AjaxResult getItems(@PathVariable("orderId") Long orderId) {
        List<EntOrderItem> orders = entOrderService.selectOrderItemList(orderId);
        return AjaxResult.success(orders);
    }

    /**
     * 新增订单信息
     */
//    @RequiresPermissions("enterprise:order:add")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody EntOrder entOrder) {
        if (UserConstants.NOT_UNIQUE.equals(entOrderService.checkSnUnique(entOrder))) {
            return AjaxResult.error("新增订单'" + entOrder.getSn() + "'失败，订单号已存在");
        }
        entOrder.setCreateBy(SecurityUtils.getUsername());
        return toAjax(entOrderService.insertOrder(entOrder));
    }

    /**
     * 修改订单信息
     */
//    @RequiresPermissions("enterprise:order:edit")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EntOrder entOrder) {
        if (StringUtils.isNotNull(entOrder.getMoney()) || StringUtils.isNotNull(entOrder.getNum())) {
            return AjaxResult.error("修改订单'" + entOrder.getSn() + "'失败，订单数据属性不允许修改");
        }
        if (UserConstants.NOT_UNIQUE.equals(entOrderService.checkSnUnique(entOrder))) {
            return AjaxResult.error("修改订单'" + entOrder.getSn() + "'失败，订单号已存在");
        }
        return toAjax(entOrderService.updateOrder(entOrder));
    }

    /**
     * 删除订单信息
     */
//    @RequiresPermissions("enterprise:order:remove")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderId}")
    public AjaxResult remove(@PathVariable Long orderId) {
        return toAjax(entOrderService.deleteOrderById(orderId));
    }
}
