package lab5.kittyMicroservice.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange kittyExchange() {
        return new TopicExchange("kitty.exchange");
    }

    @Bean
    public Queue getByIdQueue() {
        return new Queue("kitty.getById");
    }

    @Bean
    public Binding bindingGetById(Queue getByIdQueue, TopicExchange kittyExchange) {
        return BindingBuilder.bind(getByIdQueue).to(kittyExchange).with("kitty.routingKey.get");
    }

    @Bean
    public Queue makeFriendsQueue() {
        return new Queue("kitty.makeFriends");
    }

    @Bean
    public Binding bindingMakeFriends(Queue makeFriendsQueue, TopicExchange kittyExchange) {
        return BindingBuilder.bind(makeFriendsQueue).to(kittyExchange).with("kitty.routingKey.friends");
    }

    @Bean
    public Queue unfriendKittiesQueue() {
        return new Queue("kitty.unfriendKitties");
    }

    @Bean
    public Binding bindingUnfriendKitties(Queue unfriendKittiesQueue, TopicExchange kittyExchange) {
        return BindingBuilder.bind(unfriendKittiesQueue).to(kittyExchange).with("kitty.routingKey.unfriends");
    }

    @Bean
    public Queue deleteKittyQueue() {
        return new Queue("kitty.delete");
    }

    @Bean
    public Binding bindingDeleteKitty(Queue deleteKittyQueue, TopicExchange kittyExchange) {
        return BindingBuilder.bind(deleteKittyQueue).to(kittyExchange).with("kitty.routingKey.delete");
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
