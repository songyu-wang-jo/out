import {createApp} from 'vue'
import App from './App.vue'

// 引入 svg 图片组件注册插件
import 'virtual:svg-icons-register'

import router from "./router";
import store from "./store";

const app = createApp(App);
app.use(router)
app.use(store)
app.mount('#app')
