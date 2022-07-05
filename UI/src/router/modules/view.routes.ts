import {RouteRecordRaw} from "vue-router";

const pageRouters: RouteRecordRaw[] = [
    {
        path: '/',
        component: () => import("@/view/Index.vue"),
        meta: {title: '首页'}
    },
    {
        path: '/login',
        component: () => import("@/view/Login.vue"),
        meta: {title: '登录'}
    }
]

export default pageRouters