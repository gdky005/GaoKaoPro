package com.zk.gaokaopro.manager

import com.blankj.utilcode.util.SPUtils
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.GKConstant.SP_FILE_NAME_USER_INFO
import com.zk.gaokaopro.model.LoginBean

class UserInfoManager private constructor() {

    private var loginBean: LoginBean? = null


    companion object {
        val instance: UserInfoManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UserInfoManager()
        }
    }

    /**
     * 判断用户是否登录
     */
    fun isLogin(): Boolean {
        val myLoginBean: LoginBean? = checkLoginBean()

        if (myLoginBean != null) {
            if (myLoginBean.uid != GKConstant.USER_DEFAULT_INVALID_UID) {
                return true
            }
        }
        return false
    }

    /**
     * 清除用户登录信息
     */
    fun clear(): Boolean {
        return logOut()
    }

    /**
     * 让用户退出登录
     */
    fun logOut(): Boolean {
        try {
            this.loginBean = null

            val spUtils: SPUtils = SPUtils.getInstance(SP_FILE_NAME_USER_INFO)
            spUtils.clear()
            return true
        } catch (e: Exception) {
        }
        return false
    }

    fun saveUserInfo(loginBean: LoginBean?) {
        this.loginBean = loginBean

        try {
            val spUtils: SPUtils = SPUtils.getInstance(SP_FILE_NAME_USER_INFO)
            spUtils.put(GKConstant.SP_KEY_USER_NAME, loginBean!!.name)
            spUtils.put(GKConstant.SP_KEY_USER_MOBILE, loginBean.phone)
            spUtils.put(GKConstant.SP_KEY_USER_UID, loginBean.uid)
        } catch (e: Exception) {
        }
    }

    fun getUserInfo(): LoginBean? {
        return loginBean
    }

    fun getUserName(): String? {
        return checkLoginBean()?.name
    }

    fun getUserUid(): Int? {
        return checkLoginBean()?.uid
    }

    private fun checkLoginBean(): LoginBean? {
        if (loginBean == null) {
            loginBean = getUserInfoForSP()
        }
        return loginBean
    }

    private fun getUserInfoForSP(): LoginBean? {
        val spUtils: SPUtils = SPUtils.getInstance(GKConstant.SP_FILE_NAME_USER_INFO)

        val name = spUtils.getString(GKConstant.SP_KEY_USER_NAME, "")
        val phone = spUtils.getString(GKConstant.SP_KEY_USER_MOBILE, "")
        val uid = spUtils.getInt(GKConstant.SP_KEY_USER_UID, GKConstant.USER_DEFAULT_INVALID_UID)

        return LoginBean(uid, name, phone)
    }


}