package com.zk.gaokaopro.activity

import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils
import com.zk.gaokaopro.R
import com.zk.gaokaopro.manager.UserInfoManager
import com.zk.gaokaopro.model.LoginBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import com.zk.gaokaopro.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.layout_login.*
import team.zhuoke.sdk.base.BaseActivity

class LoginActivity : BaseActivity(), View.OnClickListener {

    companion object {
        const val MIN_PWD_COUNT = 6
    }

    lateinit var etLoginName: EditText
    lateinit var etLoginPassword: EditText
    lateinit var btnLogin: Button
    lateinit var tvErrorWarning: TextView
    lateinit var tvLoginPhoneNumberWarning: TextView
    lateinit var ivPasswordClear: ImageView
    lateinit var ivLoginNameClear: ImageView

    lateinit var loginViewModel : LoginViewModel

    override fun getLayoutId(): Int {
        return R.layout.layout_login
    }

    override fun initListener() {
        btnLogin.setOnClickListener(this)
        ivLoginNameClear.setOnClickListener { etLoginName.setText("") }
        ivPasswordClear.setOnClickListener { etLoginPassword.setText("") }

        textChangeListener()
    }

    override fun initViews() {
        etLoginName = findViewById(R.id.et_login_name)
        btnLogin = findViewById(R.id.button_login)
        etLoginPassword = findViewById(R.id.et_login_password)
        tvErrorWarning = findViewById(R.id.tv_error_waning)
        tvLoginPhoneNumberWarning = findViewById(R.id.tv_login_phone_number_warning)
        ivPasswordClear = findViewById(R.id.iv_password_clear)
        ivLoginNameClear = findViewById(R.id.iv_login_name_clear)

        visibleLoginClearIV(false)
        visiblePasswordClearIV(false)
    }

    override fun initData() {
        setBarState(loginRootView, R.color.white, true)
        BarUtils.addMarginTopEqualStatusBarHeight(loginRootView)

        loginViewModel = LoginViewModel()
        loginViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<LoginBean> {
            override fun success(result: LoginBean?) {
                if (result != null) {
                    UserInfoManager.instance.saveUserInfo(result)
                    ToastUtils.showShort("登录成功：${result.name}")
                    finish()
                }
            }
        })
    }

    private fun visibleLoginClearIV(isVisible: Boolean) {
        ivLoginNameClear.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun visiblePasswordClearIV(isVisible: Boolean) {
        ivPasswordClear.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun textChangeListener() {
        isClickLoginBtn(false)

        etLoginName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TextUtils.isEmpty(s)) {
                    visibleLoginClearIV(true)
                    isClickLoginBtn(true)
                } else {
                    showWarning(true, isShow = false)
                    visibleLoginClearIV(false)
                    isClickLoginBtn(false)
                }
            }

        })

        etLoginPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TextUtils.isEmpty(s)) {
                    visiblePasswordClearIV(true)
                    isClickLoginBtn(true)
                } else {
                    showWarning(false, isShow = false)
                    visiblePasswordClearIV(false)
                    isClickLoginBtn(false)
                }
            }

        })
    }

    private fun isClickLoginBtn(state: Boolean) {
        btnLogin.isSelected = state
        btnLogin.isClickable = state

        val number = etLoginName.text.toString()
        val password = etLoginPassword.text.toString()
        if (!number.isEmpty() || !password.isEmpty()) {
            btnLogin.isSelected = true
            btnLogin.isClickable = true
        }
    }

    fun login() {
        val account: String = etLoginName.text.toString()
        val password: String = etLoginPassword.text.toString()

        KeyboardUtils.hideSoftInput(this)

        if (!verifyLoginRule(account, password)) return

        showWarning(false, isShow = false)

        loginViewModel.userName = account
        loginViewModel.password = password
        loginViewModel.requestData()
    }

    /**
     * 校验登录规则:
     * 1. 正则校验手机号
     * 2. 至少6位的密码
     */
    private fun verifyLoginRule(account: String, password: String): Boolean {
        if (TextUtils.isEmpty(account)) {
            showWarning(true, isShow = true)
            return false
        }

        if (!RegexUtils.isMobileExact(account)) {
            showWarning(true, isShow = true)
            return false
        }

        if (TextUtils.isEmpty(password)) {
            showWarning(false, isShow = true)
            return false
        }

        if (password.length < MIN_PWD_COUNT) {
            showWarning(false, isShow = true)
            return false
        }
        return true
    }

    private fun showWarning(isPhone: Boolean, isShow: Boolean) {
        if (isPhone) {
            tvLoginPhoneNumberWarning.visibility = if (isShow) View.VISIBLE else View.GONE
        } else {
            tvErrorWarning.visibility = if (isShow) View.VISIBLE else View.GONE
        }
    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.button_login -> {
                login()
            }
        }

    }

    fun onRegister(v: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun onBack(v: View) {
        finish()
    }

}