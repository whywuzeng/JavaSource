layui.define(function (exports) {

    var HOSTAPI = "94.191.109.74:8081";

    // var HOSTAPI = "127.0.0.1:8081";

    var bossUrl = new REQUEST().BOSSURL;

    exports('api', {
        advertService: {
            all: bossUrl + '/advert/manager/all',
            add: bossUrl + '/advert/manager/add',
            delete: bossUrl + '/advert/manager/delete',
            update: bossUrl + '/advert/manager/update',

            enable: bossUrl + '/boss/SceneService/enable',
            disable: bossUrl + '/boss/SceneService/disable',
            displaySql: bossUrl + '/boss/SceneService/displaySql',
            listData: bossUrl + '/boss/SceneService/listData',
            getDetailData: bossUrl + '/boss/SceneService/getDetailData',
        },
        advertDetail:{
            all: bossUrl + '/advert/all',
            add: bossUrl + '/advert/add',
            delete: bossUrl + '/advert/delete',
            update: bossUrl + '/advert/update',
            upload: bossUrl + '/advert/image',
        },
        //线索管理
        trackmanager:{
            all: bossUrl + '/track/manager/all',
            add: bossUrl + '/track/manager/add',
            delete: bossUrl + '/track/manager/delete',
            update: bossUrl + '/track/manager/update',
        },
        //校区管理
        schoolmanager:{
            all: bossUrl + '/school/all',
            add: bossUrl + '/school/add',
            delete: bossUrl + '/school/delete',
            update: bossUrl + '/school/update',
        },//校区图片管理
        schoolPicmanager:{
            all: bossUrl + '/schoolPic/all',
            add: bossUrl + '/schoolPic/add',
            delete: bossUrl + '/schoolPic/delete',
            update: bossUrl + '/schoolPic/update',
        },
        //教师力量
        teachermanager:{
            all: bossUrl + '/teacher/all',
            add: bossUrl + '/teacher/add',
            delete: bossUrl + '/teacher/delete',
            update: bossUrl + '/teacher/update',
        },
        //荣耀管理
        honormanager:{
            all: bossUrl + '/honor/all',
            add: bossUrl + '/honor/add',
            delete: bossUrl + '/honor/delete',
            update: bossUrl + '/honor/update',
        },
        contentmanager:{
            all: bossUrl + '/content/manager/all',
            add: bossUrl + '/content/manager/add',
            delete: bossUrl + '/content/manager/delete',
            update: bossUrl + '/content/manager/update',
        },
        friendurl:{
            all: bossUrl + '/friend/all',
            add: bossUrl + '/friend/add',
            delete: bossUrl + '/friend/delete',
            update: bossUrl + '/friend/update',
        },
        tdkmanager:{
            all: bossUrl + '/tdkmanager/all',
            add: bossUrl + '/tdkmanager/add',
            delete: bossUrl + '/tdkmanager/delete',
            update: bossUrl + '/tdkmanager/update',
        },
    });

    /**
     * 根据环境获取请求地址
     * @constructor
     */
    function REQUEST() {
       this.BOSSURL = window.location.protocol + '//' + HOSTAPI ;
    }
});


