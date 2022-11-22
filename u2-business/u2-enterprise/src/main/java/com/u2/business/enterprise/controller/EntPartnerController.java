package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntBrand;
import com.u2.api.enterprise.domain.EntPartner;
import com.u2.api.system.service.RemoteConfigService;
import com.u2.business.enterprise.service.EntPartnerService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.domain.R;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.core.utils.poi.ExcelUtil;
import com.u2.common.core.web.controller.BaseController;
import com.u2.common.core.web.domain.model.AjaxResult;
import com.u2.common.core.web.page.TableDataInfo;
import com.u2.common.log.annotation.Log;
import com.u2.common.log.enums.BusinessType;
import com.u2.common.security.annotation.InnerAuth;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 合作方信息Controller
 *
 * @author vhans
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/enterprise/partner")
public class EntPartnerController extends BaseController {
    @Resource
    private EntPartnerService entPartnerService;

    @Resource
    private RemoteConfigService remoteConfigService;

    /**
     * 查询合作方信息列表
     */
//    @RequiresPermissions("enterprise:partner:list")
    @GetMapping("/list")
    public TableDataInfo list(EntPartner entPartner) {
        startPage();
        List<EntPartner> list = entPartnerService.selectPartnerList(entPartner);
        return getDataTable(list);
    }

    /**
     * 导出合作方信息列表
     */
//    @RequiresPermissions("enterprise:partner:export")
    @Log(title = "合作方", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntPartner entPartner) {
        List<EntPartner> list = entPartnerService.selectPartnerList(entPartner);
        ExcelUtil<EntPartner> util = new ExcelUtil<>(EntPartner.class);
        util.exportExcel(response, list, "合作方数据");
    }

    /**
     * 获取合作方信息详细信息
     */
//    @RequiresPermissions("enterprise:partner:query")
    @GetMapping(value = "/{partnerId}")
    public AjaxResult getInfo(@PathVariable("partnerId") Long partnerId) {
        return AjaxResult.success(entPartnerService.selectPartnerByPartnerId(partnerId));
    }

    /**
     * 获取合作方提供的品牌列表
     */
    @GetMapping("/brands/{partnerId}")
    public AjaxResult optionSelect(@PathVariable("partnerId") Long partnerId) {
        List<EntBrand> brands = entPartnerService.selectBrandList(partnerId);
        return AjaxResult.success(brands);
    }

    /**
     * 企业入驻
     */
    @InnerAuth
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody EntPartner entPartner) {
        String username = entPartner.getUserName();
        AjaxResult config = remoteConfigService.getConfig("ent.account.registerMember");
        if (StringUtils.isNull(config)) {
            return R.fail("当前系统没有开启注册功能！");
        }
        if (UserConstants.NOT_UNIQUE.equals(entPartnerService.checkPartnerNameUnique(entPartner))) {
            return R.fail("保存信息'" + username + "'失败，注册企业名已存在");
        } else if (StringUtils.isNotEmpty(entPartner.getPhone())
                && UserConstants.NOT_UNIQUE.equals(entPartnerService.checkPhoneUnique(entPartner))) {
            return R.fail("保存信息'" + username + "'失败，手机号码已存在");
        }
        return R.ok(entPartnerService.registerPartner(entPartner));
    }

    /**
     * 新增合作方信息
     */
//    @RequiresPermissions("enterprise:partner:add")
    @Log(title = "合作方信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody EntPartner entPartner) {
        if (UserConstants.NOT_UNIQUE.equals(entPartnerService.checkPartnerNameUnique(entPartner))) {
            return AjaxResult.error("新增合作者'" + entPartner.getUserName() + "'失败，企业名称已存在");
        } else if (StringUtils.isNotEmpty(entPartner.getPhone())
                && UserConstants.NOT_UNIQUE.equals(entPartnerService.checkPhoneUnique(entPartner))) {
            return AjaxResult.error("新增合作者'" + entPartner.getUserName() + "'失败，手机号码已存在");
        }
        entPartner.setCreateBy(SecurityUtils.getUsername());
        entPartner.setPassword(SecurityUtils.encryptPassword(entPartner.getPassword()));
        return toAjax(entPartnerService.insertPartner(entPartner));
    }

    /**
     * 修改合作方信息
     */
//    @RequiresPermissions("enterprise:partner:edit")
    @Log(title = "合作方信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EntPartner entPartner) {
        if (UserConstants.NOT_UNIQUE.equals(entPartnerService.checkPartnerNameUnique(entPartner))) {
            return AjaxResult.error("修改合作者'" + entPartner.getUserName() + "'失败，企业名称已存在");
        } else if (StringUtils.isNotEmpty(entPartner.getPhone())
                && UserConstants.NOT_UNIQUE.equals(entPartnerService.checkPhoneUnique(entPartner))) {
            return AjaxResult.error("新修改合作者'" + entPartner.getUserName() + "'失败，手机号码已存在");
        }
        entPartner.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entPartnerService.updatePartner(entPartner));
    }

    /**
     * 重置密码
     */
//    @RequiresPermissions("enterprise:partner:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody EntPartner partner) {
        partner.setPassword(SecurityUtils.encryptPassword(partner.getPassword()));
        partner.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entPartnerService.resetPwd(partner));
    }

    /**
     * 删除合作方信息
     */
//    @RequiresPermissions("enterprise:partner:remove")
    @Log(title = "合作方信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{partnerId}")
    public AjaxResult remove(@PathVariable Long partnerId) {
        return toAjax(entPartnerService.deletePartnerById(partnerId));
    }
}