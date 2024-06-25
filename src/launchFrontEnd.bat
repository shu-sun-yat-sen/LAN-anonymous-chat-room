@echo on

:: 设置变量
set MY_VAR=Hello, World!

npm list -g --depth=0 | findstr /i "http-server"

if %errorlevel% equ 0 (
start cmd /k "cd FrontEndPackage\SnakeDist && http-server -p 8082"
cd  FrontEndPackage\MainDist
http-server -p 8081
) else (
echo http-server not install.
pause
exit
)


