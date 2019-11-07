package ${targetPackage};

import lombok.Data;
import BaseEntity;

<#if importClassList?exists>
    <#list importClassList?keys as mykey>
import ${mykey};
    </#list>
</#if>
import java.io.Serializable;

<#include "header.ftl">
@Data
public class ${domainObjectClassName} extends BaseEntity implements Serializable {

<#if columns?exists>
    <#list columns as field>
    /** ${field['columnComment']} */
    private ${field['javaType']} ${field['columnName']};
    </#list>
</#if>


}
