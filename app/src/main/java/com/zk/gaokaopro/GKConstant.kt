package com.zk.gaokaopro

object GKConstant{
    val BASE_URL_ITEMS = arrayOf(
        "http://192.168.31.221:8001/GaoKao/",
        "https://zkteam.cc/GaoKao/",
        "http://yapi.gdky005.com/mock/31/",
        "http://rap2api.taobao.org/app/mock/165383/")

    val BASE_URL: String
        get() = this.BASE_URL_ITEMS[0]

    const val CODE_SUCCESS = 0

    const val PIC_COLUMN = 4

    //失效用户
    const val USER_DEFAULT_INVALID_UID = -1

    const val SP_FILE_NAME_USER_INFO = "sp_file_name_user_info"
    const val SP_KEY_USER_UID = "sp_key_user_uid"
    const val SP_KEY_USER_NAME = "sp_key_user_name"
    const val SP_KEY_USER_MOBILE = "sp_key_user_mobile"




    // webview  调整参数
    const val FLAG_WEBVIEW_URL = "flag_webview_url"


    val images = mutableListOf(
        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3351381896,4018125707&fm=26&gp=0.jpg",
        "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1218711089,2266838887&fm=26&gp=0.jpg",
        "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=283299632,427497639&fm=26&gp=0.jpg",
        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=136990643,61379176&fm=26&gp=0.jpg",
        "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1288117223,3597668578&fm=26&gp=0.jpg"
    )
}