package com.u2.business.system.service.impl;

import com.u2.api.system.domain.SysPost;
import com.u2.business.system.dao.SysPostMapper;
import com.u2.business.system.dao.SysUserPostMapper;
import com.u2.business.system.service.SysPostService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.exception.ServiceException;
import com.u2.common.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 岗位 业务层处理
 * @author vhans
 */
@Service
public class SysPostServiceImpl implements SysPostService {
    @Resource
    private SysPostMapper postMapper;

    @Resource
    private SysUserPostMapper userPostMapper;

    @Override
    public List<SysPost> selectPostList(SysPost post) {
        return postMapper.selectPostList(post);
    }

    @Override
    public List<SysPost> selectPostAll() {
        return postMapper.selectPostAll();
    }

    @Override
    public SysPost selectPostById(Long postId) {
        return postMapper.selectPostById(postId);
    }

    @Override
    public List<Long> selectPostListByUserId(Long userId) {
        return postMapper.selectPostListByUserId(userId);
    }

    @Override
    public String checkPostNameUnique(SysPost post) {
        long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId() != postId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkPostCodeUnique(SysPost post) {
        long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostCodeUnique(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId() != postId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int countUserPostById(Long postId) {
        return userPostMapper.countUserPostById(postId);
    }

    @Override
    public int deletePostById(Long postId) {
        return postMapper.deletePostById(postId);
    }

    @Override
    public int deletePostByIds(Long[] postIds) {
        for (Long postId : postIds) {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
        }
        return postMapper.deletePostByIds(postIds);
    }

    @Override
    public int insertPost(SysPost post) {
        return postMapper.insertPost(post);
    }

    @Override
    public int updatePost(SysPost post) {
        return postMapper.updatePost(post);
    }
}
