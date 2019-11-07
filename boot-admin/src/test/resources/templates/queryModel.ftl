package ${targetPackage};

import lombok.Data;
import BaseQuery;

import java.io.Serializable;

<#include "header.ftl">
@Data
public class ${domainObjectClassName}Query extends BaseQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;

}
