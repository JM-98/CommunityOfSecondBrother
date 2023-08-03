## 二师兄的在线论坛

## 资料
https://spring.io/guides/

Whatever you're building, these guides are designed to get you productive as quickly as possible – using the latest Spring project releases and techniques as recommended by the Spring team.

https://elasticsearch.cn/

elasticsearch中文社区

https://square.github.io/okhttp/

OkHttp is an HTTP client that’s efficient by default:

HTTP/2 support allows all requests to the same host to share a socket.

Connection pooling reduces request latency (if HTTP/2 isn’t available).

Transparent GZIP shrinks download sizes.

Response caching avoids the network completely for repeat requests.

https://www.h2database.com/html/quickstart.html

Very fast, open source, JDBC API

Embedded and server modes; in-memory databases

Browser based Console application

Small footprint: around 2.5 MB jar file size


参考文档

https://juejin.cn/post/7114599387510079496

## 工具
https://git-scm.com/

Git is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency.

https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/authorizing-oauth-apps

授权GitHub登录

[//]: # (IDEA功能：按住Ctrl，鼠标左键下拉多行，可以同时编辑)

## 脚本

'''sql
create table communityofsecondbrother
(
id int auto_increment primary key not null,
account_id   varchar(100),
name         varchar(50),
token        char(36),
gmt_create   bigint,
gmt_modified bigint
)collate = utf8mb3_general_ci;
'''