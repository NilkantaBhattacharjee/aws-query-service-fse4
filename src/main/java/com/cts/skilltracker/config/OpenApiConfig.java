package com.cts.skilltracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cts.skilltracker.utils.QuerySideConstants;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI skillTrackerOpenAPI(OpenApiProperties properties) {
		return new OpenAPI().info(getInfo(properties));

	}

	private Info getInfo(OpenApiProperties properties) {
		return new Info().title(properties.getProjectTitle()).description(properties.getProjectDescription())
				.version(properties.getProjectVersion()).termsOfService(QuerySideConstants.OPEN_API_TC_URL)
				.license(getLicense());
	}

	private License getLicense() {
		return new License().name(QuerySideConstants.OPEN_API_LICENSE).url(QuerySideConstants.OPEN_API_TC_URL);
	}

}
