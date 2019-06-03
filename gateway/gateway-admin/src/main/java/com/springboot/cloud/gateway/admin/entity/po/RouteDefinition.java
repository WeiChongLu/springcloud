package com.springboot.cloud.gateway.admin.entity.po;

import com.springboot.cloud.common.core.entity.po.BasePo;
import com.sun.jndi.toolkit.url.Uri;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDefinition extends BasePo {
    private Uri uri;
    private String routeId;
    private List<FilterDefinition> filters = new ArrayList<>();
    private List<PredicateDefinition> predicates = new ArrayList<>();
    private String description;
    private Integer orders;
    private String status;
}
