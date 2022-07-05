import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import VitePluginRestart from "vite-plugin-restart";
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from "unplugin-vue-components/resolvers";
import {ElementPlusResolve,} from 'vite-plugin-style-import';
import {createStyleImportPlugin} from "vite-plugin-style-import";
import {createHtmlPlugin} from "vite-plugin-html";
import {createSvgIconsPlugin} from "vite-plugin-svg-icons";
import VitePluginCompression from "vite-plugin-compression";
import vueSetupExtend from 'vite-plugin-vue-setup-extend';
import vuejsx from "@vitejs/plugin-vue-jsx";

// node js 提供的文件路径工作类
const path = require("path");

let serverPort = 9527

// https://vitejs.dev/config/
export default defineConfig(({command, mode}) => {
    // 加载 env 环境变量
    const env = loadEnv(mode, process.cwd(), '')
    if (env.out_server_port) {
        serverPort = Number(env.out_server_port)
    }
    const plugins = [
        // vue 框架支持
        vue(),
        // 使用 gzip 或者 brotli 来压缩资源 插件
        VitePluginCompression(),
        // vue3 setup 增强插件 提供 name 属性 搭配后面 keep alive 使用
        vueSetupExtend(),
        // jsx 语法支持 （jsx组件中自动跳过生命周期，即jsx中没有生命周期，在父组件onBeforeMount后执行）
        vuejsx(),
        // 组件按需自动导入插件
        Components({
            resolvers: [ElementPlusResolver()]
        }),
        // 组件按需自动导入
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        // 自动导入相关UI组件缺失的样式
        createStyleImportPlugin({
            resolves: [
                ElementPlusResolve(),
            ],
        }),
        // 监听对应的文件变化自动重启程序
        VitePluginRestart({
            restart: [
                '.env',
                '.env.development',
                '.env.production',
            ]
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
        })
    ]
    // https://vitejs.dev/config/
    return {
        server: {
            port: serverPort
        },
        build: {
            minify: true
        },
        plugins: plugins,
        // vite config 定义能通过 process.env 获取到的环境变量
        define: {
            'process.env': {...process.env, ...loadEnv(mode, process.cwd())}
        },
        envDir: './',
        envPrefix: 'out_',
        resolve: {
            alias: {
                '@': '/src'
            }
        },
        css: {
            //css预处理
            preprocessorOptions: {
                scss: {
                    /*
                    引入var.scss全局预定义变量，如果引入多个文件，可以使用
                    '@import "@/assets/scss/globalVariable1.scss";@import "@/assets/scss/globalVariable2.scss";'
                    这种格式
                     */
                    additionalData: '@import "@/assets/scss/global.scss";'
                }
            }
        }
    }
})
