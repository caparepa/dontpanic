package com.h2g2.dontpanic.services.abstracts;

import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.services.interfaces.Permission;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/15
 */
public abstract class PermissionBase implements Permission {

    private BaseActivity homeActivity;

    public PermissionBase(BaseActivity activity){
        this.homeActivity = activity;
    }

}
