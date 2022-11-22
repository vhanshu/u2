package com.u2.common.core.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TreeSelect树结构实体类(前端展示)
 *
 * @author vhans
 */
public class TreeSelect<T extends TreeEntity<T>> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /** 节点名称 */
    private String name;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect<T>> children;

    public TreeSelect() {
    }

    public TreeSelect(T t) {
        this.id = t.getId();
        this.name = t.getName();
        this.children = t.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeSelect<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeSelect<T>> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("children", getChildren())
                .toString();
    }
}
