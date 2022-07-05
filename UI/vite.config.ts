import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import VitePluginRestart from "vite-plugin-restart";
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from "unplugin-vue-components/resolvers";
import {ElementPlusResolve,} from 'vite-plugin-style-import'
import {createStyleImportPlugin} from "vite-plugin-style-import";
import {createHtmlPlugin} from "vite-plugin-html";
import {createSvgIconsPlugin} from "vite-plugin-svg-icons";
import VitePluginCompression from "vite-plugin-compression";
import vueSetupExtend from 'vite-plugin-vue-setup-extend'
import vuejsx from "@vitejs/plugin-vue-jsx"

// node js 提供的文件路径工作类
import path = require("path");

// https://vitejs.dev/config/
export default defineConfig(({command, mode}) => {
    // 加载 env 环境变量
    const env = loadEnv(mode, process.cwd(), '')
    const base = {
        plugins: [
            vue(),
            // 监听对应的文件变化自动重启程序
            VitePluginRestart({
                restart: [
                    '.env',
                    '.env.development',
                    '.env.production',
                ]
            }),
            // 组件按需自动导入插件
            Components({
                resolvers: [
                    ElementPlusResolver()
                ]
            }),
            // 自动导入相关UI组件缺失的样式
            createStyleImportPlugin({
                resolves: [
                    ElementPlusResolve(),
                ],
            }),
            // index.html 模板语法支持插件
            createHtmlPlugin({
                minify: true,
                entry: '/src/main.ts',
                inject: {
                    // 模板语法支持的数据
                    data: {
                        title: env.out_app_name
                    }
                }
            }),
            // svg 图片加载插件
            createSvgIconsPlugin({
                // Specify the icon folder to be cached
                iconDirs: [path.resolve(process.cwd(), 'src/svg')],
                // Specify symbolId format
                symbolId: 'icon-[dir]-[name]'
            }),
            // 使用 gzip 或者 brotli 来压缩资源 插件
            VitePluginCompression(),
            // vue3 setup 增强插件 提供 name 属性 搭配后面 keep alive 使用
            vueSetupExtend(),
            // jsx 语法支持 （jsx组件中自动跳过生命周期，即jsx中没有生命周期，在父组件onBeforeMount后执行）
            vuejsx()
        ]
    }
    // https://vitejs.dev/config/
    return {
        ...base,
        // vite config 定义能通过 process.env 获取到的环境变量
        define: {
            __APP_ENV__: env.APP_ENV
        },
        envDir: './',
        envPrefix: 'out_'
    }
})
