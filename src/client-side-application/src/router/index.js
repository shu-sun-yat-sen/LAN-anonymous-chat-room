import {createRouter, createWebHistory} from 'vue-router';

import App from '@/App.vue';
import ChessGame from '@/components/inputWindowComponent/ChessGame.vue';


// const routes = [
//     {
//         path: '/',
//         name: 'App',
//         component: App,
//         meta: { 
//             showExtraContent: true
//         }
//     },
//     {
//         path: '/ChessGame',
//         name: 'ChessGame',
//         component: ChessGame,
//         meta: { showExtraContent: false },
//         children :[
//             {
//                 path : 'child/:id',

//             }
//         ]
//     }
// ];

const routes = [
    {
        path: '/',
        name: 'App',
        component: App,
        meta: { 
            showExtraContent: true
        },
        children: [
            {
                path : 'ChessGame',
                name: 'ChessGame',
                component: ChessGame,
                meta: { showExtraContent: false }
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;