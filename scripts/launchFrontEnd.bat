@echo off

setlocal

:: 设置变量
set MY_VAR=Hello, World!

:: 新建一个终端窗口并在其中使用变量
start cmd /k "cd client-side-application\src\components\inputWindowComponent\SnakeGame && npm run dev"

:: 在当前终端窗口中使用变量
cd client-side-application
npm run serve
pause

endlocal
