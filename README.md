# GaoKaoPro
高考相关 app

[![CircleCI](https://circleci.com/gh/gdky005/TestCircleCI2/tree/master.svg?style=svg)](https://circleci.com/gh/gdky005/TestCircleCI2/tree/master)


## 网络使用范例

1. 创建 RecommendViewModel

```

class RecommendViewModel : BaseViewModel<ArrayList<RecommendBean>>() { //继承 BaseViewModel， 里面的泛型写上当前请求 result的返回类型

    var id = 0 // 请求参数1
    var age = 0 //请求参数2

    override fun getObservable(): Observable<GKBaseBean<ArrayList<RecommendBean>>> {
//        return gkApi.requestRecommend(id, age) //添加请求参数
        return gkApi.requestRecommend()
    }
}
```


2. 当前类只需实例化一个

```
private val recommendViewModel = RecommendViewModel()
```

3. 添加一个观察者

```
recommendViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<RecommendBean>>{
    override fun success(result: ArrayList<RecommendBean>?) {
        message.text = result.toString()
    }
})
```

4. 发起请求
```
recommendViewModel.id = 1 //添加请求参数
recommendViewModel.age = 22 //添加请求参数2
recommendViewModel.requestData()
```

## 图片设置

```
Glide.with(fragment).
    load(iconUrl).error( R.drawable.error) //异常时候显示的图片
    .placeholder( R.drawable.default) //加载成功前显示的图片
    .fallback( R.drawable.blank) //url为空的时候,显示的图片
    .into(Icon);//在RequestBuilder 中使用自定义的ImageViewTarget

```
