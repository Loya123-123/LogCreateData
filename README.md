# LogCreateData

```txt
通过 java for 造app行为数据 
执行方式 java -jar ***.jar 200 1000
两个参数含义：
参数一：控制发送每条的延时时间，默认是0 （毫秒）
参数二：循环遍历次数 默认 1000

cm:公共日志
 mid;设备唯一标识
 uid;用户uid
 vc; versionCode，程序版本号
 vn; versionName，程序版本名
 l;  系统语言
 sr; 渠道号，应用从哪个渠道来的。
 os; Android系统版本
 ar; 区域
 md; 手机型号
 ba; 手机品牌
 sv; sdkVersion
 g;  gmail
 hw; heightXwidth，屏幕宽高
 t;  客户端日志产生时的时间
 nw; 网络模式
 ln; lng经度
 la; lat 纬度
```

```json
{
"ap":"xxxxx",//项目数据来源 app pc
"cm": { //公共字段
"mid": "", // (String) 设备唯一标识
"uid": "", // (String) 用户标识
"vc": "1", // (String) versionCode，程序版本号
"vn": "1.0", // (String) versionName，程序版本名
"l": "zh", // (String) language 系统语言
"sr": "", // (String) 渠道号，应用从哪个渠道来的。
"os": "7.1.1", // (String) Android 系统版本
"ar": "CN", // (String) area 区域
"md": "BBB100-1", // (String) model 手机型号
"ba": "blackberry", // (String) brand 手机品牌
"sv": "V2.2.1", // (String) sdkVersion
"g": "", // (String) gmail
"hw": "1620x1080", // (String) heightXwidth，屏幕宽高
"t": "1506047606608", // (String) 客户端日志产生时的时间
"nw": "WIFI", // (String) 网络模式
"ln": 0, // (double) lng 经度
"la": 0 // (double) lat 纬度
},
"et": [ //事件
{
"ett": "1506047605364", //客户端事件产生时间
"en": "display", //事件名称
"kv": { //事件结果，以 key-value 形式自行定义
"goodsid": "236",
"action": "1",
"extend1": "1",
"place": "2",
"category": "75"
} } ] }

```
```txt
事件名称：loading
标签 含义
action 动作：开始加载=1，加载成功=2，加载失败=3
loading_time 加载时长：计算下拉开始到接口返回数据的时间，（开始加载报 0，加载成
功或加载失败才上报时间）
loading_way 加载类型：1-读取缓存，2-从接口拉新数据
（加载成功才上报加载类型）
extend1 扩展字段 Extend1
extend2 扩展字段 Extend2
type 加载类型：自动加载=1，用户下拽加载=2，底部加载=3（底部条触发点击
底部提示条/点击返回顶部加载）
type1 加载失败码：把加载失败状态码报回来（报空为加载成功，没有失败）

事件标签：display
标签 含义
action 动作：曝光商品=1，点击商品=2，
goodsid 商品 ID（服务端下发的 ID）
place 顺序（第几条商品，第一条为 0，第二条为 1，如此类推）
extend1 曝光类型：1 - 首次曝光 2-重复曝光
category 分类 ID（服务端定义的分类 ID）

事件标签：newsdetail
标签 含义
entry 页面入口来源：应用首页=1、push=2、详情页相关推荐=3
action 动作：开始加载=1，加载成功=2（pv），加载失败=3, 退出页面=4
goodsid 商品 ID（服务端下发的 ID）
show_style 商品样式：0、无图、1、一张大图、2、两张图、3、三张小图、4、一张小图、5、
一张大图两张小图
news_staytime 页面停留时长：从商品开始加载时开始计算，到用户关闭页面所用的时间。若中途
用跳转到其它页面了，则暂停计时，待回到详情页时恢复计时。或中途划出的时间
超过 10 分钟，则本次计时作废，不上报本次数据。如未加载成功退出，则报空。
loading_time 加载时长：计算页面开始加载到接口返回数据的时间 （开始加载报 0，加载成功或
加载失败才上报时间）
type1 加载失败码：把加载失败状态码报回来（报空为加载成功，没有失败）
category 分类 ID（服务端定义的分类 ID）


购物车标签：cart
标签 含义
itemid 商品
action 操作类型：1 添加购物车；2 改变商品数量；3 移除商品
change_num 加减数量
before_num 更改前数量
after_num 更改后数量
price 商品单价

事件名称：ad
标签 含义
entry 入口：商品列表页=1 应用首页=2 商品详情页=3
action 动作：请求广告=1 取缓存广告=2 广告位展示=3 广告展示=4 广告点击=5
content 状态：成功=1 失败=2
detail 失败码（没有则上报空）
source 广告来源:admob=1 facebook=2 ADX（百度）=3 VK（头条）=4
behavior 用户行为：
主动获取广告=1
被动获取广告=2
newstype Type: 1- 图文 2-图集 3-段子 4-GIF 5-视频 6-调查 7-纯文 8-视频+图文 9-GI
F+图文 0-其他
show_style 内容样式：无图(纯文字)=6 一张大图=1 三站小图+文=4 一张小图=2 一张大图
两张小图+文=3 图集+文 = 5
一张大图+文=11 GIF 大图+文=12 视频(大图)+文 = 13
来源于详情页相关推荐的商品，上报样式都为 0（因为都是左文右图）

事件标签：notification
标签 含义
action
动作：通知产生=1，通知弹出=2，通知点击=3，常驻通知展示（不重复上报，
一天之内只报一次）=4
type 通知 id：预警通知=1，天气预报（早=2，晚=3），常驻=4
ap_time 客户端弹出时间
content 备用字段

评论表标签：comment
标签 含义 字段类型 长度 允许空 缺省值
comment_id 评论表 int 10,0
userid 用户 id int 10,0 √ 0
p_comment_id
父级评论 id(为 0 则是一
级评论,不为 0 则是回复)
int 10,0 √
content 评论内容 string 1000 √
addtime 创建时间 string √
other_id 评论的相关 id int 10,0 √
praise_count 点赞数量 int 10,0 √ 0
reply_count 回复数量 int 10,0 √ 0

收藏标签：favorites
标签 含义 字段类型 长度 允许空 缺省值
id 主键 int 10,0
course_id 商品 id int 10,0 √ 0
userid 用户 ID int 10,0 √ 0
add_time 创建时间 string √

点赞标签：praise
标签 含义 字段类型 长度 允许空 缺省值
id 主键 id int 10,0
userid 用户 id int 10,0 √
target_id 点赞的对象 id int 10,0 √
type
点赞类型 1 问答点赞 2 问
答评论点赞 3 文章点赞数
4 评论点赞
int 10,0 √
add_time 添加时间 string √

错误日志标签：error
标签 含义
errorBrief 错误摘要
errorDetail 错误详情

启动日志标签：start
标签 含义
entry
入口： push=1 ， widget=2 ， icon=3 ， notification=4, 
lockscreen_widget =5
open_ad_type 开屏广告类型: 开屏原生广告=1, 开屏插屏广告=2
action 状态：成功=1 失败=2
loading_time 加载时长：计算下拉开始到接口返回数据的时间，（开始加载报 0，加载
成功或加载失败才上报时间）
detail 失败码（没有则上报空）
extend1 失败的 message（没有则上报空）
en 日志类型 start

```

产出数据例子：
2020-05-08 10:42:41.047 [main] INFO  com.loya.appclient.LogCreateData - 1588905761039|
```json
{
  "cm": {
    "ln": "-107.8",
    "sv": "V2.2.8",
    "os": "8.0.0",
    "g": "MDHXWV3J@gmail.com",
    "mid": "0",
    "nw": "4G",
    "l": "es",
    "vc": "13",
    "hw": "640*960",
    "ar": "MX",
    "uid": "0",
    "t": "1588865132393",
    "la": "-1.6",
    "md": "sumsung-16",
    "vn": "1.2.0",
    "ba": "Sumsung",
    "sr": "T"
  },
  "ap": "app",
  "et": [
    {
      "uid": 1,
      "ett": "1588850400375",
      "en": "start",
      "kv": {
        "entry": "1",
        "loading_time": "1",
        "action": "1",
        "open_ad_type": "2",
        "detail": "542"
      }
    },
    {
      "uid": 1,
      "ett": "1588902713874",
      "en": "display",
      "kv": {
        "goodsid": "0",
        "action": "1",
        "extend1": "2",
        "place": "3",
        "category": "70"
      }
    },
    {
      "uid": 1,
      "ett": "1588905504783",
      "en": "newsdetail",
      "kv": {
        "entry": "3",
        "goodsid": "0",
        "news_staytime": "0",
        "loading_time": "4",
        "action": "4",
        "showtype": "0",
        "category": "93",
        "type1": ""
      }
    },
    {
      "uid": 1,
      "ett": "1588867462308",
      "en": "loading",
      "kv": {
        "extend2": "",
        "loading_time": "0",
        "action": "1",
        "extend1": "",
        "type": "2",
        "type1": "325",
        "loading_way": "1"
      }
    },
    {
      "uid": 1,
      "ett": "1588864917115",
      "en": "favorites",
      "kv": {
        "itemid": 1,
        "beforeNum": 1,
        "action": 1,
        "changeNum": 0,
        "afterNum": 0
      }
    },
    {
      "uid": 1,
      "ett": "1588896112386",
      "en": "ad",
      "kv": {
        "entry": "1",
        "show_style": "5",
        "action": "3",
        "detail": "542",
        "source": "4",
        "behavior": "2",
        "content": "2",
        "newstype": "4"
      }
    },
    {
      "uid": 1,
      "ett": "1588809137087",
      "en": "start",
      "kv": {
        "entry": "3",
        "loading_time": "3",
        "action": "1",
        "open_ad_type": "1",
        "detail": "325"
      }
    },
    {
      "uid": 1,
      "ett": "1588825931795",
      "en": "error",
      "kv": {
        "errorDetail": "java.lang.NullPointerException    at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72) at cn.lift.dfdf.web.AbstractBaseController.validInbound",
        "errorBrief": "at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)"
      }
    },
    {
      "uid": 1,
      "ett": "1588879198412",
      "en": "comment",
      "kv": {
        "p_comment_id": 4,
        "addtime": "1588866212278",
        "praise_count": 456,
        "other_id": 4,
        "comment_id": 8,
        "reply_count": 136,
        "userid": 6,
        "content": "踞胜烛揉研"
      }
    },
    {
      "uid": 1,
      "ett": "1588877671710",
      "en": "favorites",
      "kv": {
        "course_id": 3,
        "id": 0,
        "add_time": "1588904707843",
        "userid": 1
      }
    }
  ]
}
```
