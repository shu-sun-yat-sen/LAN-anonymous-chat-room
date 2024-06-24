@echo off

setlocal

:: 设置变量
set MY_VAR=Hello, World!

REM 检查是否全局安装了http-server
npm list -g --depth=0 | findstr /i "http-server"

if %errorlevel% equ 0 (
    :: 新建一个终端窗口并在其中使用变量
    start cmd /k "cd FrontEndPackage\SnakeDist && http-server -p 8082"
    :: 在当前终端窗口中使用变量
    cd  FrontEndPackage\MainDist
    http-server -p 8081
) else (
    echo http-server not install.
)
endlocal
