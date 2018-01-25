package com.butchjgo.linkservice.repository;

import com.butchjgo.linkservice.common.domain.AccountInfo;
import com.butchjgo.linkservice.common.entity.BadURL;
import com.butchjgo.linkservice.common.entity.SupportedPattern;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@SpringBootApplication
@EntityScan(basePackageClasses = {AccountInfo.class, SupportedPattern.class, BadURL.class})
public class LinkserviceRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkserviceRepositoryApplication.class, args);
    }

    @Bean(name = "/account-repository")
    RemoteExporter accountRemoteRepository(@Qualifier("accountRepository") AccountRepository repository) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setServiceInterface(AccountRepository.class);
        exporter.setService(repository);
        return exporter;
    }

    @Bean(name = "/bad-url-repository")
    RemoteExporter badUrlRemoteRepository(@Qualifier("badURLRepository") BadURLRepository repository) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(repository);
        exporter.setServiceInterface(BadURLRepository.class);
        return exporter;
    }

    @Bean(name = "/supported-pattern-repository")
    RemoteExporter supportedPatternRemoteRepository(@Qualifier("supportedPatternRepository") SupportedPatternRepository repository) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setServiceInterface(SupportedPatternRepository.class);
        exporter.setService(repository);
        return exporter;
    }

}
