package site.amcu.amcuweb.social.github.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.social.connect.ConnectionFactory;
import site.amcu.amcuweb.properties.GitHubProperties;
import site.amcu.amcuweb.properties.SecurityProperties;
import site.amcu.amcuweb.social.github.connection.GitHubConnectionFactory;

/**
 * @Description:    github第三方服务配置类
 *                  该配置类不生效,就无法使用该第三方登录服务
 * @Author: Ben-Zheng
 * @Date: 2018/11/07 8:55
 */
@Configuration
@ConditionalOnProperty(prefix = "sys.properties.social.github", name = "app-id")
@Order(2)
public class GitHubAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        GitHubProperties gitHubConfig = securityProperties.getSocial().getGithub();
        return new GitHubConnectionFactory(gitHubConfig.getProviderId(), gitHubConfig.getAppId(), gitHubConfig.getAppSecret());
    }
}
