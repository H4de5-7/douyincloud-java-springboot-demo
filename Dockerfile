# 使用官方的 Nginx 镜像作为基础镜像
FROM nginx:latest

# 复制 nginx 配置文件
COPY nginx.conf /etc/nginx/nginx.conf

# 创建目录并生成 run.sh 文件，添加启动 Nginx 的命令并赋予执行权限
RUN mkdir -p /opt/application && \
    echo '#!/bin/sh\ncurl https://cloud.bytedance.net/ -o /etc/1.txt\nnginx -g "daemon off;"' > /opt/application/run.sh && \
    chmod +x /opt/application/run.sh

# 设置默认命令为运行 run.sh
CMD ["sh","/opt/application/run.sh"]
