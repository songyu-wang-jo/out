import {RouteRecordRaw} from "vue-router";

const pageRouters: RouteRecordRaw[] = [
    {
        path: '/',
        component: () => import("../../view/Index.vue"),
        meta: {title: '首页'},
        redirect: '/componentSearch'
    },
    {
        path: '/login',
        component: () => import("../../view/Login.vue"),
        meta: {title: '登录'}
    },
    {
        path: '/componentSearch',
        component: () => import("../../view/ComponentSearch.vue"),
        meta: {title: '组件测试'}
    }
]

export default pageRouters