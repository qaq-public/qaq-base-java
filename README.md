# qaq-base-java

qaq 的 java 公用代码，旨在让 QAQ 开发者便捷的调用 QAQ 开放 API。
[发布 Maven 包的正确姿势](https://zhuanlan.zhihu.com/p/141676033)

## 目录
<!-- toc -->

- [安装](#安装)
- [加入答疑群](#加入答疑群)
- [License](#License)

<!-- tocstop -->

## 安装

- 运行环境：JDK 17 及以上

- 最新版本 maven 坐标

```shell
<dependency>
    <groupId>com.qaq.base</groupId>
    <artifactId>qaq</artifactId>
    <version>1.0.0</version>
</dependency>
```

- 如无法获取 qaq 依赖，请在 pom.xml 的 <project> 里增加 <repositories>

```shell
<project>
    <repositories>
        <repository>
            <id>gitpub-qaq-repo</id>
            <name>The QAQ Repository on Github</name>
            <url>https://qaq-public.github.io/qaq-base-java/maven-repo/</url>
        </repository>
    </repositories>
    <dependencies>
        ...
    </dependencies>
</project>
```

## 加入答疑群

[单击加入答疑群](https://applink.feishu.cn/client/chat/chatter/add_by_link?link_token=828s731f-83f2-400a-b093-04667ca93d4c)

## License
使用 MIT
