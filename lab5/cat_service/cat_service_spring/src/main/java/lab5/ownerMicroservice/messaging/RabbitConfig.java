package lab5.ownerMicroservice.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
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
    public DirectExchange ownerExchange() {
        return new DirectExchange("owner.exchange");
    }

    @Bean
    public Queue saveOwnerQueue() {
        return new Queue("owner.save");
    }

    @Bean
    public Queue getOwnerQueue() {
        return new Queue("owner.getById");
    }

    @Bean
    public Queue updateOwnerQueue() {
        return new Queue("owner.update");
    }

    @Bean
    public Queue deleteOwnerQueue() {
        return new Queue("owner.delete");
    }

    @Bean
    public Queue addKittyToOwnerQueue() {
        return new Queue("owner.addKitty");
    }

    @Bean
    public Queue gatewayResponseQueue() {
        return new Queue("gateway.responses");
    }

    @Bean
    public Binding bindSave() {
        return BindingBuilder.bind(saveOwnerQueue()).to(ownerExchange()).with("owner.routingKey.save");
    }

    @Bean
    public Binding bindGetById() {
        return BindingBuilder.bind(getOwnerQueue()).to(ownerExchange()).with("owner.routingKey.get");
    }

    @Bean
    public Binding bindUpdate() {
        return BindingBuilder.bind(updateOwnerQueue()).to(ownerExchange()).with("owner.routingKey.update");
    }

    @Bean
    public Binding bindDelete() {
        return BindingBuilder.bind(deleteOwnerQueue()).to(ownerExchange()).with("owner.routingKey.delete");
    }

    @Bean
    public Binding bindAddKitty() {
        return BindingBuilder.bind(addKittyToOwnerQueue()).to(ownerExchange()).with("owner.routingKey.addKitty");
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