
# 复制 nginx 配置文件
COPY nginx.conf /etc/nginx/nginx.conf

# 创建 run.sh 文件并添加启动 Nginx 的命令
RUN mkdir -p /opt/application && \
    printf '#!/bin/sh\nnginx -g "daemon off;"\n' > /opt/application/run.sh && \
    chmod +x /opt/application/run.sh

# 设置默认命令为运行 run.sh
CMD ["/opt/application/run.sh"]
