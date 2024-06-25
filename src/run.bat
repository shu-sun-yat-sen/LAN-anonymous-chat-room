@echo off

rem 切换到前端应用的目录并启动前端应用
echo Starting client-side application...
cd client-side-application/
start cmd /k "npm run serve"

rem 检查前端应用是否启动成功
if %errorlevel% neq 0 (
    echo Failed to start client-side application!
    exit /b %errorlevel%
)

rem 返回到脚本所在的根目录
cd ..

rem 等待几秒钟以确保前端服务器启动
timeout /t 5 /nobreak

rem 切换到后端应用的目录并启动后端应用
echo Starting server-side application...
cd server2/
java -jar demo1-0.0.1-SNAPSHOT.jar

rem 检查后端应用是否启动成功
if %errorlevel% neq 0 (
    echo Failed to start server-side application!
    exit /b %errorlevel%
)

echo Both applications started successfully.
pause