package com.github.tanxinzheng.module.system.wopi.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WopiFileVO {

    @JsonProperty(value = "BaseFileName")
    private String baseFileName;
    @JsonProperty(value = "Size")
    private Long size;
    @JsonProperty(value = "OwnerId")
    private String ownerId;
    @JsonProperty(value = "Version")
    private Long version;
    @JsonProperty(value = "SHA256")
    private String sha256;
    @JsonProperty(value = "UserCanWriter")
    private Boolean userCanWriter;
    @JsonProperty(value = "SupportsUpdate")
    private Boolean supportsUpdate;
    @JsonProperty(value = "SupportsLocks")
    private Boolean supportsLocks;
    @JsonProperty(value = "WebEditingDisabled")
    private Boolean webEditingDisabled;
    @JsonProperty(value = "HostViewUrl")
    private String hostViewUrl;
    @JsonProperty(value = "ClientUrl")
    private String clientUrl;
    @JsonProperty(value = "AllowExternalMarketplace")
    private Boolean allowExternalMarketplace;
    @JsonProperty(value = "UserCanWrite")
    private Boolean userCanWrite;

}
