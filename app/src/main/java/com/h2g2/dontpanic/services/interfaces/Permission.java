package com.h2g2.dontpanic.services.interfaces;

import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/15
 */
public interface Permission {

    int REQUEST_CODE_ASK_PERMISSIONS = 123;

    List<String> getPermissionNotGranted(List<String> permissionList);

    List<String> setPermissionList();

    void checkPermissionList();
}
