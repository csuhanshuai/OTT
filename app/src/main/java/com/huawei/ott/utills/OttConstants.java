package com.huawei.ott.utills;

/**
 * Created by Jonas on 2015/11/19.
 * 常量
 */
public class OttConstants
{
    /**
     * 获取数据成功
     */
    public static final String NETSUCCEED = "0";
    public static final String SESSIONID = "sessionid";
    public static final String ISPLTY = "isplty";
    /**
     * 自动登录
     */
    public static String AUTOLOGIN = "autologin";
    /**
     * 客户端配置本地存储
     */
    public static final String AndroidPhoneConfig = "AndroidPhoneConfig";
    /**
     * EPG端配置存储
     */
    public static final String EPGConfig = "EPGConfig";
    public static final String QUERYPROFILECONFIG = "QueryProfileConfig";
    public static final String SWITCHPROFILECONFIG = "switchProfileConfig";

    public static final String AUTHENTICATECOIFIG = "authenticatecoifig";


    public static final String OTTIP = "http://221.226.2.232:";
    public static final String EPGPORT = "http://221.226.2.232:17200";
    public static final String EDSPORT = "http://221.226.2.232:17682";
    /**
     * 是否打开引导界面
     */
    public static final String IS_OPEN_MAIN_PAGER = "IS_OPEN_MAIN_PAGER";

    public static final String OAUTH = "http://221.226.2.232:17200/EPG/JSON/TSSSOAuth";

    //登录认证  需要authenticator
    //    public static final String AUTHENTICATE =  EPGPORT+"/EPG/JSON/Authenticate";

    //当用户登录时，客户端必须调用3.3“Subscriber Authentication Interface”来验证用户。客户端可以调用这个登录界面获得enctytoken参数和其他参数，在认证要求。
    public static final String LOGIN = EPGPORT+"/EPG/JSON/Login?UserID=";

    public static final String EDSLOGIN = EDSPORT+"/EDS/JSON/Login?UserID=";

    public static final String AUTHENTICATION = "http://221.226.2.232:17200/EPG/JSON/Authentication";

    public static final String AUTHENTICATE = "http://221.226.2.232:17200/EPG/JSON/Authenticate";
    /**
     * 心跳接口
     */
    public static final String HEARTBIT = "http://221.226.2.232:17200/EPG/JSON/HeartBit";

    /**
     * 获取终端在平台侧的配置参数。
     */
    public static final String GETCUSTOMIZECONFIG = "http://221.226.2.232:17200/EPG/JSON/GetCustomizeConfig";

    /**
     * 查询用户的地理位置信息
     */
    public static final String QUERYLOCATION = "http://221.226.2.232:17200/EPG/JSON/QueryLocation";

    /**
     * 查询profile列表
     */
    public static final String QUERYPROFILE = "http://221.226.2.232:17200/EPG/JSON/QueryProfile";
    /**
     * 客户端使用此接口从配置文件切换到另一个配置文件。
     */
    public static final String SWITCHPROFILE = "http://221.226.2.232:17200/EPG/JSON/SwitchProfile";


    /**
     * 频道列表(查询所有频道的信息)
     */
    public static final String ALLCHANNEL = "http://221.226.2.232:17200/EPG/JSON/AllChannel";

    /**
     * VOD列表(点播列表指定类别下子类别列表)
     */
    public static final String VODLIST = "http://221.226.2.232:17200/EPG/JSON/VodList";

    /**
     * VOD列表(点播列表指定类别下子类别列表)
     */
    public static final String PLAY = "http://221.226.2.232:17200/EPG/JSON/Play";

    /**
     * 推荐
     */
    public static final String STATICRECMFILM = "http://221.226.2.232:17200/EPG/JSON/StaticRecmFilm";

    /**
     * 指定类型的子类别列表
     */
    public static final String CATEGORYLIST = "http://221.226.2.232:17200/EPG/JSON/CategoryList";

    /**
     * 推荐的视频点播列表
     */
    public static final String RECMVODLIST = "http://221.226.2.232:17200/EPG/JSON/RecmVodList";

    /**
     * 序列集列表
     */
    public static final String SITCOMLIST = "http://221.226.2.232:17200/EPG/JSON/SitcomList";

    /**
     * 查询通道列表
     */
    public static final String CHANNELLIST = "http://221.226.2.232:17200/EPG/JSON/ChannelList";

    /**
     * 获得指定通道的节目列表
     */
    public static final String PLAYBILLLIST = "http://221.226.2.232:17200/EPG/JSON/PlayBillList";

    /**
     * 客户端使用这个接口来获得当前的节目，以前的节目和下一个节目
     */
    public static final String PLAYBILLCONTEXT = "http://221.226.2.232:17200/EPG/JSON/PlayBillContext";

    /**
     * 客户端使用此接口从一个特定的类别取得增值服务表
     */
    public static final String VASLIST = "http://221.226.2.232:17200/EPG/JSON/VasList";

    /**
     * 查询基于内容外键的内容信息
     */
    public static final String GETCONTENTBYFOREIGNSN = "http://221.226.2.232:17200/EPG/JSON/GetContentByForeignSn";

    /**
     * 搜索
     */
    public static final String SEARCH = "http://221.226.2.232:17200/EPG/JSON/Search";

    /**
     * 搜索整个文本
     */
    public static final String FULLTEXTSEARCH = "http://221.226.2.232:17200/EPG/JSON/FullTextSearch";

    /**
     * 内容详细信息(内容包括视频点播节目，频道，分类，方法和节目。)
     */
    public static final String CONTENTDETAIL = "http://221.226.2.232:17200/EPG/JSON/ContentDetail";

    /**
     * 查询每周的排名和总排名的电视频道
     */
    public static final String CHANNELBOARD = "http://221.226.2.232:17200/EPG/JSON/ChannelBoard";

    /**
     * 查询直播的电视节目
     */
    public static final String QUERYPLAYBILL = "http://221.226.2.232:17200/EPG/JSON/QueryPlayBill";

    /**
     * 评价内容
     */
    public static final String SCORECONTENT = "http://221.226.2.232:17200/EPG/JSON/ScoreContent";

    /**
     * 查询一个区域内的推荐内容
     */
    public static final String QUERYRECMDREGIONCONTENT = "http://221.226.2.232:17200/EPG/JSON/QueryRecmdRegionContent";

    /**
     * 从指定的EPG类别查询推荐频道列表
     */
    public static final String RECOMMENDCHANNELLIST = "http://221.226.2.232:17200/EPG/JSON/RecommendChannelList";

    /**
     * 查询内容产生的区域的信息
     */
    public static final String QUERYPRODUCEZONE = "http://221.226.2.232:17200/EPG/JSON/QueryProduceZone";

    /**
     * 查询系统的马赛克通道
     */
    public static final String QUERYMOSAICCHANNEL = "http://221.226.2.232:17200/EPG/JSON/QueryMosaicChannel";

    /**
     * 此接口用于在客户端播放或停止一个实时的电视节目时，报告重放历史
     */
    public static final String PLAYRECORD = "http://221.226.2.232:17200/EPG/JSON/PlayRecord";

    /**
     * 记录页面访问的次数
     */
    public static final String UIACCESS = "http://221.226.2.232:17200/EPG/JSON/UIAccess";

    /**
     * 查询最热门的电视节目，并显示订阅的节目。
     */
    public static final String QUERYHOTPROGRAM = "http://221.226.2.232:17200/EPG/JSON/QueryHotProgram";

    /**
     * 当用户搜索内容时，系统可以列出相关的热点搜索关键词
     */
    public static final String QUERYHOTKEY = "http://221.226.2.232:17200/EPG/JSON/QueryHotKey";

    /**
     * 查询编码列表
     */
    public static final String GETISOCODETABLE = "http://221.226.2.232:17200/EPG/JSON/GetISOCodeTable";

    /**
     * 查询缓存中的内容数据的版本
     */
    public static final String GETDATAVERSION = "http://221.226.2.232:17200/EPG/JSON/GetDataVersion";


    /**
     * 搜索最频繁搜索的热词 
     */
    public static final String GETHOTKEYWORDS = "http://221.226.2.232:17200/EPG/JSON/GetHotKeywords";

    /**
     * 获得一个类别中的内容
     */
    public static final String GETCONTENTLISTBYCATEGORY = "http://221.226.2.232:17200/EPG/JSON/GetContentListByCategory";

    /**
     * 查询与产品相关联的内容列表
     */
    public static final String GETCONTENTBYPRODUCT = "http://221.226.2.232:17200/EPG/JSON/GetContentByProduct";

    /**
     * 获得当前节目、以前的节目和下个节目的信息
     */
    public static final String PLAYBILLCONTEXTEX = "http://221.226.2.232:17200/EPG/JSON/PlayBillContextEx";

    /**
     * 设置内容的全局过滤标准
     */
    public static final String SETGLOBALFILTERCOND = "http://221.226.2.232:17200/EPG/JSON/SetGlobalFilterCond";

    /**
     * 获得搜索的关键词自动推荐功能
     */
    public static final String GETASSOCIATEDKEYWORDS = "http://221.226.2.232:17200/EPG/JSON/GetAssociatedKeywords";



    /**
     * MEM提供用于查询前N和访问记录在最近X天的UI界面
     */
    public static final String GETTOPNTVODPROGRAM = "http://221.226.2.232:17200/EPG/JSON/getTopNTVODProgram";

    /**
     * 报告操作，包括页面浏览和用户在页面上进行的内容播放
     */
    public static final String USERTRACE = "http://221.226.2.232:17200/EPG/JSON/UserTrace";

    /**
     * 查询所有的产品，包括那些已经和未被订阅的产品
     */
    public static final String GETALLPRODUCTS = "http://221.226.2.232:17200/EPG/JSON/GetAllProducts";
    /**
     * 推荐
     */
    public static final String QUERYDYNAMICRECMFILM = "http://221.226.2.232:17200/EPG/JSON/QueryDynamicRecmFilm";
    /**
     * 下载存云盘
     */
    public static final String DOWNLOAD = "http://221.226.2.232:17200/EPG/JSON/AddPVR";

    /**
     * 验证内容并返回验证成功后内容播放URL
     */
    public static final String AUTHORIZEANDPLAY = "http://221.226.2.232:17200/EPG/JSON/AuthorizeAndPlay";

    /**
     * 获取频道分类
     */
    public static final String GETGENRELIST = "http://221.226.2.232:17200/EPG/JSON/GetGenreList";
    /**
     * 添加到收藏
     */
    public static final String FAVARITEOPERATION = "http://221.226.2.232:17200/EPG/JSON/FavoriteManagement";

    public static final String CHANNELMORE = "ChannelMore";
    public static final String DEMANDFM = "Demandfm";
    /**
     * 存储用户名
     */
    public static final String username = "username";
    /**
     * 存储 密码
     */
    public static final String password = "password";
    /**
     * 是否记住密码
     */
    public static final String remember = "remember";
    /**
     * 无网络提示
     */
    public static final String NONET = "No network connection";
    /**
     * 密码或者用户名为空提示
     */
    public static final String UPNULL = "Psername or Password can't be null";

    /**
     * 点播界面的title
     */
    public static String ONDEMAND = "On Demand";
    /**
     * 直播界面的title
     */
    public static String LIVETV = "Live TV";

    /**
     * 请求查询返回结果的数量的参数名
     */

    public static String COUNT = "count";
    /**
     * 请求查询返回结果的偏移量的参数名，建议为一屏能显示的数量或者每次滑动刷新时所期望的获取量
     */
    public static String OFFSET = "offset";
    /**
     * 请求查询的动作类型
     */
    public static String ACTION = "action";

    public static String NAME = "name";
    public static String ID = "id";
    public static String ACTOR = "actor";
    public static String DIRECTOR = "director";
    public static String ADAPTOR = "adaptor";
    public static String PLAYTYPE = "playType";
    public static String SCORE = "score";
    public static String CHANNO = "channo";
    public static String MEDIAID = "mediaid";
    public static String FILTERLIST = "filterlist";
    public static String BEGINTIME = "begintime";

    public static String TYPE = "type";
    public static String ORDERTYPE = "orderType";
    public static String PROGRAMFM = "programfm";
    public static String RECOMMENDEDFM = "Recommendedfm";
    public static String NETWORK = "network";
    public static String SEARCHRECENT = "search_recent";
    public static String KEY = "key";
    public static String CONTENTTYPE = "contenttype";
    public static String UNKNOW = "Unknow";
    public static String ALLCONTENTTYPE = "VOD,TELEPLAY_VOD,CHANNEL,PROGRAM,TVOD,VAS,AUDIO_CHANNEL,VIDEO_CHANNEL,WEB_CHANNEL,AUDIO_VOD,VIDEO_VOD";
    public static String UPE = "Username or Password error";
    public static String INTRODUCE = "introduce";
    public static String errorurl = "error url!";
    public static String screenw = "screenwidth";
    public static String screenh = "screenheight";
}
