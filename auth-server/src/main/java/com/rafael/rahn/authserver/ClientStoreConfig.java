package com.rafael.rahn.authserver;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

@Configuration
public class ClientStoreConfig {

    // classe de configuracao de quem tem permissao de acesso aos recursos protegidos
    // e como essa identificacao ocorre

    @Bean
    RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString()) // ID salvo, interno
                .clientId("client-server") // identificacao do client, externo
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC) // forma de autenticacao
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // fluxo de autenticacao, pode ter mais de um
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                // garante a seguranca, pois sera rederecionado nessa URI do client
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/client-server-oidc") // path padrao spring ja vai saber usar esse caminho no client, client-server id do client
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true).build()) // tela de permissao de scopos autorizando o uso desse dados
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);

    }
}
