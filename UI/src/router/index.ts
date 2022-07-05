import {createRouter, createWebHashHistory, RouteRecordRaw, RouterOptions} from "vue-router";
import pageRouters from "./modules/view.routes";

function initRoutes(): RouteRecordRaw[] {
    return pageRouters
}

const routes = initRoutes()

const routerOption: RouterOptions = {
    history: createWebHashHistory(),
    routes
}

const router = createRouter(routerOption)

router.beforeEach((to, from, next) => {
    // 如果路由 元数据携带 title 则将网页的标题设置为 SongYu | ${title}
    if (to.meta.title) {
        document.title = 'SongYu | ' + to.meta.title
    }
    next()
})

export default router