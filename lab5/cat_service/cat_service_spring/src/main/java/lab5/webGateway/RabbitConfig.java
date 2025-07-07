package lab5.webGateway;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue kittyGetByIdQueue() {
        return new Queue("kitty.getById");
    }

    @Bean
    public Queue ownerGetByIdQueue() {
        return new Queue("owner.getById");
    }

    @Bean
    public Queue gatewayResponseQueue() {
        return new Queue("gateway.responses");
    }

    @Bean
    public DirectExchange ownerExchange() {
        return new DirectExchange("owner.exchange");
    }

    @Bean
    public DirectExchange kittyExchange() {
        return new DirectExchange("kitty.exchange");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
