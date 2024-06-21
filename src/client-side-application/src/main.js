import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'
// import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App);

// 全局注册方法
app.use(ElementPlus);
app.use(router).mount('#app');
