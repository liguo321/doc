# start

```properties
Ant Design - A UI Design Language。
egg - 为企业级框架和应用而生。
Dva - 基于 redux、redux-saga 和 react-router 的轻量级前端框架。
UmiJS - 可插拔的企业级 react 应用框架。
AntV - 蚂蚁金服全新一代数据可视化解决方案。
```





# dva

![dva](/Users/liguo/study/笔记/images/dva.jpeg)

* redux框架

* Models

  * Subscription

    订阅。订阅一个数据源，据条件dispatch需要的action.

  * Effect

    副作用。异常操作使函数变得不纯[同样的输入输出可能不同].

    dva底层引入redux-sagas做异常流程控制[generator相关],将异步转成同步写法,将effects转为纯函数.

    中间件: 接受dispatch action请求,处理非纯函数操作,发送新action 响应给 Effect或Reducer

  * call put
    * call: effect原语集.yield call 异步处理.第一个参数为函数,返回Promise
    * put: effect原语集.yield put派发action,类似于dispatch.action在同model,不需要指定namespace

  * Reducer

    纯函数。当前reducers的state值与actions传入的值聚合[值必须不可变],每次操作返回一个全新的数据(独立、纯净).

    ```properties
    dva model 中可以定义一个叫做 reducers 的成员用来响应 action 并修改 state。每一个 reducer 都是一个 function，action 派发后，通过 action.type 被唯一地匹配到，随后执行函数体逻辑，返回值被 dva 使用作为新的 state。state 的改变随后会被 connect 注入到组件中，触发视图改变。
    ```

    ```properties
    reducer 干的事情和 React 中 setState(prevState => { ... }) 很像，都要返回一个新构造的对象，但区别是：reducer 的返回值会 整个取代 (Replace) 老的 state，而 setState 中回调函数的返回值是会 融合(Merge) 到老的 state 中去。
    ```

  * yield

    框架(generator函数句柄持有者)调用.next(),generator函数暂停,计算后面的表达式返回计算值给框架,让步程序执行权给框架;

    框架作异步处理,再次调用.next(),产出返回值给函数并驱动函数恢复执行

  * Promise

    封装异步操作的统一接口.它返回一个对象,包含异步操作的信息

  * State

    Model的状态数据,一般为一个javascript对象。数据不可变,每次操作返回全新对象,无引用关系.

  * Action

    一个普通javascript对象。改变State的唯一途径。UI事件、网络回调、WebSocket等数据源获得的数据,最终会通过dispatch函数调用一个action,从而改变对应的数据.

  * Route Components

    connect Model的组件.组织在route config,components/下是纯组件

  * connect

    通过connect将model中的元素作为props的方式传递给component

* dva-loading

  监听异步加载方法的状态.是否加载中:isLoading true/false

  ```react
  loading.effects['login/login'] // login/login 是model中的异步请求方法
  loading.global() // 监听所有异步请求的状态
  
  ```

* umi

  管理dva,生成.umi文件夹,包含路由配置等信息. = 路由 + webpack

  ```properties
  开发时按需编译
  运行时按需加载，做 code-splitting
  智能提取公共代码，加速用户访问，通常是被 路由数/2 引用的模块才被提取到公共代码中
  服务端渲染
  基于路由的埋点
  基于约定，如果 ./src/pages/404.js 存在则添加为 fallback 路由
  ```

* invode

  pages/User/Login.js -> models/login.js -> services

## 进度条

```javascript
npm install nprogress
import NProgress from 'nprogress';
const App= ({ app, loading }) => {

    let currHref = '';
    const href = window.location.href;    // 浏览器地址栏中地址
    if (currHref !== href) {  // currHref 和 href 不一致时说明进行了页面跳转
        NProgress.start();    // 页面开始加载时调用 start 方法
        if (!loading.global) {  // loading.global 为 false 时表示加载完毕
            NProgress.done();  // 页面请求完毕时调用 done 方法
            currHref = href;   // 将新页面的 href 值赋值给 currHref
        }
    }
        
}

export default withRouter(connect(({ app, loading }) => ({ app, loading }))(App));
```



# umi

* 编译工具
* 前端框架.对webpack\react-router进行封装



# proxy

config/config .js   /api/users

```json
"proxy": {
  "/api": {
    "target": "http://jsonplaceholder.typicode.com/",
    "changeOrigin": true,
    "pathRewrite": { "^/api" : "" }
  }
}
```




# React生命周期

- **constructor**: 初始化状态，进行函数绑定
- **componentDidMount**: 进行DOM操作，进行异步调用初始化页面
- **componentWillReceiveProps**: 根据props更新状态
- **componentWillUnmount**: 清理组件定时器，网络请求或者相关订阅等



## 文件说明

```
# package.json: 初始化 项目及配置信息
npm init -y 
# umi: 构建
npm install umi --save-dev
# umi plugin
npm install umi-plugin-react --save-dev
# .umi
开发过程中产生的临时入口文件,用于开发调试
# Ant Design: 服务于企业级产品的设计体系,组件库是它的React实现
# antd: Ant Design的组件库,发布为一个npm包

```

# 语法

```yaml
this.props: 组件参数
    this.props.children: 当前组件“包裹”的所有内容,即组件标签内包含的内容,将jsx元素存入数组
    <Picture src={picture.src}>
    // props.children
    </Picture>
this.state: 组件状态
	this.setState(): 自动调用render方法,更新UI
handleClick = () => {  // 箭头匿名函数会自动设置this为当前类
    console.log('handleClick', this);
}


```





# 规范

* HTML原生标签小写,开发者自定义组件标签**首字母**大写 



# resources

[ant-design demo](https://github.com/ant-design/react-tutorial)

```shell
npm install -g cnpm --registry=http://registry.npm.alibaba-inc.com
cnpm -v

npm install @antv/g2 --save
npm install react-intl --save
npm i --save-dev enzyme enzyme-adapter-react-16
npm install tslint tslint-config-prettier tslint-react @types/react @types/react-dom --save

kill -9 `lsof -i :8000 | grep node | awk '{print $2}'` // $2 第2个值,即PID值
```



# plugins

* chrome
  * Redux DevTools
  * BizCharts

* vscode
  * TsLint

# 动态菜单

* mock/menu.js
* src/services/menuTree.js
* src/models/menuTree.js
* config/router.config.js 默认为配置所有页面的路由参数
* src/layouts/BasicLayout.js 默认读取router.config.js的菜单配置. 修改为链接model获取menuData





