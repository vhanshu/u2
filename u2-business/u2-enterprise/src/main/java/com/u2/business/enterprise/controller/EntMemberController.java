package com.u2.business.enterprise.controller;

import com.u2.api.enterprise.domain.EntMember;
import com.u2.api.enterprise.domain.model.LoginMember;
import com.u2.api.system.service.RemoteConfigService;
import com.u2.business.enterprise.service.EntMemberService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 会员信息Controller
 *
 * @author vhans
 * @date 2022-05-25
 */
@RestController
@RequestMapping("/member")
public class EntMemberController extends BaseController {
    @Resource
    private EntMemberService entMemberService;

    @Resource
    private RemoteConfigService remoteConfigService;

    /**
     * 查询会员信息列表
     */
//    @RequiresPermissions("enterprise:member:list")
    @GetMapping("/list")
    public TableDataInfo list(EntMember entMember) {
        startPage();
        List<EntMember> list = entMemberService.selectMemberList(entMember);
        return getDataTable(list);
    }

    /**
     * 导出会员信息列表
     */
//    @RequiresPermissions("enterprise:member:export")
    @Log(title = "会员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntMember entMember) {
        List<EntMember> list = entMemberService.selectMemberList(entMember);
        ExcelUtil<EntMember> util = new ExcelUtil<>(EntMember.class);
        util.exportExcel(response, list, "会员信息数据");
    }

    /**
     * 导入会员信息列表
     */
    @Log(title = "会员管理", businessType = BusinessType.IMPORT)
//    @RequiresPermissions("enterprise:member:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<EntMember> util = new ExcelUtil<>(EntMember.class);
        List<EntMember> memberList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = entMemberService.importMember(memberList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<EntMember> util = new ExcelUtil<>(EntMember.class);
        util.importTemplateExcel(response, "会员数据");
    }

    /**
     * 根据ID获取会员信息详细信息
     */
//    @RequiresPermissions("enterprise:member:query")
    @GetMapping(value = "/{memberId}")
    public AjaxResult getInfo(@PathVariable("memberId") Long memberId) {
        return AjaxResult.success(entMemberService.selectMemberById(memberId));
    }

    /**
     * 获取当前会员信息
     */
    @InnerAuth
    @GetMapping("/info/{memberName}")
    public R<LoginMember> info(@PathVariable("memberName") String memberName) {
        EntMember entMember = entMemberService.selectMemberByMemberName(memberName);
        if (StringUtils.isNull(entMember)) {
            return R.fail("会员名或密码错误");
        }
        LoginMember entMemberVo = new LoginMember();
        entMemberVo.setEntMember(entMember);
        return R.ok(entMemberVo);
    }

    /**
     * 注册会员信息
     */
    @InnerAuth
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody EntMember entMember) {
        String memberName = entMember.getMemberName();
        AjaxResult config = remoteConfigService.getConfig("ent.member.register");
        if (StringUtils.isNull(config)) {
            return R.fail("当前系统没有开启注册功能！");
        }
        if (UserConstants.NOT_UNIQUE.equals(entMemberService.checkMemberNameUnique(entMember))) {
            return R.fail("保存信息'" + memberName + "'失败，注册账号已存在");
        }
        return R.ok(entMemberService.registerMember(entMember));
    }

    /**
     * 新增会员信息
     */
//    @RequiresPermissions("enterprise:member:add")
    @Log(title = "会员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody EntMember entMember) {
        if (UserConstants.NOT_UNIQUE.equals(entMemberService.checkMemberNameUnique(entMember))) {
            return AjaxResult.error("新增会员'" + entMember.getMemberName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(entMember.getPhoneNumber())
                && UserConstants.NOT_UNIQUE.equals(entMemberService.checkPhoneUnique(entMember))) {
            return AjaxResult.error("新增会员'" + entMember.getMemberName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(entMember.getEmail())
                && UserConstants.NOT_UNIQUE.equals(entMemberService.checkEmailUnique(entMember))) {
            return AjaxResult.error("新增会员'" + entMember.getMemberName() + "'失败，邮箱账号已存在");
        }
        entMember.setCreateBy(SecurityUtils.getUsername());
        entMember.setPassword(SecurityUtils.encryptPassword(entMember.getPassword()));
        return toAjax(entMemberService.insertMember(entMember));
    }

    /**
     * 修改会员信息
     */
//    @RequiresPermissions("enterprise:member:edit")
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EntMember entMember) {
        if (UserConstants.NOT_UNIQUE.equals(entMemberService.checkMemberNameUnique(entMember))) {
            return AjaxResult.error("修改会员'" + entMember.getMemberName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(entMember.getPhoneNumber())
                && UserConstants.NOT_UNIQUE.equals(entMemberService.checkPhoneUnique(entMember))) {
            return AjaxResult.error("修改会员'" + entMember.getMemberName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(entMember.getEmail())
                && UserConstants.NOT_UNIQUE.equals(entMemberService.checkEmailUnique(entMember))) {
            return AjaxResult.error("修改会员'" + entMember.getMemberName() + "'失败，邮箱账号已存在");
        }
        entMember.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entMemberService.updateMember(entMember));
    }

    /**
     * 删除会员信息
     */
//    @RequiresPermissions("enterprise:member:remove")
    @Log(title = "会员信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(entMemberService.deleteMemberByIds(ids));
    }

    /**
     * 重置密码
     */
//    @RequiresPermissions("system:user:edit")
    @Log(title = "会员管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody EntMember entMember) {
        entMember.setPassword(SecurityUtils.encryptPassword(entMember.getPassword()));
        entMember.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entMemberService.resetPwd(entMember));
    }

    /**
     * 状态修改
     */
//    @RequiresPermissions("system:user:edit")
    @Log(title = "会员管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody EntMember entMember) {
        entMember.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(entMemberService.updateMemberStatus(entMember));
    }
}