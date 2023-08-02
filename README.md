# nacos-listener-test
spring 实现 nacos 配置的监听,核心内容在 `config/AppConfig.java` 中

### 步骤
1. 实现 Nacos 提供的回调接口 `Listener`
2. 通过 `@NacosInjected` 注解注入 `ConfigService`
3. 实现 Spring 提供的扩展接口 `InitializingBean`，在 `afterPropertiesSet()` 方法中添加监听器 `configService.addListener(DATA_ID, GROUP, this);`
4. 最后当 Nacos 上的配置发生变化时，就会触发该方法 `receiveConfigInfo(String configInfo)` 进行获取最新的配置内容
